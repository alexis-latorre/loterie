package com.loterie.dao;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.loterie.entities.Privilege;

@Stateful
public class PrivilegeDao extends LoterieDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	
	/**
	 * <b><i>creerPrivilege</i></b><br />
	 * <pre>public void creerPrivilege({@link com.loterie.entities.Privilege Privilege} privilege)</pre>
	 * Crée une nouvelle entité privilege et la stocke en BDD 
	 * 
	 * @param privilege - entité à créer en BDD
	 */
	public void creer(Privilege privilege) {
		super.creer(privilege);
	}
	
	/**
	 * <b><i>maj</i></b><br />
	 * <pre>public void maj({@link com.loterie.entities.Privilege Privilege} privilege)</pre>
	 * Met à jour l'entité privilege en BDD 
	 * 
	 * @param privilege - entité à mettre à jour
	 */
	public void maj(Privilege privilege) {
		super.maj(privilege);
	}
}
