package com.loterie.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "retard")
public class Retard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(targetEntity = Utilisateur.class)
	@JoinColumn(name = "fk_joueur_id")
	private Utilisateur utilisateur;
	@Column(name = "date_constat")
	private String dateConstat;
	@Column(name = "date_relance")
	private String dateRelance;
	boolean relance;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public String getDateConstat() {
		return dateConstat;
	}
	
	public void setDateConstat(String dateConstat) {
		this.dateConstat = dateConstat;
	}
	
	public String getDateRelance() {
		return dateRelance;
	}
	
	public void setDateRelance(String dateRelance) {
		this.dateRelance = dateRelance;
	}
	
	public boolean isRelance() {
		return relance;
	}
	
	public void setRelance(boolean relance) {
		this.relance = relance;
	}
}
