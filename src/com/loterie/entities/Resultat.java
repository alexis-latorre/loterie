package com.loterie.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resultat_euromillions")
public class Resultat {
	@Id
	@Column(name = "numero_tirage")
	private Long id;
	@Column(name = "date_tirage")
	private String date;
	@Column(name = "boule1")
	private String b1;
	@Column(name = "boule2")
	private String b2;
	@Column(name = "boule3")
	private String b3;
	@Column(name = "boule4")
	private String b4;
	@Column(name = "boule5")
	private String b5;
	@Column(name = "etoile1")
	private String e1;
	@Column(name = "etoile2")
	private String e2;
	@Column(name = "gagnant_rang1")
	private String rang1;
	@Column(name = "gagnant_rang2")
	private String rang2;
	@Column(name = "gagnant_rang3")
	private String rang3;
	@Column(name = "gagnant_rang4")
	private String rang4;
	@Column(name = "gagnant_rang5")
	private String rang5;
	@Column(name = "gagnant_rang6")
	private String rang6;
	@Column(name = "gagnant_rang7")
	private String rang7;
	@Column(name = "gagnant_rang8")
	private String rang8;
	@Column(name = "gagnant_rang9")
	private String rang9;
	@Column(name = "gagnant_rang10")
	private String rang10;
	@Column(name = "gagnant_rang11")
	private String rang11;
	@Column(name = "gagnant_rang12")
	private String rang12;
	@Column(name = "gagnant_rang13")
	private String rang13;
	@Column(name = "mymillion")
	private String myMillion;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getB1() {
		return b1;
	}

	public void setB1(String b1) {
		this.b1 = b1;
	}

	public String getB2() {
		return b2;
	}

	public void setB2(String b2) {
		this.b2 = b2;
	}

	public String getB3() {
		return b3;
	}

	public void setB3(String b3) {
		this.b3 = b3;
	}

	public String getB4() {
		return b4;
	}

	public void setB4(String b4) {
		this.b4 = b4;
	}

	public String getB5() {
		return b5;
	}

	public void setB5(String b5) {
		this.b5 = b5;
	}

	public String getE1() {
		return e1;
	}

	public void setE1(String e1) {
		this.e1 = e1;
	}

	public String getE2() {
		return e2;
	}

	public void setE2(String e2) {
		this.e2 = e2;
	}

	public String getRang1() {
		return rang1;
	}

	public void setRang1(String rang1) {
		this.rang1 = rang1;
	}

	public String getRang2() {
		return rang2;
	}

	public void setRang2(String rang2) {
		this.rang2 = rang2;
	}

	public String getRang3() {
		return rang3;
	}

	public void setRang3(String rang3) {
		this.rang3 = rang3;
	}

	public String getRang4() {
		return rang4;
	}

	public void setRang4(String rang4) {
		this.rang4 = rang4;
	}

	public String getRang5() {
		return rang5;
	}

	public void setRang5(String rang5) {
		this.rang5 = rang5;
	}

	public String getRang6() {
		return rang6;
	}

	public void setRang6(String rang6) {
		this.rang6 = rang6;
	}

	public String getRang7() {
		return rang7;
	}

	public void setRang7(String rang7) {
		this.rang7 = rang7;
	}

	public String getRang8() {
		return rang8;
	}

	public void setRang8(String rang8) {
		this.rang8 = rang8;
	}

	public String getRang9() {
		return rang9;
	}

	public void setRang9(String rang9) {
		this.rang9 = rang9;
	}

	public String getRang10() {
		return rang10;
	}

	public void setRang10(String rang10) {
		this.rang10 = rang10;
	}

	public String getRang11() {
		return rang11;
	}

	public void setRang11(String rang11) {
		this.rang11 = rang11;
	}

	public String getRang12() {
		return rang12;
	}

	public void setRang12(String rang12) {
		this.rang12 = rang12;
	}

	public String getRang13() {
		return rang13;
	}

	public void setRang13(String rang13) {
		this.rang13 = rang13;
	}

	public String getMyMillion() {
		return myMillion;
	}

	public void setMyMillion(String myMillion) {
		this.myMillion = myMillion;
	}
}
