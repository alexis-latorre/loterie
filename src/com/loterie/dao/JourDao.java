package com.loterie.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import com.loterie.config.Constants;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Grille;
import com.loterie.entities.Jour;
import com.loterie.entities.Utilisateur;

@Stateful
public class JourDao extends LoterieDao {
	private Map<String, Object> params = new HashMap<String, Object>();
	
	public void creer(Jour jour) {
		super.creer(jour);
	}

	public Jour trouverParDate(String dateJour) {
		params.clear();
		params.put("dateJour", dateJour);
		return (Jour) super.resultat(Constants.SELECT_JOUR_PAR_DATE, params, "trouverParDate");
	}

	public Jour trouverDernierJourJoue(Grille grille) {
		params.clear();
		params.put("grille", grille);
		return (Jour) super.resultat(Constants.SELECT_JOUR_DERNIER_JOUE_PAR_GRILLE, params, "trouverDernierJourJoue");
	}
	
	public List<Jour> trouverParLGU(LienGrilleUtilisateur lgu) {
		params.clear();
		params.put("lgu", lgu);
		return (List<Jour>) super.resultats(Constants.SELECT_JOUR_PAR_LGU, params, "trouverParLGU");
	}

	public List<Jour> trouverParJourEtUtilisateur(String dateJour, Utilisateur utilisateur) {
		params.clear();
		params.put("dateJour", dateJour);
		params.put("utilisateur", utilisateur);
		return (List<Jour>) super.resultats(Constants.SELECT_JOUR_PAR_DATE_ET_UTILISATEUR, params, 
				"trouverParJourEtUtilisateur");
	}

	public List<Jour> trouverParIntervalleEtUtilisateur(String premiereDate, String derniereDate, 
			Utilisateur utilisateur) {
		params.clear();
		params.put("date_debut", premiereDate);
		params.put("date_fin", derniereDate);
		params.put("utilisateur", utilisateur);
		return (List<Jour>) super.resultats(Constants.SELECT_JOUR_PAR_INTERVALLE_ET_UTILISATEUR, params, 
				"trouverParIntervalleEtUtilisateur");
	}
}
