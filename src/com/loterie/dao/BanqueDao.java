package com.loterie.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.loterie.entities.Banque;

@Stateful
public class BanqueDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	
	public void creerBanque(Banque banque) {
		em.persist(banque);
	}

	public void maj(Banque banque) {
		em.merge(banque);
	}
}
