package com.loterie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alerte")
public class Alerte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	private String message;
	private boolean acquittee;
	@ManyToOne(targetEntity = Utilisateur.class)
	@JoinColumn(name = "fk_utilisateur_id")
	private Utilisateur utilisateur;
	@ManyToOne(targetEntity = Grille.class)
	@JoinColumn(name = "fk_grille_id")
	private Grille grille;
	@ManyToOne(targetEntity = LienGrilleUtilisateur.class)
	@JoinColumn(name = "fk_lgu_id")
	private LienGrilleUtilisateur lgu;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getAcquittee() {
		return acquittee;
	}

	public void setAcquittee(boolean acquittee) {
		this.acquittee = acquittee;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public Grille getGrille() {
		return grille;
	}
	
	public void setGrille(Grille grille) {
		this.grille = grille;
	}
}
