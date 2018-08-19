package com.loterie.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.loterie.tools.Tools;

class TestTools {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void test() {
		testComparerChainesNonNull("correct", "correct", true);
		testComparerChainesNonNull("incorrect", "correct", false);
		testComparerChainesNonNull(null, "null", false);

		int[] options = {
				Tools.VERIF_TAILLE
				};
		Object[] criteres = {
				8
				};
		try {
			testMotDePasseValide("test", options, criteres, false);
			testMotDePasseValide("testtest", options, criteres, true);

			if (options != null) {
				options[0] = -1;
			}
			testMotDePasseValide("test", options, criteres, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* Étant donné que la chaîne retournée est constituée de caractères 
		 * aléatoires, on ne test que la taille de la chaîne attendue */
		testChaineAleatoire(10, false, false, false, 10);
		testChaineAleatoire(10, true, true, true, 10);
		
		testEncoderSHA256("mdp256", "2d39e4cc7a57ab53478f92864b3ec2ff230354558f046478f11a1ecf62fa1aea");
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
