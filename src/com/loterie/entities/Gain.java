package com.loterie.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gains_par_joueur")
public class Gain {
	@Id
	private String id;
	@Column(name = "utilisateur_id")
	private Long idUtilisateur;
	@Column(name = "date_jour")
	private String dateJour;
	private String nom;
	private Double gains;
	
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}
	
	public String getDateJour() {
		return dateJour;
	}
	
	public String getNom() {
		return nom;
	}
	
	public Double getGains() {
		return gains;
	}
}
