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
import com.loterie.dao.PortefeuilleDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Utilisateur;
import com.loterie.forms.UtilisateurCreditForm;
import com.loterie.forms.UtilisateurRecuperationForm;

@WebServlet(urlPatterns = {
		Constants.URL_ADMIN_ACCUEIL,
		Constants.URL_ADMIN_CREDITER
		})
public class AdministrationServlet extends HttpServlet {
	private static final long serialVersionUID = 5L;

	@EJB
	private UtilisateurDao utilisateurDao;
	@EJB
	private PortefeuilleDao portefeuilleDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		// Si l'utilisateur n'a pas de droit d'aministration, il sera redirigé vers la page d'erreur 403
		String cible = Constants.URN_ADMIN_403;
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		// Vérifie si l'utilisateur existe en session
		if (utilisateur != null) {
			// Vérifie que l'utilisateur en session est au minimum un modérateur
			if (utilisateur.estModerateur() || utilisateur.estAdministrateur()) {
				switch (uri) {
					case Constants.URL_ADMIN_CREDITER: {
						/*UtilisateurCreditForm ucf = new UtilisateurCreditForm(utilisateurDao, portefeuilleDao, req);
						ucf.crediter();*/
						UtilisateurRecuperationForm urf = new UtilisateurRecuperationForm(utilisateurDao, req);
						urf.recupererRang(Constants.UTILISATEUR_ROLE_MEMBRE);
						cible = Constants.URN_ADMIN_CREDITER;
						break;
					}
					default: cible = Constants.URN_ADMIN_ACCUEIL;
				}
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		// Si l'utilisateur n'a pas de droit d'aministration, il sera redirigé vers la page d'erreur 403
		String cible = Constants.URN_ADMIN_403;
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		// Vérifie si l'utilisateur existe en session
		if (utilisateur != null) {
			// Vérifie que l'utilisateur en session est au minimum un modérateur
			if (utilisateur.estModerateur() || utilisateur.estAdministrateur()) {
				switch (uri) {
					case Constants.URL_ADMIN_CREDITER: {
						UtilisateurCreditForm ucf = new UtilisateurCreditForm(utilisateurDao, portefeuilleDao, req);
						ucf.crediter();
						resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_ADMIN_CREDITER);
						return;
					}
					default: cible = Constants.URN_ADMIN_ACCUEIL;
				}
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}
}
