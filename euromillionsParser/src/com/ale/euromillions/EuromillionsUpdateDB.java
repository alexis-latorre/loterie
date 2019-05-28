package com.ale.euromillions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EuromillionsUpdateDB {
	public static final boolean DEBUG 				= false;
	public static final String DB_NAME				= "loterie_dev";
	public static final String DB_USER				= "loterie";
	public static final String DB_PASS				= "Gg8KxR73";
	
	public static final String EUROMILLIONS_CONDITION_RANG1					= "5:2";
	public static final String EUROMILLIONS_CONDITION_RANG2					= "5:1";
	public static final String EUROMILLIONS_CONDITION_RANG3					= "5:0";
	public static final String EUROMILLIONS_CONDITION_RANG4					= "4:2";
	public static final String EUROMILLIONS_CONDITION_RANG5					= "4:1";
	public static final String EUROMILLIONS_CONDITION_RANG6					= "3:2";
	public static final String EUROMILLIONS_CONDITION_RANG7					= "4:0";
	public static final String EUROMILLIONS_CONDITION_RANG8					= "2:2";
	public static final String EUROMILLIONS_CONDITION_RANG9					= "3:1";
	public static final String EUROMILLIONS_CONDITION_RANG10				= "3:0";
	public static final String EUROMILLIONS_CONDITION_RANG11				= "1:2";
	public static final String EUROMILLIONS_CONDITION_RANG12				= "2:1";
	public static final String EUROMILLIONS_CONDITION_RANG13				= "2:0"; 
	
	public String date = "";
	private Connection connect;

	private static void log(String msg) {
		if (DEBUG) {
			Date now = new Date();
			System.out.println(now +  " : " + msg);
		}
	}

	public static void main(String[] args) {
		log("Instanciation EuromillionsUpdateDB");
		EuromillionsUpdateDB eudb = new EuromillionsUpdateDB(args[0]);
		log("Execution update");
		eudb.update();
	}
	
	public EuromillionsUpdateDB(String date) {
		log("date = " + date);
		this.date = date;
	}
	
	public void update() {
		Date now = new Date(); 
		
		if (null == this.date || this.date.isEmpty()) {
			System.err.println("Aucune date fournie");
		}
        
    	String gainsRang1 = "";
    	String gainsRang2 = "";
    	String gainsRang3 = "";
    	String gainsRang4 = "";
    	String gainsRang5 = "";
    	String gainsRang6 = "";
    	String gainsRang7 = "";
    	String gainsRang8 = "";
    	String gainsRang9 = "";
    	String gainsRang10 = "";
    	String gainsRang11 = "";
    	String gainsRang12 = "";
    	String gainsRang13 = "";
    	//String myMillion = "";

		connectDB();
		
		try {
            Statement statement = connect.createStatement();
            Statement statement2 = connect.createStatement();
            String query = "SELECT DISTINCT g.*, j.id AS 'jourId' FROM grille g "
            		+ "JOIN lien_grille_utilisateur lgu ON lgu.fk_grille_id = g.id "
            		+ "JOIN jour j ON j.fk_lien_gu_id = lgu.id "
            		+ "WHERE j.paye = 1 AND j.date_jour = '" + this.date + " 12:00:00';";
    		log("Requete (grilles) : " + query);
            ResultSet grilles = statement.executeQuery(query);
            String date_tirage = this.date.substring(8, 10) + "/" + this.date.substring(5, 7) + "/" + this.date.substring(0, 4);
            query = "SELECT * FROM resultat_euromillions WHERE date_tirage = '" + date_tirage + "' ORDER BY numero_tirage DESC LIMIT 1;";
    		log("Requete (resultats) : " + query);
            ResultSet resultats = statement2.executeQuery(query);
        	List<String> resultatNumeros = new ArrayList<String>();
        	List<String> resultatEtoiles = new ArrayList<String>();

            while (resultats.next()) {
            	resultatNumeros.add(resultats.getString("boule1"));
            	resultatNumeros.add(resultats.getString("boule2"));
            	resultatNumeros.add(resultats.getString("boule3"));
            	resultatNumeros.add(resultats.getString("boule4"));
            	resultatNumeros.add(resultats.getString("boule5"));
            	resultatEtoiles.add(resultats.getString("etoile1"));
            	resultatEtoiles.add(resultats.getString("etoile2"));
            	gainsRang1 = resultats.getString("gains_rang1");
            	gainsRang2 = resultats.getString("gains_rang2");
            	gainsRang3 = resultats.getString("gains_rang3");
            	gainsRang4 = resultats.getString("gains_rang4");
            	gainsRang5 = resultats.getString("gains_rang5");
            	gainsRang6 = resultats.getString("gains_rang6");
            	gainsRang7 = resultats.getString("gains_rang7");
            	gainsRang8 = resultats.getString("gains_rang8");
            	gainsRang9 = resultats.getString("gains_rang9");
            	gainsRang10 = resultats.getString("gains_rang10");
            	gainsRang11 = resultats.getString("gains_rang11");
            	gainsRang12 = resultats.getString("gains_rang12");
            	gainsRang13 = resultats.getString("gains_rang13");
            	//myMillion = resultats.getString("mymillion");
            }

            while (grilles.next()) {
				String rNumeros = grilles.getString("numeros");
	    		log("rNumeros = " + rNumeros);
				String rEtoiles = grilles.getString("etoiles");
	    		log("rEtoiles = " + rEtoiles);
				String jourId = grilles.getString("jourId");
	    		log("jourId = " + jourId);

				String[] numeros = rNumeros.split(",");
				String[] etoiles = rEtoiles.split(",");

				int nbNumeros = 0;
				int nbEtoiles = 0;
				
				for (String numero : numeros) {
					if (resultatNumeros.contains(numero)) {
						nbNumeros++;
					}
				}
				
				for (String etoile : etoiles) {
					if (resultatEtoiles.contains(etoile)) {
						nbEtoiles++;
					}
				}
				
				String rang = nbNumeros + ":" + nbEtoiles;
				String gains = "0";
				
				if (rang.equals(EUROMILLIONS_CONDITION_RANG1)) {
					gains = gainsRang1;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG2)) {
					gains = gainsRang2;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG3)) {
					gains = gainsRang3;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG4)) {
					gains = gainsRang4;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG5)) {
					gains = gainsRang5;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG6)) {
					gains = gainsRang6;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG7)) {
					gains = gainsRang7;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG8)) {
					gains = gainsRang8;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG9)) {
					gains = gainsRang9;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG10)) {
					gains = gainsRang10;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG11)) {
					gains = gainsRang11;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG12)) {
					gains = gainsRang12;
				} else if (rang.equals(EUROMILLIONS_CONDITION_RANG13)) {
					gains = gainsRang13;
				}
				
				if (!gains.equals("0")) {
					query = "UPDATE jour SET gains = ? WHERE id = ?;";
		            PreparedStatement ps = connect.prepareStatement(query);
		            ps.setString(1, gains);
		            ps.setString(2, jourId);
		            ps.executeUpdate();
		            ps.close();
				}
			}
            grilles.close();
            resultats.close();
            statement.close();
            statement2.close();
            connect.close();
			System.out.println(now + " : Mise a jour des gains effectuee");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(now + " : Mise a jour des gains echouee. Recommencer");
		}
	}

	private void connectDB() {
		try {
			log("Initialisation du driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            log("Connexion a la base avec jdbc:mysql://51.255.49.30:3306/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS);
            connect = DriverManager.getConnection("jdbc:mysql://51.255.49.30:3306/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
