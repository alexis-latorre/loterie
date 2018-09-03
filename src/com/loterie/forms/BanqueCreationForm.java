package com.loterie.forms;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.loterie.dao.BanqueDao;
import com.loterie.entities.Banque;

public class BanqueCreationForm {
	
	private Map<String, String> erreurs;
	private BanqueDao banqueDao;
	private Banque banque;
	
	
	public BanqueCreationForm(BanqueDao banqueDao) {
		this.banqueDao = banqueDao;
		erreurs = new HashMap<>();
		banque = new Banque();
		banque.setDateCreation(new DateTime());
		banque.setFonds(0D);
		banque.setMises(0D);
		banque.setGains(0D);
		
		this.banqueDao.creer(banque);
	}
	
	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}
}
