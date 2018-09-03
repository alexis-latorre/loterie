package com.loterie.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import com.loterie.config.Constants;
import com.loterie.entities.Grille;
import com.loterie.entities.LienGrilleUtilisateur;

@Stateful
public class LienGUDao extends LoterieDao {
	private Map<String, Object> params = new HashMap<String, Object>();
	
	public void creer(LienGrilleUtilisateur lienGU) {
		super.creer(lienGU);
	}

	public void supprimer(LienGrilleUtilisateur lienGU) {
		super.supprimer(lienGU);
	}

	public LienGrilleUtilisateur trouverParId(Long id) {
		params.clear();
		params.put("id", id);
		return (LienGrilleUtilisateur) super.resultat(Constants.SELECT_GRILLE_PAR_ID, params, "trouverParId");
	}

	public List<LienGrilleUtilisateur> trouverParGrille(Grille grille) {
		params.clear();
		params.put("grille", grille);
		return (List<LienGrilleUtilisateur>) super.resultat(Constants.SELECT_LIEN_GU_PAR_GRILLE, params, 
				"trouverParGrille");
	}
}
