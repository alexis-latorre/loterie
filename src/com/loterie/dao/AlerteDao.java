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

@Stateless
public class AlerteDao extends LoterieDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, Object> params = new HashMap<String, Object>();

	public List<Alerte> trouverTout() {
		params.clear();	
		return (List<Alerte>) super.resultats(Constants.SELECT_ALERTES, params, "trouverTout");
	}
	
	public Alerte trouverParUtilisateur(Utilisateur utilisateur) {
		params.clear();
		params.put("utilisateur", utilisateur);	
		return (Alerte) super.resultat(Constants.SELECT_ALERTES_PAR_UTILISATEUR, params, "trouverParUtilisateur");
	}
	
	public Alerte trouverParGrille(Grille grille) {
		params.clear();
		params.put("grille", grille);	
		return (Alerte) super.resultat(Constants.SELECT_ALERTES_PAR_GRILLE, params, "trouverParGrille");
	}
	
	public Alerte trouverParLGU(LienGrilleUtilisateur lgu) {
		params.clear();
		params.put("lgu", lgu);	
		return (Alerte) super.resultat(Constants.SELECT_ALERTES_PAR_LGU, params, "trouverParLGU");
	}
}
