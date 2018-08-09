package com.loterie.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.loterie.config.Constants;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Jour;
import com.loterie.entities.Utilisateur;

@Stateful
public class JourDao {
	@PersistenceContext(unitName = "loterie_PU")
	private EntityManager em;
	
	public Jour trouverParLGU(LienGrilleUtilisateur lgu) {
		Jour jour = null;
		
		try {
			Query requete = em.createQuery(Constants.SELECT_JOUR_PAR_LGU);
			requete.setParameter("lgu", lgu);
			jour = (Jour) requete.getSingleResult();
		} catch (Exception e) {
			System.out.println("[WARNING]: Pas de jour trouvee pour le lien '" + lgu.getId() + "'.");
		}
		
		return jour;		
	}
	
	public Jour trouverParNumero(int numero) {
		Jour jour = null;
		
		try {
			Query requete = em.createQuery(Constants.SELECT_JOUR_PAR_NUMERO);
			requete.setParameter("numero", numero);
			jour = (Jour) requete.getSingleResult();
		} catch (Exception e) {
			System.out.println("[WARNING]: Pas de jour trouvee pour le numero '" + numero + "'.");
		}
		
		return jour;		
	}
	
	public List<Jour> trouverParNumeroEtUtilisateur(int numero, Utilisateur utilisateur) {
		List<Jour> jour = new ArrayList<Jour>();
		
		try {
			Query requete = em.createQuery(Constants.SELECT_JOUR_PAR_NUMERO_ET_UTILISATEUR);
			requete.setParameter("numero", numero);
			requete.setParameter("utilisateur", utilisateur);
			jour = (List<Jour>) requete.getResultList();
		} catch (Exception e) {
			System.out.println("[WARNING]: Pas de jour trouvee pour le numero '" + numero + "' et l'utilisateur '" + utilisateur.getPseudo() + "'.");
		}
		
		return jour;		
	}
}
