package com.loterie.tools;

import java.nio.charset.Charset;

import org.joda.time.DateTime;
import com.google.common.hash.Hashing;
import com.loterie.config.Constants;
import com.loterie.config.Messages;

public class Tools {	
	public static final int VERIF_TAILLE 		= 1;
	public static final int VERIF_REGEXP 		= 2;

	/**
	 * <b><i>comparerChainesNonNull</i></b><br />
	 * <pre>public static boolean comparerChainesNonNull({@link java.lang.String String} s1, {@link java.lang.String String} s2)</pre>
	 * Compare deux chaînes de caractères et vérifie qu'elles ne sont pas <tt>null</tt>. 
	 * 
	 * @param s1 - première chaîne à comparer
	 * @param s2 - deuxième chaîne à comparer
	 * @return <tt>true</tt> si s1 = s2 et qu'aucune des deux chaînes n'est <tt>null</tt>.
	 */
	public static boolean comparerChainesNonNull(String s1, String s2) {
		boolean result = s1 != null && s1.equals(s2);
		
		if (!result) {
			if (s1 == null) {
				System.out.println(Messages.E_COMPARER_CHAINES_NULL);
			} else {
				System.out.println(Messages.E_COMPARER_CHAINES_DIFFERENT);
			}
		}
		
		return result;
	}
	
	/**
	 * <b><i>motDePasseValide</i></b><br />
	 * <pre>public static boolean motDePasseValide({@link java.lang.String String} motDePasse, int[] options, {@link java.lang.Object Object}[] criteres) throws {@link java.lang.Exception Exception}</pre>
	 * Vérifie l'intégrité d'une chaîne de caractères selon les options et critères passés
	 * en paramètre
	 *
	 * @param motDePasse - chaîne à vérifier
	 * @param options - étapes de validation
	 * @param criteres - critères de validation
	 * @return <tt>true</tt> si toutes les étapes sont validées, <tt>false</tt> sinon
	 * @throws Exception si le nombre de critères diffère du nombre d'options
	 */
	public static boolean motDePasseValide(String motDePasse, int[] options, Object[] criteres) throws Exception {
		boolean resultat = true;
		boolean checkpoint = false;
		
		/* Il faut que le tableau de critères contienne strictement le même nombre
		 * d'objets que le tableau d'options, sinon une exception est levée
		 */
		if (options.length != criteres.length) {
			throw new Exception();
		}
		
		/* Pour chaque option passée en paramètre,
		 * on valide son critère associé
		 */
		for (int i = 0; i < options.length; i++) {
			int option = options[i];
			
			// Si il est demandé de vérifier la taille de la chaîne
			if (option == VERIF_TAILLE) {
				checkpoint = motDePasse.length() >= (int) criteres[i];
				// Concatène le resultat du checkpoint à la vérification globale
				resultat &= checkpoint;
				
				// Lève une exception si le checkpoint est invalide
				if (! checkpoint) {
					System.out.printf(Messages.E_INTEGRITE_ETAPE_MANQUEE, "VERIF_TAILLE");
				}
			}
			// Si il est demandé de vérifier une correspondance avec une REGEXP
			if (option == VERIF_REGEXP) {
				checkpoint = motDePasse.matches((String) criteres[i]);
				// Concatène le resultat du checkpoint à la vérification globale
				resultat &= checkpoint;
				
				// Lève une exception si le checkpoint est invalide
				if (! checkpoint) {
					System.out.printf(Messages.E_INTEGRITE_ETAPE_MANQUEE, "VERIF_REGEXP");
				}
			}
		}
		// Retourne le résultat globale du passage dans les différents checkpoints
		return resultat;
	}
	
	/**
	 * <b><i>chaineAleatoire</i></b><br />
	 * <pre>public static {@link java.lang.String String} chaineAleatoire(int taille, boolean casse, boolean nombres, boolean speciaux)</pre>
	 * Génère une chaîne de caractères aléatoire selon les options passées en paramètre
	 * 
	 * @param taille - taille de la chaîne de caractères à générer 
	 * @param casse - [F] utilisation de lettres majuscules pour générer la chaîne
	 * @param chiffres - [F] utilisation de chiffres pour générer la chaîne
	 * @param speciaux - [F] utilisation de caractères spéciaux pour générer la chaîne
	 * @return la chaîne de caractères générée
	 */
	public static String chaineAleatoire(int taille, boolean casse, boolean chiffres, boolean speciaux) {
		String resultat = "";
		// Crée le pool de caractères de base (lettres minuscules)
		String characteres = Constants.LETTRES_MIN;
		
		// Si le flag casse est présent, les lettres majuscules sont ajoutées au pool de caractères
		if (casse) {
			characteres += Constants.LETTRES_MAJ;
		}

		// Si le flag chiffres est présent, les chiffres sont ajoutés au pool de caractères
		if (chiffres) {
			characteres += Constants.CHIFFRES;
		}

		// Si le flag speciaux est présent, les caractères speciaux sont ajoutés au pool de caractères
		if (speciaux) {
			characteres += Constants.SPECIAUX;
		}
		
		// Chaque caractère est choisit aléatoirement dans le pool de caractères et est ajouté à la chaîne de retour
		while (resultat.length() < taille) {
			resultat += characteres.toCharArray()[(int) Math.round(Math.random() * (characteres.length() - 1))];
		}
		
		return resultat;
	}
	
	/**
	 * <b><i>encoderSHA256</i></b><br />
	 * <pre>public static {@link java.lang.String String} encoderSHA256({@link java.lang.String String} chaine)</pre>
	 * Encode la chaîne de caractères au format sha256
	 * 
	 * @param chaine la chaîne de caractères à encoder
	 * @return la chaîne encodée en sha256
	 */
	public static String encoderSHA256(String chaine) {
		return Hashing.sha256().hashString(chaine, Charset.forName("UTF-8")).toString();
	}
	
	/**
	 * <b><i>getMaintenant</i></b><br />
	 * <pre>public static {@link java.lang.String String} getMaintenant()</pre>
	 * Retourne la date du jour au format 'AAAA-MM-JJ'
	 * 
	 * @return la date courante au format 'yyyy-MM-dd'
	 */
	public static String getMaintenant() {
		DateTime maintenant = new DateTime();
		return maintenant.getYear() + "-" + maintenant.getMonthOfYear() + "-" + maintenant.getDayOfMonth();
	}
	
	/**
	 * <b><i>getMaintenant</i></b><br />
	 * <pre>public static {@link java.lang.String String} getMaintenant()</pre>
	 * Retourne le numéro dans l'année de la semaine courante
	 * 
	 * @return le numéro de la semaine courante
	 */
	public static int getNumeroSemaineCourante() {
		DateTime maintenant = new DateTime();		
		return maintenant.getWeekOfWeekyear();
	}
	
	/**
	 * <b><i>padRight</i></b><br />
	 * <pre>public static {@link java.lang.String String} padRight({@link java.lang.Object Object} o, int n)</pre>
	 * Justifie l'objet passé en paramètre (<tt>String</tt>, <tt>int</tt> ou autre format convertible en {@link java.lang.String String})
	 * en ajoutant devant la chaîne autant de <tt>'0'</tt> que nécessaire pour atteindre la taille souhaitée
	 * <pre><i>Exemple : </i><ul><li>padRight(1, 2) = "01"</li><li>padRight("exemple", 10) = "000exemple"</li></pre> 
	 * 
	 * @param o - objet à justifier
	 * @param n - taille de la chaîne en sortie
	 * @return la chaîne justifiée à droite
	 * @see #padRight(Object, int, char)
	 */
	public static String padRight(Object o, int n) {
		return padRight(o, n, '0');  
	}
	
	/**
	 * <b><i>padRight</i></b><br />
	 * <pre>public static {@link java.lang.String String} padRight({@link java.lang.Object Object} o, int n, char c)</pre>
	 * Justifie l'objet passé en paramètre (<tt>String</tt>, <tt>int</tt> ou autre format convertible en {@link java.lang.String String})
	 * en ajoutant devant la chaîne autant de caractère <tt>c</tt> que nécessaire pour atteindre la taille souhaitée
	 * <pre><i>Exemple : </i><ul><li>padRight(1, 2, "0") = "01"</li><li>padRight("exemple", 10, "#") = "###exemple"</li></pre>
	 * 
	 * @param o - objet à justifier
	 * @param n - taille de la chaîne en sortie
	 * @param c - caractère de justification
	 * @return la chaîne justifiée à droite
	 */
	public static String padRight(Object o, int n, char c) {
		// Convertit en String l'objet passé en paramètre
		String source = String.valueOf(o);
		String retour = "";

		// Si la chaîne à justifier dépasse la taille demandé, la chaîne est retournée sans transformation
		if (source.length() >= n) {
			retour = source;
		} else {
			// Ajoute les caractères nécessaires à la chaîne de retour avant d'y ajouter la source
			for (int i = 0; i < (n - source.length()); i++) {
				retour += c;
			}
			retour += source;
		}
		return retour;
	}

	
	/**
	 * <b><i>padLeft</i></b><br />
	 * <pre>public static {@link java.lang.String String} padLeft({@link java.lang.Object Object} o, int n)</pre>
	 * Justifie l'objet passé en paramètre (<tt>String</tt>, <tt>int</tt> ou autre format convertible en {@link java.lang.String String})
	 * en ajoutant devant la chaîne autant de <tt>'0'</tt> que nécessaire pour atteindre la taille souhaitée
	 * <pre><i>Exemple : </i><ul><li>padRight(1, 2) = "10"</li><li>padRight("exemple", 10) = "exemple000"</li></pre>
	 * 
	 * @param o - objet à justifier
	 * @param n - taille de la chaîne en sortie
	 * @return la chaîne justifiée à gauche
	 * @see #padLeft(Object, int, char)
	 */
	public static String padLeft(Object o, int n) {
		return padLeft(o, n, '0');  
	}

	
	/**
	 * <b><i>padLeft</i></b><br />
	 * <pre>public static {@link java.lang.String String} padLeft({@link java.lang.Object Object} o, int n, char c)</pre>
	 * Justifie l'objet passé en paramètre (<tt>String</tt>, <tt>int</tt> ou autre format convertible en {@link java.lang.String String})
	 * en ajoutant après la chaîne autant de caractère <tt>c</tt> que nécessaire pour atteindre la taille souhaitée
	 * <pre><i>Exemple : </i><ul><li>padRight(1, 2, "0") = "10"</li><li>padRight("exemple", 10, "#") = "exemple###"</li></pre>
	 * 
	 * @param o - objet à justifier
	 * @param n - taille de la chaîne en sortie
	 * @param c - caractère de justification
	 * @return la chaîne justifiée à gauche
	 */
	public static String padLeft(Object o, int n, char c) {
		// Convertit en String l'objet passé en paramètre
		String source = String.valueOf(o);
		// Ajoute la source au retour
		String retour = source;

		// Si la chaîne à justifier dépasse la taille demandé, la chaîne est retournée sans transformation
		if (source.length() < n) {
			// Ajoute les caractères nécessaires à la chaîne de retour pour atteindre la taille souhaitée
			for (int i = 0; i < (n - source.length()); i++) {
				retour += c;
			}
		}
		return retour;
	}
	
	public static String getProchainJour(int idJour, DateTime dateDebut) {
		int jourDebut = dateDebut.getDayOfWeek();
		
		if (idJour != jourDebut) {
			if (idJour > jourDebut) {
				dateDebut = dateDebut.plusDays(idJour - jourDebut);
			} else {
				dateDebut = dateDebut.plusDays(7 - (jourDebut - idJour));
			}
		}
		return dateDebut.getYear() + "-" + padRight(dateDebut.getMonthOfYear(), 2) + "-" + padRight(dateDebut.getDayOfMonth(), 2);
	}
}