package com.loterie.dao;

import javax.ejb.Stateful;
import com.loterie.entities.Portefeuille;

@Stateful
public class PortefeuilleDao extends LoterieDao {

	/**
	 * <b><i>creer</i></b><br />
	 * <pre>public void creer({@link com.loterie.entities.Portefeuille Portefeuille} portefeuille)</pre>
	 * Crée une nouvelle entité portefeuille et la stocke en BDD 
	 * 
	 * @param portefeuille - entité à créer en BDD
	 */
	public void creer(Portefeuille portefeuille) {
		super.creer(portefeuille);
	}

	/**
	 * <b><i>maj</i></b><br />
	 * <pre>public void maj({@link com.loterie.entities.Portefeuille Portefeuille} portefeuille)</pre>
	 * Met à jour l'entité portefeuille en BDD 
	 * 
	 * @param portefeuille - entité à mettre à jour
	 */
	public void maj(Portefeuille portefeuille) {
		super.maj(portefeuille);
	}
}
