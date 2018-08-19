package com.loterie.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.loterie.config.Constants;
import com.loterie.entities.Jeu;

@Stateless
public class JeuDao {
	@PersistenceContext(name = "loterie_PU")
	private EntityManager em;
	
	public Jeu trouverParNom(String nom) {
		Jeu jeu = null;
		
		try {
			Query query = em.createQuery(Constants.SELECT_JEU_PAR_NOM);
			query.setParameter("nom", nom);
			
			jeu = (Jeu) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("[ERROR]: Pas de jeu trouve avec le nom '" + nom + "'.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jeu;
	}
}
