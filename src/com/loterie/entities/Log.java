package com.loterie.entities;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.loterie.tools.Tools;

@Entity
@Table(name = "log")
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Timestamp utc;
	private Date jour;
	private Time heure;
	private String niveau;
	private String type;
	@ManyToOne(targetEntity = Utilisateur.class)
	@JoinColumn(name = "fk_declencheur")
	private Utilisateur declencheur;
	@ManyToOne(targetEntity = Utilisateur.class)
	@JoinColumn(name = "fk_joueur_lie")
	private Utilisateur utilisateurLie;
	@JoinColumn(name = "fk_grille_liee")
	private Grille grilleLiee;
	private String message;
	
	public void LogInit(String message, String niveau, String type, Utilisateur declencheur, 
			Object entiteLiee) {
		this.message = message;
		this.jour = Date.valueOf(Tools.getDate());
		this.heure = Time.valueOf(Tools.getHeure());
		this.niveau = niveau;
		this.type = type;
		this.declencheur = declencheur;
		
		if (entiteLiee != null) {
			if (Utilisateur.class.equals(entiteLiee.getClass())) {
				this.utilisateurLie = (Utilisateur) entiteLiee;
			} else if (Grille.class.equals(entiteLiee.getClass())) {
				this.grilleLiee = (Grille) entiteLiee;
			}
		}
	}

	public void LogInit(String message, String niveau, String type, Utilisateur declencheur,
			Utilisateur utilisateurLie, Grille grilleLiee) {
		this.message = message;
		this.jour = Date.valueOf(Tools.getDate());
		this.heure = Time.valueOf(Tools.getHeure());
		this.niveau = niveau;
		this.type = type;
		this.declencheur = declencheur;
		this.utilisateurLie = utilisateurLie;
		this.grilleLiee = grilleLiee;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Timestamp getUtc() {
		return utc;
	}
	
	public void setUtc(Timestamp utc) {
		this.utc = utc;
	}
	
	public Date getJour() {
		return jour;
	}
	
	public void setJour(Date jour) {
		this.jour = jour;
	}
	
	public Time getHeure() {
		return heure;
	}
	
	public void setHeure(Time heure) {
		this.heure = heure;
	}
	
	public String getNiveau() {
		return niveau;
	}
	
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Utilisateur getDeclencheur() {
		return declencheur;
	}
	
	public void setDeclencheur(Utilisateur declencheur) {
		this.declencheur = declencheur;
	}
	
	public Utilisateur getUtilisateurLie() {
		return utilisateurLie;
	}
	
	public void setUtilisateurLie(Utilisateur utilisateurLie) {
		this.utilisateurLie = utilisateurLie;
	}
	
	public Grille getGrilleLiee() {
		return grilleLiee;
	}
	
	public void setGrilleLiee(Grille grilleLiee) {
		this.grilleLiee = grilleLiee;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
