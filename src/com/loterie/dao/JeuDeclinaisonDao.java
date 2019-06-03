package com.loterie.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public JeuDeclinaison trouverParIndex(Map<String, String> indexes) {
		params.clear();
		params.putAll(indexes);
		String where = "";
		String tmp = "";
		Iterator<Entry<String, String>> it = indexes.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<String, String> entrySet = (Map.Entry<String, String>) it.next();
			tmp += " (d." + entrySet.getKey() + " = :" + entrySet.getKey() + ") AND";
		}
		
		if (tmp.length() > 0) {
			where = " WHERE (" + tmp.substring(1, tmp.length() - 4) + ")";
		}
		System.out.println(Constants.SELECT_JEU_DECLINAISONS + where);
		return (JeuDeclinaison) super.resultat(Constants.SELECT_JEU_DECLINAISONS + where, params, "trouverParIndex");
	}
}
