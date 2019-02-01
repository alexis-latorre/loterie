package com.loterie.servlets;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.config.Constants;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.LogDao;
import com.loterie.entities.Utilisateur;
import com.loterie.forms.GrilleRecuperationDuJourForm;

@WebServlet(urlPatterns = {
		Constants.URL_JOUR_AFFICHER_DETAILS
})
public class JourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private LienGUDao lguDao;
	@EJB
	private JourDao jourDao;
	@EJB
	private LogDao logDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cible = Constants.URN_MEMBRE_403;
		// String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		HttpSession session = req.getSession();
		boolean loggedIn = false;
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		// Met à jour l'information de connexion de la session
		if (session.getAttribute("loggedIn") != null) {
			loggedIn = (boolean) session.getAttribute("loggedIn");
		}
		
		// Si l'utilisateur est authentifié, son espace membre lui est montré
		if (loggedIn) {
			GrilleRecuperationDuJourForm grdjf = new GrilleRecuperationDuJourForm(lguDao, jourDao, utilisateur, req);
			req.setAttribute("jour", grdjf.getJour());
			cible = Constants.URN_JOUR_AFFICHER_DETAILS;
		}
		// Transmet l'information de connexion à la page JSP
		req.setAttribute("loggedIn", loggedIn);

		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}
	
}
