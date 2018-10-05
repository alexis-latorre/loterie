package com.loterie.business;

import com.loterie.config.LiensConfig;
import com.loterie.entities.Grille;

public class GrilleHTML {
	private Grille g;
	
	public GrilleHTML(Grille grille) {
		setG(grille);
	}

	public Grille getG() {
		return g;
	}

	public void setG(Grille grille) {
		this.g = grille;
	}

	public String getURL() {
		if (g == null) {
			return "&lt;grille supprimée&gt;";
		}
		String[] params = {g.getId().toString(), g.getNom()};
		return LiensConfig.getLienConfigFormatte(LiensConfig.LIEN_GRILLE, params);		
	}
}
