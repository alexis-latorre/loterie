package com.loterie.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.loterie.config.Constants;
import com.loterie.entities.Jeu;
import com.loterie.tools.Tools;

@Stateless
public class JeuDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, Object> params = new HashMap<String, Object>();
	
	/**
	 * <b><i>resultat</i></b><br />
	 * <pre>public {@link com.loterie.entities.Jeu Jeu} resultat({@link java.lang.String String} reqStr, {@link java.util.Map Map}<{@link java.lang.String String}, {@link java.lang.Object Object}> params, {@link java.lang.String String} source)</pre>
	 * Retourne un résultat unique de la requête passé en paramètre pour ses options renseignées 
	 * 
	 * @param reqStr - requête SQL à exécuter
	 * @param params - paramètres à attacher à la requête
	 * @param source - nom de la méthode appelant la requête
	 * 
	 * @return le résultat de la requête
	 */
	private Jeu resultat(String reqStr, Map<String, Object> params, String source) {
		return (Jeu) Tools.executerRequete(reqStr, params, em, false, this.getClass().getName() + "." + source);
	}
	
	/**
	 * <b><i>trouverParNom</i></b><br />
	 * <pre>public {@link com.loterie.entities.Jeu Jeu} trouverParNom({@link java.lang.String String} nom)</pre>
	 * Retourne le Jeu correspondant au nom renseigné
	 * 
	 * @param nom - nom du jeu à trouver en BDD
	 * 
	 * @return le jeu récupéré en BDD
	 */
	public Jeu trouverParNom(String nom) {
		params.clear();
		params.put("nom", nom);
		
		return resultat(Constants.SELECT_JEU_PAR_NOM, params, "trouverParNom");
	}
}
