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
	private String jourDeTirage;
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
	
	public String[] getJourDeTirage() {
		if (jourDeTirage == null) {
			return null;	
		} else {
			return jourDeTirage.split(",");
		}
	}
	
	public void setJourDeTirage(String[] joursDeTirage) {
		if (joursDeTirage == null) {
			this.jourDeTirage = null;
		} else {
			this.jourDeTirage = String.join(",", joursDeTirage);
		}
	}

	public String getHeureValidation() {
		return heureValidation;
	}

	public void setHeureValidation(String heureValidation) {
		this.heureValidation = heureValidation;
	}
}
