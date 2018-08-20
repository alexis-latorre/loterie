package com.loterie.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import com.loterie.config.Messages;
import com.loterie.dao.PortefeuilleDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Portefeuille;
import com.loterie.entities.Utilisateur;

public class CreationPortefeuilleForm {
	private PortefeuilleDao portefeuilleDao;
	private UtilisateurDao utilisateurDao;
	private Utilisateur utilisateur;
	private Portefeuille portefeuille;
	private Map<String, String> erreurs;
	
	public CreationPortefeuilleForm(PortefeuilleDao portefeuilleDao, UtilisateurDao utilisateurDao, HttpServletRequest req) {
		this.portefeuilleDao = portefeuilleDao;
		this.utilisateurDao = utilisateurDao;
		HttpSession session = req.getSession();
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		portefeuille = utilisateur.getPortefeuille();
		erreurs = new HashMap<>();
		
		if (portefeuille == null) {
			portefeuille = new Portefeuille();
			portefeuille.setDateCreation(new DateTime());
			portefeuille.setFonds(0D);
			this.portefeuilleDao.creerPortefeuille(portefeuille);
			utilisateur.setPortefeuille(portefeuille);
			this.utilisateurDao.maj(utilisateur);
		}
	}
	
	public void ajouterFonds(String strFonds) {
		Double fonds = 0D;
		try {
			fonds = Double.valueOf(strFonds);
		} catch (NumberFormatException e) {
			erreurs.put("fonds", Messages.MSG_FONDS_INVALIDES);
		}
		portefeuille.ajouterFonds(fonds);
		portefeuilleDao.maj(portefeuille);
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}
}
