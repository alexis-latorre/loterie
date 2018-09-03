package com.loterie.forms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.loterie.dao.GrilleDao;
import com.loterie.dao.LienGUDao;
import com.loterie.entities.Grille;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;

public class GrilleJointureForm {
	private GrilleDao grilleDao;
	private LienGUDao lienGUDao;
	private HttpServletRequest req;
	private HttpSession session;
	
	public GrilleJointureForm(GrilleDao grilleDao, LienGUDao lienGUDao, HttpServletRequest req) {
		this.grilleDao = grilleDao;
		this.lienGUDao = lienGUDao;
		this.req = req;
		this.session = this.req.getSession();
	}

	public void rejoindre() {
		String id = req.getParameter("id");
		Grille grille = grilleDao.trouverParId(Long.valueOf(id));
		LienGrilleUtilisateur lienGU = new LienGrilleUtilisateur(); 

		lienGU.setUtilisateur((Utilisateur) session.getAttribute("utilisateur"));
		lienGU.setGrille(grille);
		lienGUDao.creer(lienGU);
	}

	public void quitter() {
		String id = req.getParameter("id");
		Grille grille = grilleDao.trouverParId(Long.valueOf(id));
		LienGrilleUtilisateur lienGU = lienGUDao.trouverParGrille(grille).get(0);
		lienGUDao.supprimer(lienGU);
	}

}
