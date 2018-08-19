package com.loterie.forms;

import java.util.List;

import org.joda.time.DateTime;

import com.loterie.business.MoisHTML;
import com.loterie.dao.JourDao;
import com.loterie.entities.Jour;
import com.loterie.entities.Utilisateur;

public class RecuperationGrillesDuMoisForm {
	private JourDao jourDao;
	private MoisHTML mois;
	
	public RecuperationGrillesDuMoisForm(DateTime dt, Utilisateur utilisateur, JourDao jourDao) {
		this.jourDao = jourDao;
		mois = new MoisHTML(dt);
		List<Jour> jours = this.jourDao.trouverParIntervalleEtUtilisateur(mois.getPremiereDate(), mois.getDerniereDate(), utilisateur);
		mois.combinerJours(jours);
	}
	
	public MoisHTML getMois() {
		return mois;
	}
}
