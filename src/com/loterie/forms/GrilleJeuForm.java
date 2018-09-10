package com.loterie.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
import com.loterie.managers.EMFManager;
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
		erreurs = new HashMap<String, String>();
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
		// Initialise une nouvelle transaction
		EntityManagerFactory emf = EMFManager.getInstance();
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			
			// Initialise les différentes variables calculant le coût global du pari
			Double prixGrille = 0D;
			List<Utilisateur> joueurs = new ArrayList<>();
			List<Jour> joursAjouer = new ArrayList<>();		
			List<String> joursJouesGrille = new ArrayList<>();
			
			// Récupère le type de jeu à jouer
			Jeu jeu = grille.getJeu();
			// Récupère les jours de tirage du jeu
			String[] jours = jeu.getJoursDeTirage();
			// Récupère le prix d'un tirage du jeu
			Double prixTirage = jeu.getPrixTirage();
			// Récupère la banque liée à la grille
			Banque banque = grille.getBanque();
			// Récupère l'heure et la minute limites de validation de la grille
			String[] splitValidation = jeu.getHeureValidation().split(":");
			int heureValidation = Integer.parseInt(splitValidation[0]);
			int minuteValidation = Integer.parseInt(splitValidation[1]);
			// Initialise la date de prise en compte du pari
			DateTime maintenant = new DateTime();
			// Initialise la première date à tester
			DateTime dateValidation = maintenant
					.withHourOfDay(heureValidation)
					.withMinuteOfHour(minuteValidation)
					.withSecondOfMinute(0)
					.withMillisOfSecond(0);
			// Récupère le numéro du premier jour joué de la semaine
			int prochainJourDeJeu = Integer.parseInt(jours[0]);		
			int idJour = 0;
			
			// Récupère la période de jeu définie dans les paramètres passés
			int nbPeriode = Integer.parseInt(periode.substring(0, 1));
			// Le premier caractère définie si on doit jouer des jours ou des semaines.
			String typePeriode = periode.substring(1);
	
			// Si la date limite de validation est dépassée, alors la période de validation est considérée à J+1
			if (maintenant.isAfter(dateValidation)) {
				dateValidation = dateValidation.plusDays(1);
			} else {
				dateValidation = maintenant;
			}
			
			// Cherche le prochain jour de tirage à partir de la date actuelle
			for (int i = 0; i < jours.length; i++) {
				if (Integer.parseInt(jours[i]) >= dateValidation.getDayOfWeek()) {
					prochainJourDeJeu = Integer.parseInt(jours[i]);
					idJour = i;
					break;
				}
			}
			
			/* Si on joue par semaine, alors il faut multiplier 
			 * le nombre de semaines jouées par le nombre de tirages par semaine */
			if (typePeriode.equals("s")) {
				nbPeriode *= jours.length;
			}
			
			// Pour chaque période jouée, on commence par récupérer la prochaine date à jouer (yyyy-MM-dd)
			for (int i = 0; i < nbPeriode; i++) {
				String prochainJour = Tools.getProchainJour(prochainJourDeJeu, dateValidation);
				// Change le point de départ de la recherche du prochain jour de jeu
				dateValidation = DateTime.parse(prochainJour);
				idJour++;
				
				// Si on arrive à la fin du tableau de jours de jeu, retourne au début pour obtenir le prochain jour de jeu
				if (idJour >= jours.length) {
					idJour = 0;
				}
				prochainJourDeJeu = Integer.parseInt(jours[idJour]);
				
				// Parcourt tous les liens grille <-> utilisateur liés à la grille jouée
				for (LienGrilleUtilisateur lgu : lgus) {
					Utilisateur joueur = lgu.getUtilisateur();
					
					// Stocke dans une table temporaire les joueurs uniques de la grille
					if (!joueurs.contains(joueur)) {
						joueurs.add(joueur);
					}
					// Initialise la liste temporaire des jours déjà joués par l'utilisateur sur cette grille
					List<String> datesJouees = new ArrayList<>();
					// Sauf preuve du contraire, le jour de jeu n'est pas considéré comme joué
					boolean jourDejaJoue = false;
	
					// Récupère d'éventuels jours déjà joués pour cet utilisateur sur cette grille
					List<Jour> joursLGU = jourDao.trouverParLGU(lgu);
					// Les jours joués pour cet utilisateur et cette grille sont stockés dans une liste temporaire
					if (joursLGU != null && !joursLGU.isEmpty()) {
						for (Jour j : joursLGU) {
							datesJouees.add(j.getDateJour());
						}
						jourDejaJoue = datesJouees.contains(prochainJour);
					}
					
					// Si le jour n'a pas déjà été joué, il est ajouté à la liste temporaire
					if (!jourDejaJoue) {
						// Initialise le jour à jouer
						Jour jour = new Jour();
						jour.setDateJour(prochainJour);
						jour.setLgu(lgu);
						jour.setPaye(true);
						joursAjouer.add(jour);
						
						// Stocke dans la liste des jours joués pour la grille la date du jour créé
						if (!joursJouesGrille.contains(prochainJour)) {
							joursJouesGrille.add(prochainJour);
						}
					}
				}
			}
			// Le prix de la grille est fonction du nombre de jours joués multiplié par le prix d'un tirage
			prixGrille = prixTirage * joursJouesGrille.size();
			// Le prix par joueur est égal au prix de la grille divisé par le nombre de joueurs
			Double prixParJoueur = prixGrille / joueurs.size();
			
			for (Utilisateur joueur : joueurs) {
				// Récupère le portefeuille du joueur pour le débiter
				Portefeuille portefeuille = joueur.getPortefeuille();
	
				// TODO: Initialiser la création du portefeuille à la création de compte
				if (portefeuille == null) {
					PortefeuilleCreationForm cpf = new PortefeuilleCreationForm(portefeuilleDao, utilisateurDao, joueur); 
					portefeuille = cpf.getPortefeuille();
				}
				portefeuille.retirerFonds(prixParJoueur);
				em.merge(portefeuille);
				//em.refresh(portefeuille);
				//portefeuilleDao.maj(portefeuille);
				banque.ajouterFonds(prixParJoueur);
				banqueDao.maj(banque);
			}
			
			if (prixGrille <= banque.getFonds()) {
				banque.retirerFonds(prixGrille);
				banqueDao.maj(banque);
				
				for (Jour jour : joursAjouer) {
					jourDao.creer(jour);
				}
			}
			Utilisateur joueur = utilisateurDao.trouverParPseudo(utilisateur.getPseudo());
			session.setAttribute("utilisateur", joueur);
			em.flush();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			
			//emf.close();
		}
	}
	
	public Long getGrilleId() {
		return grille.getId();
	}
}
