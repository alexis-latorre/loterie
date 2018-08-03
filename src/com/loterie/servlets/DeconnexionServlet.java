package com.loterie.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.dao.UtilisateurDao;

@WebServlet(urlPatterns = {"/deconnexion"})
public class DeconnexionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private static final String HOME_PAGE = "/WEB-INF/publique/homePage.jsp";
	@EJB
	private UtilisateurDao utilisateurDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if (session.getAttribute("loggedIn") != null) {
			req.setAttribute("loggedIn", false);
		}
		
		if (session.getAttribute("utilisateur") != null) {
		req.setAttribute("utilisateur", null);
		}

		req.getServletContext().getRequestDispatcher(HOME_PAGE).forward(req, resp);
	}	
}
