package com.loterie.forms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.loterie.dao.JeuDao;
import com.loterie.entities.Jeu;

public class JeuRecuperationDuJourForm {
	private List<Jeu> jeuxJour = new ArrayList<Jeu>();

	public JeuRecuperationDuJourForm(JeuDao jeuDao, HttpServletRequest req) {		
		try {
			String id = req.getParameter("id");
			
			if (null == id) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				id = sdf.format(new Date());
			}
			String[] parameters = id.split("-");
			int jourId = Integer.valueOf(parameters[2]);
			int mois = Integer.valueOf(parameters[1]);
			int annee = Integer.valueOf(parameters[0]);
			DateTime dt = new DateTime().withDayOfMonth(jourId).withMonthOfYear(mois).withYear(annee);

			jeuxJour = jeuDao.trouverParJourTirage(String.valueOf(dt.getDayOfWeek()));
		} catch (NumberFormatException e) {
		}
	}
	
	public List<Jeu> getJeuxJour() {
		return jeuxJour;
	}
}
