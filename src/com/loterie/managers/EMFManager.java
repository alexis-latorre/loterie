package com.loterie.managers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFManager {
	private static EntityManagerFactory INSTANCE = Persistence.createEntityManagerFactory("puLoterie");
	
	private EMFManager() {}
	
	public static EntityManagerFactory getInstance() {
		return INSTANCE;
	}
}
