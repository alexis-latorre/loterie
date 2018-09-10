package com.loterie.servlets;
import com.loterie.config.Constants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Utilisateur;
import com.loterie.managers.DAOManager;

@WebServlet(urlPatterns = {
		Constants.URL_MODIFIER_MDP
		})
public class GestionMdpServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private UtilisateurDao utilisateurDao = DAOManager.getUtilisateur();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher(Constants.URN_CREER_MDP).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		String mdp = req.getParameter("motDePasse");
		String mdpc = req.getParameter("motDePasseConfirmation");
		
		// L'utilisateur doit exister pour modifier son mot de passe
		if (utilisateur != null) {
			utilisateurDao.changerMotDePasse(utilisateur, mdp, mdpc);
			req.getServletContext().getRequestDispatcher(Constants.URN_ACCUEIL).forward(req, resp);
		}
	}
	
}
