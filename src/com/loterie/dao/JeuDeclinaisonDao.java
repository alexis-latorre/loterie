package com.loterie.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.loterie.config.Constants;
import com.loterie.entities.Jeu;
import com.loterie.entities.JeuDeclinaison;

@Stateless
public class JeuDeclinaisonDao extends LoterieDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, Object> params = new HashMap<String, Object>();
	/**
	 * <b><i>trouverParNom</i></b><br />
	 * <pre>public {@link java.util.List List}<{@link com.loterie.entities.JeuDeclinaison JeuDeclinaison}> trouverParJeu({@link com.loterie.entities.Jeu Jeu} jeu)</pre>
	 * Retourne le JeuDeclinaison correspondant au jeu renseigné
	 * 
	 * @param nom - nom du jeuDeclinaison à trouver en BDD
	 * 
	 * @return le jeuDeclinaison récupéré en BDD
	 */
	public List<JeuDeclinaison> trouverParJeu(Jeu jeu) {
		params.clear();
		params.put("jeu", jeu);		
		return (List<JeuDeclinaison>) super.resultats(Constants.SELECT_JEU_DECLINAISONS_PAR_JEU, params, "trouverParJeu");
	}
}
