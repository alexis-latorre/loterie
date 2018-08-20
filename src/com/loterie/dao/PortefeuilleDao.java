package com.loterie.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.loterie.entities.Portefeuille;

@Stateful
public class PortefeuilleDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	
	public void creerPortefeuille(Portefeuille portefeuille) {
		em.persist(portefeuille);
	}

	public void maj(Portefeuille portefeuille) {
		em.merge(portefeuille);
	}
}
