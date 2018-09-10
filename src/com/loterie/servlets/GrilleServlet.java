package com.loterie.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.config.Constants;
import com.loterie.dao.BanqueDao;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.JeuDao;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.PortefeuilleDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Utilisateur;
import com.loterie.forms.GrilleAffichageForm;
import com.loterie.forms.GrilleAlimentationForm;
import com.loterie.forms.GrilleCreationForm;
import com.loterie.forms.GrilleJeuForm;
import com.loterie.forms.GrilleJointureForm;
import com.loterie.managers.DAOManager;

@WebServlet(urlPatterns = {
		Constants.URL_MEMBRE_PROFIL,
		Constants.URL_MEMBRE_AFFICHER_GRILLES,
		Constants.URL_MEMBRE_CREER_GRILLE, 
		Constants.URL_MEMBRE_AFFICHER_GRILLE,
		Constants.URL_MEMBRE_MODIFIER_GRILLE, 
		Constants.URL_MEMBRE_SUPPRIMER_GRILLE,
		Constants.URL_MEMBRE_REJOINDRE_GRILLE,
		Constants.URL_MEMBRE_QUITTER_GRILLE,
		Constants.URL_MEMBRE_BANQUE_AJOUT,
		Constants.URL_MEMBRE_JOUER_GRILLE
	})
public class GrilleServlet extends HttpServlet {
	private static final long serialVersionUID = 6L;
	private GrilleDao grilleDao = DAOManager.getGrille();
	private LienGUDao lienGUDao = DAOManager.getLgu();
	private JeuDao jeuDao = DAOManager.getJeu();
	private BanqueDao banqueDao = DAOManager.getBanque();
	private PortefeuilleDao portefeuilleDao = DAOManager.getPortefeuille();
	private JourDao jourDao = DAOManager.getJour();
	private UtilisateurDao utilisateurDao = DAOManager.getUtilisateur();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		String cible = Constants.URN_MEMBRE_403;
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		if (utilisateur != null) {
			// Vérifie que l'utilisateur est accréditer pour les opérations qui suivent
			if (utilisateur.estMembre()) {
				if (uri.equals(Constants.URL_MEMBRE_AFFICHER_GRILLE)) {
					// Affiche le détail d'une grille
					GrilleAffichageForm gaf = new GrilleAffichageForm(grilleDao, jourDao, req);
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
					GrilleCreationForm gcf = new GrilleCreationForm(grilleDao, utilisateurDao, req);
					req = gcf.getFormulaire(req.getParameter("id"));
					cible = Constants.URN_MEMBRE_MODIFIER_GRILLE;
					
				} else if (uri.equals(Constants.URL_MEMBRE_REJOINDRE_GRILLE)) {
					// Permet à l'utilisateur de rejoindre une grille
					GrilleJointureForm gjf = new GrilleJointureForm(grilleDao, lienGUDao, req);
					gjf.rejoindre();
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
					
				} else if (uri.equals(Constants.URL_MEMBRE_QUITTER_GRILLE)) {
					// Permet à l'utilisateur de quitter une grille
					GrilleJointureForm gjf = new GrilleJointureForm(grilleDao, lienGUDao, req);
					gjf.quitter();
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
		
		if (session.getAttribute("loggedIn") != null) {
			loggedIn = (boolean) session.getAttribute("loggedIn");
		}
		
		if (uri.equals(Constants.URL_MEMBRE_CREER_GRILLE)) {
			// Transmet les données nécessaires à la création d'une grille
			GrilleCreationForm gcf = new GrilleCreationForm(grilleDao, jeuDao, banqueDao, lienGUDao, utilisateurDao, req);

			// Si aucune erreur n'est détectée, crée une nouvlle grille en BDD
			if (gcf.getErreurs().isEmpty()) {
				gcf.creer();
				resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
				return;
			} else {
				cible = Constants.URN_MEMBRE_CREER_GRILLE;
			}
			
		} else if (uri.equals(Constants.URL_MEMBRE_MODIFIER_GRILLE)) {
			// Transmet les données nécessaires à la modification d'une grille
			GrilleCreationForm gcf = new GrilleCreationForm(grilleDao, req);
			gcf.modifier();
			resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
			return;
			
		} else if (uri.equals(Constants.URL_MEMBRE_BANQUE_AJOUT)) {
			// Transmet les données nécessaires à la modification des fonds d'une grille
			
			// Seuls les modérateurs/Administrateurs peuvent créditer une grille
			if (loggedIn && utilisateur != null && utilisateur.estModerateur()) {
				cible = Constants.URN_MEMBRE_AFFICHER_GRILLE;				
				GrilleAlimentationForm gaf = new GrilleAlimentationForm(portefeuilleDao, banqueDao, req);
				gaf.modifier();
				
				if (gaf.getErreurs().isEmpty()) {
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLE + 
							"?id=" + gaf.getGrilleId());
					return;
				}
				
			}
		} else if (uri.equals(Constants.URL_MEMBRE_JOUER_GRILLE)) {
			// Joue la grille pour la période donnée
			cible = Constants.URN_MEMBRE_AFFICHER_GRILLE;
			
			GrilleJeuForm gjf = new GrilleJeuForm(lienGUDao, jourDao, banqueDao, portefeuilleDao, utilisateurDao, req);
			
			// Si aucun problème n'est détecté, la grille est jouée
			if (gjf.getErreurs().isEmpty())  {
				gjf.jouer();
				resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLE + 
						"?id=" + gjf.getGrilleId());
				return;
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

}
