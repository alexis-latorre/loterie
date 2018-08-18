package com.loterie.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.DateTime;
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

	public List<Jour> trouverParJourEtUtilisateur(String date_jour, Utilisateur utilisateur) {
		List<Jour> jour = new ArrayList<Jour>();
		
		try {
			Query requete = em.createQuery(Constants.SELECT_JOUR_PAR_DATE_ET_UTILISATEUR);
			requete.setParameter("date_jour", date_jour);
			requete.setParameter("utilisateur", utilisateur);
			jour = (List<Jour>) requete.getResultList();
		} catch (Exception e) {
			System.out.println("[WARNING]: Pas de jour trouvee pour la date '" + date_jour + "' et l'utilisateur '" + utilisateur.getPseudo() + "'.");
		}
		
		return jour;		
	}

	public Jour trouverParDate(String date_jour) {
		Jour jour = null;
		
		try {
			Query requete = em.createQuery(Constants.SELECT_JOUR_PAR_DATE);
			requete.setParameter("date_jour", date_jour);
			jour = (Jour) requete.getSingleResult();
		} catch (Exception e) {
			System.out.println("[WARNING]: Pas de jour trouve pour la date '" + date_jour + "'.");
		}
		
		return jour;
	}
}