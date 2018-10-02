package com.loterie.business;

import com.loterie.config.Constants;
import com.loterie.config.RolesConfig;
import com.loterie.entities.Utilisateur;

public class UtilisateurHTML {
	private Utilisateur u;
	private String classRole;
	
	public UtilisateurHTML(Utilisateur utilisateur) {
		setU(utilisateur);
		setClassRole(utilisateur.getNomRole());
	}

	public Utilisateur getU() {
		return u;
	}

	public void setU(Utilisateur utilisateur) {
		this.u = utilisateur;
	}

	public String getClassRole() {
		return classRole;
	}

	public void setClassRole(String role) {
		this.classRole = RolesConfig.getClasse(role.toLowerCase());
	}

	public String getURL() {
		return "<a href=\"" + Constants.CONTEXTE + Constants.URL_ADMIN_DETAILS_UTILISATEUR + "?id=" + u.getId() + "\""
				+ " title=\"Voir les détails de l'utilisateur\">" + u.getPseudo() + "</a>";
	}
}
