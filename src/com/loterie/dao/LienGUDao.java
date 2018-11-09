package com.loterie.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import com.loterie.config.Constants;
import com.loterie.entities.Grille;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;

@Stateful
public class LienGUDao extends LoterieDao {
	private Map<String, Object> params = new HashMap<String, Object>();

	/**
	 * <b><i>creer</i></b><br />
	 * <pre>public void creer({@link com.loterie.entities.LienGrilleUtilisateur LienGrilleUtilisateur} lgu)</pre>
	 * Crée une nouvelle entité lgu et la stocke en BDD 
	 * 
	 * @param lgu - entité à créer en BDD
	 */
	public void creer(LienGrilleUtilisateur lgu) {
		super.creer(lgu);
	}
	
	/**
	 * <b><i>supprimer</i></b><br />
	 * <pre>public void supprimer({@link com.loterie.entities.LienGrilleUtilisateur LienGrilleUtilisateur} lgu)</pre>
	 * Supprime l'entité lgu de l'application et de la BDD 
	 * 
	 * @param lgu - entité à supprimer
	 */
	public void supprimer(LienGrilleUtilisateur lgu) {
		super.supprimer(lgu);
	}

	/**
	 * <b><i>trouverParId</i></b><br />
	 * <pre>public {@link com.loterie.entities.LienGrilleUtilisateur LienGrilleUtilisateur} trouverParId({@link java.lang.Long Long} id)</pre>
	 * Retourne le lien grille/utilisateur qui correspond à l'id renseigné
	 * 
	 * @param id - id de la grille à récupérer en BDD
	 * 
	 * @return la grille récupérée en BDD
	 */
	public LienGrilleUtilisateur trouverParId(Long id) {
		params.clear();
		params.put("id", id);
		return (LienGrilleUtilisateur) super.resultat(Constants.SELECT_GRILLE_PAR_ID, params, "trouverParId");
	}
	
	/**
	 * <b><i>trouverParGrille</i></b><br />
	 * <pre>public {@link java.util.List List}<{@link com.loterie.entities.LienGrilleUtilisateur LienGrilleUtilisateur}> trouverParGrille({@link com.loterie.entities.Grille Grille} grille)</pre>
	 * Retourne les liens concernant la grille renseignée
	 * 
	 * @param grille - la grille dont il faut trouver les liens en BDD
	 * 
	 * @return les liens récupérés en BDD
	 */
	public List<LienGrilleUtilisateur> trouverParGrille(Grille grille) {
		params.clear();
		params.put("grille", grille);
		return (List<LienGrilleUtilisateur>) super.resultats(Constants.SELECT_LIEN_GU_PAR_GRILLE, params,
				"trouverParGrille");
	}

	public LienGrilleUtilisateur trouverParGrilleEtUtilisateur(Grille grille, Utilisateur utilisateur) {
		params.clear();
		params.put("grille", grille);
		params.put("utilisateur", utilisateur);
		return (LienGrilleUtilisateur) super.resultat(Constants.SELECT_LIEN_GU_PAR_GRILLE_ET_UTILISATEUR, params,
				"trouverParGrilleEtUtilisateur");
	}
}
