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
import com.loterie.entities.Banque;
import com.loterie.entities.Grille;
import com.loterie.entities.Jeu;
import com.loterie.entities.Jour;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.tools.Tools;

public class JeuGrilleForm {
	private LienGUDao lguDao;
	private JourDao jourDao;
	private BanqueDao banqueDao;
	private Map<String, String> erreurs;
	private String periode;
	private List<LienGrilleUtilisateur> lgus;
	private Grille grille;
	
	public JeuGrilleForm(LienGUDao lguDao, JourDao jourDao, BanqueDao banqueDao, HttpServletRequest req) {
		this.banqueDao = banqueDao;
		this.lguDao = lguDao;
		this.jourDao = jourDao;
		erreurs = new HashMap<>();
		periode = req.getParameter("periode");
		HttpSession session = req.getSession();
		grille = (Grille) session.getAttribute("grille");
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
			}
		}
		int nbPeriode = Integer.parseInt(periode.substring(0, 1));
		String typePeriode = periode.substring(1);
		System.out.println(periode);
		System.out.println(periode.substring(0, 1));
		System.out.println(periode.substring(1));
		DateTime maintenant = new DateTime();
		
		if (maintenant.isAfter(dateValidation)) {
			dateValidation = dateValidation.plusDays(1);
		}
		
		if (typePeriode.equals("s")) {
			nbPeriode *= jours.length;
		}
		Double prixTirage = jeu.getPrixTirage();
		Double prix = 0D;
		List<Jour> joursAjouer = new ArrayList<>();
		
		for (int i = 0; i < nbPeriode; i++) {
			String prochainJour = Tools.getProchainJour(prochainJourDeJeu, dateValidation);
			dateValidation = DateTime.parse(prochainJour);
			idJour++;
			
			if (idJour >= jours.length) {
				idJour = 0;
			}
			prochainJourDeJeu = Integer.parseInt(jours[idJour]);
			
			for (LienGrilleUtilisateur lgu : lgus) {
				List<Jour> joursLGU = jourDao.trouverParLGU(lgu);
				List<String> datesJouees = new ArrayList<>();
				
				for (Jour j : joursLGU) {
					datesJouees.add(j.getDateJour());
				}
				boolean jourDejaJoue = false;
				
				if (joursLGU != null) {
					jourDejaJoue = datesJouees.contains(prochainJour);					
				}

				if (!jourDejaJoue) {
					Jour jour = new Jour();
					jour.setDateJour(prochainJour);
					jour.setLgu(lgu);
					jour.setPaye(false);
					joursAjouer.add(jour);
					prix += prixTirage;
				}
			}
		}
		
		if (prix <= grille.getBanque().getFonds()) {
			banque.retirerFonds(prix);
			banqueDao.maj(banque);
			
			for (Jour jour : joursAjouer) {
				jourDao.enregistrerJour(jour);
			}
		}
	}
	
	public Long getGrilleId() {
		return grille.getId();
	}
}
