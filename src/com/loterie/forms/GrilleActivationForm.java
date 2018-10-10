package com.loterie.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.loterie.config.Messages;
import com.loterie.dao.GrilleDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Utilisateur;

public class GrilleActivationForm {
	private GrilleDao grilleDao;
	private Grille grille;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public GrilleActivationForm(GrilleDao grilleDao, Utilisateur utilisateur, HttpServletRequest req) {
		this.grilleDao = grilleDao;
		String id = req.getParameter("id");
		
		try {
			this.grille = grilleDao.trouverParId(Long.valueOf(id));
		} catch (Exception e) {
			erreurs.put("id", Messages.MSG_ID_INVALIDE);
		}
	}

	public void activer() {
		try {
			this.grille.setActive(true);
			this.grilleDao.maj(grille);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void desactiver() {
		try {
			this.grille.setActive(false);
			this.grilleDao.maj(grille);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void retablir() {
		try {
			this.grille.setVisible(true);
			this.grilleDao.maj(grille);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
