package com.loterie.entities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grille")
public class Grille {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeros;
	private String etoiles;
	@Column(name = "etoile_plus")
	private boolean etoilePlus;
	@Column(name = "mymillion")
	private String myMillion;
	@ManyToOne
	@JoinColumn(name = "fk_createur")
	private Utilisateur utilisateur;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
}
