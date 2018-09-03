package com.loterie.dao;
import com.loterie.config.Constants;

import static com.loterie.tools.Tools.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

@Stateful
public class UtilisateurDao extends LoterieDao {
	private Map<String, Object> params = new HashMap<String, Object>();
	
	public void creer(Utilisateur utilisateur) {
		super.creer(utilisateur);
	}

	public void maj(Utilisateur utilisateur) {
		super.maj(utilisateur);
	}

	public Utilisateur trouverParId(Long id) {
		params.clear();
		params.put("id", id);
		return (Utilisateur) super.resultat(Constants.SELECT_UTILISATEUR_PAR_ID, params, 
				"trouverParId");
	}

	public Utilisateur trouverParPseudo(String pseudo) {
		params.clear();
		params.put("pseudo", pseudo);
		return (Utilisateur) super.resultat(Constants.SELECT_UTILISATEUR_PAR_NOM, params, "trouverParPseudo");
	}

	public Utilisateur trouverParNomEtPrenom(String nom, String prenom) {
		params.clear();
		params.put("nom", nom);
		params.put("prenom", prenom);
		return (Utilisateur) super.resultat(Constants.SELECT_UTILISATEUR_PAR_NOM_PRENOM, params, 
				"trouverParNomEtPrenom");
	}

	public Utilisateur trouverParEmail(String email) {
		params.clear();
		params.put("email", email);
		return (Utilisateur) super.resultat(Constants.SELECT_UTILISATEUR_PAR_EMAIL, params, "trouverParEmail");
	}
	
	public List<Utilisateur> trouverParRoleMinimum(Long niveau) {
		params.clear();
		params.put("niveau", niveau);
		return (List<Utilisateur>) super.resultat(Constants.SELECT_UTILISATEURS_PAR_NIVEAU_SUP, params, 
				"trouverParRoleMinimum");
	}
	
	public void changerMotDePasse(Utilisateur utilisateur, String mdp, String mdpc) {		
		int[] listeControles = {
				Tools.VERIF_TAILLE
		};
		
		Object[] listeParametres = {
				Constants.MDP_TAILLE_MIN
		};
		
		if (listeControles.length != listeParametres.length) {
			System.out.println("[ERROR]: UtilisateurDao.changerMotDePasse(): La liste de paramètres doit contenir "
					+ "le même nombre que la liste de contrôles.");
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
		String grainDeSel = chaineAleatoire((int) Math.round(Math.random() * Constants.VARIABILITE_SEL) + 
				Constants.SEL_TAILLE_MIN, true, true, false);
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
}
