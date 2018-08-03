package com.loterie.servlets;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loterie.dao.UtilisateurDao;
import com.loterie.forms.CreationUtilisateurForm;

@WebServlet(urlPatterns = {"/inscription"})
public class InscriptionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID 		= 4L;
	private static final String SUBSCR_PAGE 		= "/WEB-INF/publique/subscriptionPage.jsp";
	private static final String SUBSCR_OK_PAGE 		= "/WEB-INF/publique/homePage.jsp";
	@EJB
	private UtilisateurDao utilisateurDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().getRequestDispatcher(SUBSCR_PAGE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CreationUtilisateurForm cuf = new CreationUtilisateurForm(utilisateurDao, req);
		Map<String, String> erreurs = cuf.getErreurs();
		
		if (erreurs.isEmpty()) {
			cuf.creerUtilisateur();
			req.getServletContext().getRequestDispatcher(SUBSCR_OK_PAGE).forward(req, resp);
		} else {
			req.getServletContext().getRequestDispatcher(SUBSCR_PAGE).forward(req, resp);
		}
	}

}
