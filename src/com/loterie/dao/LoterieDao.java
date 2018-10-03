package com.loterie.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.loterie.tools.Tools;

public class LoterieDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	
	/**
	 * <b><i>creer</i></b><br />
	 * <pre>public void creer({@link java.lang.Object Object} entite)</pre>
	 * Crée une nouvelle entité et la stocke en BDD 
	 * 
	 * @param entite - entité à créer en BDD
	 */
	protected void creer(Object entite) {
		em.persist(entite);
	}
	
	/**
	 * <b><i>maj</i></b><br />
	 * <pre>public void maj({@link java.lang.Object Object} entite)</pre>
	 * Met à jour l'entité en BDD 
	 * 
	 * @param entite - entité à mettre à jour
	 */
	protected void maj(Object entite) {
		em.merge(entite);
	}
	
	/**
	 * <b><i>supprimer</i></b><br />
	 * <pre>public void supprimer({@link java.lang.Object Object} entite)</pre>
	 * Supprime l'entité de l'application et de la BDD 
	 * 
	 * @param entite - entité à supprimer
	 */
	protected void supprimer(Object entite) {
		// Si l'Entity Manager ne pilote pas la entite, elle y est ajoutée avant
		if (!em.contains(entite)) {
			entite = em.merge(entite);
		}
		em.remove(entite);
	}
	
	/**
	 * <b><i>resultat</i></b><br />
	 * <pre>public {@link java.lang.Object Object} resultat({@link java.lang.String String} reqStr, {@link java.util.Map Map}<{@link java.lang.String String}, {@link java.lang.Object Object}> params, {@link java.lang.String String} source)</pre>
	 * Retourne un résultat unique de la requête passé en paramètre pour ses options renseignées 
	 * 
	 * @param reqStr - requête SQL à exécuter
	 * @param params - paramètres à attacher à la requête
	 * @param source - nom de la méthode appelant la requête
	 * 
	 * @return le résultat de la requête
	 */
	protected Object resultat(String reqStr, Map<String, Object> params, String source) {
		return Tools.executerRequete(reqStr, params, em, false, this.getClass().getName() + "." + source);
	}
	
	/**
	 * <b><i>resultats</i></b><br />
	 * <pre>public {@link java.util.List List}<{@link java.lang.Object Object}> resultats({@link java.lang.String String} reqStr, {@link java.util.Map Map}<{@link java.lang.String String}, {@link java.lang.Object Object}> params, {@link java.lang.String String} source)</pre>
	 * Retourne la liste des résultats de la requête passé en paramètre pour ses options renseignées 
	 * 
	 * @param reqStr - requête SQL à exécuter
	 * @param params - paramètres à attacher à la requête
	 * @param source - nom de la méthode appelant la requête
	 * 
	 * @return la liste des résultats de la requête
	 */
	protected List<?> resultats(String reqStr, Map<String, Object> params, String source) {		
		return (List<?>) Tools.executerRequete(reqStr, params, em, true, this.getClass().getName() + "." + source);
	}
	
	public void clearCache() {
		em.getEntityManagerFactory().getCache().evictAll();
	}
}
