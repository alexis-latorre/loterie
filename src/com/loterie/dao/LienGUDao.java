package com.loterie.dao;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.loterie.config.Constants;
import com.loterie.entities.Grille;
import com.loterie.entities.LienGrilleUtilisateur;

@Stateful
public class LienGUDao {
	
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public void enregistrerLienGrilleUtilisateur(LienGrilleUtilisateur lienGU) {
		em.persist(lienGU);
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public LienGrilleUtilisateur trouverParId(Long id) {
		LienGrilleUtilisateur resultat = null;
		
		try {
			Query query = em.createQuery(Constants.SELECT_GRILLE_PAR_ID);
			query.setParameter("id", id);
			
			resultat = (LienGrilleUtilisateur) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("[ERROR]: Pas de lien grille-utilisateur trouve pour l'id '" + id + "'.");
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return resultat;
	}

	public void supprimerLienGU(LienGrilleUtilisateur lienGU) {
		if (!em.contains(lienGU)) {
			lienGU = em.merge(lienGU);
		}
		em.remove(lienGU);
	}

	public LienGrilleUtilisateur trouverParGrille(Grille grille) {
		LienGrilleUtilisateur liensGU = null;
		
		try {
			Query query = em.createQuery(Constants.SELECT_LIEN_GU_PAR_GRILLE);
			query.setParameter("grille", grille);
			
			liensGU = (LienGrilleUtilisateur) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("[WARNING]: Pas de lien grille-utilisateur trouve pour la grille '" + grille.getId() + "'.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return liensGU;
	}
}
