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

import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Utilisateur;

@WebServlet(urlPatterns = {"/accueil", "/login"})
public class ConnexionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private UtilisateurDao utilisateurDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		boolean loggedIn = false;
				
		if (session.getAttribute("loggedIn") != null) {
			loggedIn = (boolean) session.getAttribute("loggedIn");
		}
		req.setAttribute("loggedIn", loggedIn);

		req.getServletContext().getRequestDispatcher(Constants.URL_ACCUEIL).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cible = Constants.URL_ACCUEIL;
		HttpSession session = req.getSession();
		String pseudo = req.getParameter("pseudo");
		Utilisateur utilisateur = null;
		utilisateur = utilisateurDao.trouverParPseudo(pseudo);
		
		if (utilisateur != null) {
			session.setAttribute("utilisateur", utilisateur);
			String mdp = utilisateur.getMotDePasse();
			
			if (mdp != null) {
				boolean mdpValide = utilisateurDao.verifierMotDePasse(utilisateur, req);
				
				if (mdpValide) {
					utilisateurDao.changerGrainDeSel(utilisateur, req.getParameter("motDePasse"));
					session.setAttribute("loggedIn", true);
					req.setAttribute("utilisateur", utilisateur);
					cible = Constants.URL_ACCUEIL;
				} else {
					session.setAttribute("loggedIn", false);
					session.setAttribute("utilisateur", null);
					cible = Constants.URL_ACCUEIL;
				}
			} else {
				cible = Constants.URL_CREER_MDP;
			}
		} else {
			cible = Constants.URL_ACCUEIL;
		}
		Map<String, String> erreursUtilisateur = utilisateurDao.getErreurs();
		req.setAttribute("erreursUtilisateur", erreursUtilisateur);
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}
	
}
