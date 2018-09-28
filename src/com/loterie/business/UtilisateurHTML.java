package com.loterie.business;

import com.loterie.config.RolesConfig;
import com.loterie.entities.Utilisateur;

public class UtilisateurHTML {
	private Utilisateur utilisateur;
	private String classRole;
	
	public UtilisateurHTML(Utilisateur utilisateur) {
		setUtilisateur(utilisateur);
		setClassRole(utilisateur.getNomRole());
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getClassRole() {
		return classRole;
	}

	public void setClassRole(String role) {		
		this.classRole = RolesConfig.getClasse(role);
	}
}
