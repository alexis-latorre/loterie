package com.loterie.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.loterie.business.JourHTML;
import com.loterie.business.MoisHTML;
import com.loterie.entities.Grille;

class TestBusiness {
	private MoisHTML ajdHTML = null;
	private MoisHTML origineHTML = null;
	private MoisHTML premierLundiMoisHTML = null;
	private MoisHTML moisSuivantHTML = null;
	private DateTime ajd = new DateTime()
			.withYear(2018)
			.withMonthOfYear(9)
			.withDayOfMonth(12)
			.withHourOfDay(0)
			.withMinuteOfHour(0)
			.withSecondOfMinute(0);
	private DateTime moisPrecedent = ajd.minusMonths(1);
	private DateTime moisSuivant = ajd.plusMonths(1);
	private DateTime origine = new DateTime()
			.withYear(1970)
			.withMonthOfYear(1)
			.withDayOfMonth(1)
			.withHourOfDay(0)
			.withMinuteOfHour(0)
			.withSecondOfMinute(0);
	private DateTime premierLundiMois = new DateTime()
			.withYear(2018)
			.withMonthOfYear(1)
			.withDayOfMonth(1)
			.withHourOfDay(0)
			.withMinuteOfHour(0)
			.withSecondOfMinute(0);
	private int dernierJour = 31;
	private int numJour = 12;
	private String premiereDate = "2018-08-27";
	private String derniereDate = "2018-09-30";
	private String[] joursHTML = {"2018-08-27", "2018-08-28", "2018-08-29", "2018-08-30", "2018-08-31", "2018-09-01", 
			"2018-09-02", "2018-09-03", "2018-09-04", "2018-09-05", "2018-09-06", "2018-09-07", "2018-09-08", 
			"2018-09-09", "2018-09-10", "2018-09-11", "2018-09-12", "2018-09-13", "2018-09-14", "2018-09-15", 
			"2018-09-16", "2018-09-17", "2018-09-18", "2018-09-19", "2018-09-20", "2018-09-21", "2018-09-22", 
			"2018-09-23", "2018-09-24", "2018-09-25", "2018-09-26", "2018-09-27", "2018-09-28", "2018-09-29", 
			"2018-09-30"};
	private List<JourHTML> jours = new ArrayList<JourHTML>();
	
	private JourHTML jourHTML = null;
	private Grille grille = new Grille();
	
	@BeforeEach
	void setUp() throws Exception {
		ajdHTML = new MoisHTML(ajd);
		origineHTML = new MoisHTML(origine);
		premierLundiMoisHTML = new MoisHTML(premierLundiMois);
		moisSuivantHTML = new MoisHTML(moisSuivant);
		
		jourHTML = new JourHTML(origine);
	}

	@Test
	public void test() {
		testGetDernierJour();
		
		testGetAujourdhui();
		
		testGetPremiereDate();
		
		testGetDerniereDate();
		
		testGetNumero();
		
		for (String jourStr : joursHTML) {
			jours.add(new JourHTML(DateTime.parse(jourStr)));
		}		
		testGetJours();

		testTrouverJourParDate(true);
		testTrouverJourParDate(false);
		
		testGetNom();
		
		testGetAnnee();
		
		testGetMoisPrecedent();
		
		testGetMoisSuivant();

		testIsAnneeAjd(ajdHTML, true);
		testIsAnneeAjd(origineHTML, false);

		testIsMoisAjd(ajdHTML, true);
		testIsMoisAjd(moisSuivantHTML, false);
		
		ajdHTML.combinerJours(null);
		
		testGetNumeroDansSemaine();
		
		testGetNumeroDansMois();
		
		testGetNumeroDansAnnee();
		
		testGetNumeroMois();
		
		testGetNomLong();
		
		testGetNomCourt();
		
		testGetInitiale();
		
		jourHTML.addGrille(grille);
		
		testGetGrilles();
		
		testIsPaye();
	}

	private void testIsPaye() {
		Object[] expected = {
				false
		};
		
		Object[] actual = {
				jourHTML.isPaye()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetGrilles() {
		List<Grille> grilles = new ArrayList<Grille>();
		grilles.add(grille);
		
		Object[] expected = {
				grilles
		};
		
		Object[] actual = {
				jourHTML.getGrilles()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetInitiale() {
		Object[] expected = {
				"j"
		};
		
		Object[] actual = {
				jourHTML.getInitiale()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetNomCourt() {
		Object[] expected = {
				"jeu"
		};
		
		Object[] actual = {
				jourHTML.getNomCourt()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetNomLong() {
		Object[] expected = {
				"jeudi"
		};
		
		Object[] actual = {
				jourHTML.getNomLong()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetNumeroMois() {
		Object[] expected = {
				1
		};
		
		Object[] actual = {
				jourHTML.getNumeroMois()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetNumeroDansAnnee() {
		Object[] expected = {
				1
		};
		
		Object[] actual = {
				jourHTML.getNumeroDansAnnee()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetNumeroDansMois() {
		Object[] expected = {
				1
		};
		
		Object[] actual = {
				jourHTML.getNumeroDansMois()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetNumeroDansSemaine() {
		Object[] expected = {
				4
		};
		
		Object[] actual = {
				jourHTML.getNumeroDansSemaine()
		};
		assertArrayEquals(expected, actual);
	}

	private void testIsMoisAjd(MoisHTML mois, boolean ex) {
		Object[] expected = {
				ex
		};
		
		Object[] actual = {
				mois.isMoisAjd()
		};
		assertArrayEquals(expected, actual);
	}

	private void testIsAnneeAjd(MoisHTML mois, boolean ex) {
		Object[] expected = {
				ex
		};
		
		Object[] actual = {
				mois.isAnneeAjd()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetMoisSuivant() {
		Object[] expected = {
				moisSuivant
		};
		
		Object[] actual = {
				ajdHTML.getMoisSuivant()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetMoisPrecedent() {
		Object[] expected = {
				moisPrecedent
		};
		
		Object[] actual = {
				ajdHTML.getMoisPrecedent()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetAnnee() {
		Object[] expected = {
				1970
		};
		
		Object[] actual = {
				origineHTML.getAnnee()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetNom() {
		Object[] expected = {
				"janvier"
		};
		
		Object[] actual = {
				origineHTML.getNom()
		};
		assertArrayEquals(expected, actual);
	}

	private void testTrouverJourParDate(boolean test) {
		if (test) {
			Object[] expected = {
					jours.get(0).toString()
			};
			
			Object[] actual = {
					ajdHTML.trouverJourParDate(premiereDate).toString()
			};
			assertArrayEquals(expected, actual);
		} else {
			Object[] expected = {
					null
			};
			
			Object[] actual = {
					ajdHTML.trouverJourParDate("0000-00-00")
			};
			assertArrayEquals(expected, actual);
		}
	}
	
	private boolean compareArrays(List<JourHTML> arr1, List<JourHTML> arr2) {
		int i = 0;
		
		if (arr1.size() != arr2.size()) {
			System.out.println(arr1.size() + " != " + arr2.size());
			return false;
		}
		
		for (JourHTML jour : arr1) {
			if (!jour.toString().equals(arr2.get(i).toString())) {
				return false;
			}
			i++;
		}
		
		return true;
	}

	private void testGetJours() {
		Object[] expected = {
				true
		};
		
		Object[] actual = {
				compareArrays(jours, ajdHTML.getJours())
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetNumero() {
		Object[] expected = {
				1
		};
		
		Object[] actual = {
				origineHTML.getNumero()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetDerniereDate() {
		Object[] expected = {
				derniereDate
		};
		
		Object[] actual = {
				ajdHTML.getDerniereDate()
		};
		assertArrayEquals(expected, actual);	
	}

	private void testGetPremiereDate() {
		Object[] expected = {
				premiereDate
		};
		
		Object[] actual = {
				ajdHTML.getPremiereDate()
		};
		assertArrayEquals(expected, actual);	
	}

	private void testGetAujourdhui() {
		Object[] expected = {
				numJour
		};
		
		Object[] actual = {
				ajdHTML.getAujourdhui()
		};
		assertArrayEquals(expected, actual);		
	}

	private void testGetDernierJour() {
		Object[] expected = {
				dernierJour
		};
		
		Object[] actual = {
				premierLundiMoisHTML.getDernierJour()
		};
		assertArrayEquals(expected, actual);		
	}

	public TestBusiness() throws Exception {
		setUp();
	}
}
