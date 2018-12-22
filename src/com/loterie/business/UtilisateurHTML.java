package com.loterie.business;

import java.util.List;
import java.util.Map;

import com.loterie.config.LiensConfig;
import com.loterie.config.RolesConfig;
import com.loterie.entities.Retard;
import com.loterie.entities.Utilisateur;

public class UtilisateurHTML {
	private Utilisateur u;
	private String classRole;
	private List<GrilleHTML> grilles;
	private Retard retard;
	private Map<String, String> privileges;
	
	public UtilisateurHTML(Utilisateur utilisateur) {
		setU(utilisateur);
		setClassRole(utilisateur.getNomRole());
		setPrivileges(utilisateur.getPrivilege().getPrivileges());
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
		if (u == null) {
			return "&lt;utilisateur supprimé&gt;";
		}
		String[] params = {u.getId().toString(), u.getPseudo()};
		return LiensConfig.getLienConfigFormatte(LiensConfig.LIEN_UTILISATEUR, params);		
	}

	public List<GrilleHTML> getGrilles() {
		return grilles;
	}

	public void setGrilles(List<GrilleHTML> grilles) {
		this.grilles = grilles;
	}

	public Retard getRetard() {
		return retard;
	}

	public void setRetard(Retard retard) {
		this.retard = retard;
	}

	public Map<String, String> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Map<String, String> privileges) {
		if (null == privileges) System.out.println("priv null");
		else
		System.out.println(privileges.toString());
		this.privileges = privileges;
	}
}
