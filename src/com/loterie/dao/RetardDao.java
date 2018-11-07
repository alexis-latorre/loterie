package com.loterie.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.loterie.config.Constants;
import com.loterie.entities.Retard;
import com.loterie.entities.Utilisateur;

@Stateless
public class RetardDao extends LoterieDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, Object> params = new HashMap<String, Object>();
	/**
	 * <b><i>trouverParNom</i></b><br />
	 * <pre>public {@link com.loterie.entities.Retard Retard} trouverParNom({@link java.lang.String String} nom)</pre>
	 * Retourne le Retard correspondant au nom renseigné
	 * 
	 * @param nom - nom du retard à trouver en BDD
	 * 
	 * @return le retard récupéré en BDD
	 */
	public List<Retard> trouverTout() {
		params.clear();	
		return (List<Retard>) super.resultats(Constants.SELECT_RETARDS, params, "trouverTout");
	}
	
	public Retard trouverParUtilisateur(Utilisateur utilisateur) {
		params.clear();
		params.put("utilisateur", utilisateur);	
		return (Retard) super.resultat(Constants.SELECT_RETARD_PAR_UTILISATEUR, params, "trouverParUtilisateur");
	}
}
