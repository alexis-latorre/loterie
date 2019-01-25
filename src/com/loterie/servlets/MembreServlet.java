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

import com.loterie.config.Constants;
import com.loterie.dao.LogDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Utilisateur;
import com.loterie.forms.UtilisateurModificationForm;

@WebServlet(urlPatterns = {
		Constants.URL_MEMBRE_PROFIL,
		Constants.URL_MEMBRE_MODIFIER_PROFIL
})
public class MembreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private UtilisateurDao utilisateurDao;
	@EJB
	private LogDao logDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cible = Constants.URN_MEMBRE_403;
		// String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		HttpSession session = req.getSession();
		boolean loggedIn = false;

		// Met à jour l'information de connexion de la session
		if (session.getAttribute("loggedIn") != null) {
			loggedIn = (boolean) session.getAttribute("loggedIn");
		}
		
		// Si l'utilisateur est authentifié, son espace membre lui est montré
		if (loggedIn) {
			cible = Constants.URN_MEMBRE_PROFIL;
		}
		// Transmet l'information de connexion à la page JSP
		req.setAttribute("loggedIn", loggedIn);

		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cible = Constants.URN_MEMBRE_403;
		// String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		HttpSession session = req.getSession();
		boolean loggedIn = false;

		// Met à jour l'information de connexion de la session
		if (session.getAttribute("loggedIn") != null) {
			loggedIn = (boolean) session.getAttribute("loggedIn");
		}
		
		if (loggedIn) {
			cible = Constants.URN_MEMBRE_PROFIL;
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			
			if (utilisateur.estBasique()) {
				UtilisateurModificationForm umf = new UtilisateurModificationForm(utilisateur, utilisateurDao, logDao,
						req);
				umf.valider();
				Map<String, String> erreurs = umf.getErreurs();
				
				if (erreurs.size() > 0) {
					req.setAttribute("erreurs", erreurs);
				} else {
					umf.modifier();
				}
			}
		}
		// Transmet l'information de connexion à la page JSP
		req.setAttribute("loggedIn", loggedIn);

		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}
	
}
