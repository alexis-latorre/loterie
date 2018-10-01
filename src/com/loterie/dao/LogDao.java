package com.loterie.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import com.loterie.config.Constants;
import com.loterie.entities.Log;

@Stateful
public class LogDao extends LoterieDao {
	private Map<String, Object> params = new HashMap<String, Object>();

	/**
	 * <b><i>creer</i></b><br />
	 * <pre>public void creer({@link com.loterie.entities.Log Log} log)</pre>
	 * Crée une nouvelle entité log et la stocke en BDD 
	 * 
	 * @param log - entité à créer en BDD
	 */
	public void creer(Log log) {
		super.creer(log);
	}

	/**
	 * <b><i>trouverParDate</i></b><br />
	 * <pre>public {@link com.loterie.entities.Log Log} trouverParDate({@link java.lang.String String} dateLog)</pre>
	 * Retourne le log qui correspond à la date renseignée
	 * 
	 * @param dateLog - date du log à récupérer en BDD
	 * 
	 * @return le log récupéré en BDD
	 */
	public Log trouverParDate(String dateLog) {
		params.clear();
		params.put("dateLog", dateLog);
		return (Log) super.resultat(Constants.SELECT_JOUR_PAR_DATE, params, "trouverParDate");
	}

	public List<Log> trouver() {
		return (List<Log>) super.resultats(Constants.SELECT_LOG, null, null);
	}
}
