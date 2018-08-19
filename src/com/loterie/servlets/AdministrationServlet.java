package com.loterie.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.config.Constants;
import com.loterie.entities.Utilisateur;

@WebServlet(urlPatterns = {
		Constants.URL_ADMIN_ACCUEIL
		})
public class AdministrationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Si l'utilisateur n'a pas de droit d'aministration, il sera redirigé vers la page d'erreur 403
		String cible = Constants.URN_ADMIN_403;
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		// Vérifie si l'utilisateur existe en session
		if (utilisateur != null) {
			// Vérifie que l'utilisateur en session est au minimum un modérateur
			if (utilisateur.estModerateur()) {
				cible = Constants.URN_ADMIN_ACCUEIL;
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

}
