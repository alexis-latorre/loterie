package com.loterie.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.loterie.tools.Tools;

class TestTools {
	private Date now = new Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private DateTime dt = new DateTime();

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		testComparerChainesNonNull("correct", "correct", true);
		testComparerChainesNonNull("incorrect", "correct", false);
		testComparerChainesNonNull(null, "null", false);

		int[] options = {
				Tools.VERIF_TAILLE
				};
		Object[] criteres = {
				8
				};
		testMotDePasseValide("test", options, criteres, false);
		testMotDePasseValide("testtest", options, criteres, true);

		options[0] = -1;
		testMotDePasseValide("test", options, criteres, true);
		
		/* Étant donné que la chaîne retournée est constituée de caractères 
		 * aléatoires, on ne test que la taille de la chaîne attendue */
		testChaineAleatoire(10, false, false, false, 10);
		testChaineAleatoire(10, true, true, true, 10);
		
		testEncoderSHA256("mdp256", "2d39e4cc7a57ab53478f92864b3ec2ff230354558f046478f11a1ecf62fa1aea");

		int[] options2 = {
				Tools.VERIF_TAILLE,
				Tools.VERIF_REGEXP
				};
		Object[] criteres2 = {
				8,
				"test"
				};
		try {
			testMotDePasseValide("test", options2, criteres2, true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		int[] options3 = {
				3
				};
		Object[] criteres3 = {
				8,
				"test"
				};
		try {
			testMotDePasseValide("test", options3, criteres3, true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		testDateTools();
		
		testGetNumeroSemaineCourante();

		testPadLeft(false);
		testPadLeft(true);

		testGetProchainJour(0);
		testGetProchainJour(1);
		testGetProchainJour(6);
	}

	private void testGetProchainJour(int jour) {
		DateTime search = dt.plusDays(jour);
		
		Object[] expected = {
				sdf.format(search.toDate())
		};
		
		Object[] actual = {
				Tools.getProchainJour(search.getDayOfWeek(), dt)
		};
		assertArrayEquals(expected, actual);
	}

	private void testPadLeft(boolean longueur) {
		String in = "ABC";
		String out = "ABC000";
		
		if (longueur) {
			in = "ABCDEFGHI";
			out = "ABCDEFGHI";
		}
		
		Object[] expected = {
				out
		};
		
		Object[] actual = {
				Tools.padLeft(in, 6)
		};
		assertArrayEquals(expected, actual);
	}

	private void testGetNumeroSemaineCourante() {
		Object[] expected = {
				dt.getWeekOfWeekyear()
		};
		
		Object[] actual = {
				Tools.getNumeroSemaineCourante()
		};
		assertArrayEquals(expected, actual);
	}

	private void testDateTools() {
		Object[] expected = {
				sdf.format(now)
		};
		
		Object[] actual = {
				Tools.getMaintenant()
		};
		assertArrayEquals(expected, actual);		
	}

	private void testMotDePasseValide(String motDePasse, int[] options, Object[] criteres, boolean resultat) throws Exception {
		Object[] expected = {
				resultat
		};
		
		Object[] actual = {
				Tools.motDePasseValide(motDePasse, options, criteres)
		};
		assertArrayEquals(expected, actual);
	}

	private void testComparerChainesNonNull(String s1, String s2, boolean resultat) {
		Object[] expected = {
				resultat
		};
		
		Object[] actual = {
				Tools.comparerChainesNonNull(s1, s2)
		};
		assertArrayEquals(expected, actual);
	}
	
	private void testChaineAleatoire(int taille, boolean casse, boolean nombres, boolean speciaux, int resultat) {
		Object[] expected = {
				resultat
		};
		
		Object[] actual = {
				Tools.chaineAleatoire(taille, casse, nombres, speciaux).length()
		};
		assertArrayEquals(expected, actual);
	}
	
	private void testEncoderSHA256(String chaine, String resultat) {
		Object[] expected = {
				resultat
		};
		
		Object[] actual = {
				Tools.encoderSHA256(chaine)
		};
		assertArrayEquals(expected, actual);
	}
	
	public TestTools() throws Exception {
		setUp();
	}
}
