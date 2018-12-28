package com.loterie.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.loterie.config.Constants;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pseudo;	
	private String nom;	
	private String prenom;	
	private String email;
	@Column(name = "grain_de_sel")
	private String grainDeSel;
	@Column(name = "mot_de_passe")	
	private String motDePasse;
	private Long niveau;
	@ManyToOne
	@JoinColumn(name = "fk_portefeuille_id")
	private Portefeuille portefeuille;
	@ManyToOne
	@JoinColumn(name = "fk_privilege_id")
	private Privilege privilege;
	@Transient
	private String privileges = null;
	@Transient
	private List<Grille> grilles;
	@Transient
	private String nomRole;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGrainDeSel() {
		return grainDeSel;
	}
	
	public void setGrainDeSel(String grainDeSel) {
		this.grainDeSel = grainDeSel;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
	
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public Long getNiveau() {
		return niveau;
	}
	
	public void setNiveau(Long niveau) {
		this.niveau = niveau;
	}
	
	public Portefeuille getPortefeuille() {
		return portefeuille;
	}
	
	public List<Grille> getGrilles() {
		return grilles;
	}

	public void setGrilles(List<Grille> grilles) {
		this.grilles = grilles;
	}
	
	public String getNomRole() {
		return nomRole;
	}

	public void setNomRole(String nomRole) {
		this.nomRole = nomRole;
	}

	public void setPortefeuille(Portefeuille portefeuille) {
		this.portefeuille = portefeuille;
	}
	
	public boolean estAdministrateur() {
		return this.niveau <= Constants.L_UTILISATEUR_ROLE_ADMIN;
	}
	
	public boolean estModerateur() {
		return this.niveau <= Constants.L_UTILISATEUR_ROLE_MODERATEUR;
	}
	
	public boolean estMembre() {
		return this.niveau <= Constants.L_UTILISATEUR_ROLE_MEMBRE;
	}
	
	public boolean estBasique() {
		return this.niveau <= Constants.L_UTILISATEUR_ROLE_BASIQUE;
	}
	
	public Privilege getPrivilege() {
		return this.privilege;
	}
	
	public boolean checkPrivilege(String priv) {
		if (null == privileges) {
			privileges = privilege.getPrivilege();
		}
		return privilege.checkPrivilege(priv);
	}
}
