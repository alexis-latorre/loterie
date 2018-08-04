package com.loterie.dao;
import com.loterie.config.Constants;

import static com.loterie.tools.Tools.*;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

@Stateful
public class UtilisateurDao {
	// TODO: Déplacer dans un ConnexionForm 
	private static final String ERR_MDP_INVALIDE 		= "Le mot de passe saisi est invalide ou l'utilisateur n'existe pas.";
	
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public void enregistrerUtilisateur(Utilisateur utilisateur) {
		em.persist(utilisateur);
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	private void ajouterErreur(String cle, String erreur) {
		erreurs.put(cle, erreur);
	}
	
	public void changerMotDePasse(Utilisateur utilisateur, HttpServletRequest req) {
		String mdp = req.getParameter("motDePasse");
		String mdpc = req.getParameter("motDePasseConfirmation");
		
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

		if (comparerChainesNonNull(mdp, mdpc) && motDePasseValide(mdp, listeControles, listeParametres)) {
			changerGrainDeSel(utilisateur, mdp);
		}
	}
	
	public void changerGrainDeSel(Utilisateur utilisateur, String mdp) {
		String grainDeSel = chaineAleatoire((int) Math.round(Math.random() * Constants.VARIABILITE_SEL) + Constants.SEL_TAILLE_MIN, true, true, false);
		String sha256mdp = encoderSHA256(mdp + grainDeSel);
		utilisateur.setGrainDeSel(grainDeSel);
		utilisateur.setMotDePasse(sha256mdp);
		em.merge(utilisateur);
	}
	
	public boolean verifierMotDePasse(Utilisateur utilisateur, HttpServletRequest req) {
		String mdp = req.getParameter("motDePasse");
		String mdp256 = utilisateur.getMotDePasse();
		String grainDeSel = utilisateur.getGrainDeSel();
		String sha256mdp = encoderSHA256(mdp + grainDeSel);
		
		boolean valide = sha256mdp.equals(mdp256);
		ajouterErreur("mdpInvalide", ERR_MDP_INVALIDE);
		
		return valide;
	}

	public Utilisateur trouverParPseudo(String pseudo) {
		Utilisateur utilisateur = null;
		
		try {
			Query query = em.createQuery(Constants.SELECT_UTILISATEUR_PAR_NOM);
			query.setParameter("pseudo", pseudo);
			
			utilisateur = (Utilisateur) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			//ajouterErreur("mdpInvalide", ERR_MDP_INVALIDE);
		}
		
		return utilisateur;
	}

	public Utilisateur trouverParNomEtPrenom(String nom, String prenom) {
		Utilisateur utilisateur = null;
		
		try {
			Query query = em.createQuery(Constants.SELECT_UTILISATEUR_PAR_NOM_PRENOM);
			query.setParameter("nom", nom);
			query.setParameter("prenom", prenom);
			
			utilisateur = (Utilisateur) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}

	public Utilisateur trouverParEmail(String email) {
		Utilisateur utilisateur = null;
		
		try {
			Query query = em.createQuery(Constants.SELECT_UTILISATEUR_PAR_EMAIL);
			query.setParameter("email", email);

			utilisateur = (Utilisateur) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}
}
