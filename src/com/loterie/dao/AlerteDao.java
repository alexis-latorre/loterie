package com.loterie.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.loterie.config.Constants;
import com.loterie.entities.Alerte;
import com.loterie.entities.Grille;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.DevTools;

@Stateless
public class AlerteDao extends LoterieDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, Object> params = new HashMap<String, Object>();

	public List<Alerte> trouverTout() {
		DevTools.clearCache(this);
		params.clear();	
		return (List<Alerte>) super.resultats(Constants.SELECT_ALERTES, params, "trouverTout");
	}
	
	public List<Alerte> trouverParUtilisateur(Utilisateur utilisateur) {
		DevTools.clearCache(this);
		params.clear();
		params.put("utilisateur", utilisateur);	
		return (List<Alerte>) super.resultats(Constants.SELECT_ALERTES_PAR_UTILISATEUR, params, "trouverParUtilisateur");
	}
	
	public List<Alerte> trouverParGrille(Grille grille) {
		DevTools.clearCache(this);
		params.clear();
		params.put("grille", grille);	
		return (List<Alerte>) super.resultats(Constants.SELECT_ALERTES_PAR_GRILLE, params, "trouverParGrille");
	}
	
	public List<Alerte> trouverParLGU(LienGrilleUtilisateur lgu) {
		DevTools.clearCache(this);
		params.clear();
		params.put("lgu", lgu);	
		return (List<Alerte>) super.resultats(Constants.SELECT_ALERTES_PAR_LGU, params, "trouverParLGU");
	}
	
	public void clearCache() {
		super.clearCache();
	}
}
