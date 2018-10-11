package com.loterie.business;

import com.loterie.config.LiensConfig;
import com.loterie.entities.Grille;

public class GrilleHTML {
	private Grille g;
	private String url = "";
	
	public GrilleHTML(Grille grille) {
		setG(grille);
	}

	public Grille getG() {
		return g;
	}

	public void setG(Grille grille) {
		this.g = grille;
	}

	public String getUrl() {
		if (url.isEmpty()) {
			if (g == null) {
				url = "&lt;grille supprimée&gt;";
			}
			String[] params = {g.getId().toString(), g.getNom()};
			url =  LiensConfig.getLienConfigFormatte(LiensConfig.LIEN_GRILLE, params);
		}
		return url;
	}
}
