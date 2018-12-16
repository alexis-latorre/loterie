package com.loterie.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.loterie.config.Messages;
import com.loterie.dao.PortefeuilleDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Portefeuille;
import com.loterie.entities.Utilisateur;

public class UtilisateurCreditForm {
	private UtilisateurDao utilisateurDao;
	private Utilisateur utilisateur;
	private PortefeuilleDao portefeuilleDao;
	private Portefeuille portefeuille;
	private Double fonds;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private HttpServletRequest req;

	public UtilisateurCreditForm(UtilisateurDao utilisateurDao, PortefeuilleDao portefeuilleDao, 
			HttpServletRequest req) {
		this.utilisateurDao = utilisateurDao;
		this.portefeuilleDao = portefeuilleDao;
		this.req = req;
		utilisateur = validerUtilisateur(req.getAttribute("joueur"));
		portefeuille = utilisateur.getPortefeuille();
		fonds = validerFonds(req.getParameter("fonds"));
		majErreurs();
	}

	private void majErreurs() {
		req.setAttribute("erreurs", erreurs);
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	private Utilisateur validerUtilisateur(Object id) {
		try {
			return utilisateurDao.trouverParId(Long.parseLong((String)id));
		} catch (Exception e) {
			erreurs.put("joueur", Messages.MSG_UTILISATEUR_INTROUVABLE);
			return null;
		}
	}

	private Double validerFonds(String fonds) {
		try {
			return Double.parseDouble(fonds);
		} catch (Exception e) {
			erreurs.put("fonds", Messages.MSG_CREDIT_INVALIDE);
			return 0D;
		}
	}

	public Map<String, Object> crediter() {
		String action = "crédité";
		portefeuille.ajouterFonds(fonds);
		portefeuilleDao.maj(portefeuille);
		utilisateur.setPortefeuille(portefeuille);
		utilisateurDao.maj(utilisateur);
		majErreurs();
		Map<String, Object> retour = new HashMap<String, Object>();
		
		if (fonds < 0D) {
			action = "débité";
			fonds *= -1;
		}
		retour.put("action", action);
		retour.put("fonds", fonds);
		retour.put("joueur", utilisateur);
		return retour;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
}
