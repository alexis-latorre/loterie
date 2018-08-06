package com.loterie.dao;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.loterie.entities.LienGrilleUtilisateur;

@Stateful
public class LienGUDao {
	
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public void enregistrerLienGrilleUtilisateur(LienGrilleUtilisateur lienGU) {
		em.persist(lienGU);
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}
}
