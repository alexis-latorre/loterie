package com.loterie.business;

import com.loterie.config.LiensConfig;
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
		String[] params = {u.getId().toString(), u.getPseudo()};
		return LiensConfig.getLienConfigFormatte(LiensConfig.LIEN_UTILISATEUR, params);		
	}
}
