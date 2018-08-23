package com.loterie.dao;
import com.loterie.config.Constants;

import static com.loterie.tools.Tools.*;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

@Stateful
public class UtilisateurDao {	
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	
	public void enregistrerUtilisateur(Utilisateur utilisateur) {
		em.persist(utilisateur);
	}
	
	public void changerMotDePasse(Utilisateur utilisateur, String mdp, String mdpc) {		
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
			if (comparerChainesNonNull(mdp, mdpc) && motDePasseValide(mdp, listeControles, listeParametres)) {
				changerGrainDeSel(utilisateur, mdp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changerGrainDeSel(Utilisateur utilisateur, String mdp) {
		String grainDeSel = chaineAleatoire((int) Math.round(Math.random() * Constants.VARIABILITE_SEL) + Constants.SEL_TAILLE_MIN, true, true, false);
		String sha256mdp = encoderSHA256(mdp + grainDeSel);
		utilisateur.setGrainDeSel(grainDeSel);
		utilisateur.setMotDePasse(sha256mdp);
		maj(utilisateur);
	}
	
	public boolean verifierMotDePasse(Utilisateur utilisateur, String mdp) {
		String mdp256 = utilisateur.getMotDePasse();
		String grainDeSel = utilisateur.getGrainDeSel();
		String sha256mdp = encoderSHA256(mdp + grainDeSel);
		
		boolean valide = sha256mdp.equals(mdp256);
		
		return valide;
	}

	public Utilisateur trouverParPseudo(String pseudo) {
		Utilisateur utilisateur = null;
		
		try {
			Query query = em.createQuery(Constants.SELECT_UTILISATEUR_PAR_NOM);
			query.setParameter("pseudo", pseudo);
			
			utilisateur = (Utilisateur) query.getSingleResult();
			em.refresh(utilisateur);
		} catch (NoResultException e) {
			System.out.println("[WARNING]: No user found with pseudo '" + pseudo + "'.");
		} catch (Exception e) {
			//e.printStackTrace();
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

	public void maj(Utilisateur utilisateur) {
		em.merge(utilisateur);
	}
}
