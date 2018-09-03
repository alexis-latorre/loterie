package com.loterie.dao;
import com.loterie.config.Constants;
import com.loterie.config.Messages;

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

	/**
	 * <b><i>creer</i></b><br />
	 * <pre>public void creer({@link com.loterie.entities.Utilisateur Utilisateur} utilisateur)</pre>
	 * Crée une nouvelle entité utilisateur et la stocke en BDD 
	 * 
	 * @param utilisateur - entité à créer en BDD
	 */
	public void creer(Utilisateur utilisateur) {
		super.creer(utilisateur);
	}

	/**
	 * <b><i>maj</i></b><br />
	 * <pre>public void maj({@link com.loterie.entities.Utilisateur Utilisateur} utilisateur)</pre>
	 * Met à jour l'entité utilisateur en BDD 
	 * 
	 * @param utilisateur - entité à mettre à jour
	 */
	public void maj(Utilisateur utilisateur) {
		super.maj(utilisateur);
	}

	/**
	 * <b><i>trouverParId</i></b><br />
	 * <pre>public {@link com.loterie.entities.Utilisateur Utilisateur} trouverParId({@link java.lang.Long Long} id)</pre>
	 * Retourne l'utilisateur qui correspond à l'id renseigné
	 * 
	 * @param id - id de l'utilisateur à récupérer en BDD
	 * 
	 * @return l'utilisateur récupéré en BDD
	 */
	public Utilisateur trouverParId(Long id) {
		params.clear();
		params.put("id", id);
		return (Utilisateur) super.resultat(Constants.SELECT_UTILISATEUR_PAR_ID, params, "trouverParId");
	}

	/**
	 * <b><i>trouverParPseudo</i></b><br />
	 * <pre>public {@link com.loterie.entities.Utilisateur Utilisateur} trouverParPseudo({@link java.lang.String String} pseudo)</pre>
	 * Retourne l'utilisateur qui correspond au pseudo renseigné
	 * 
	 * @param pseudo - pseudo de l'utilisateur à récupérer en BDD
	 * 
	 * @return l'utilisateur récupéré en BDD
	 */
	public Utilisateur trouverParPseudo(String pseudo) {
		params.clear();
		params.put("pseudo", pseudo);
		return (Utilisateur) super.resultat(Constants.SELECT_UTILISATEUR_PAR_NOM, params, "trouverParPseudo");
	}

	/**
	 * <b><i>trouverParNomEtPrenom</i></b><br />
	 * <pre>public {@link com.loterie.entities.Utilisateur Utilisateur} trouverParNomEtPrenom({@link java.lang.String String} nom, {@link java.lang.String String} prenom)</pre>
	 * Retourne l'utilisateur qui correspond au nom et au prenom renseignés
	 * 
	 * @param nom - nom de l'utilisateur à récupérer en BDD
	 * @param prenom - prenom de l'utilisateur à récupérer en BDD
	 * 
	 * @return l'utilisateur récupéré en BDD
	 */
	public Utilisateur trouverParNomEtPrenom(String nom, String prenom) {
		params.clear();
		params.put("nom", nom);
		params.put("prenom", prenom);
		return (Utilisateur) super.resultat(Constants.SELECT_UTILISATEUR_PAR_NOM_PRENOM, params, 
				"trouverParNomEtPrenom");
	}

	/**
	 * <b><i>trouverParEmail</i></b><br />
	 * <pre>public {@link com.loterie.entities.Utilisateur Utilisateur} trouverParEmail({@link java.lang.String String} email)</pre>
	 * Retourne l'utilisateur qui correspond à l'email renseigné
	 * 
	 * @param email - email de l'utilisateur à récupérer en BDD
	 * 
	 * @return l'utilisateur récupéré en BDD
	 */
	public Utilisateur trouverParEmail(String email) {
		params.clear();
		params.put("email", email);
		return (Utilisateur) super.resultat(Constants.SELECT_UTILISATEUR_PAR_EMAIL, params, "trouverParEmail");
	}

	/**
	 * <b><i>trouverParRoleMinimum</i></b><br />
	 * <pre>public {@link java.util.List List}<{@link com.loterie.entities.Utilisateur Utilisateur}> trouverParRoleMinimum({@link java.lang.Long Long} niveau)</pre>
	 * Retourne l'utilisateur qui est au moins du niveau rensigné
	 * 
	 * @param niveau - niveau de l'utilisateur à récupérer en BDD
	 * 
	 * @return lliste des utilisateures récupérés en BDD
	 */
	public List<Utilisateur> trouverParRoleMinimum(Long niveau) {
		params.clear();
		params.put("niveau", niveau);
		return (List<Utilisateur>) super.resultats(Constants.SELECT_UTILISATEURS_PAR_NIVEAU_SUP, params, 
				"trouverParRoleMinimum");
	}
	
	/**
	 * <b><i>changerMotDePasse</i></b><br />
	 * <pre>public void changerMotDePasse({@link com.loterie.entities.Utilisateur Utilisateur} utilisateur, {@link java.lang.String String} mdp, {@link java.lang.String String} mdpc)</pre>
	 * Contrôle que le nouveau mot de passe mdp respecte bien les spécifications de mot de passe demandées.
	 * Compare ensuite mdp et mdpc pour vérifier que le mot de passe et le mot de passe de confirmation saisis sont bien identiques.
	 * Si toutes les étapes de contrôles sont validées, le nouveau mot de passe est mis à jour en BDD 
	 * 
	 * @param utilisateur - utilisateur pour qui le mot de passe doit être changé
	 * @param mdp - nouveau mot de passe à mettre à jour
	 * @param mdpc - confirmation du nouveau mot de passe
	 */
	public void changerMotDePasse(Utilisateur utilisateur, String mdp, String mdpc) {		
		int[] listeControles = {
				Tools.VERIF_TAILLE
		};
		
		Object[] listeParametres = {
				Constants.MDP_TAILLE_MIN
		};
		
		if (listeControles.length != listeParametres.length) {
			System.out.println(Messages.E_LISTES_VERIF_MDP_DIFFERENTES);
			return;
		}

		try {
			// Si toutes les étapes de contrôles sont validées, le nouveau mot de passe est mis à jour en BDD 
			if (comparerChainesNonNull(mdp, mdpc) && motDePasseValide(mdp, listeControles, listeParametres)) {
				// Le mot de passe est mis à jour par la mise à jour du grain de sel également
				changerGrainDeSel(utilisateur, mdp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b><i>changerGrainDeSel</i></b><br />
	 * <pre>public void changerGrainDeSel({@link com.loterie.entities.Utilisateur Utilisateur} utilisateur, {@link java.lang.String String} mdp)</pre>
	 * Génère un grain de sel aléatoire et le met à jour en BDD 
	 * 
	 * @param utilisateur - utilisateur pour qui le mot de passe et le grain de sel doivent être changés
	 * @param mdp - nouveau mot de passe à mettre à jour
	 */
	public void changerGrainDeSel(Utilisateur utilisateur, String mdp) {
		String grainDeSel = chaineAleatoire((int) Math.round(Math.random() * Constants.VARIABILITE_SEL) + 
				Constants.SEL_TAILLE_MIN, true, true, false);
		String sha256mdp = encoderSHA256(mdp + grainDeSel);
		utilisateur.setGrainDeSel(grainDeSel);
		utilisateur.setMotDePasse(sha256mdp);
		maj(utilisateur);
	}

	/**
	 * <b><i>verifierMotDePasse</i></b><br />
	 * <pre>public boolean verifierMotDePasse({@link com.loterie.entities.Utilisateur Utilisateur} utilisateur, {@link java.lang.String String} mdp)</pre>
	 * Vérifie que le mot de passe saisie par l'utilisateur correspond bien à son mot de passe stocké en base 
	 * 
	 * @param utilisateur - utilisateur pour qui il faut vérifier le mot de passe
	 * @param mdp - mot de passe saisi dans le formulaire de connexion
	 * 
	 * @return vrai si la chaîne saisie correspond au mot de passe utilisateur
	 */
	public boolean verifierMotDePasse(Utilisateur utilisateur, String mdp) {
		String mdp256 = utilisateur.getMotDePasse();
		String grainDeSel = utilisateur.getGrainDeSel();
		String sha256mdp = encoderSHA256(mdp + grainDeSel);
		
		boolean valide = sha256mdp.equals(mdp256);
		
		return valide;
	}
}
