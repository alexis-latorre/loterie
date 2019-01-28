package com.loterie.servlets;
import com.loterie.config.Constants;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.dao.GrilleDao;
import com.loterie.dao.JourDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Utilisateur;
import com.loterie.forms.UtilisateurConnexionForm;
import com.loterie.tools.DevTools;
import com.loterie.forms.GrilleRecuperationDuMoisForm;

@WebServlet(urlPatterns = {
		//"/clearCache",
		Constants.URL_PUBLIC_ACCUEIL,
		Constants.URL_PUBLIC_CONNEXION
		})
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private UtilisateurDao utilisateurDao;
	@EJB
	private JourDao jourDao;
	@EJB
	private GrilleDao grilleDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//utilisateurDao.clearCache();
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		
		if (uri.equals("/clearCache")) {
			DevTools.clearCache(jourDao);
			resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_PUBLIC_ACCUEIL);
			return;
		}
		HttpSession session = req.getSession();
		boolean loggedIn = false;

		// Met à jour l'information de connexion de la session
		if (session.getAttribute("loggedIn") != null) {
			loggedIn = (boolean) session.getAttribute("loggedIn");
		}
		
		// Si l'utilisateur est authentifié, le calendrier est affiché en page d'accueil
		if (loggedIn) {
			req.setAttribute("titrePage", "dashboard");
			// L'utilisateur n'est récupéré que s'il est authentifié, pas besoin de le récupérer avant
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");			
			// Récupère les grilles du mois pour lequel l'utilisateur participe ou celles qu'il a créées
			GrilleRecuperationDuMoisForm gdmf = new GrilleRecuperationDuMoisForm(jourDao, grilleDao, utilisateur, req);
			//TODO: bouger cette portion
			req.setAttribute("mois", gdmf.getMois());
			req.setAttribute("anneeAjd", gdmf.getMois().isAnneeAjd());
			req.setAttribute("moisAjd", gdmf.getMois().isMoisAjd());
		} else {
			req.setAttribute("titrePage", "connection");
		}
		// Transmet l'information de connexion à la page JSP
		req.setAttribute("loggedIn", loggedIn);

		req.getServletContext().getRequestDispatcher(Constants.URN_ACCUEIL).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cible = Constants.URN_ACCUEIL;
		HttpSession session = req.getSession();
		UtilisateurConnexionForm ucf = new UtilisateurConnexionForm(utilisateurDao, grilleDao, req);
		Utilisateur utilisateur = ucf.getUtilisateur();
		Map<String, String> erreurs = ucf.getErreurs();
		boolean connexionOK = erreurs.isEmpty();
		
		/* Les actions de type POST ne peuvent être effectuées que si l'utilisateur est authentifié, sinon il est
		redirigé vers l'accueil */
		if (utilisateur != null) {
			session.setAttribute("utilisateur", utilisateur);
			req.setAttribute("titrePage", "dashboard");
			
			/* Si le mot de passe n'est pas encore défini en BDD, l'utilisateur est invité à le créer sur la 
			 * page dédiée à la création/modification de mot de passe
			 */
			if (utilisateur.getMotDePasse() != null) {				
				// Si toute la procédure de connexion s'est bien déroulée
				if (connexionOK) {
					// La session et la JSP sont mis à jour
					session.setAttribute("loggedIn", true);
					req.setAttribute("utilisateur", utilisateur);
					// L'utilisateur est redirigé vers la page d'accueil
					// TODO: Rediriger vers la page demandée par l'utilisateur 
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_PUBLIC_ACCUEIL);
					return;
				} else {
					// La session est réinitialisée en cas d'échec d'authentification
					session.setAttribute("loggedIn", false);
					session.setAttribute("utilisateur", null);
					cible = Constants.URN_ACCUEIL;
				}
			} else {
				cible = Constants.URN_CREER_MDP;
			}
		} else {
			req.setAttribute("titrePage", "connection");
			cible = Constants.URN_ACCUEIL;
		}
		// Transmet les erreurs (s'il y en a) à la JSP
		req.setAttribute("erreursUtilisateur", erreurs);
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}
	
}
