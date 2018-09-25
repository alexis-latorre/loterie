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
	private int numeroMois;
	private String nomLong;
	private String nomCourt;
	private String initiale;
	private List<Grille> grilles;
	private boolean paye;
	
	/**
	 * <b><i>JourHTML</i></b><br />
	 * <pre>public JourHTML({@link org.joda.time.DateTime DateTime} dt)</pre>
	 * Construit un objet métier à afficher à l'utilisateur 
	 * 
	 * @param dt - réprésentation de la date en objet DateTime 
	 * @return l'objet construit
	 */
	public JourHTML(DateTime dt) {
		this.dt = dt;
		this.numeroDansSemaine = dt.getDayOfWeek();
		int indiceJour = this.numeroDansSemaine - 1;
		this.numeroDansMois = dt.getDayOfMonth();
		this.numeroDansAnnee = dt.getDayOfYear();
		this.numeroMois = dt.getMonthOfYear();
		this.nomLong = Constants.JOURS_LONGS[indiceJour];
		this.nomCourt = Constants.JOURS_COURTS[indiceJour];
		this.initiale = Constants.JOURS_INITIALES[indiceJour];
		this.grilles = new ArrayList<>();
	}
	
	/**
	 * <b><i>getNumeroDansSemaine</i></b><br />
	 * <pre>public int getNumeroDansSemaine()</pre>
	 * Retourne le numéro du jour dans la semaine (ex : 1 pour lundi, 4 pour jeudi) 
	 * 
	 * @return le numéro du jour dans la semaine
	 */
	public int getNumeroDansSemaine() {
		return numeroDansSemaine;
	}
	
	/**
	 * <b><i>getNumeroDansMois</i></b><br />
	 * <pre>public int getNumeroDansMois()</pre>
	 * Retourne le numéro du jour dans le mois (1 à 31)
	 * 
	 * @return le numéro du jour dans le mois
	 */
	public int getNumeroDansMois() {
		return numeroDansMois;
	}
	
	/**
	 * <b><i>getNumeroDansAnnee</i></b><br />
	 * <pre>public int getNumeroDansAnnee()</pre>
	 * Retourne le numéro du jour dans l'année (1 à 365) 
	 * 
	 * @return le numéro du jour dans le mois
	 */
	public int getNumeroDansAnnee() {
		return numeroDansAnnee;
	}
	
	/**
	 * <b><i>getNumeroMois</i></b><br />
	 * <pre>public int getNumeroMois()</pre>
	 * Retourne le numéro du mois dans l'année (1 à 12) 
	 * 
	 * @return le numéro du mois dans l'année
	 */
	public int getNumeroMois() {
		return numeroMois;
	}
	
	/**
	 * <b><i>getNomLong</i></b><br />
	 * <pre>public {@link java.lang.String String} getNomLong()</pre>
	 * Retourne le nom long du jour de la semaine (ex : "lundi", "vendredi")
	 * 
	 * @return le nom complet du jour de la semaine
	 */
	public String getNomLong() {
		return nomLong;
	}
	
	/**
	 * <b><i>getNomCourt</i></b><br />
	 * <pre>public {@link java.lang.String String} getNomCourt()</pre>
	 * Retourne le nom court du jour de la semaine (ex : "lun", "ven")
	 * 
	 * @return le nom court du jour de la semaine
	 */
	public String getNomCourt() {
		return nomCourt;
	}
	
	/**
	 * <b><i>getInitiale</i></b><br />
	 * <pre>public {@link java.lang.String String} getInitiale()</pre>
	 * Retourne l'initiale du jour de la semaine (ex : "l", "ma", "me", "v")
	 * 
	 * @return l'initiale du jour de la semaine
	 */
	public String getInitiale() {
		return initiale;
	}
	
	/**
	 * <b><i>getDateJour</i></b><br />
	 * <pre>public {@link java.lang.String String} getDateJour()</pre>
	 * Retourne la date du jour au format yyyy-MM-dd
	 * 
	 * @return la date du jour
	 */
	public String getDateJour() {
		return Tools.padRight(dt.getYear(), 4) + "-" + Tools.padRight(dt.getMonthOfYear(), 2) + "-" + Tools.padRight(dt.getDayOfMonth(), 2);
	}
	
	/**
	 * <b><i>getGrilles</i></b><br />
	 * <pre>public {@link java.util.List List}<{@link com.loterie.entities.Grille Grille}> getGrilles()</pre>
	 * Retourne les grilles associées à la date du jour
	 * 
	 * @return la liste de grilles
	 */
	public List<Grille> getGrilles() {
		return grilles;
	}
	
	/**
	 * <b><i>addGrille</i></b><br />
	 * <pre>public void addGrille({@link com.loterie.entities.Grille Grille} grille)</pre>
	 * Ajoute une grille à l'objet métier du jour
	 * 
	 * @param grille - la grille à ajouter
	 */
	public void addGrille(Grille grille) {
		this.grilles.add(grille);
	}
	
	/**
	 * <b><i>isPaye</i></b><br />
	 * <pre>public boolean isPaye()</pre>
	 * Renseigne si le jour représenté est payé par le joueur
	 * 
	 * @return vrai si le jour est payé
	 */
	public boolean isPaye() {
		return paye;
	}
	
	public String toString() {
		return dt.toString() + ";" + numeroDansSemaine + ";" + numeroDansMois + ";" + numeroDansAnnee + ";" + 
				numeroMois + ";" + nomLong + ";" + nomCourt + ";" + initiale + ";" + grilles;
	}
}
