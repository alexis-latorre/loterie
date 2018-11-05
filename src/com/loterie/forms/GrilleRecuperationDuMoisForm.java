package com.loterie.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.loterie.business.MoisHTML;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.JourDao;
import com.loterie.entities.Jour;
import com.loterie.entities.Utilisateur;

public class GrilleRecuperationDuMoisForm {
	private JourDao jourDao;
	private GrilleDao grilleDao;
	private MoisHTML mois;

	public GrilleRecuperationDuMoisForm(JourDao jourDao, GrilleDao grilleDao, Utilisateur utilisateur, HttpServletRequest req) {
		DateTime moisSurveille = new DateTime();
		
		try {
			int mois = Integer.valueOf(req.getParameter("mois"));
			int annee = Integer.valueOf(req.getParameter("annee"));
			
			if (mois > 0 && mois < 13 && annee > -1) {
				moisSurveille = new DateTime().withMonthOfYear(mois).withYear(annee);
			}
		} catch (NumberFormatException e) {
		}

		this.jourDao = jourDao;
		this.grilleDao = grilleDao;
		mois = new MoisHTML(moisSurveille);
		mois.setGrilles(this.grilleDao.trouverParUtilisateur(utilisateur));
		List<Jour> jours = this.jourDao.trouverParIntervalleEtUtilisateur(mois.getPremiereDate(), mois.getDerniereDate(), utilisateur);
		mois.combinerJours(jours);
	}
	
	public MoisHTML getMois() {
		return mois;
	}
}
