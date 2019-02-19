package com.loterie.forms;

import static com.loterie.tools.Tools.comparerChainesNonNull;
import static com.loterie.tools.Tools.motDePasseValide;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.loterie.config.Constants;
import com.loterie.config.Messages;
import com.loterie.config.Privileges;
import com.loterie.dao.LogDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.Logger;
import com.loterie.tools.Tools;

public class UtilisateurModificationForm {
	private UtilisateurDao utilisateurDao;
	private LogDao logDao;
	private Utilisateur utilisateur;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private HttpServletRequest req;
	
	public UtilisateurModificationForm(Utilisateur utilisateur, UtilisateurDao utilisateurDao, LogDao logDao,
			HttpServletRequest req) {
		this.utilisateur = utilisateur;
		this.utilisateurDao = utilisateurDao;
		this.logDao = logDao;
		this.req = req;
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public void modifier() {		
		if (erreurs.size() == 0) {
			utilisateurDao.maj(utilisateur);
			utilisateurDao.clearCache();
			Logger.log(logDao, Messages.LOG_MODIFIER_PROFIL, Constants.LOG_INFO, 
					Constants.LOG_COMPTE, utilisateur);
			req.setAttribute("messageSucces", "Informations mises à jour avec succès");
		}
	}
	
	public void valider() {
		String nom = req.getParameter("nom").trim().replaceAll("  ", " ");
		String prenom = req.getParameter("prenom").trim().replaceAll("  ", " ");
		String email = req.getParameter("email").trim().replaceAll("  ", " ");
		String mdp = req.getParameter("motDePasse").trim().replaceAll("  ", " ");
		
		if (null != nom && nom.length() > 0) {
			validerParametre("nom", Privileges.UTILISATEUR_PROP_MODIFIER_NOM);
		}
		
		if (null != prenom && prenom.length() > 0) {
			validerParametre("prenom", Privileges.UTILISATEUR_PROP_MODIFIER_PRENOM);
		}
		
		if (null != email && email.length() > 0) {
			validerParametre("email", Privileges.UTILISATEUR_PROP_MODIFIER_EMAIL);
		}
		
		if (null != mdp && mdp.length() > 0) {
			validerParametre("motDePasse", Privileges.UTILISATEUR_PROP_MODIFIER_MOT_DE_PASSE);
		}
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
							utilisateur.setNom(Tools.titre(param));
							break;
						case "prenom":
							utilisateur.setPrenom(Tools.titre(param));
							break;
						case "email":
							utilisateur.setEmail(param);
							break;
						case "motDePasse": {
							boolean err = false;
							String mdp = param;
							String mdpc = req.getParameter("motDePasseConfirmation").trim().replaceAll("  ", " ");
							int[] listeControles = {
									Tools.VERIF_TAILLE
							};
							
							Object[] listeParametres = {
									Constants.MDP_TAILLE_MIN
							};
							
							try {
								if (!motDePasseValide(mdp, listeControles, listeParametres)) {
									erreurs.put("motDePasse", Messages.MSG_ERREUR_MDP);
									err = true;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							if (!comparerChainesNonNull(mdp, mdpc)) {
								erreurs.put("motDePasse", Messages.MSG_ERREUR_MDPC);
								err = true;
							}

							if (!err) {
								utilisateur.setMotDePasse(Tools.encoderSHA256(mdp));
								utilisateurDao.changerGrainDeSel(utilisateur, mdp);
							}
						}
						break;
					}
				}
			}
		}
	}
}
