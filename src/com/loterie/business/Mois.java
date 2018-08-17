package com.loterie.business;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class Mois {
	private int premierJour;
	private int dernierJour;
	private int numero;
	private List<Jour> jours;
	
	public Mois(DateTime dt) {
		this.premierJour = dt.dayOfMonth().getMinimumValue();
		this.dernierJour = dt.dayOfMonth().getMaximumValue();
		this.numero = dt.getMonthOfYear();
		this.jours = new ArrayList<Jour>();

		DateTime pj = new DateTime(dt.getYear(), dt.getMonthOfYear(), this.premierJour, 0, 0, 0);
		DateTime dj = new DateTime(dt.getYear(), dt.getMonthOfYear(), this.dernierJour, 0, 0, 0);

		int premierJourSemaine = pj.getDayOfWeek();
		int dernierJourSemaine = dj.getDayOfWeek();
		
		if (premierJourSemaine != 1) {			
			for (int i = premierJourSemaine; i > 1; i--) {
				Jour jour = new Jour(pj.minusDays(i - 1));
				this.jours.add(jour);
			}
		}
		
		for (int i = 0; i < dernierJour; i++) {
			Jour jour = new Jour(pj.plusDays(i));
			this.jours.add(jour);
		}
		
		if (dernierJourSemaine != 7) {			
			for (int i = 1; i <= (7 - dernierJourSemaine); i++) {
				Jour jour = new Jour(dj.plusDays(i));
				this.jours.add(jour);
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<Jour> getJours() {
		return jours;
	}

	public Jour getJour(String date) {
		for (Jour jour : jours) {
			if (jour.getDateJour().equals(date)) {
				return jour;
			}
		}
		return null;
	}

	public void setJours(List<Jour> jours) {
		this.jours = jours;
	}
}
