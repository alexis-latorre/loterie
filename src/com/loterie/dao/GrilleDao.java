package com.loterie.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.loterie.config.Constants;
import com.loterie.entities.Grille;
import com.loterie.entities.Utilisateur;

@Stateful
public class GrilleDao {
	
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	public void enregistrerGrille(Grille grille) {
		em.persist(grille);
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}
	
	public List<Grille> trouverParCreateur(Utilisateur utilisateur) {
		List<Grille> grilles = new ArrayList<Grille>();
		
		try {
			Query query = em.createQuery(Constants.SELECT_GRILLES_PAR_CREATEUR);
			query.setParameter("utilisateur", utilisateur);
			
			grilles = (List<Grille>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("[WARNING]: Pas de grille trouvee pour '" + utilisateur.getPseudo() + "'.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return grilles;
	}
	
	public List<Grille> trouverParUtilisateur(Utilisateur utilisateur) {
		List<Grille> grilles = new ArrayList<Grille>();
		
		try {
			Query query = em.createQuery(Constants.SELECT_GRILLES_PAR_UTILISATEUR);
			query.setParameter("utilisateur", utilisateur);
			
			grilles = (List<Grille>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("[WARNING]: Pas de grille trouvee pour '" + utilisateur.getPseudo() + "'.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return grilles;
	}

	public Grille trouverParId(Long id) {
		Grille grille = null;
		
		try {
			Query query = em.createQuery(Constants.SELECT_GRILLE_PAR_ID);
			query.setParameter("id", id);
			
			grille = (Grille) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("[ERROR]: Pas de grille trouvee pour l'id '" + id + "'.");
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return grille;
	}

	public void supprimerGrille(Grille grille) {
		if (!em.contains(grille)) {
			grille = em.merge(grille);
		}
		em.remove(grille);
	}

	public void modifierGrille(Grille grille) {
		em.merge(grille);
	}

	public List<Grille> trouverParJourEtUtilisateur(String date_jour, Utilisateur utilisateur) {
		List<Grille> grilles = new ArrayList<Grille>();
		
		try {
			Query query = em.createQuery(Constants.SELECT_GRILLES_PAR_DATE_ET_UTILISATEUR);
			query.setParameter("utilisateur", utilisateur);
			query.setParameter("date_jour", date_jour);
			
			grilles = (List<Grille>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("[WARNING]: Pas de grille trouvee pour '" + utilisateur.getPseudo() + "' le '" + date_jour + "'.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return grilles;
	}

	public List<Grille> trouverParIntervalleEtUtilisateur(String date_debut, String date_fin, Utilisateur utilisateur) {
		List<Grille> grilles = new ArrayList<Grille>();
		
		try {
			Query query = em.createQuery(Constants.SELECT_GRILLES_PAR_INTERVALLE_ET_UTILISATEUR);
			query.setParameter("utilisateur", utilisateur);
			query.setParameter("date_debut", date_debut);
			query.setParameter("date_fin", date_fin);
			
			grilles = (List<Grille>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("[WARNING]: Pas de grille trouvee pour '" + utilisateur.getPseudo() + "' entre le '" + date_debut + "' et le '" + date_fin + "'.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return grilles;
	}
}
