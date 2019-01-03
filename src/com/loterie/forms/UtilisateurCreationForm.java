package com.loterie.forms;
import com.loterie.config.Constants;
import com.loterie.config.Messages;
import com.loterie.config.Privileges;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.loterie.dao.PrivilegeDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Privilege;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

import static com.loterie.tools.Tools.*;

public class UtilisateurCreationForm {	
	private Utilisateur utilisateur;
	private Map<String, String> erreurs;
	private Map<String, String> champs;
	private UtilisateurDao utilisateurDao;
	private PrivilegeDao privilegeDao;
	
	public UtilisateurCreationForm(UtilisateurDao utilisateurDao, PrivilegeDao privilegeDao, HttpServletRequest req) {
		this.utilisateurDao = utilisateurDao;
		this.privilegeDao = privilegeDao;
		utilisateur = new Utilisateur();
		erreurs = new HashMap<String, String>();
		champs = new HashMap<String, String>();
		String pseudo = req.getParameter("pseudo");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String email = req.getParameter("email");
		String mdp = req.getParameter("motDePasse");
		String mdpc = req.getParameter("motDePasseConfirmation");
		
		validerPseudo(pseudo);
		validerNom(nom, prenom);
		validerEmail(email);
		validerMDP(mdp, mdpc);
		
		if (erreurs.isEmpty()) {
			String grainDeSel = chaineAleatoire((int) Math.round(Math.random() * Constants.VARIABILITE_SEL) + 
					Constants.SEL_TAILLE_MIN, true, true, false);
			String sha256mdp = encoderSHA256(mdp + grainDeSel);
			
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setGrainDeSel(grainDeSel);
			utilisateur.setMotDePasse(sha256mdp);
			utilisateur.setNiveau(Constants.L_UTILISATEUR_ROLE_BASIQUE);
			Privilege p = new Privilege();
			p.setPrivilege(Privileges.PRIVILEGES_UTILISATEUR);
			this.privilegeDao.creer(p);
			utilisateur.setPrivilege(p);
		}
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public void creerUtilisateur() {
		utilisateurDao.creer(utilisateur);
	}
	
	private void validerPseudo(String pseudo) {
		if (null != pseudo && pseudo.trim().length() > 0) {
			Utilisateur utilisateur = utilisateurDao.trouverParPseudo(pseudo);
			
			if (utilisateur != null) {
				erreurs.put("pseudo", Messages.MSG_PSEUDO_EXISTANT);
			} else {
				champs.put("pseudo", pseudo);
			}
		} else {
			erreurs.put("pseudo", Messages.MSG_CHAMP_VIDE);
		}
	}
	
	private void validerNom(String nom, String prenom) {
		if (null != nom && nom.trim().length() > 0 && null != prenom && prenom.trim().length() > 0) {
			Utilisateur utilisateur = utilisateurDao.trouverParNomEtPrenom(nom, prenom);
			
			if (utilisateur != null) {
				erreurs.put("nom", Messages.MSG_UTILISATEUR_EXISTANT);
				erreurs.put("prenom", Messages.MSG_UTILISATEUR_EXISTANT);
			} else {
				champs.put("nom", nom);
				champs.put("prenom", prenom);
			}
		} else {
			if (null == nom || nom.trim().length() == 0) {
				erreurs.put("nom", Messages.MSG_CHAMP_VIDE);
			}
			if (null == prenom || prenom.trim().length() == 0) {
				erreurs.put("prenom", Messages.MSG_CHAMP_VIDE);
			}
		}
	}
	
	private void validerEmail(String email) {
		if (null != email && email.trim().length() > 0) {
			Utilisateur utilisateur = utilisateurDao.trouverParEmail(email);
			
			if (utilisateur != null) {
				erreurs.put("email", Messages.MSG_EMAIL_EXISTANT);
			} else {
				champs.put("email", email);
			}
		} else {
			erreurs.put("email", Messages.MSG_CHAMP_VIDE);
		}
	}
	
	private void validerMDP(String mdp, String mdpc) {
		
		int[] listeControles = {
				Tools.VERIF_TAILLE
		};
		
		Object[] listeParametres = {
				Constants.MDP_TAILLE_MIN
		};
		
		if (listeControles.length != listeParametres.length) {
			System.out.println("[ERREUR]: UtilisateurDao.changerMotDePasse(): La liste de paramètres doit contenir "
					+ "le même nombre que la liste de contrôles.");
			return;
		}
		
		try {
			if (null == mdp || mdp.trim().length() == 0) {
				erreurs.put("motDePasse", Messages.MSG_ERREUR_MDP);
			}
			
			if (!motDePasseValide(mdp, listeControles, listeParametres)) {
				erreurs.put("motDePasse", Messages.MSG_ERREUR_MDP);
			}
		} catch (Exception e) {
			erreurs.put("motDePasse", Messages.MSG_ERREUR_MDP);
			e.printStackTrace();
		}
		
		if (!comparerChainesNonNull(mdp, mdpc)) {
			erreurs.put("motDePasseConfirmation", Messages.MSG_ERREUR_MDPC);
		}
	}

	public Map<String, String> getChamps() {
		return champs;
	}
}
