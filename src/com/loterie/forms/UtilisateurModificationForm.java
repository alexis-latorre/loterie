package com.loterie.forms;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.loterie.config.Messages;
import com.loterie.config.Privileges;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Utilisateur;

public class UtilisateurModificationForm {
	private UtilisateurDao utilisateurDao;
	private Utilisateur utilisateur;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private HttpServletRequest req;
	
	public UtilisateurModificationForm(Utilisateur utilisateur, UtilisateurDao utilisateurDao, HttpServletRequest req) {
		this.utilisateur = utilisateur;
		this.utilisateurDao = utilisateurDao;
		this.req = req;
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public void modifier() {		
		if (erreurs.size() == 0) {
			utilisateurDao.maj(utilisateur);
		}
	}
	
	public void valider() {
		validerParametre("nom", Privileges.UTILISATEUR_PROP_MODIFIER_NOM);
		validerParametre("prenom", Privileges.UTILISATEUR_PROP_MODIFIER_PRENOM);
	}

	private void validerParametre(String nom, String priv) {
		Enumeration<String> params = req.getParameterNames();
		
		while (params.hasMoreElements()) {
			if (params.nextElement().equalsIgnoreCase(nom)) {
				String param = req.getParameter(nom).trim().replaceAll("  ", " ");
				
				if (param.isEmpty()) {
					erreurs.put(nom, Messages.MSG_CHAMP_VIDE);
				} else if (!utilisateur.checkPrivilege(priv)) {
					erreurs.put(nom, Messages.MSG_PRIVILEGE_MANQUANT);
				} else {
					switch (nom) {
						case "nom":
							utilisateur.setNom(param);
						case "prenom":
							utilisateur.setPrenom(param);
						break;
					}
				}
			}
		}
	}
}
