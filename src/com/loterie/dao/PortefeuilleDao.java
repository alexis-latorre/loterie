package com.loterie.dao;

import javax.ejb.Stateful;
import com.loterie.entities.Portefeuille;

@Stateful
public class PortefeuilleDao extends LoterieDao {
	
	public void creer(Portefeuille portefeuille) {
		super.creer(portefeuille);
	}

	public void maj(Portefeuille portefeuille) {
		super.maj(portefeuille);
	}
}
