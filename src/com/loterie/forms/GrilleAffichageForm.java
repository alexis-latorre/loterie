package com.loterie.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.loterie.config.Messages;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.JourDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Jour;
import com.loterie.entities.Utilisateur;

public class GrilleAffichageForm {
	private Map<String, String> erreurs = new HashMap<String, String>();
	private GrilleDao grilleDao;
	private String className = this.getClass().getName(); 
	private Grille grille;
	private HttpServletRequest req;
	private HttpSession session;
	private Utilisateur utilisateur;
	private Jour jour;

	public GrilleAffichageForm(GrilleDao grilleDao, Utilisateur utilisateur, HttpServletRequest req) {
		session = req.getSession();
		this.grilleDao = grilleDao;
		this.utilisateur = utilisateur;
		this.req = req;
	}

	public GrilleAffichageForm(GrilleDao grilleDao, JourDao jourDao, UtilisateurDao utilisateurDao, HttpServletRequest req) {
		session = req.getSession();
		this.grilleDao = grilleDao;
		this.req = req;
		Long id = validerId(req.getParameter("id"));
		grille = this.grilleDao.trouverParId(id);
		validerGrille(grille);
		jour = jourDao.trouverDernierJourJoue(grille);
		grille.setJoueurs(utilisateurDao.trouverParGrille(grille));
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	private Long validerId(String id) {
		try {
			return Long.valueOf(id);
		} catch (Exception e) {
			System.out.printf(Messages.E_LONG_INVALIDE, className + ".validerId", id);
			erreurs.put("id", Messages.MSG_ID_INVALIDE);
			return null;
		}
	}

	private void validerGrille(Grille grille) {
		if (grille == null) {
			erreurs.put("grille", Messages.MSG_ID_INTROUVABLE);
		}
	}

	public HttpServletRequest afficherGrille() {		
		session.setAttribute("grille", grille);
		req.setAttribute("utilisateur", utilisateur);
		req.setAttribute("grille", grille);
		req.setAttribute("jour", jour);	
		req.setAttribute("erreurs", erreurs);
		return req;
	}

	public HttpServletRequest afficherGrillesUtilisateur() {
		List<Grille> grillesRejointes = grilleDao.trouverParUtilisateur(utilisateur);
		List<Long> grillesIds = new ArrayList<Long>();
		List<Grille> grillesCreees = grilleDao.trouverParCreateur(utilisateur);
		List<Grille> grilles = new ArrayList<Grille>();

		if (grillesRejointes != null && !grillesRejointes.isEmpty()) {
			for (Grille grille : grillesRejointes) {
				grillesIds.add(grille.getId());
				grille.setRejoindre(false);
				grilles.add(grille);
			}
		}

		if (grillesCreees != null && !grillesCreees.isEmpty()) {
			for (Grille grille : grillesCreees) {
				if (!grillesIds.contains(grille.getId())) {
					grille.setRejoindre(true);
					grilles.add(grille);
				}
			}
		}
		req.setAttribute("grilles", grilles);
		return req;
	}
}
