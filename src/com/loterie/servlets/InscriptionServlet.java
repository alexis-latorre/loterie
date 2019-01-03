package com.loterie.servlets;
import com.loterie.config.Constants;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loterie.dao.PrivilegeDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.forms.UtilisateurCreationForm;

@WebServlet(urlPatterns = {
		Constants.URL_PUBLIC_INSCRIPTION
	})
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID 		= 4L;
	@EJB
	private UtilisateurDao utilisateurDao;
	@EJB
	private PrivilegeDao privilegeDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher(Constants.URN_INSCRIPTION).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilisateurCreationForm cuf = new UtilisateurCreationForm(utilisateurDao, privilegeDao, req);
		Map<String, String> erreurs = cuf.getErreurs();
		
		// Si aucune erreur n'est décelée, l'utilisateur est inscrit en base
		if (erreurs.isEmpty()) {
			cuf.creerUtilisateur();
			req.getServletContext().getRequestDispatcher(Constants.URN_ACCUEIL).forward(req, resp);
		} else {
			req.setAttribute("champs", cuf.getChamps());
			req.setAttribute("erreurs", erreurs);
			req.getServletContext().getRequestDispatcher(Constants.URN_INSCRIPTION).forward(req, resp);
		}
	}

}
