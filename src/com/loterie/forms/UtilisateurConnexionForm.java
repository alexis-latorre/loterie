package com.loterie.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.loterie.config.Messages;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Grille;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;

public class UtilisateurConnexionForm {
	private Utilisateur utilisateur;
	private Map<String, String> erreurs;
	private UtilisateurDao utilisateurDao;
	private LienGUDao lguDao;
	private GrilleDao grilleDao;
	
	public UtilisateurConnexionForm(UtilisateurDao utilisateurDao, LienGUDao lguDao, GrilleDao grilleDao, 
			HttpServletRequest req) {
		this.utilisateur = null;
		this.utilisateurDao = utilisateurDao;
		this.lguDao = lguDao;
		this.grilleDao = grilleDao;
		this.erreurs = new HashMap<String, String>();

		String pseudo = req.getParameter("pseudo");
		String mdp = req.getParameter("motDePasse");
		
		if (trouverUtilisateur(pseudo)) {
			if (verifierMotDePasse(mdp)) {
				// Le mot de passe est regénérer en base (changement du hash SHA256 et du grain de sel)
				utilisateurDao.changerGrainDeSel(utilisateur, mdp);
			}
		}
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	private boolean trouverUtilisateur(String pseudo) {
		utilisateur = utilisateurDao.trouverParPseudo(pseudo);

		if (utilisateur == null) {
			erreurs.put("mdpInvalide", Messages.MSG_MDP_INVALIDE);
		} else {
			List<LienGrilleUtilisateur> lgus = lguDao.trouverParUtilisateur(utilisateur);
			utilisateur.setLgus(lgus);
			List<Grille> grilles = grilleDao.trouverParUtilisateur(utilisateur);
			utilisateur.setGrilles(grilles);
		}
		return utilisateur != null;
	}
	
	private boolean verifierMotDePasse(String mdp) {
		boolean mdpValide = utilisateurDao.verifierMotDePasse(utilisateur, mdp);
		
		if (!mdpValide) {
			erreurs.put("mdpInvalide", Messages.MSG_MDP_INVALIDE);
		}
		return mdpValide;
	}
}
