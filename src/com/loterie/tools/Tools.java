package com.loterie.tools;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import com.google.common.hash.Hashing;
import com.loterie.config.Constants;

public class Tools {
	private static final String LETTRES_MAJ 	= "abcdefghijklmnopqrstuvwxyz";
	private static final String LETTRES_MIN 	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NOMBRES 		= "0123456789";
	private static final String SPECIAUX 		= "&-_=$;:!£%?./+#@[] {}";
	
	public static final int VERIF_TAILLE 		= 1;
	public static final int VERIF_REGEXP 		= 2;

	public static boolean comparerChainesNonNull(String s1, String s2) {
		boolean result = s1 != null && s1.equals(s2);
		
		if (!result) {
			if (s1 == null) {
				System.out.println("[WARNING]: Tools.comparerChainesNonNull(): La comparaison a échoué car s1 est null");
			} else {
				System.out.println("[WARNING]: Tools.comparerChainesNonNull(): La comparaison a échoué car s2 est différent de s1");
			}
		}
		
		return result;
	}
	
	public static boolean motDePasseValide(String motDePasse, int[] options, Object[] criteres) {
		boolean resultat = true;
		boolean checkpoint = false;
		
		for (int i = 0; i < options.length; i++) {
			int option = options[i];
			
			if (option == VERIF_TAILLE) {
				checkpoint = motDePasse.length() >= (int) criteres[i];
				resultat &= checkpoint;
				
				if (! checkpoint) {
					System.out.println("[WARNING]: Tools.motDePasseValide(): Le mot de passe ne respecte pas l'étape LENGTH_CHECK");
				}
			}
		}
		return resultat;
	}
	
	public static String chaineAleatoire(int taille, boolean casse, boolean nombres, boolean speciaux) {
		String resultat = "";
		String characteres = LETTRES_MAJ;
		
		if (casse) {
			characteres += LETTRES_MIN;
		}
		
		if (nombres) {
			characteres += NOMBRES;
		}
		
		if (speciaux) {
			characteres += SPECIAUX;
		}
		
		while (resultat.length() < taille) {
			resultat += characteres.toCharArray()[(int) Math.round(Math.random() * (characteres.length() - 1))];
		}
		
		return resultat;
	}
	
	public static String encoderSHA256(String chaine) {
		return Hashing.sha256().hashString(chaine, Charset.forName("UTF-8")).toString();
	}
	
	public static String URIsansContexte(HttpServletRequest req) {
		return req.getRequestURI().replaceAll(Constants.CONTEXTE, "");
	}
}