package com.loterie.servlets;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loterie.config.Constants;
import com.loterie.dao.UtilisateurDao;
import com.loterie.forms.UtilisateurCreationForm;
import com.loterie.managers.DAOManager;

@WebServlet(urlPatterns = {
		Constants.URL_PUBLIC_INSCRIPTION
	})
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID 		= 4L;
	private UtilisateurDao utilisateurDao = DAOManager.getUtilisateur();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher(Constants.URN_INSCRIPTION).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilisateurCreationForm cuf = new UtilisateurCreationForm(utilisateurDao, req);
		Map<String, String> erreurs = cuf.getErreurs();
		
		// Si aucune erreur n'est décelée, l'utilisateur est inscrit en base
		if (erreurs.isEmpty()) {
			cuf.creerUtilisateur();
			req.getServletContext().getRequestDispatcher(Constants.URN_INSCRIPTION_OK).forward(req, resp);
		} else {
			req.getServletContext().getRequestDispatcher(Constants.URN_INSCRIPTION).forward(req, resp);
		}
	}

}
