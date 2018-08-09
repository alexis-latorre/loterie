package com.loterie.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jour")
public class Jour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long numero;
	@ManyToOne(targetEntity = LienGrilleUtilisateur.class)
	@JoinColumn(name = "fk_lien_gu_id")
	private LienGrilleUtilisateur lgu;
	private Long paye;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getNumero() {
		return numero;
	}
	
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
	public LienGrilleUtilisateur getLgu() {
		return lgu;
	}
	
	public void setLgus(LienGrilleUtilisateur lgu) {
		this.lgu = lgu;
	}
	
	public boolean isPaye() {
		return paye == 1L;
	}
	
	public void setPaye(boolean paye) {
		this.paye = paye ? 1L : 0L;
	}
}
