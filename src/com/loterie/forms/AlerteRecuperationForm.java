package com.loterie.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.loterie.business.AlerteHTML;
import com.loterie.dao.AlerteDao;
import com.loterie.dao.LienGUDao;
import com.loterie.entities.Alerte;
import com.loterie.entities.Grille;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;

public class AlerteRecuperationForm {
	private LienGUDao lguDao;
	private AlerteDao alerteDao;
	private List<LienGrilleUtilisateur> lgus = new ArrayList<LienGrilleUtilisateur>();
	
	public AlerteRecuperationForm(LienGUDao lguDao, AlerteDao alerteDao, Utilisateur utilisateur, HttpServletRequest req) {
		this.lguDao = lguDao;
		this.alerteDao = alerteDao;
		List<Grille> grilles = utilisateur.getGrilles();
		
		if (null != grilles) {
			for (Grille grille : grilles) {
				LienGrilleUtilisateur lgu = this.lguDao.trouverParGrilleEtUtilisateur(grille, utilisateur);
				lgus.add(lgu);
			}
		}
	}

	public List<AlerteHTML> getAlertes() {
		List<AlerteHTML> alertesRecuperees = new ArrayList<AlerteHTML>();
		
		for (LienGrilleUtilisateur lgu : lgus) {
			List<Alerte> alertes = alerteDao.trouverParLGU(lgu);
			
			if (null != alertes) {
				for (Alerte alerte : alertes) {
					alertesRecuperees.add(new AlerteHTML(alerte));
				}
			}
		}
		return alertesRecuperees;
	}
}
