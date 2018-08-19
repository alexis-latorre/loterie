package com.loterie.business;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.loterie.config.Constants;
import com.loterie.entities.Grille;
import com.loterie.tools.Tools;

public class JourHTML {
	private DateTime dt;
	private int numeroDansSemaine;
	private int numeroDansMois;
	private int numeroDansAnnee;
	private String nomLong;
	private String nomCourt;
	private String initiale;
	private List<Grille> grilles;
	private boolean paye;
	
	public JourHTML(DateTime dt) {
		this.dt = dt;
		this.numeroDansSemaine = dt.getDayOfWeek();
		int indiceJour = this.numeroDansSemaine - 1;
		this.numeroDansMois = dt.getDayOfMonth();
		this.numeroDansAnnee = dt.getDayOfYear();
		this.nomLong = Constants.JOURS_LONGS[indiceJour];
		this.nomCourt = Constants.JOURS_COURTS[indiceJour];
		this.initiale = Constants.JOURS_INITIALES[indiceJour];
		this.grilles = new ArrayList<>();
	}

	public int getNumeroDansSemaine() {
		return numeroDansSemaine;
	}

	public void setNumeroDansSemaine(int numeroDansSemaine) {
		this.numeroDansSemaine = numeroDansSemaine;
	}

	public int getNumeroDansMois() {
		return numeroDansMois;
	}

	public void setNumeroDansMois(int numeroDansMois) {
		this.numeroDansMois = numeroDansMois;
	}

	public int getNumeroDansAnnee() {
		return numeroDansAnnee;
	}

	public void setNumeroDansAnnee(int numeroDansAnnee) {
		this.numeroDansAnnee = numeroDansAnnee;
	}

	public String getNomLong() {
		return nomLong;
	}

	public void setNomLong(String nomLong) {
		this.nomLong = nomLong;
	}

	public String getNomCourt() {
		return nomCourt;
	}

	public void setNomCourt(String nomCourt) {
		this.nomCourt = nomCourt;
	}

	public String getInitiale() {
		return initiale;
	}

	public void setInitiale(String initiale) {
		this.initiale = initiale;
	}
	
	public String getDateJour() {
		return Tools.padRight(dt.getYear(), 4) + "-" + Tools.padRight(dt.getMonthOfYear(), 2) + "-" + Tools.padRight(dt.getDayOfMonth(), 2);
	}

	public List<Grille> getGrilles() {
		return grilles;
	}

	public void setGrilles(List<Grille> grilles) {
		this.grilles = grilles;
	}

	public void addGrille(Grille grille) {
		this.grilles.add(grille);
	}

	public boolean isPaye() {
		return paye;
	}

	public void setPaye(boolean paye) {
		this.paye = paye;
	}
}
