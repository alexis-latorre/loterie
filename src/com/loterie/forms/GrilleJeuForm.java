package com.loterie.forms;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import com.loterie.dao.BanqueDao;
import com.loterie.dao.GrilleDao;
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
	private GrilleDao grilleDao;
	private HttpServletRequest req;
	private Map<String, String> erreurs;
	private String periode;
	private String date;
	private List<LienGrilleUtilisateur> lgus;
	private Grille grille;
	private Utilisateur utilisateur;
	private Utilisateur joueur;
	private HttpSession session;
	private int jourJeu;
	private int moisJeu;
	private int anneeJeu;
	
	public GrilleJeuForm(LienGUDao lguDao, JourDao jourDao, BanqueDao banqueDao, PortefeuilleDao portefeuilleDao, 
			UtilisateurDao utilisateurDao, GrilleDao grilleDao, HttpServletRequest req) {
		this.banqueDao = banqueDao;
		this.lguDao = lguDao;
		this.jourDao = jourDao;
		this.portefeuilleDao = portefeuilleDao;
		this.utilisateurDao = utilisateurDao;
		this.grilleDao = grilleDao;
		this.req = req;
		erreurs = new HashMap<String, String>();
		periode = req.getParameter("periode");
		date = req.getParameter("date");
		String myMillion = req.getParameter("mymillion");
		session = this.req.getSession();
		grille = (Grille) session.getAttribute("grille");
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		setJoueur(null);
		
		try {
			setJoueur((Utilisateur) this.utilisateurDao.trouverParId(
					Long.valueOf(req.getParameter("joueur"))));
		} catch (Exception e) {
		}
		
		lgus = this.lguDao.trouverParGrille(grille);
		
		if (periode.length() != 2) {
			erreurs.put("format", "La période ne respecte pas le format demandé");
		}
		
		if (grille == null) {
			erreurs.put("grille", "La grille n'est pas valide");
		} else {
			grille.setMyMillion(myMillion);
		}
		
		if (date == null || date.isEmpty()) {
			date = Tools.getDateTiret();
		} else {
			Pattern p = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
			Matcher m = p.matcher(date);
			
			if (m.matches()) {
				String[] dateArgs = date.split("-");
				
				jourJeu = Integer.valueOf(dateArgs[2]);
				moisJeu = Integer.valueOf(dateArgs[1]);
				anneeJeu = Integer.valueOf(dateArgs[0]);
				
				if (jourJeu > 31 || moisJeu > 12 ) {
					erreurs.put("date", "La date ne respecte pas le format demandé");
				}
			} else {
				erreurs.put("date", "La date ne respecte pas le format demandé");
			}
		}
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Map<String, Object> jouer() {
		Map<String, Object> retour = new HashMap<String, Object>();
		retour.put("grille", grille);
		String retourPeriode = "";
		Jeu jeu = grille.getJeu();
		Banque banque = grille.getBanque();
		int heureValidation = Integer.parseInt(jeu.getHeureValidation().split(":")[0]);
		int minuteValidation = Integer.parseInt(jeu.getHeureValidation().split(":")[1]);
		String[] jours = jeu.getJourDeTirage();
		DateTime ajd = new DateTime()
				.withYear(anneeJeu)
				.withMonthOfYear(moisJeu)
				.withDayOfMonth(jourJeu)
				.withHourOfDay(heureValidation)
				.withMinuteOfHour(minuteValidation)
				.withSecondOfMinute(0)
				.withMillisOfSecond(0);
		DateTime dateValidation = ajd;
		int prochainJourDeJeu = Integer.parseInt(jours[0]);		
		int idJour = 0;
		int nbPeriode = Integer.parseInt(periode.substring(0, 1));
		String typePeriode = periode.substring(1);
		String idGroupe = UUID.randomUUID().toString();
		
		if (dateValidation.isAfter(DateTime.now().plusDays(1))) {
			retour.put("messageEchec", "Cette date de jeu est trop loin dans le futur pour être jouée");
			return retour;
		}	

		if (typePeriode.equals("j")) {
			retourPeriode = nbPeriode + " jour";
			
			for (int i = 0; i < jours.length; i++) {
				if (Integer.parseInt(jours[i]) >= ajd.getDayOfWeek()) {
					prochainJourDeJeu = Integer.parseInt(jours[i]);
					idJour = i;
					break;
				}
			}
		} else {
			retourPeriode = nbPeriode + " semaine";
		}
		if (nbPeriode > 1) {
			retourPeriode += "s";
		}

		retour.put("joueur", joueur);
		retour.put("periode", retourPeriode);
		
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
		int nbJoueurs = joueurs.size();
		Double prixParJoueur = prix / nbJoueurs;
		List<LienGrilleUtilisateur> lgus = new ArrayList<LienGrilleUtilisateur>();
		List<Portefeuille> portefeuilles = new ArrayList<Portefeuille>();
		boolean commit = true;
		
		for (Utilisateur joueur : joueurs) {
			Double prixApayer = prixParJoueur;
			Double fondsLGU = 0D;
			Double fondsPortefeuille = 0D;
			LienGrilleUtilisateur lgu = lguDao.trouverParGrilleEtUtilisateur(grille, joueur);
			Portefeuille portefeuille = joueur.getPortefeuille();
			
			if (portefeuille == null) {
				PortefeuilleCreationForm cpf = new PortefeuilleCreationForm(portefeuilleDao, utilisateurDao, joueur); 
				portefeuille = cpf.getPortefeuille();
				fondsPortefeuille = portefeuille.getFonds();
			}
			
			if (null != lgu) {
				fondsLGU = lgu.getFonds();
			}
			
			// On regarde d'abord si le joueur a les fonds sur la grille
			if (fondsLGU >= prixApayer) {
				lgu.retirerFonds(prixParJoueur);
				lgus.add(lgu);
			// Ou s'il faut compléter avec son portefeuille
			} else if ((fondsLGU + fondsPortefeuille) >= prixApayer) {
				if (fondsLGU < prixApayer) {
					prixApayer -= fondsLGU;
					lgu.retirerFonds(fondsLGU);
					lgus.add(lgu);

					portefeuille.retirerFonds(prixApayer);
					portefeuilles.add(portefeuille);
					banque.ajouterFonds(prixParJoueur);
				} else {
					lgu.retirerFonds(prixApayer);
					prixApayer = prixParJoueur - lgu.getFonds();
				}
			// Ou si on pioche tout directement dans son portefeuille
			} else {
				/* TODO: paramétrer s'il est possible de forcer le jeu malgré un solde négatif
				// Si le joueur n'a pas du tout les fonds pour jouer, on abandonne l'opération complète
				if (portefeuille.getFonds() < prixParJoueur) {
					commit = false;
					retour.put("messageEchec", joueur.getPseudo() + " n'a pas assez de fonds pour jouer.");
					break;
				} else {*/
					portefeuille.retirerFonds(prixParJoueur);
					portefeuilles.add(portefeuille);
					banque.ajouterFonds(prixParJoueur);
				//} FIN TODO
			}
		}
		
		if (prix <= banque.getFonds()) {
			banque.retirerFonds(prix);
			
			for (Jour jour : joursAjouer) {
				jour.setGains(0D);
				jour.setNbJoueurs((long) nbJoueurs);
				jour.setGainsRedistribues(0D);
				jour.setGroupe(idGroupe);
				try {
					jourDao.creer(jour);
				} catch (Exception e) {
					Throwable cause = e.getCause();
					boolean derniereCause = false;
					 
					while (!derniereCause) {
						if (null != cause.getCause()) {
							cause = cause.getCause();
						} else {
							derniereCause = true;
						}
						
						if (cause.getClass().equals(SQLIntegrityConstraintViolationException.class)) {
							retour.put("messageEchec", "Grille déjà jouée pour cette période");
						}
					}
					commit = false;
				}
			}
		}
		
		if (commit) {
			for (LienGrilleUtilisateur lgu : lgus) {	
				lguDao.maj(lgu);
			}
			
			for (Portefeuille portefeuille : portefeuilles) {	
				portefeuilleDao.maj(portefeuille);
			}
			banqueDao.maj(banque);
			grilleDao.maj(grille);
			utilisateurDao.clearCache();
		}
		Utilisateur joueur = utilisateurDao.trouverParId(utilisateur.getId());
		joueur.setGrilles(utilisateur.getGrilles());
		retour.put("jour", jourDao.trouverDernierJourJoue(grille));
		session.setAttribute("utilisateur", joueur);
		return retour;
	}
	
	public Long getGrilleId() {
		return grille.getId();
	}

	public Utilisateur getJoueur() {
		return joueur;
	}

	public void setJoueur(Utilisateur joueur) {
		this.joueur = joueur;
	}
}
