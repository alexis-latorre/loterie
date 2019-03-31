package com.loterie.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lien_grille_utilisateur")
public class LienGrilleUtilisateur {
	@Id
	private Long id;
	@ManyToOne(targetEntity = Grille.class)
	@JoinColumn(name = "fk_grille_id")
	private Grille grille;
	@ManyToOne(targetEntity = Utilisateur.class)
	@JoinColumn(name = "fk_utilisateur_id")
	private Utilisateur utilisateur;
	private Double fonds; 
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Grille getGrille() {
		return grille;
	}
	
	public void setGrille(Grille grille) {
		this.grille = grille;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public void setFonds(Double fonds) {
		this.fonds = fonds;
	}
	
	public Double getFonds() {
		return this.fonds;
	}

	public void ajouterFonds(Double montant) {
		this.fonds += montant;
	}

	public void retirerFonds(Double montant) {
		this.fonds -= montant;
		
	}
}