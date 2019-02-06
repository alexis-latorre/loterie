package com.loterie.forms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;

import com.loterie.business.JourHTML;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Jour;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;

public class GrilleRecuperationDuJourForm {
	private JourHTML jour;
	private List<LienGrilleUtilisateur> lgus;

	public GrilleRecuperationDuJourForm(LienGUDao lguDao, JourDao jourDao, Utilisateur utilisateur, 
			HttpServletRequest req) {		
		try {
			String id = req.getParameter("id");
			
			if (null == id) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
				id = sdf.format(new Date());
			}
			String[] parameters = id.split("-");
			int jourId = Integer.valueOf(parameters[2]);
			int mois = Integer.valueOf(parameters[1]);
			int annee = Integer.valueOf(parameters[0]);
			lgus = lguDao.trouverParUtilisateur(utilisateur);			
			jour = new JourHTML(new DateTime().withYear(annee).withMonthOfYear(mois).withDayOfMonth(jourId));
			double gains = 0.0D;
			
			for (LienGrilleUtilisateur lgu : lgus) {
				Jour j = jourDao.trouverParDateEtLGU(jour.getDateJour() + " 12:00:00", lgu);
				Grille grille = lgu.getGrille();
				
				if (null != j) {
					grille.setPaye(j.getPaye());
					gains += j.getGains();
					jour.addGrille(grille);
				} else {
					String[] joursDeJeu = grille.getJeu().getJourDeTirage();
					
					for (String jourDeJeu : joursDeJeu) {
						if (jourDeJeu.equals(String.valueOf(jour.getNumeroDansSemaine()))) {
							jour.addGrille(grille);
						}
					}
				}
			}
			jour.setGains(gains);
		} catch (NumberFormatException e) {
		}
	}
	
	public JourHTML getJour() {
		return jour;
	}
}
