package com.loterie.forms;
import com.loterie.config.Constants; 

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

import static com.loterie.tools.Tools.*;

public class CreationUtilisateurForm {
	private static final String PSEUDO_EXISTANT 		= "Le pseudo choisi existe déjà.";
	private static final String UTILISATEUR_EXISTANT 	= "L'utilisateur est déjà affilié à un compte.";
	private static final String EMAIL_EXISTANT 			= "Cette adresse email est déjà utilisée par un autre compte.";
	private static final String ERREUR_MDP 				= "Le mot de passe entré n'est pas valide.";
	private static final String ERREUR_MDPC 			= "Les mots de passe entrés sont différents.";
	
	private Utilisateur utilisateur;
	private Map<String, String> erreurs;
	private UtilisateurDao utilisateurDao;
	
	public CreationUtilisateurForm(UtilisateurDao utilisateurDao, HttpServletRequest req) {
		this.utilisateurDao = utilisateurDao;
		utilisateur = new Utilisateur();
		erreurs = new HashMap<String, String>();
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
			String grainDeSel = chaineAleatoire((int) Math.round(Math.random() * Constants.VARIABILITE_SEL) + Constants.SEL_TAILLE_MIN, true, true, false);
			String sha256mdp = encoderSHA256(mdp + grainDeSel);
			
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setGrainDeSel(grainDeSel);
			utilisateur.setMotDePasse(sha256mdp);
			utilisateur.setNiveau(Constants.UTILISATEUR_ROLE_BASIQUE);
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
		Utilisateur utilisateur = utilisateurDao.trouverParPseudo(pseudo);
		
		if (utilisateur != null) {
			erreurs.put("pseudo", PSEUDO_EXISTANT);
		}
	}
	
	private void validerNom(String nom, String prenom) {
		Utilisateur utilisateur = utilisateurDao.trouverParNomEtPrenom(nom, prenom);
		
		if (utilisateur != null) {
			erreurs.put("nom", UTILISATEUR_EXISTANT);
			erreurs.put("prenom", UTILISATEUR_EXISTANT);
		}
	}
	
	private void validerEmail(String email) {
		Utilisateur utilisateur = utilisateurDao.trouverParEmail(email);
		
		if (utilisateur != null) {
			erreurs.put("email", EMAIL_EXISTANT);
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
			System.out.println("[ERROR]: UtilisateurDao.changerMotDePasse(): La liste de paramètres doit contenir le même nombre que la liste de contrôles.");
			return;
		}
		
		try {
			if (!motDePasseValide(mdp, listeControles, listeParametres)) {
				erreurs.put("motDePasse", ERREUR_MDP);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (!comparerChainesNonNull(mdp, mdpc)) {
			erreurs.put("motDePasse", ERREUR_MDPC);
		}
	}
}
