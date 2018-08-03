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
import com.loterie.entities.Utilisateur;

@WebServlet(urlPatterns = {"/updatePassword"})
public class GestionMdpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private static final String CREATE_PASS_PAGE = "/WEB-INF/set-password.jsp";
	private static final String HOME_PAGE = "/index.jsp";
	@EJB
	private UtilisateurDao utilisateurDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher(CREATE_PASS_PAGE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		if (utilisateur != null) {
			utilisateurDao.changerMotDePasse(utilisateur, req);
			req.getServletContext().getRequestDispatcher(HOME_PAGE).forward(req, resp);
		}
	}
	
}
