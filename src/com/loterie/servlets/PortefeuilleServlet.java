package com.loterie.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.config.Constants;
import com.loterie.dao.PortefeuilleDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Utilisateur;
import com.loterie.forms.PortefeuilleCreationForm;
import com.loterie.managers.DAOManager;

@WebServlet(urlPatterns = {
		Constants.URL_MEMBRE_PORTEFEUILLE,
		Constants.URL_MEMBRE_PORTEFEUILLE_AJOUT
})
public class PortefeuilleServlet extends HttpServlet {
	private static final long serialVersionUID = 7L;
	private PortefeuilleDao portefeuilleDao = DAOManager.getPortefeuille();
	private UtilisateurDao utilisateurDao = DAOManager.getUtilisateur();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cible = Constants.URN_MEMBRE_403;
		HttpSession session = req.getSession();
		boolean loggedIn = false;
		
		// L'information de connexion de l'utilisateur est mise à jour
		if (session.getAttribute("loggedIn") != null) {
			loggedIn = (boolean) session.getAttribute("loggedIn");
		}
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		// Pour consulter son portefeuille, l'utilisateur doit être authentifié et au minimum de niveau membre
		if (loggedIn && utilisateur != null) {
			cible = Constants.URN_MEMBRE_PORTEFEUILLE;
			req.setAttribute("utilisateur", utilisateur);
		}

		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cible = Constants.URN_MEMBRE_403;
		HttpSession session = req.getSession();
		boolean loggedIn = false;
		String fonds = req.getParameter("fonds");
		
		if (session.getAttribute("loggedIn") != null) {
			loggedIn = (boolean) session.getAttribute("loggedIn");
		}
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		// Pour créer son portefeuille, l'utilisateur doit être authentifié et au minimum de niveau membre
		if (loggedIn && utilisateur != null) {
			cible = Constants.URN_MEMBRE_PORTEFEUILLE;
			PortefeuilleCreationForm cpf = new PortefeuilleCreationForm(portefeuilleDao, utilisateurDao, req);
			cpf.ajouterFonds(fonds);
			
			req.setAttribute("erreurs", cpf.getErreurs());
			
			if (cpf.getErreurs().isEmpty()) {
				resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_PORTEFEUILLE);
				return;
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

}
