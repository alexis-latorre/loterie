package com.loterie.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;

import com.loterie.config.Constants;
import com.loterie.entities.Resultat;

@Stateful
public class ResultatDao extends LoterieDao {
	private Map<String, Object> params = new HashMap<String, Object>();

	/**
	 * <b><i>creer</i></b><br />
	 * <pre>public void creer({@link com.loterie.entities.Resultat Resultat} resultat)</pre>
	 * Crée une nouvelle entité resultat et la stocke en BDD 
	 * 
	 * @param resultat - entité à créer en BDD
	 */
	public void creer(Resultat resultat) {
		super.creer(resultat);
	}
	
	public Resultat trouverDernier() {
		params.clear();
		return (Resultat) super.resultats(Constants.SELECT_DERNIER_RESULTAT, params, "trouverDernier").get(0);
	}
	
	public void clearCache() {
		super.clearCache();
	}
}
