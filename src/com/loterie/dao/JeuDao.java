package com.loterie.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;
import com.loterie.config.Constants;
import com.loterie.entities.Jeu;

@Stateful
public class JeuDao extends LoterieDao {
	private Map<String, Object> params = new HashMap<String, Object>();
	private static JeuDao INSTANCE = new JeuDao();

	private JeuDao() {}

	public static JeuDao getInstance() {
		return INSTANCE;
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
		return (Jeu) super.resultat(Constants.SELECT_JEU_PAR_NOM, params, "trouverParNom");
	}
}
