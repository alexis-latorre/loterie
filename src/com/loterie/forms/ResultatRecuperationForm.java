package com.loterie.forms;

import java.util.Arrays;
import java.util.List;

import com.loterie.config.Constants;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.ResultatDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Jour;
import com.loterie.entities.Resultat;
import com.loterie.entities.Utilisateur;

public class ResultatRecuperationForm {
	private ResultatDao resultatDao;
	private JourDao jourDao;
	private LienGUDao lienGuDao;
	private Utilisateur utilisateur;
	
	public ResultatRecuperationForm(ResultatDao resultatDao, JourDao jourDao, LienGUDao lienGuDao, Utilisateur utilisateur) {
		this.resultatDao = resultatDao;
		this.jourDao = jourDao;
		this.lienGuDao = lienGuDao;
		this.utilisateur = utilisateur;
	}
	
	public Resultat dernierResultat() {
		Resultat dernier = resultatDao.trouverDernier();
		
		try {
			if (utilisateur != null) {
				String dateJeu = dernier.getDate().substring(6, 10) + "-" + dernier.getDate().substring(3, 5) + "-" + dernier.getDate().substring(0, 2) + " 12:00:00";
				
				for (Grille grille : utilisateur.getGrilles()) {
					Jour jour = jourDao.trouverParDateEtLGU(dateJeu, 
							lienGuDao.trouverParGrilleEtUtilisateur(grille, utilisateur));
					
					if (jour != null) {
						List<String> numeros = Arrays.asList(grille.getNumeros());
						List<String> etoiles = Arrays.asList(grille.getEtoiles());
						int nbNumeros = 0;
						int nbEtoiles = 0;

						if (numeros.contains(dernier.getB1())) {
							nbNumeros++;
						}
						if (numeros.contains(dernier.getB2())) {
							nbNumeros++;
						}
						if (numeros.contains(dernier.getB3())) {
							nbNumeros++;
						}
						if (numeros.contains(dernier.getB4())) {
							nbNumeros++;
						}
						if (numeros.contains(dernier.getB5())) {
							nbNumeros++;
						}
						if (etoiles.contains(dernier.getE1())) {
							nbEtoiles++;
						}
						if (etoiles.contains(dernier.getE2())) {
							nbEtoiles++;
						}
						
						String condGain = nbNumeros + ":" + nbEtoiles;
						
						if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG13)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang13()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG12)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang12()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG11)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang11()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG10)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang10()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG9)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang9()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG8)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang8()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG7)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang7()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG6)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang6()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG5)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang5()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG4)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang4()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG3)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang3()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG2)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang2()));
						} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG1)) {
							grille.setGains(Double.parseDouble(dernier.getGainsRang1()));
						} else {
							grille.setGains(0);
						}
						grille.setJouee(true);
					} else {
						grille.setJouee(false);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dernier;
	}
}
