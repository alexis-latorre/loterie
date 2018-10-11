package com.loterie.business;

import com.loterie.config.RolesConfig;
import com.loterie.entities.Grille;
import com.loterie.entities.Log;
import com.loterie.entities.Utilisateur;

public class LogHTML {
	private Log l;
	private String message;
	
	public LogHTML(Log log) {		
		if (log != null) {
			setL(log);
			message = log.getMessage();

			if (message.contains("%u")) {
				Utilisateur u = log.getDeclencheur();
				u.setNomRole(RolesConfig.getRole(u.getNiveau()));
				UtilisateurHTML uHTML = new UtilisateurHTML(u);
				message = message.replaceAll("%u", uHTML.getURL());
			}

			if (message.contains("%j")) {
				Utilisateur j = log.getUtilisateurLie();
				j.setNomRole(RolesConfig.getRole(j.getNiveau()));
				UtilisateurHTML jHTML = new UtilisateurHTML(j);
				message = message.replaceAll("%j", jHTML.getURL());
			}

			if (message.contains("%g")) {
				Grille g = log.getGrilleLiee();
				GrilleHTML gHTML = new GrilleHTML(g);
				message = message.replaceAll("%g", gHTML.getUrl());
			}
		}
	}

	public Log getL() {
		return l;
	}

	public void setL(Log log) {
		this.l = log;
	}
	
	public String getMessage() {
		return message;
	}
}
