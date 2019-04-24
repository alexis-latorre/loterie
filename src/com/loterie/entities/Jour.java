package com.loterie.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import com.loterie.tools.Tools;

@Entity
@Table(name = "jour")
public class Jour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "date_jour")
	private String dateJour;
	@ManyToOne(targetEntity = LienGrilleUtilisateur.class)
	@JoinColumn(name = "fk_lien_gu_id")
	private LienGrilleUtilisateur lgu;
	private Long paye;
	private Double gains;
	@Column(name = "gains_redistribues")
	private Double gainsRedistribues;
	@Column(name = "nb_joueurs")
	private Long nbJoueurs;
	@Transient
	private Date date = null;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDateJour() {
		return dateJour.substring(0, 10);
	}
	
	public void setDateJour(String dateJour) {
		this.dateJour = dateJour;
	}
	
	public void setDateJour(DateTime dt) {
		this.dateJour = Tools.padRight(dt.getYear(), 4) + "-" + Tools.padRight(dt.getMonthOfYear(), 2) + "-" + 
				Tools.padRight(dt.getDayOfMonth(), 2) + " 12:00:00";
	}
	
	public LienGrilleUtilisateur getLgu() {
		return lgu;
	}
	
	public void setLgu(LienGrilleUtilisateur lgu) {
		this.lgu = lgu;
	}
	
	public boolean getPaye() {
		return paye == 1L;
	}
	
	public void setPaye(boolean paye) {
		this.paye = paye ? 1L : 0L;
	}

	public Double getGains() {
		return gains;
	}

	public void setGains(Double gains) {
		this.gains = gains;
	}

	public Double getGainsRedistribues() {
		return gainsRedistribues;
	}

	public void setGainsRedistribues(Double gainsRedistribues) {
		this.gainsRedistribues = gainsRedistribues;
	}

	public Long getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(Long nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public Date getDate() {
		if (date == null) {
			setDate(this.dateJour);
		}
		return date;
	}
	
	private void setDate(String dateStr) {
		String[] args = dateStr.split(" ")[0].split("-");
		this.date = new DateTime()
				.withYear(Integer.parseInt(args[0]))
				.withMonthOfYear(Integer.parseInt(args[1]))
				.withDayOfMonth(Integer.parseInt(args[2]))
				.withHourOfDay(12)
				.withMinuteOfHour(0)
				.withSecondOfMinute(0)
				.withMillisOfSecond(0)
				.toDate();
	}
}
