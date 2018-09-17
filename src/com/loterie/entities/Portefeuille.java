package com.loterie.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.loterie.tools.Tools;

@Entity
@Table(name = "portefeuille")
public class Portefeuille {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "date_creation")
	private String dateCreation;
	private Double fonds;
	
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
		this.dateCreation = Tools.padRight(dt.getYear(), 4) + "-" + Tools.padRight(dt.getMonthOfYear(), 2) + "-" + 
				Tools.padRight(dt.getDayOfMonth(), 2);
	}
	
	public Double getFonds() {
		return fonds;
	}
	
	public void setFonds(Double fonds) {
		this.fonds = fonds;
	}

	public void ajouterFonds(Double montant) {
		this.fonds += montant;
	}

	public void retirerFonds(Double montant) {
		this.fonds -= montant;
		
	}
}
