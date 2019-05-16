package com.loterie.business;

import com.loterie.config.RolesConfig;
import com.loterie.entities.Alerte;
import com.loterie.entities.Grille;
import com.loterie.entities.Utilisateur;

public class AlerteHTML {
	private Alerte a;
	private String message;
	
	public AlerteHTML(Alerte a) {		
		if (a != null) {
			setA(a);
			message = a.getMessage();

			if (message.contains("%u")) {
				Utilisateur u = a.getUtilisateur();
				u.setNomRole(RolesConfig.getRole(u.getNiveau()));
				UtilisateurHTML uHTML = new UtilisateurHTML(u);
				message = message.replaceAll("%u", uHTML.getURL());
			}

			if (message.contains("%g")) {
				Grille g = a.getGrille();
				GrilleHTML gHTML = new GrilleHTML(g);
				message = message.replaceAll("%g", gHTML.getUrl());
			}
		}
	}

	public Alerte getA() {
		return a;
	}

	public void setA(Alerte a) {
		this.a = a;
	}
	
	public String getMessage() {
		return message;
	}
}
