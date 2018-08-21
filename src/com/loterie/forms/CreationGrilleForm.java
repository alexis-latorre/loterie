package com.loterie.forms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.loterie.config.Constants;
import com.loterie.dao.BanqueDao;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.JeuDao;
import com.loterie.entities.Banque;
import com.loterie.entities.Grille;
import com.loterie.entities.Jeu;
import com.loterie.entities.Utilisateur;

public class CreationGrilleForm {
	
	private Map<String, String> erreurs;
	private GrilleDao grilleDao;
	private JeuDao jeuDao;
	private BanqueDao banqueDao;
	private Utilisateur utilisateur;
	private Jeu jeu;
	private Banque banque;
	
	
	public CreationGrilleForm(GrilleDao grilleDao, JeuDao jeuDao, BanqueDao banqueDao, HttpServletRequest req) {
		this.grilleDao = grilleDao;
		this.jeuDao = jeuDao;
		this.banqueDao = banqueDao;
		erreurs = new HashMap<>();
		HttpSession session = req.getSession();
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		jeu = this.jeuDao.trouverParNom(Constants.EUROMILLIONS_NOM);
		CreationBanqueForm cbf = new CreationBanqueForm(this.banqueDao);
		banque = cbf.getBanque();
		
		List<String> numeros = new ArrayList<>();
		List<String> etoiles = new ArrayList<>();
		
		if (req.getParameterValues("numeros[]") != null) {
			numeros = Arrays.asList(req.getParameterValues("numeros[]"));
		}
		
		if (req.getParameterValues("etoiles[]") != null) {
			etoiles = Arrays.asList(req.getParameterValues("etoiles[]"));
		}
		boolean etoilePlus = req.getParameter("etoilePlus") != null && req.getParameter("etoilePlus").equals("on");
		Grille grille = new Grille();

		if (numeros.size() >= Constants.EUROMILLIONS_NUMEROS_SELECTION_MIN 
				&& numeros.size() <= Constants.EUROMILLIONS_NUMEROS_SELECTION_MAX
				&& etoiles.size() >= Constants.EUROMILLIONS_ETOILES_SELECTION_MIN
				&& etoiles.size() <= Constants.EUROMILLIONS_ETOILES_SELECTION_MAX) {
			grille.setNumeros(numeros);
			grille.setEtoiles(etoiles);
			grille.setEtoilePlus(etoilePlus);
			grille.setMyMillion(null);
			grille.setJeu(jeu);
			grille.setUtilisateur(utilisateur);
			grille.setBanque(banque);
			this.grilleDao.enregistrerGrille(grille);
		}
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}
}
