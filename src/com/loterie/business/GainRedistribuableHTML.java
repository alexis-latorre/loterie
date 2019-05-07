package com.loterie.business;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.loterie.entities.Jour;
import com.loterie.entities.Grille;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.TokenManager;
import com.loterie.tools.Tools;

public class GainRedistribuableHTML {
	private Jour jour;
	private Grille grille;
	private List<Utilisateur> joueurs;
	private List<Jour> jours;
	private String date;
	private String token;
	
	public GainRedistribuableHTML(Jour jour, Grille grille, List<Utilisateur> joueurs, List<Jour> jours) {
		this.setJour(jour);
		this.setDate(Tools.getDateSlash(jour.getDateJour()));
		this.setGrille(grille);
		this.setJoueurs(joueurs);
		System.out.println(jours);
		this.setJours(jours);
		
		try {
			this.setToken(TokenManager.newToken(this));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public List<Jour> getJours() {
		return jours;
	}

	public void setJours(List<Jour> jours) {
		this.jours = jours;
	}

	public int getNbJoueurs() {
		return this.joueurs.size();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
