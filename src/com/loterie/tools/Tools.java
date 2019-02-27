package com.loterie.tools;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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
		boolean result = s1 != null && s1.trim().length() > 0 && s1.equals(s2);
		
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
			throw new Exception("options.length != criteres.length");
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
				resultat = resultat && checkpoint;
				
				// Lève une exception si le checkpoint est invalide
				if (!checkpoint) {
					System.out.printf(Messages.E_INTEGRITE_ETAPE_MANQUEE, "VERIF_TAILLE");
				}
			}
			// Si il est demandé de vérifier une correspondance avec une REGEXP
			if (option == VERIF_REGEXP) {
				throw new Exception("Not yet implemented!");
				/*checkpoint = motDePasse.matches((String) criteres[i]);
				// Concatène le resultat du checkpoint à la vérification globale
				resultat &= checkpoint;
				
				// Lève une exception si le checkpoint est invalide
				if (! checkpoint) {
					System.out.printf(Messages.E_INTEGRITE_ETAPE_MANQUEE, "VERIF_REGEXP");
				}*/
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
	 * @param chaine - la chaîne de caractères à encoder
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
		return padRight(maintenant.getYear(), 4) + "-" + padRight(maintenant.getMonthOfYear(), 2) + "-" + 
				padRight(maintenant.getDayOfMonth(), 2);
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

	/**
	 * <b><i>getProchainJour</i></b><br />
	 * <pre>public static {@link java.lang.String String} getProchainJour(int idJour, {@link org.joda.time.DateTime DateTime} dateDebut)</pre>
	 * Récupère la date (au format yyyy-MM-dd) du prochain jour identifié par idJour depuis dateDebut
	 * 
	 * @param idJour - numéro du jour recherché
	 * @param dateDebut - date de référence
	 * @return la chaîne justifiée à gauche
	 */
	public static String getProchainJour(int idJour, DateTime dateDebut) {
		// Récupère le numéro de jour dans la semaine de la date donnée
		int jourDebut = dateDebut.getDayOfWeek();
		
		// Pas de traitement si le jour recherché est le même que celui de la date donnée
		if (idJour != jourDebut) {
			// Calcule le delta entre le jour demandé et la date de début et ajoute le delta à la date de début
			if (idJour > jourDebut) {
				dateDebut = dateDebut.plusDays(idJour - jourDebut);
			} else {
				dateDebut = dateDebut.plusDays(7 - (jourDebut - idJour));
			}
		}
		return dateDebut.getYear() + "-" + padRight(dateDebut.getMonthOfYear(), 2) + "-" + 
			padRight(dateDebut.getDayOfMonth(), 2) + " 12:00:00";
	}

	/**
	 * <b><i>executerRequete</i></b><br />
	 * <pre>public static {@link java.lang.Object Object} executerRequete({@link java.lang.String String} reqStr, {@link java.util.Map Map}<{@link java.lang.String String}, {@link java.lang.Object Object}> params, {@link javax.persistence.EntityManager EntityManager} em, boolean multiple, {@link java.lang.String String} source)</pre>
	 * Exécute une requête en base et retourne son resultat sous forme d'{@link java.lang.Object Object}
	 * 
	 * @param reqStr - requête à exécuter
	 * @param params - paramètres à ajouter à la requête
	 * @param em - EntityManager utilisé
	 * @param multiple - [F] indique si le résultat attendu est une liste
	 * @param source - nom de la classe et méthode appelant la requête
	 * @return un objet ou une liste d'objet selon <tt>multiple</tt>
	 */
	public static Object executerRequete(String reqStr, Map<String, Object> params, EntityManager em, boolean multiple, 
			String source) {
		List<Object> resultats = null;
		
		try {
			// La requête est créée dans l'EntityManager
			Query query = em.createQuery(reqStr);
			
			// Tous les paramètres sont ajoutées à la requête
			if (params != null) {
				for (Map.Entry<String, Object> entree : params.entrySet()) {
					query.setParameter(entree.getKey(), entree.getValue());
				}
			}
			resultats = query.getResultList();
			
			if (!(resultats.size() > 0)) {
				/*System.out.println(query.toString());
				Object[] keys = params.keySet().toArray();
				
				for (Object k : keys) {
					String key = (String) k;
					System.out.println(key + " -> " + params.get(key));
				}*/
				throw new NoResultException();
			}
			
			if (multiple) {
				// La liste des résultats est stockée dans l'objet de retour
				return resultats;
			} else {
				// Le résultat unique est stocké dans l'objet de retour
				return resultats.get(0);
			}
		} catch (NoResultException e) {
			System.out.printf(Messages.A_RESULTAT_VIDE, source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * <b><i>getDateSlash</i></b><br />
	 * <pre>public static {@link java.lang.String String} getDateSlash()</pre>
	 * Retourne la date du moment
	 * 
	 * @return la date du moment au format jj/mm/aaaa
	 */
	public static String getDateSlash() {
		DateTime now = new DateTime();
		String jour = String.valueOf(now.getDayOfMonth());
		String mois = String.valueOf(now.getMonthOfYear());
		String annee = String.valueOf(now.getYear());
		
		if (jour.length() == 1) {
			jour = "0" + jour;
		}
		
		if (mois.length() == 1) {
			mois = "0" + mois;
		}
		return jour + "/" + mois + "/" + annee;
	}

	/**
	 * <b><i>getDateTiret</i></b><br />
	 * <pre>public static {@link java.lang.String String} getDateTiret()</pre>
	 * Retourne la date du moment
	 * 
	 * @return la date du moment au format aaaa-mm-jj
	 */
	public static String getDateTiret() {
		DateTime now = new DateTime();
		String jour = String.valueOf(now.getDayOfMonth());
		String mois = String.valueOf(now.getMonthOfYear());
		String annee = String.valueOf(now.getYear());
		
		if (jour.length() == 1) {
			jour = "0" + jour;
		}
		
		if (mois.length() == 1) {
			mois = "0" + mois;
		}
		return annee + "-" + mois + "-" + jour;
	}

	/**
	 * <b><i>getHeure</i></b><br />
	 * <pre>public static {@link java.lang.String String} getHeure()</pre>
	 * Retourne l'heure du moment
	 * 
	 * @return l'heure du moment au format hh:mm:ss
	 */
	public static String getHeure() {
		DateTime now = new DateTime();
		return now.getHourOfDay() + ":" + now.getMinuteOfHour() + ":" + now.getSecondOfMinute();
	}
	
	/**
	 * <b><i>concatenerTableauxString</i></b><br />
	 * <pre>public static {@link java.lang.String String}[] concatenerTableauxString()</pre>
	 * 
	 * @param arrays
	 * @return
	 */
	public static String[] concatenerTableauxString(String[] ... arrays) {
		int taille = 0;
		int pos = 0;
		
		for (String[] array : arrays) {
			taille += array.length;
		}
		
		if (taille == 0) {
			return null;
		} else {
			String[] resultat = new String[taille];
			
			for (String[] array : arrays) {
				int length = array.length;
				System.arraycopy(array, 0, resultat, pos, length);
				pos += length;
			}
			return resultat;
		}
	}
	
	/**
	 * <b><i>titre</i></b><br />
	 * <pre>public static {@link java.lang.String String} titre({@link java.lang.String String} str)</pre>
	 * Permet de mettre le première lettre d'une chaîne en majuscule
	 * 
	 * @param str - chaîne à mettre en titre
	 * @return la chaîne mise en titre
	 */
	public static String titre(String param) {
		String r = "";
		
		if (param.length() > 0) {
			r = param.substring(0, 1).toUpperCase();
		}
		
		if (param.length() > 1) {
			r += param.substring(1);
		}
		
		return r;
	}
}