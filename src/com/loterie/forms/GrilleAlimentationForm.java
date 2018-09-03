package com.loterie.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.loterie.dao.BanqueDao;
import com.loterie.dao.PortefeuilleDao;
import com.loterie.entities.Banque;
import com.loterie.entities.Grille;
import com.loterie.entities.Portefeuille;
import com.loterie.entities.Utilisateur;

public class GrilleAlimentationForm {
	private Map<String, String> erreurs = new HashMap<String, String>();
	private PortefeuilleDao portefeuilleDao;
	private BanqueDao banqueDao;
	private HttpServletRequest req;
	private HttpSession session;

	public GrilleAlimentationForm(PortefeuilleDao portefeuilleDao, BanqueDao banqueDao, HttpServletRequest req) {
		session = req.getSession();
		this.portefeuilleDao = portefeuilleDao;
		this.banqueDao = banqueDao;
		this.req = req;
		session = this.req.getSession();
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void modifier() {
		String fonds = req.getParameter("fonds");
		Grille grille = (Grille) session.getAttribute("grille");
		Banque banque = grille.getBanque();
		Portefeuille portefeuille = ((Utilisateur) session.getAttribute("utilisateur")).getPortefeuille();
		
		if (grille != null && fonds != null && !fonds.isEmpty()) {
			Double montant = Double.valueOf(fonds);
			
			if (portefeuille.getFonds() >= montant) { 
				portefeuille.retirerFonds(montant);
				portefeuilleDao.maj(portefeuille);
				
				banque.ajouterFonds(montant);					
				banqueDao.maj(banque);
			}
		}
	}
}
