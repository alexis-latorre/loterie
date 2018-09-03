package com.loterie.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "banque")
public class Banque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "date_creation")
	private String dateCreation;
	private Double fonds;
	private Double mises;
	private Double gains;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public void setDateCreation(DateTime dt) {
		this.dateCreation = dt.getYear() + "-" + dt.getMonthOfYear() + "-" + dt.getDayOfMonth();
	}
	
	public Double getFonds() {
		return fonds;
	}
	
	public void setFonds(Double fonds) {
		this.fonds = fonds;
	}

	public void ajouterFonds(Double fonds) {
		this.fonds += fonds;
	}

	public void retirerFonds(Double montant) {
		this.fonds -= montant;
	}

	public Double getMises() {
		return mises;
	}

	public void ajouterMises(Double mises) {
		this.mises += mises;
	}

	public void setMises(Double mises) {
		this.mises = mises;
	}

	public void ajouterGains(Double gains) {
		this.gains += gains;
	}

	public Double getGains() {
		return gains;
	}

	public void setGains(Double gains) {
		this.gains = gains;
	}
}
