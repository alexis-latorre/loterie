package com.loterie.forms;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.loterie.dao.BanqueDao;
import com.loterie.entities.Banque;

public class BanqueCreationForm {
	
	private Map<String, String> erreurs = new HashMap<String, String>();
	private BanqueDao banqueDao;
	private Banque banque;
	
	/**
	 * <b><i>BanqueCreationForm</i></b><br />
	 * <pre>public BanqueCreationForm({@link com.loterie.dao.BanqueDao BanqueDao} banqueDao)</pre>
	 * Initialise une nouvelle banque
	 * 
	 * @param banqueDao - lien vers la Dao Banque
	 */
	public BanqueCreationForm(BanqueDao banqueDao) {
		this.banqueDao = banqueDao;
		banque = new Banque();
		banque.setDateCreation(new DateTime());
		banque.setFonds(0D);
		banque.setMises(0D);
		banque.setGains(0D);
		
		this.banqueDao.creer(banque);
	}
	
	/**
	 * <b><i>getBanque</i></b><br />
	 * <pre>public {@link com.loterie.entities.Banque Banque} getBanque()</pre>
	 * récupère l'entité Banque liée au formulaire
	 * 
	 * @return l'entité liée
	 */
	public Banque getBanque() {
		return banque;
	}

	/**
	 * <b><i>getErreurs</i></b><br />
	 * <pre>public {@link java.util.Map Map}<{@link java.lang.String String}, {@link java.lang.String String}> getErreurs()</pre>
	 * récupère les erreurs du formulaire
	 * 
	 * @return la map d'erreurs
	 */
	public Map<String, String> getErreurs() {
		return erreurs;
	}
}
