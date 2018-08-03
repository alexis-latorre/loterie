package com.loterie.servlets;

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
	private static final String HOME_PAGE = "/WEB-INF/publique/homePage.jsp";
	private static final String CREATE_PASS_PAGE = "/WEB-INF/set-password.jsp";
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

		req.getServletContext().getRequestDispatcher(HOME_PAGE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String pseudo = req.getParameter("pseudo");
		Utilisateur utilisateur = null;
		utilisateur = utilisateurDao.trouverParPseudo(pseudo);
		String cible = HOME_PAGE;
		
		if (utilisateur != null) {
			session.setAttribute("utilisateur", utilisateur);
			String mdp = utilisateur.getMotDePasse();
			
			if (mdp != null) {
				boolean mdpValide = utilisateurDao.verifierMotDePasse(utilisateur, req);
				
				if (mdpValide) {
					utilisateurDao.changerGrainDeSel(utilisateur, req.getParameter("motDePasse"));
					session.setAttribute("loggedIn", true);
					cible = HOME_PAGE;
				} else {
					session.setAttribute("loggedIn", false);
					session.setAttribute("utilisateur", null);
					cible = HOME_PAGE;
				}
			} else {
				cible = CREATE_PASS_PAGE;
			}
		} else {
			cible = HOME_PAGE;
		}
		Map<String, String> erreursUtilisateur = utilisateurDao.getErreurs();
		req.setAttribute("erreursUtilisateur", erreursUtilisateur);
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}
	
}
