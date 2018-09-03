package com.loterie.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.loterie.config.Constants;
import com.loterie.entities.Jeu;

@Stateless
public class JeuDao extends LoterieDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, Object> params = new HashMap<String, Object>();
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
		return (Jeu) super.resultat(Constants.SELECT_JEU_PAR_NOM, params, "trouverParNom");
	}
}
