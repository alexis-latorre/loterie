package com.loterie.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import com.loterie.dao.BanqueDao;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.PortefeuilleDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Banque;
import com.loterie.entities.Grille;
import com.loterie.entities.Jeu;
import com.loterie.entities.Jour;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Portefeuille;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

public class GrilleJeuForm {
	private LienGUDao lguDao;
	private JourDao jourDao;
	private BanqueDao banqueDao;
	private PortefeuilleDao portefeuilleDao;
	private UtilisateurDao utilisateurDao;
	private HttpServletRequest req;
	private Map<String, String> erreurs;
	private String periode;
	private List<LienGrilleUtilisateur> lgus;
	private Grille grille;
	private Utilisateur utilisateur;
	private HttpSession session;
	
	public GrilleJeuForm(LienGUDao lguDao, JourDao jourDao, BanqueDao banqueDao, PortefeuilleDao portefeuilleDao, 
			UtilisateurDao utilisateurDao, HttpServletRequest req) {
		this.banqueDao = banqueDao;
		this.lguDao = lguDao;
		this.jourDao = jourDao;
		this.portefeuilleDao = portefeuilleDao;
		this.utilisateurDao = utilisateurDao;
		this.req = req;
		erreurs = new HashMap<>();
		periode = req.getParameter("periode");
		session = this.req.getSession();
		grille = (Grille) session.getAttribute("grille");
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		lgus = this.lguDao.trouverParGrille(grille);
		
		if (periode.length() != 2) {
			erreurs.put("format", "La période ne respecte pas le format demandé");
		}
		
		if (grille == null) {
			erreurs.put("grille", "La grille n'est pas valide");
		}
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void jouer() {
		Jeu jeu = grille.getJeu();
		Banque banque = grille.getBanque();
		int heureValidation = Integer.parseInt(jeu.getHeureValidation().split(":")[0]);
		int minuteValidation = Integer.parseInt(jeu.getHeureValidation().split(":")[1]);
		String[] jours = jeu.getJourDeTirage();
		DateTime ajd = new DateTime()
				.withHourOfDay(heureValidation)
				.withMinuteOfHour(minuteValidation)
				.withSecondOfMinute(0)
				.withMillisOfSecond(0);
		DateTime dateValidation = ajd;
		int prochainJourDeJeu = Integer.parseInt(jours[0]);		
		int idJour = 0;
		
		for (int i = 0; i < jours.length; i++) {
			if (Integer.parseInt(jours[i]) >= ajd.getDayOfWeek()) {
				prochainJourDeJeu = Integer.parseInt(jours[i]);
				idJour = i;
				break;
			}
		}
		int nbPeriode = Integer.parseInt(periode.substring(0, 1));
		String typePeriode = periode.substring(1);
		DateTime maintenant = new DateTime();

		if (maintenant.isAfter(dateValidation)) {
			dateValidation = dateValidation.plusDays(1);
		} else {
			dateValidation = maintenant;
		}
		
		if (typePeriode.equals("s")) {
			nbPeriode *= jours.length;
		}
		List<Jour> joursAjouer = new ArrayList<>();		
		List<Utilisateur> joueurs = new ArrayList<>();
		Double prixTirage = jeu.getPrixTirage();
		Double prix = 0D;
		List<String> datesTotalesJouees = new ArrayList<>();
		
		for (int i = 0; i < nbPeriode; i++) {
			String prochainJour = Tools.getProchainJour(prochainJourDeJeu, dateValidation);
			dateValidation = DateTime.parse(prochainJour.substring(0, 10));
			idJour++;
			
			if (idJour >= jours.length) {
				idJour = 0;
			}
			prochainJourDeJeu = Integer.parseInt(jours[idJour]);
			
			for (LienGrilleUtilisateur lgu : lgus) {
				List<Jour> joursLGU = jourDao.trouverParLGU(lgu);
				Utilisateur joueur = lgu.getUtilisateur();
				
				if (!joueurs.contains(joueur)) {
					joueurs.add(joueur);
				}
				List<String> datesJouees = new ArrayList<>();
				
				if (joursLGU != null && !joursLGU.isEmpty()) {
					for (Jour j : joursLGU) {
						datesJouees.add(j.getDateJour());
					}
				}
				boolean jourDejaJoue = false;
				
				if (joursLGU != null) {
					jourDejaJoue = datesJouees.contains(prochainJour);					
				}
				
				if (!jourDejaJoue) {
					Jour jour = new Jour();
					jour.setDateJour(prochainJour);
					jour.setLgu(lgu);
					jour.setPaye(true);
					joursAjouer.add(jour);
					
					if (!datesTotalesJouees.contains(prochainJour)) {
						datesTotalesJouees.add(prochainJour);
					}
				}
			}
		}
		prix = prixTirage * datesTotalesJouees.size();
		Double prixParJoueur = prix / joueurs.size();
		
		for (Utilisateur joueur : joueurs) {			
			Portefeuille portefeuille = joueur.getPortefeuille();

			if (portefeuille == null) {
				PortefeuilleCreationForm cpf = new PortefeuilleCreationForm(portefeuilleDao, utilisateurDao, joueur); 
				portefeuille = cpf.getPortefeuille();
			}
			portefeuille.retirerFonds(prixParJoueur);
			portefeuilleDao.maj(portefeuille);
			banque.ajouterFonds(prixParJoueur);
			banqueDao.maj(banque);
		}
		
		if (prix <= banque.getFonds()) {
			banque.retirerFonds(prix);
			banqueDao.maj(banque);
			
			for (Jour jour : joursAjouer) {
				jourDao.creer(jour);
			}
		}
		Utilisateur joueur = utilisateurDao.trouverParPseudo(utilisateur.getPseudo());
		session.setAttribute("utilisateur", joueur);
	}
	
	public Long getGrilleId() {
		return grille.getId();
	}
}
