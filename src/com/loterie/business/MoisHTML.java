package com.loterie.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.loterie.entities.Grille;
import com.loterie.entities.Jour;

public class MoisHTML {
	private int premierJour;
	private int dernierJour;
	private int aujourdhui;
	private String premiereDate;
	private String derniereDate;
	private int numero;
	private List<JourHTML> jours;
	private Map<String, JourHTML> joursHTML;
	
	public MoisHTML(DateTime dt) {
		this.premierJour = dt.dayOfMonth().getMinimumValue();
		this.dernierJour = dt.dayOfMonth().getMaximumValue();
		this.setAujourdhui(dt.getDayOfMonth());
		this.numero = dt.getMonthOfYear();
		this.jours = new ArrayList<JourHTML>();
		this.joursHTML = new HashMap<>();

		DateTime pj = new DateTime(dt.getYear(), dt.getMonthOfYear(), this.premierJour, 0, 0, 0);
		DateTime dj = new DateTime(dt.getYear(), dt.getMonthOfYear(), this.dernierJour, 0, 0, 0);

		int premierJourSemaine = pj.getDayOfWeek();
		int dernierJourSemaine = dj.getDayOfWeek();
		
		if (premierJourSemaine != 1) {			
			for (int i = premierJourSemaine; i > 1; i--) {
				JourHTML jour = new JourHTML(pj.minusDays(i - 1));
				this.jours.add(jour);
				this.joursHTML.put(jour.getDateJour(), jour);
				
				if (i == premierJourSemaine) {
					premiereDate = jour.getDateJour();
				}
			}
		}
		
		for (int i = 0; i < dernierJour; i++) {
			JourHTML jour = new JourHTML(pj.plusDays(i));
			this.jours.add(jour);
			this.joursHTML.put(jour.getDateJour(), jour);
		}
		
		if (dernierJourSemaine != 7) {			
			for (int i = 1; i <= (7 - dernierJourSemaine); i++) {
				JourHTML jour = new JourHTML(dj.plusDays(i));
				this.jours.add(jour);
				this.joursHTML.put(jour.getDateJour(), jour);
				
				if (i == (7 - dernierJourSemaine)) {
					derniereDate = jour.getDateJour();
				}
			}
		}
	}
	
	public int getPremierJour() {
		return premierJour;
	}
	
	public void setPremierJour(int premierJour) {
		this.premierJour = premierJour;
	}
	
	public int getDernierJour() {
		return dernierJour;
	}
	
	public void setDernierJour(int dernierJour) {
		this.dernierJour = dernierJour;
	}

	public int getAujourdhui() {
		return aujourdhui;
	}

	public void setAujourdhui(int aujourdhui) {
		this.aujourdhui = aujourdhui;
	}

	public String getPremiereDate() {
		return premiereDate;
	}

	public String getDerniereDate() {
		return derniereDate;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<JourHTML> getJours() {
		return jours;
	}

	public JourHTML getJour(String date) {
		for (JourHTML jour : jours) {
			if (jour.getDateJour().equals(date)) {
				return jour;
			}
		}
		return null;
	}

	public void setJours(List<JourHTML> jours) {
		this.jours = jours;
	}

	public Map<String, JourHTML> getJoursHTML() {
		return joursHTML;
	}

	public void combinerJours(List<Jour> jours) {
		for (Jour jour : jours) {
			JourHTML jourHTML = joursHTML.get(jour.getDateJour()); 
			Grille grille = jour.getLgu().getGrille();
			grille.setPaye(jour.isPaye());
			jourHTML.addGrille(grille);
			//jourHTML.setPaye(jour.isPaye());
		}
	}
}
