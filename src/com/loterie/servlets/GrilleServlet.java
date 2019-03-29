package com.loterie.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.config.Constants;
import com.loterie.config.Messages;
import com.loterie.dao.BanqueDao;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.JeuDao;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.LogDao;
import com.loterie.dao.PortefeuilleDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Utilisateur;
import com.loterie.forms.GrilleActivationForm;
import com.loterie.forms.GrilleAffichageForm;
import com.loterie.forms.GrilleAlimentationForm;
import com.loterie.forms.GrilleCreationForm;
import com.loterie.forms.GrilleJeuForm;
//import com.loterie.forms.GrilleJointureForm;
import com.loterie.tools.Logger;
import com.loterie.tools.Tools;

@WebServlet(urlPatterns = {
		//Constants.URL_MEMBRE_PROFIL,
		Constants.URL_MEMBRE_AFFICHER_GRILLES,
		//Constants.URL_MEMBRE_CREER_GRILLE, 
		Constants.URL_MEMBRE_AFFICHER_GRILLE,
		Constants.URL_MEMBRE_MODIFIER_GRILLE, 
		//Constants.URL_MEMBRE_SUPPRIMER_GRILLE,
		//Constants.URL_MEMBRE_REJOINDRE_GRILLE,
		//Constants.URL_MEMBRE_QUITTER_GRILLE,
		//Constants.URL_MEMBRE_BANQUE_AJOUT,
		Constants.URL_MEMBRE_JOUER_GRILLE,
		//Constants.URL_MEMBRE_DESACTIVER_GRILLE,
		//Constants.URL_MEMBRE_ACTIVER_GRILLE
	})
public class GrilleServlet extends HttpServlet {
	private static final long serialVersionUID = 6L;
	@EJB
	private GrilleDao grilleDao;
	@EJB
	private LienGUDao lienGUDao;
	@EJB
	private JeuDao jeuDao;
	@EJB
	private BanqueDao banqueDao;
	@EJB
	private PortefeuilleDao portefeuilleDao;
	@EJB
	private JourDao jourDao;
	@EJB
	private UtilisateurDao utilisateurDao;
	@EJB
	private LogDao logDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		String cible = Constants.URN_MEMBRE_403;
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		String dateJour = Tools.getDateTiret();
		req.setAttribute("dateJour", dateJour);

		if (utilisateur != null) {
			// Vérifie que l'utilisateur est accréditer pour les opérations qui suivent
			if (utilisateur.estMembre()) {
				if (uri.equals(Constants.URL_MEMBRE_AFFICHER_GRILLE)) {
					// Affiche le détail d'une grille
					GrilleAffichageForm gaf = new GrilleAffichageForm(grilleDao, jourDao, utilisateurDao, req);
					req = gaf.afficherGrille();
					cible = Constants.URN_MEMBRE_AFFICHER_GRILLE;
					
				} else if (uri.equals(Constants.URL_MEMBRE_AFFICHER_GRILLES)) {
					// Affiche les grilles crées et/ou jouées par l'utilisateur
					GrilleAffichageForm gaf = new GrilleAffichageForm(grilleDao, utilisateur, req);
					req = gaf.afficherGrillesUtilisateur();
					cible = Constants.URN_MEMBRE_AFFICHER_GRILLES;
					
				} else if (uri.equals(Constants.URL_MEMBRE_CREER_GRILLE)) {
					// Affiche le formulaire pour créer une nouvelle grille
					GrilleCreationForm gcf = new GrilleCreationForm(utilisateurDao, req);
					req = gcf.getFormulaire();		
					cible = Constants.URN_MEMBRE_CREER_GRILLE;
					
				} else if (uri.equals(Constants.URL_MEMBRE_SUPPRIMER_GRILLE)) {
					// Supprime la grille indiquée
					// TODO: vérifier si l'utilisateur a les droits sur cette grille
					GrilleCreationForm gcf = new GrilleCreationForm(grilleDao, req);
					gcf.supprimer();
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
					
				} else if (uri.equals(Constants.URL_MEMBRE_MODIFIER_GRILLE)) {
					// Affiche le formulaire pour modifier la grille
					GrilleCreationForm gcf = new GrilleCreationForm(grilleDao, jeuDao, banqueDao,
							lienGUDao, utilisateurDao, req);
					req = gcf.getFormulaire(req.getParameter("id"));
					cible = Constants.URN_MEMBRE_MODIFIER_GRILLE;
					
				} else if (uri.equals(Constants.URL_MEMBRE_REJOINDRE_GRILLE)) {
					// Permet à l'utilisateur de rejoindre une grille
					//GrilleJointureForm gjf = new GrilleJointureForm(grilleDao, lienGUDao, req);
					//TODO: réactiver
					//gjf.rejoindre();
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
					
				} else if (uri.equals(Constants.URL_MEMBRE_QUITTER_GRILLE)) {
					// Permet à l'utilisateur de quitter une grille
					//GrilleJointureForm gjf = new GrilleJointureForm(grilleDao, lienGUDao, req);
					//TODO: réactiver
					//gjf.quitter();
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
					
				} else if (uri.equals(Constants.URL_MEMBRE_ACTIVER_GRILLE) || 
						uri.equals(Constants.URL_MEMBRE_DESACTIVER_GRILLE)) {
					// Permet à l'utilisateur d'activer ou de désactiver une grille
					GrilleActivationForm gaf = new GrilleActivationForm(grilleDao, utilisateur, req);
					
					if (uri.equals(Constants.URL_MEMBRE_ACTIVER_GRILLE)) {
						gaf.activer();
					} else if (uri.equals(Constants.URL_MEMBRE_DESACTIVER_GRILLE)) {
						gaf.desactiver();
					}
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
					
				} else {
					cible = Constants.URN_MEMBRE_ACCUEIL;
				}
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cible = Constants.URN_MEMBRE_AFFICHER_GRILLES;
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		boolean loggedIn = false;
		String dateJour = Tools.getDateTiret();
		req.setAttribute("dateJour", dateJour);
		
		if (session.getAttribute("loggedIn") != null) {
			loggedIn = (boolean) session.getAttribute("loggedIn");
		}
		
		if (uri.equals(Constants.URL_MEMBRE_CREER_GRILLE)) {
			// Transmet les données nécessaires à la création d'une grille
			GrilleCreationForm gcf = new GrilleCreationForm(grilleDao, jeuDao, banqueDao, lienGUDao, utilisateurDao, 
					req);

			// Si aucune erreur n'est détectée, crée une nouvlle grille en BDD
			if (gcf.getErreurs().isEmpty()) {
				Map<String, Object> retour = gcf.creer();
				Logger.log(logDao, Messages.LOG_CREER_GRILLE, 
						Constants.LOG_INFO, Constants.LOG_GRILLE, utilisateur, retour.get("grille"));
				
				for (Utilisateur joueur : (List<Utilisateur>) retour.get("joueurs")) {
					Logger.log(logDao, Messages.LOG_AJOUTER_JOUEUR_GRILLE, 
							Constants.LOG_INFO, Constants.LOG_GRILLE, utilisateur, joueur, 
							(Grille)retour.get("grille"));
				}
				resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
				return;
			} else {
				cible = Constants.URN_MEMBRE_CREER_GRILLE;
			}
			
		} else if (uri.equals(Constants.URL_MEMBRE_MODIFIER_GRILLE)) {
			// Transmet les données nécessaires à la modification d'une grille
			GrilleCreationForm gcf = new GrilleCreationForm(grilleDao, jeuDao, banqueDao, lienGUDao, utilisateurDao, 
					req);
			Map<String, Object> retour = gcf.modifier();
			
			for (Utilisateur joueurRetire : (List<Utilisateur>)retour.get("joueursRetires")) {
				Logger.log(logDao, Messages.LOG_RETIRER_JOUEUR_GRILLE, 
						Constants.LOG_INFO, Constants.LOG_GRILLE, utilisateur, joueurRetire, 
						(Grille)retour.get("grille"));
			}
			
			for (Utilisateur joueurAjoute : (List<Utilisateur>)retour.get("joueursAjoutes")) {
				Logger.log(logDao, Messages.LOG_AJOUTER_JOUEUR_GRILLE, 
						Constants.LOG_INFO, Constants.LOG_GRILLE, utilisateur, joueurAjoute, 
						(Grille)retour.get("grille"));
			}
			Logger.log(logDao, Messages.LOG_MODIFIER_GRILLE, 
					Constants.LOG_INFO, Constants.LOG_GRILLE, utilisateur, (Grille)retour.get("grille"));
			resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLE +
					"?id=" + ((Grille)retour.get("grille")).getId());
			return;
			
		} else if (uri.equals(Constants.URL_MEMBRE_BANQUE_AJOUT)) {
			// Transmet les données nécessaires à la modification des fonds d'une grille
			
			// Seuls les modérateurs/Administrateurs peuvent créditer une grille
			if (loggedIn && utilisateur != null && utilisateur.estModerateur()) {
				cible = Constants.URN_MEMBRE_AFFICHER_GRILLE;				
				GrilleAlimentationForm gaf = new GrilleAlimentationForm(portefeuilleDao, banqueDao, req);
				gaf.modifier();
				
				if (gaf.getErreurs().isEmpty()) {
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLE);
					return;
				}
				
			}
		} else if (uri.equals(Constants.URL_MEMBRE_JOUER_GRILLE)) {
			// Joue la grille pour la période donnée
			cible = Constants.URN_MEMBRE_AFFICHER_GRILLE;
			
			GrilleJeuForm jgf = new GrilleJeuForm(lienGUDao, jourDao, banqueDao, portefeuilleDao, utilisateurDao, req);
			Map<String, String> erreurs = jgf.getErreurs();

			//TODO: bouger cette portion
			// Si aucun problème n'est détecté, la grille est jouée
			if (erreurs.isEmpty()) {
				Map<String, Object> retour = jgf.jouer();
				Object messageEchec = retour.get("messageEchec");
				
				if (null != messageEchec && !((String) messageEchec).isEmpty()) {
					req.setAttribute("jour", retour.get("jour"));
					req.setAttribute("messageEchec", messageEchec);
				} else {
					if (jgf.getJoueur() != null) {
						Logger.log(logDao, "%u a joué la grille %g pour " + retour.get("periode") + ".", 
								Constants.LOG_INFO, Constants.LOG_GRILLE, (Utilisateur) retour.get("joueur"), 
								retour.get("grille"));
					} else {
						Logger.log(logDao, "%u a joué la grille %g pour " + retour.get("periode") + ".", 
								Constants.LOG_INFO, Constants.LOG_GRILLE, utilisateur, retour.get("grille"));
					}
					req.setAttribute("messageSucces", "Grille jouée avec succès");
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLE + 
							"?id=" + jgf.getGrilleId());
					return;
				}
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

}
