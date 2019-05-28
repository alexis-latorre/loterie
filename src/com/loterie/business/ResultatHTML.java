package com.loterie.business;

import java.util.List;

import com.loterie.entities.Resultat;

public class ResultatHTML {
	private Resultat dernier;
	private List<Resultat> historique;
	private int nbPages;
	private int idPage;

	public Resultat getDernier() {
		return dernier;
	}

	public void setDernier(Resultat dernier) {
		this.dernier = dernier;
	}

	public List<Resultat> getHistorique() {
		return historique;
	}

	public void setHistorique(List<Resultat> historique) {
		this.historique = historique;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public int getIdPage() {
		return idPage;
	}

	public void setIdPage(int idPage) {
		this.idPage = idPage;
	}
}
