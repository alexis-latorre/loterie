package com.loterie.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "jeu_declinaison")
public class JeuDeclinaison {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JoinColumn(name = "fk_jeu_id")
	private Jeu jeu;
	private String index1;
	private String index2;
	private String index3;
	private String index4;
	private String index5;
	private String index6;
	private String index7;
	private String index8;
	private String index9;
	private String index10;
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String param6;
	private String param7;
	private String param8;
	private String param9;
	private String param10;
	@Transient
	private Map<String, String> indexes = new HashMap<String, String>();
	@Transient
	private Map<String, String> params = new HashMap<String, String>();
	
	public JeuDeclinaison() {
		
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public String getIndex1() {
		return index1;
	}

	public void setIndex1(String index1) {
		this.index1 = index1;
	}

	public String getIndex2() {
		return index2;
	}

	public void setIndex2(String index2) {
		this.index2 = index2;
	}

	public String getIndex3() {
		return index3;
	}

	public void setIndex3(String index3) {
		this.index3 = index3;
	}

	public String getIndex4() {
		return index4;
	}

	public void setIndex4(String index4) {
		this.index4 = index4;
	}

	public String getIndex5() {
		return index5;
	}

	public void setIndex5(String index5) {
		this.index5 = index5;
	}

	public String getIndex6() {
		return index6;
	}

	public void setIndex6(String index6) {
		this.index6 = index6;
	}

	public String getIndex7() {
		return index7;
	}

	public void setIndex7(String index7) {
		this.index7 = index7;
	}

	public String getIndex8() {
		return index8;
	}

	public void setIndex8(String index8) {
		this.index8 = index8;
	}

	public String getIndex9() {
		return index9;
	}

	public void setIndex9(String index9) {
		this.index9 = index9;
	}

	public String getIndex10() {
		return index10;
	}

	public void setIndex10(String index10) {
		this.index10 = index10;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}

	public String getParam6() {
		return param6;
	}

	public void setParam6(String param6) {
		this.param6 = param6;
	}

	public String getParam7() {
		return param7;
	}

	public void setParam7(String param7) {
		this.param7 = param7;
	}

	public String getParam8() {
		return param8;
	}

	public void setParam8(String param8) {
		this.param8 = param8;
	}

	public String getParam9() {
		return param9;
	}

	public void setParam9(String param9) {
		this.param9 = param9;
	}

	public String getParam10() {
		return param10;
	}

	public void setParam10(String param10) {
		this.param10 = param10;
	}

	public Map<String, String> getIndexes() {
		return indexes;
	}

	public void setIndexes(Map<String, String> indexes) {
		this.indexes = indexes;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
