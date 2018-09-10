package com.loterie.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jeu")
public class Jeu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@Column(name = "tirage_par_semaine")
	private Long tirageParSemaine;
	@Column(name = "jour_de_tirage")
	private String joursDeTirage;
	@Column(name = "prix_par_tirage")
	private Double prixTirage;
	@Column(name = "heure_validation")
	private String heureValidation;

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
	
	public Long getTirageParSemaine() {
		return tirageParSemaine;
	}
	
	public void setTirageParSemaine(Long tirageParSemaine) {
		this.tirageParSemaine = tirageParSemaine;
	}
	
	public String[] getJoursDeTirage() {
		if (joursDeTirage == null) {
			return null;	
		} else {
			return joursDeTirage.split(",");
		}
	}
	
	public void setJoursDeTirage(String[] joursDeTirage) {
		if (joursDeTirage == null) {
			this.joursDeTirage = null;
		} else {
			this.joursDeTirage = String.join(",", joursDeTirage);
		}
	}
	
	public Double getPrixTirage() {
		return prixTirage;
	}

	public void setPrixTirage(Double prixTirage) {
		this.prixTirage = prixTirage;
	}

	public String getHeureValidation() {
		return heureValidation;
	}

	public void setHeureValidation(String heureValidation) {
		this.heureValidation = heureValidation;
	}
}
