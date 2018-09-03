package com.loterie.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.joda.time.DateTime;

import com.loterie.entities.Grille;
import com.loterie.entities.Jour;

public class MoisHTML {
	private int dernierJour;
	private int aujourdhui;
	private String premiereDate;
	private String derniereDate;
	private int numero;
	private List<JourHTML> jours;
	private String nom;
	private int annee;
	private DateTime moisPrecedent;
	private DateTime moisSuivant;
	
	/**
	 * <b><i>MoisHTML</i></b><br />
	 * <pre>public MoisHTML({@link org.joda.time.DateTime DateTime} dt)</pre>
	 * Construit un objet métier à afficher à l'utilisateur 
	 * 
	 * @param dt - réprésentation de la date en objet DateTime 
	 * @return l'objet construit
	 */
	public MoisHTML(DateTime dt) {
		this.dernierJour = dt.dayOfMonth().getMaximumValue();
		this.aujourdhui = dt.getDayOfMonth();
		this.numero = dt.getMonthOfYear();
		this.jours = new ArrayList<JourHTML>();
		this.nom = dt.monthOfYear().getAsText(Locale.FRANCE);
		this.annee = dt.getYear();
		this.moisPrecedent = dt.minusMonths(1);
		this.moisSuivant = dt.plusMonths(1);

		DateTime pj = new DateTime(dt.getYear(), dt.getMonthOfYear(), 1, 0, 0, 0);
		DateTime dj = new DateTime(dt.getYear(), dt.getMonthOfYear(), this.dernierJour, 0, 0, 0);

		int premierJourSemaine = pj.getDayOfWeek();
		int dernierJourSemaine = dj.getDayOfWeek();
		
		// Si le premier jour du mois n'est pas un lundi, on démarre depuis le lundi précédent pour la présentation
		if (premierJourSemaine != 1) {
			// Ajoute les jours du dernier lundi à la date entamant le mois en cours
			for (int i = premierJourSemaine; i > 1; i--) {
				JourHTML jour = new JourHTML(pj.minusDays(i - 1));
				// Ajoute les entités liées
				this.jours.add(jour);
				
				// Le jour le plus éloigné est la première date à traiter en affichage
				if (i == premierJourSemaine) {
					premiereDate = jour.getDateJour();
				}
			}
		} else {
			JourHTML jour = new JourHTML(pj);
			premiereDate = jour.getDateJour();
		}
		
		// Pour le mois en cours, on traite normalement
		for (int i = 0; i < dernierJour; i++) {
			JourHTML jour = new JourHTML(pj.plusDays(i));
			// Ajoute les entités liées
			this.jours.add(jour);
		}

		// Si le dernier jour du mois n'est pas un vendredi, on fini au vendredi suivant pour la présentation
		if (dernierJourSemaine != 7) {	
			// Ajoute les jours de la dernière date du mois en cours jusqu'au vendredi suivant		
			for (int i = 1; i <= (7 - dernierJourSemaine); i++) {
				JourHTML jour = new JourHTML(dj.plusDays(i));
				// Ajoute les entités liées
				this.jours.add(jour);

				// Le jour le plus éloigné est la dernière date à traiter en affichage
				if (i == (7 - dernierJourSemaine)) {
					derniereDate = jour.getDateJour();
				}
			}
		} else {
			JourHTML jour = new JourHTML(dj);
			derniereDate = jour.getDateJour();
		}
	}
	
	/**
	 * <b><i>getDernierJour</i></b><br />
	 * <pre>public int getDernierJour()</pre>
	 * Retourne le dernier jour du mois (28 à 31)
	 * 
	 * @return le numéro du dernier jour dans le mois
	 */
	public int getDernierJour() {
		return dernierJour;
	}
	
	/**
	 * <b><i>getAujourdhui</i></b><br />
	 * <pre>public int getAujourdhui()</pre>
	 * Retourne le numéro du jour actuel dans le mois
	 * 
	 * @return le numéro du jour actuel
	 */
	public int getAujourdhui() {
		return aujourdhui;
	}
	
	/**
	 * <b><i>getPremiereDate</i></b><br />
	 * <pre>public {@link java.lang.String String} getPremiereDate()</pre>
	 * Retourne la première date de l'objet à afficher
	 * 
	 * @return la première date à afficher
	 */
	public String getPremiereDate() {
		return premiereDate;
	}
	
	/**
	 * <b><i>getDerniereDate</i></b><br />
	 * <pre>public {@link java.lang.String String} getDerniereDate()</pre>
	 * Retourne la dernière date de l'objet à afficher
	 * 
	 * @return la dernière date à afficher
	 */
	public String getDerniereDate() {
		return derniereDate;
	}
	
	/**
	 * <b><i>getNumero</i></b><br />
	 * <pre>public int getNumero()</pre>
	 * Retourne le numéro du mois de l'objet à afficher
	 * 
	 * @return le numéro du mois à afficher
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * <b><i>getJours</i></b><br />
	 * <pre>public {@link java.util.List List}<{@link com.loterie.business.JourHTML JourHTML}> getJours()</pre>
	 * Retourne les jours métier associés au mois métier à afficher
	 * 
	 * @return la liste des jours métiers
	 */
	public List<JourHTML> getJours() {
		return jours;
	}
	
	/**
	 * <b><i>trouverJourParDate</i></b><br />
	 * <pre>public {@link com.loterie.business.JourHTML JourHTML} trouverJourParDate({@link java.lang.String String} date)</pre>
	 * Retourne le jours métier dont la date correspond au paramètre
	 * 
	 * @param date - date à rechercher
	 * @return le jour correspondant à la date recherchée
	 */
	public JourHTML trouverJourParDate(String date) {
		for (JourHTML jour : jours) {
			if (jour.getDateJour().equals(date)) {
				return jour;
			}
		}
		return null;
	}
	
	/**
	 * <b><i>getNom</i></b><br />
	 * <pre>public {@link java.lang.String String} getNom()</pre>
	 * Retourne le nom du mois de l'objet à afficher
	 * 
	 * @return le nom du mois à afficher
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * <b><i>getAnnee</i></b><br />
	 * <pre>public int getAnnee()</pre>
	 * Retourne l'année du mois de l'objet à afficher
	 * 
	 * @return l'année du mois à afficher
	 */
	public int getAnnee() {
		return annee;
	}
	
	/**
	 * <b><i>getMoisPrecedent</i></b><br />
	 * <pre>public {@link org.joda.time.DateTime DateTime} getMoisPrecedent()</pre>
	 * Retourne la référence vers le mois précédent
	 * 
	 * @return la date du premier jour du mois précédent
	 */
	public DateTime getMoisPrecedent() {
		return moisPrecedent;
	}
	
	/**
	 * <b><i>getMoisSuivant</i></b><br />
	 * <pre>public {@link org.joda.time.DateTime DateTime} getMoisSuivant()</pre>
	 * Retourne la référence vers le mois suivant
	 * 
	 * @return la date du premier jour du mois suivant
	 */
	public DateTime getMoisSuivant() {
		return moisSuivant;
	}
	
	/**
	 * <b><i>combinerJours</i></b><br />
	 * <pre>public void combinerJours({@link java.util.List List}<{@link com.loterie.business.JourHTML JourHTML}> jours)</pre>
	 * Lie les jours récupérés de la BDD aux objets métier à afficher.
	 * 
	 * @param jours - entités récupérées de la BDD
	 */
	public void combinerJours(List<Jour> jours) {
		for (Jour jour : jours) {
			// Le jour venant de la BDD est lié à son objet métier par sa date
			JourHTML jourHTML = trouverJourParDate(jour.getDateJour()); 
			Grille grille = jour.getLgu().getGrille();
			grille.setPaye(jour.isPaye());
			// La grille du jour est ajoutée à l'objet métier pour affichage
			jourHTML.addGrille(grille);
		}
	}
}
