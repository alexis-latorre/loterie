package com.ale.euromillions;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.SystemUtils;

import com.ale.csvreader.CSV;
import com.ale.csvreader.CSVReader;
import com.ale.downloader.Downloader;
import com.ale.unzipper.Unzipper;

public class EuromillionsParser {
	private static final String PROG_DIR_LINUX		= "/etc";
	private static final String PROG_DIR_WINDOWS	= "C:\\Users\\alatorre\\AppData\\Local";
	private static final boolean DEBUG				= EuromillionsUpdateDB.DEBUG;
	private static final String DB_NAME				= EuromillionsUpdateDB.DB_NAME;
	private static final String DB_USER				= EuromillionsUpdateDB.DB_USER;
	private static final String DB_PASS				= EuromillionsUpdateDB.DB_PASS;
	
	private String[] args = {""};
	private Connection connect;
	private CSV csv = null;

	private static void log(String msg) {
		if (DEBUG) {
			Date now = new Date();
			System.out.println(now +  " : " + msg);
		}
	}
	
	public static void main(String[] args) throws Exception {		
		log("Initialisation");
		log("Instanciation Parser");
		EuromillionsParser ep = new EuromillionsParser(args);
		log("Execution parser");
		ep.parse();
	}
	
	public EuromillionsParser(String[] args) {
		this.args = args;
	}
	
	private void parse() throws Exception {
		String lastId = "";
		Date now = new Date();
		String dateSlash = "";
		String dateTiret = "";
		
		if (null != args && args.length > 0) {
			log("args est renseigne : " + args);
			Pattern regexSlash = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
			Pattern regexTiret = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
			Matcher slash = regexSlash.matcher(args[0]);
			Matcher tiret = regexTiret.matcher(args[0]);
			
			if (slash.find()) {
				dateSlash = args[0];
				dateTiret = args[0].substring(6, 10) + "-" + args[0].substring(3, 5) + "-" + args[0].substring(0, 2);
			} else if (tiret.find()) {
				dateTiret = args[0];
				dateSlash = args[0].substring(8, 10) + "/" + args[0].substring(5, 7) + "/" + args[0].substring(0, 4);
			}
			log("dateSlash = " + dateSlash);
			log("dateTiret = " + dateTiret);
		}
		log("Connexion a la BDD");
		connectDB();
		
		if (dateSlash.isEmpty() && dateTiret.isEmpty()) {
            log("Pas de date renseignee");
            log("Telechargement du fichier archive");
			downloadFile();
			String newId = "";
			boolean foundLast = false;
			
			try {
		        log("Creation statement");
				Statement statement = connect.createStatement();
		        log("Requete : SELECT numero_tirage FROM resultat_euromillions ORDER BY numero_tirage DESC LIMIT 1");
	            ResultSet resultSet = statement.executeQuery("SELECT numero_tirage FROM resultat_euromillions ORDER BY numero_tirage DESC LIMIT 1");
	            
	            while (resultSet.next()) {
			        log("	Lecture ligne resultat :");
					lastId = resultSet.getString("numero_tirage");
			        log("	- lastId = " + lastId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				String sql = "";
				String date = "";

		        log("Lecture des lignes du CSV");
				for (Map<Object, String> line : csv.getLines()) {
					newId = line.get("annee_numero_de_tirage");
			        log("	ID recherche : " + newId);
					String d = line.get("date_de_tirage");
			        log("	date de tirage = " + d);
									
					if (foundLast) {
				        log("	Dernier ID atteint");
						sql += ", ('" + line.get("annee_numero_de_tirage") + "', " +
								"'" + line.get("date_de_tirage") + "', " +
								line.get("boule_1") + ", " +
								line.get("boule_2") + ", " +
								line.get("boule_3") + ", " +
								line.get("boule_4") + ", " +
								line.get("boule_5") + ", " +
								line.get("etoile_1") + ", " +
								line.get("etoile_2") + ", " +
								line.get("nombre_de_gagnant_au_rang1_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang1_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang2_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang2_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang3_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang3_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang4_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang4_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang5_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang5_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang6_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang6_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang7_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang7_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang8_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang8_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang9_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang9_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang10_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang10_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang11_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang11_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang12_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang12_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang13_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang13_Euro_Millions").replaceAll(",", ".") + ", " +
								"'" + line.get("numero_My_Million").replaceAll(",", ", ") + "')";
						date = d.substring(6, 10) + "-" + d.substring(3, 5) + "-" + d.substring(0, 2);
					} else {
						foundLast = newId.equals(lastId);
				        log("	L'ID recherche est-il egal au dernier ID ? -> " + foundLast);
					}
				}
				
				if (foundLast) {
					if (!newId.equals(lastId)) {
						log("Debut de transaction SQL");
						PreparedStatement tr = connect.prepareStatement("START TRANSACTION;");
						tr.executeUpdate();
						
						if (sql.length() > 2) {
							sql = "INSERT INTO resultat_euromillions VALUES " + sql.substring(2) + ";";
						}
						
						try {
				            PreparedStatement ps = connect.prepareStatement(sql);
				            ps.execute();
				    		log("Execustion requete : " + ps);
							System.out.println(now + " : Resultats mis a jour.");
							log("Initialisation MAJ gains en BDD");
							EuromillionsUpdateDB eudb = new EuromillionsUpdateDB(date);
							log("Execution MAJ gains en BDD");
							eudb.update();
							tr = connect.prepareStatement("COMMIT;");
							tr.executeUpdate();
							log("Commit de transaction SQL");
							ps.close();
							log("Fermeture prepared statement");
						} catch (Exception e) {
							tr = connect.prepareStatement("ROLLBACK;");
							tr.executeUpdate();
							log("Rollback sur la transaction SQL");
							e.printStackTrace();
						} finally {
							tr.close();
							log("Fermeture statement");
						}
					} else {
						System.out.println(now + " : Resultats deja a jour.");
					}
				} else {
					System.err.println(now + " : Le dernier id '" + lastId + "' n'a pas ete trouve dans le fichier.");
				}
			} catch (Exception e) {
				System.err.println(now + " : " + e.getMessage());
				e.printStackTrace();
			} finally {
				connect.close();
			}
		} else {
			log("dateSlash = " + dateSlash);
			log("dateTiret = " + dateTiret);
			String sql = "SELECT * FROM resultat_euromillions WHERE date_tirage = ?";
			PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, dateSlash);
			log("Requete : " + ps);
			ResultSet rs = ps.executeQuery();
			
			if (!rs.next()) {
				boolean found = false;
				String sql2 = "";
				log("	Aucun resultat");
				log("Telechargement de l'archive");
				downloadFile();

				log("Lecture des lignes CSV");
				for (Map<Object, String> line : csv.getLines()) {
					String d = line.get("date_de_tirage");
									
					if (d.equals(dateSlash)) {
						found = true;
						log("	La date cherchee (" + dateSlash + ") correspond a la ligne CSV");
						sql2 += ", ('" + line.get("annee_numero_de_tirage") + "', " +
								"'" + line.get("date_de_tirage") + "', " +
								line.get("boule_1") + ", " +
								line.get("boule_2") + ", " +
								line.get("boule_3") + ", " +
								line.get("boule_4") + ", " +
								line.get("boule_5") + ", " +
								line.get("etoile_1") + ", " +
								line.get("etoile_2") + ", " +
								line.get("nombre_de_gagnant_au_rang1_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang1_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang2_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang2_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang3_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang3_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang4_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang4_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang5_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang5_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang6_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang6_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang7_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang7_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang8_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang8_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang9_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang9_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang10_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang10_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang11_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang11_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang12_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang12_Euro_Millions").replaceAll(",", ".") + ", " +
								line.get("nombre_de_gagnant_au_rang13_Euro_Millions_en_europe") + ", " +
								line.get("rapport_du_rang13_Euro_Millions").replaceAll(",", ".") + ", " +
								"'" + line.get("numero_My_Million").replaceAll(",", ", ") + "')";
						PreparedStatement tr = connect.prepareStatement("START TRANSACTION;");
						tr.executeUpdate();
						log("	Debut de transaction SQL");
						
						if (sql2.length() > 2) {
							sql2 = "INSERT INTO resultat_euromillions VALUES " + sql2.substring(2) + ";";
						}
						
						try {
							log("	Creation prepared statement");
				            PreparedStatement ps2 = connect.prepareStatement(sql2);
				    		log("	Requete : " + ps2);
				    		ps2.executeUpdate();
							System.out.println(now + " : Resultats mis a jour.");
							log("Initialisation MAJ gains BDD");
							EuromillionsUpdateDB eudb = new EuromillionsUpdateDB(dateTiret);
							log("Execution MAJ gains BDD");
							eudb.update();
							tr = connect.prepareStatement("COMMIT;");
							tr.executeUpdate();
							log("	Commit de la transation");
							ps2.close();
							log("	Fermeture du prepared statement");
						} catch (Exception e) {
							tr = connect.prepareStatement("ROLLBACK;");
							tr.executeUpdate();
							log("	Rollback de la transaction");
							e.printStackTrace();
						} finally {
							tr.close();
							log("	Fermeture de la transaction");
						}
					}
				}
				
				if (!found) {
					log ("La date " + dateSlash + " n'a pas ete trouvee dans le fichier");
				}
			} else {
				log("Initialisation MAJ gains BDD");
				EuromillionsUpdateDB eudb = new EuromillionsUpdateDB(dateTiret);
				log("Execution MAJ gains BDD");
				eudb.update();
			}
		}
	}

	private void downloadFile() throws Exception {		
		String propFile;
		
		if (SystemUtils.IS_OS_WINDOWS) {
			propFile = PROG_DIR_WINDOWS;
		} else {
			propFile = PROG_DIR_LINUX;
		}		
		propFile += File.separator + "MyLoterie" + File.separator + "myLoterie.properties";
        log("lecture du fichier '" + propFile + "'");
		FileReader in = new FileReader(new File(propFile));
        log("Parsing du fichier properties");
		Properties prop = new Properties();
		prop.load(in);
        log("Fermeture du fichier properties");
		in.close();
		String url = prop.getProperty("url");
        log("url=" + url);
		String filename = prop.getProperty("filename");
        log("filename=" + filename);
		String tmpDir = prop.getProperty("tmpDir");
        log("tmpDir=" + tmpDir);

        log("Instanciation Downloader");
		Downloader dl = new Downloader();
        log("Telechargement du fichier '" + url + "/" + filename + "' vers '" + tmpDir + File.separator + filename + "'");
		dl.download(url + "/" + filename, tmpDir + File.separator + filename);

        log("Instanciation Unzipper");
		Unzipper unzipper = new Unzipper(tmpDir + File.separator + filename, tmpDir);
        log("Dezippage du fichier archive");
		unzipper.extract();
        log("Suppression de l'archive temporaire");
		new File(tmpDir + File.separator + filename).delete();

        log("Initialisation Buffer CSV");
		CSVReader csvReader = new CSVReader(tmpDir + File.separator + filename.substring(0, filename.length() - 4) + ".csv");
        log("Lecture du fichier CSV");
		csv = csvReader.getCSV();
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
