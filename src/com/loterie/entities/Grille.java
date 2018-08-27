package com.loterie.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "grille")
public class Grille {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String numeros;
	private String etoiles;
	@Column(name = "etoile_plus")
	private boolean etoilePlus;
	@Column(name = "mymillion")
	private String myMillion;
	@ManyToOne
	@JoinColumn(name = "fk_createur")
	private Utilisateur utilisateur;
	@JoinColumn(name = "fk_jeu_id")
	private Jeu jeu;
	@JoinColumn(name = "fk_banque_id")
	private Banque banque;
	private boolean publique;
	@Transient
	private boolean rejoindre;
	@Transient
	private boolean paye;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String[] getNumeros() {
		if (numeros == null) {
			return null;	
		} else {
			return numeros.split(",");
		}
	}
	
	public void setNumeros(List<String> numeros) {
		if (numeros == null) {
			this.numeros = null;
		} else {
			this.numeros = String.join(",", numeros);
		}
	}
	
	public String[] getEtoiles() {
		if (etoiles == null) {
			return null;	
		} else {
			return etoiles.split(",");
		}
	}
	
	public void setEtoiles(List<String> etoiles) {
		if (etoiles == null) {
			this.etoiles = null;
		} else {
			this.etoiles = String.join(",", etoiles);
		}
	}
	
	public boolean getEtoilePlus() {
		return etoilePlus;
	}
	
	public void setEtoilePlus(boolean etoilePlus) {
		this.etoilePlus = etoilePlus;
	}
	
	public String getMyMillion() {
		return myMillion;
	}
	
	public void setMyMillion(String myMillion) {
		this.myMillion = myMillion;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public Jeu getJeu() {
		return jeu;
	}
	
	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	public boolean isPublique() {
		return publique;
	}

	public void setPublique(boolean publique) {
		this.publique = publique;
	}

	public boolean getRejoindre() {
		return rejoindre;
	}

	public void setRejoindre(boolean rejoindre) {
		this.rejoindre = rejoindre;
	}

	public boolean getPaye() {
		return paye;
	}

	public void setPaye(boolean paye) {
		this.paye = paye;
	}
}
