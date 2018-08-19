package com.loterie.servlets;
import com.loterie.config.Constants;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.dao.UtilisateurDao;

@WebServlet(urlPatterns = {
		Constants.URL_PUBLIC_DECONNEXION
		})
public class DeconnexionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	@EJB
	private UtilisateurDao utilisateurDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		// Assure que la connexion est bien terminée
		if (session.getAttribute("loggedIn") != null) {
			session.setAttribute("loggedIn", false);
		}

		// Assure que l'utilisateur est bien détruit de la session
		if (session.getAttribute("utilisateur") != null) {
			session.setAttribute("utilisateur", null);
		}
		// Détruit la session avant de renvoyer l'utilisateur à l'accueil
		session.invalidate();
		req.getServletContext().getRequestDispatcher(Constants.URN_ACCUEIL).forward(req, resp);
	}	
}
