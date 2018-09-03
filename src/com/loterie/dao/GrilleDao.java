package com.loterie.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.loterie.config.Constants;
import com.loterie.entities.Grille;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

@Stateful
public class GrilleDao {
	
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, Object> params = new HashMap<String, Object>();
	
	/**
	 * <b><i>creer</i></b><br />
	 * <pre>public void creer({@link com.loterie.entities.Grille Grille} grille)</pre>
	 * Crée une nouvelle entité grille et la stocke en BDD 
	 * 
	 * @param grille - entité à créer en BDD
	 */
	public void creer(Grille grille) {
		em.persist(grille);
	}
	
	/**
	 * <b><i>maj</i></b><br />
	 * <pre>public void maj({@link com.loterie.entities.Grille Grille} grille)</pre>
	 * Met à jour l'entité grille en BDD 
	 * 
	 * @param grille - entité à mettre à jour
	 */
	public void maj(Grille grille) {
		em.merge(grille);
	}
	
	/**
	 * <b><i>supprimer</i></b><br />
	 * <pre>public void supprimer({@link com.loterie.entities.Grille Grille} grille)</pre>
	 * Supprime l'entité grille de l'application et de la BDD 
	 * 
	 * @param grille - entité à supprimer
	 */
	public void supprimer(Grille grille) {
		// Si l'Entity Manager ne pilote pas la grille, elle y est ajoutée avant
		if (!em.contains(grille)) {
			grille = em.merge(grille);
		}
		em.remove(grille);
	}
	
	/**
	 * <b><i>resultat</i></b><br />
	 * <pre>public {@link com.loterie.entities.Grille Grille} resultat({@link java.lang.String String} reqStr, {@link java.util.Map Map}<{@link java.lang.String String}, {@link java.lang.Object Object}> params, {@link java.lang.String String} source)</pre>
	 * Retourne un résultat unique de la requête passé en paramètre pour ses options renseignées 
	 * 
	 * @param reqStr - requête SQL à exécuter
	 * @param params - paramètres à attacher à la requête
	 * @param source - nom de la méthode appelant la requête
	 * 
	 * @return le résultat de la requête
	 */
	private Grille resultat(String reqStr, Map<String, Object> params, String source) {
		return (Grille) Tools.executerRequete(reqStr, params, em, false, this.getClass().getName() + "." + source);
	}
	
	/**
	 * <b><i>resultats</i></b><br />
	 * <pre>public {@link java.util.List List}<{@link com.loterie.entities.Grille Grille}> resultats({@link java.lang.String String} reqStr, {@link java.util.Map Map}<{@link java.lang.String String}, {@link java.lang.Object Object}> params, {@link java.lang.String String} source)</pre>
	 * Retourne la liste des résultats de la requête passé en paramètre pour ses options renseignées 
	 * 
	 * @param reqStr - requête SQL à exécuter
	 * @param params - paramètres à attacher à la requête
	 * @param source - nom de la méthode appelant la requête
	 * 
	 * @return la liste des résultats de la requête
	 */
	private List<Grille> resultats(String reqStr, Map<String, Object> params, String source) {		
		return (List<Grille>) Tools.executerRequete(reqStr, params, em, true, this.getClass().getName() + "." + source);
	}
	
	/**
	 * <b><i>trouverParId</i></b><br />
	 * <pre>public void trouverParCreateur({@link java.lang.Long Long} id)</pre>
	 * Retourne la grille Qui correspond à l'id renseigné
	 * 
	 * @param id - id de la grille à récupérer en BDD
	 * 
	 * @return la grille récupérée en BDD
	 */
	public Grille trouverParId(Long id) {
		params.clear();
		params.put("id", id);
		
		return resultat(Constants.SELECT_GRILLE_PAR_ID, params, "trouverParId");
	}
	
	/**
	 * <b><i>trouverParCreateur</i></b><br />
	 * <pre>public void trouverParCreateur({@link com.loterie.entities.Utilisateur Utilisateur} utilisateur)</pre>
	 * Retourne les grilles créées par l'utilisateur renseigné
	 * 
	 * @param utilisateur - l'utilisateur dont il faut trouver les grilles en BDD
	 * 
	 * @return les grilles récupérées en BDD
	 */
	public List<Grille> trouverParCreateur(Utilisateur utilisateur) {
		params.clear();
		params.put("utilisateur", utilisateur);
		
		return resultats(Constants.SELECT_GRILLES_PAR_CREATEUR, params, "trouverParCreateur");
	}
	
	/**
	 * <b><i>trouverParUtilisateur</i></b><br />
	 * <pre>public void trouverParUtilisateur({@link com.loterie.entities.Utilisateur Utilisateur} utilisateur)</pre>
	 * Retourne les grilles jouées par l'utilisateur renseigné
	 * 
	 * @param utilisateur - l'utilisateur dont il faut trouver les grilles en BDD
	 * 
	 * @return les grilles récupérées en BDD
	 */
	public List<Grille> trouverParUtilisateur(Utilisateur utilisateur) {
		params.clear();
		params.put("utilisateur", utilisateur);
		
		return resultats(Constants.SELECT_GRILLES_PAR_UTILISATEUR, params, "trouverParUtilisateur");
	}
	
	/**
	 * <b><i>trouverParJourEtUtilisateur</i></b><br />
	 * <pre>public void trouverParJourEtUtilisateur({@link java.lang.String String} date_jour, {@link com.loterie.entities.Utilisateur Utilisateur} utilisateur)</pre>
	 * Retourne les grilles jouées par la date et l'utilisateur renseignés
	 * 
	 * @param date_jour - date du jour dont il faut trouver les grilles en BDD
	 * @param utilisateur - l'utilisateur dont il faut trouver les grilles en BDD
	 * 
	 * @return les grilles récupérées en BDD
	 */
	public List<Grille> trouverParJourEtUtilisateur(String date_jour, Utilisateur utilisateur) {
		params.clear();
		params.put("utilisateur", utilisateur);
		params.put("date_jour", date_jour);
		
		return resultats(Constants.SELECT_GRILLES_PAR_DATE_ET_UTILISATEUR, params, "trouverParJourEtUtilisateur");
	}
	
	/**
	 * <b><i>trouverParIntervalleEtUtilisateur</i></b><br />
	 * <pre>public void trouverParIntervalleEtUtilisateur({@link java.lang.String String} date_debut, {@link java.lang.String String} date_fin, {@link com.loterie.entities.Utilisateur Utilisateur} utilisateur)</pre>
	 * Retourne les grilles jouées dans l'intervalle et l'utilisateur renseignés
	 * 
	 * @param date_debut - date de début dont il faut trouver les grilles en BDD
	 * @param date_fin - date de fin dont il faut trouver les grilles en BDD
	 * @param utilisateur - l'utilisateur dont il faut trouver les grilles en BDD
	 * 
	 * @return les grilles récupérées en BDD
	 */
	public List<Grille> trouverParIntervalleEtUtilisateur(String date_debut, String date_fin, Utilisateur utilisateur) {
		params.clear();
		params.put("utilisateur", utilisateur);
		params.put("date_debut", date_debut);
		params.put("date_fin", date_fin);
		
		return resultats(Constants.SELECT_GRILLES_PAR_INTERVALLE_ET_UTILISATEUR, params, "trouverParIntervalleEtUtilisateur");
	}
}
