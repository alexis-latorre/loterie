package com.loterie.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;

import com.loterie.config.Constants;
import com.loterie.entities.Gain;

@Stateful
public class GainDao extends LoterieDao {
	private Map<String, Object> params = new HashMap<String, Object>();

	public List<Gain> trouverParDateEtUtilisateur(String date, Long id) {
		params.clear();
		params.put("date", date);
		params.put("id", id);
		return (List<Gain>) super.resultats(Constants.SELECT_GAINS_PAR_JOUR_ET_UTILISATEUR, params, 
				"trouverParDateEtUtilisateur");
	}

	public List<Gain> trouverParPeriodeEtUtilisateur(String dateDebut, String dateFin, Long id) {
		params.clear();
		params.put("date_debut", dateDebut);
		params.put("date_fin", dateFin);
		params.put("id", id);
		return (List<Gain>) super.resultats(Constants.SELECT_GAINS_PAR_PERIODE_ET_UTILISATEUR, params, 
				"trouverParPeriodeEtUtilisateur");
	}
	
	public void clearCache() {
		super.clearCache();
	}
}
