package com.loterie.business;

import java.util.List;

import com.loterie.entities.Jour;
import com.loterie.entities.Grille;
import com.loterie.entities.Utilisateur;

public class GainRedistribuableHTML {
	private Jour jour;
	private Grille grille;
	private List<Utilisateur> joueurs;
	
	public GainRedistribuableHTML(Jour jour, Grille grille, List<Utilisateur> joueurs) {
		this.setJour(jour);
		this.setGrille(grille);
		this.setJoueurs(joueurs);
	}
	
	public Jour getJour() {
		return jour;
	}

	public void setJour(Jour jour) {
		this.jour = jour;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}
	
	public List<Utilisateur> getJoueurs() {
		return joueurs;
	}
	
	public void setJoueurs(List<Utilisateur> joueurs) {
		this.joueurs = joueurs;
	}
	
	
}
