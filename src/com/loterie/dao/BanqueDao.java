package com.loterie.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.loterie.entities.Banque;

@Stateful
public class BanqueDao extends LoterieDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	
	/**
	 * <b><i>creerBanque</i></b><br />
	 * <pre>public void creerBanque({@link com.loterie.entities.Banque Banque} banque)</pre>
	 * Crée une nouvelle entité banque et la stocke en BDD 
	 * 
	 * @param banque - entité à créer en BDD
	 */
	public void creer(Banque banque) {
		super.creer(banque);
	}
	
	/**
	 * <b><i>maj</i></b><br />
	 * <pre>public void maj({@link com.loterie.entities.Banque Banque} banque)</pre>
	 * Met à jour l'entité banque en BDD 
	 * 
	 * @param banque - entité à mettre à jour
	 */
	public void maj(Banque banque) {
		super.maj(banque);
	}
}
