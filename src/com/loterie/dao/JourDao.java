package com.loterie.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import com.loterie.config.Constants;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Grille;
import com.loterie.entities.Jour;
import com.loterie.entities.Utilisateur;

@Stateful
public class JourDao extends LoterieDao {
	private Map<String, Object> params = new HashMap<String, Object>();

	/**
	 * <b><i>creer</i></b><br />
	 * <pre>public void creer({@link com.loterie.entities.Jour Jour} jour)</pre>
	 * Crée une nouvelle entité jour et la stocke en BDD 
	 * 
	 * @param jour - entité à créer en BDD
	 */
	public void creer(Jour jour) {
		super.creer(jour);
	}

	/**
	 * <b><i>trouverParDate</i></b><br />
	 * <pre>public {@link com.loterie.entities.Jour Jour} trouverParDate({@link java.lang.String String} dateJour)</pre>
	 * Retourne le jour qui correspond à la date renseignée
	 * 
	 * @param dateJour - date du jour à récupérer en BDD
	 * 
	 * @return le jour récupéré en BDD
	 */
	public Jour trouverParDate(String dateJour) {
		params.clear();
		params.put("dateJour", dateJour);
		return (Jour) super.resultat(Constants.SELECT_JOUR_PAR_DATE, params, "trouverParDate");
	}

	/**
	 * <b><i>trouverDernierJourJoue</i></b><br />
	 * <pre>public {@link com.loterie.entities.Jour Jour} trouverDernierJourJoue({@link com.loterie.entities.Grille Grille} grille)</pre>
	 * Retourne le jour qui correspond au dernier jour où la grille renseignée a été jouée
	 * 
	 * @param grille - grille dont il faut récupérer la date en BDD
	 * 
	 * @return le jour récupéré en BDD
	 */
	public Jour trouverDernierJourJoue(Grille grille) {
		params.clear();
		params.put("grille", grille);
		return (Jour) super.resultat(Constants.SELECT_JOUR_DERNIER_JOUE_PAR_GRILLE, params, "trouverDernierJourJoue");
	}
	
	/**
	 * <b><i>trouverParLGU</i></b><br />
	 * <pre>public {@link java.util.List List}<{@link com.loterie.entities.Jour Jour}> trouverParLGU({@link com.loterie.entities.LienGrilleUtilisateur LienGrilleUtilisateur} lgu)</pre>
	 * Retourne le jour qui correspond au LienGrilleUtilisateur renseigné
	 * 
	 * @param lgu - lien dont il faut récupérer le jour en BDD
	 * 
	 * @return le jour récupéré en BDD
	 */
	public List<Jour> trouverParLGU(LienGrilleUtilisateur lgu) {
		params.clear();
		params.put("lgu", lgu);
		return (List<Jour>) super.resultats(Constants.SELECT_JOUR_PAR_LGU, params, "trouverParLGU");
	}
	
	/**
	 * <b><i>trouverParJourEtUtilisateur</i></b><br />
	 * <pre>public {@link java.util.List List}<{@link com.loterie.entities.Jour Jour}> trouverParJourEtUtilisateur({@link java.lang.String String} dateJour, {@link com.loterie.entities.Utilisateur Utilisateur} utilisateur)</pre>
	 * Retourne le jour qui correspond à la date et à l'utilisateur renseignés
	 * 
	 * @param dateJour - date du jour à récupérer en BDD
	 * @param utilisateur - utilisateur dont il faut récupérer le jour en BDD
	 * 
	 * @return le jour récupéré en BDD
	 */
	public List<Jour> trouverParJourEtUtilisateur(String dateJour, Utilisateur utilisateur) {
		params.clear();
		params.put("dateJour", dateJour);
		params.put("utilisateur", utilisateur);
		return (List<Jour>) super.resultats(Constants.SELECT_JOUR_PAR_DATE_ET_UTILISATEUR, params, 
				"trouverParJourEtUtilisateur");
	}
	
	/**
	 * <b><i>trouverParIntervalleEtUtilisateur</i></b><br />
	 * <pre>public {@link java.util.List List}<{@link com.loterie.entities.Jour Jour}> trouverParIntervalleEtUtilisateur({@link java.lang.String String} premiereDate, {@link java.lang.String String} derniereDate, {@link com.loterie.entities.Utilisateur Utilisateur} utilisateur)</pre>
	 * Retourne le jour qui correspond à la date et à l'utilisateur renseignés
	 * 
	 * @param premiereDate - date de début du jour à récupérer en BDD
	 * @param derniereDate - date de fin du jour à récupérer en BDD
	 * @param utilisateur - utilisateur dont il faut récupérer le jour en BDD
	 * 
	 * @return le jour récupéré en BDD
	 */
	public List<Jour> trouverParIntervalleEtUtilisateur(String premiereDate, String derniereDate, 
			Utilisateur utilisateur) {
		params.clear();
		params.put("date_debut", premiereDate);
		params.put("date_fin", derniereDate);
		params.put("utilisateur", utilisateur);
		return (List<Jour>) super.resultats(Constants.SELECT_JOUR_PAR_INTERVALLE_ET_UTILISATEUR, params, 
				"trouverParIntervalleEtUtilisateur");
	}
	
	public void clearCache() {
		super.clearCache();
	}
}
