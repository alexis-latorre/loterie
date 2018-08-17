package com.loterie.servlets;
import com.loterie.business.Mois;
import com.loterie.config.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import com.loterie.dao.GrilleDao;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Jour;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

@WebServlet(urlPatterns = {
		Constants.URL_ROOT,
		Constants.URL_PUBLIC_ACCUEIL,
		Constants.URL_PUBLIC_CONNEXION
		})
public class ConnexionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private UtilisateurDao utilisateurDao;
	@EJB
	private JourDao jourDao;
	@EJB
	private GrilleDao grilleDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		boolean loggedIn = false;
				
		if (session.getAttribute("loggedIn") != null) {
			loggedIn = (boolean) session.getAttribute("loggedIn");
		}
		
		if (loggedIn) {
			DateTime maintenant = new DateTime();
			Mois mois = new Mois(maintenant);
			
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			//List<Grille> grilles = new ArrayList<Grille>();
			
			for (com.loterie.business.Jour jour : mois.getJours()) {
				Jour jourDb = jourDao.trouverParDate(jour.getDateJour());
				if (jourDb != null) {

					List<Grille> grilles = grilleDao.trouverParJourEtUtilisateur(jour.getDateJour(), utilisateur);
					
					if (grilles != null) {
						jour.setGrilles(grilles);
						jour.setPaye(jourDb.isPaye());
					}
					/*Grille grille = jour.getLgu().getGrille();
					grilles.add(grille);*/
				}
			}
			//req.setAttribute("grilles", grilles);
			req.setAttribute("mois", mois);
		}
		req.setAttribute("loggedIn", loggedIn);

		req.getServletContext().getRequestDispatcher(Constants.URN_ACCUEIL).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cible = Constants.URN_ACCUEIL;
		HttpSession session = req.getSession();
		String pseudo = req.getParameter("pseudo");
		Utilisateur utilisateur = null;
		utilisateur = utilisateurDao.trouverParPseudo(pseudo);
		
		if (utilisateur != null) {
			session.setAttribute("utilisateur", utilisateur);
			String mdp = utilisateur.getMotDePasse();
			
			if (mdp != null) {
				boolean mdpValide = utilisateurDao.verifierMotDePasse(utilisateur, req);
				
				if (mdpValide) {
					utilisateurDao.changerGrainDeSel(utilisateur, req.getParameter("motDePasse"));
					session.setAttribute("loggedIn", true);
					req.setAttribute("utilisateur", utilisateur);
					Tools.redirigerVers(req, resp, Constants.URL_PUBLIC_ACCUEIL);
					return;
				} else {
					session.setAttribute("loggedIn", false);
					session.setAttribute("utilisateur", null);
					cible = Constants.URN_ACCUEIL;
				}
			} else {
				cible = Constants.URN_CREER_MDP;
			}
		} else {
			cible = Constants.URN_ACCUEIL;
		}
		Map<String, String> erreursUtilisateur = utilisateurDao.getErreurs();
		req.setAttribute("erreursUtilisateur", erreursUtilisateur);
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}
	
}
