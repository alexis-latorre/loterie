package com.loterie.tools;

import java.util.List;

import javax.ejb.EJB;

import com.loterie.config.Constants;
import com.loterie.dao.LogDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Log;
import com.loterie.entities.Utilisateur;

/**
 * <b><i>Logger</i></b><br>
 * <pre>{@link com.loterie.tools.Logger Logger}</pre>
 * Classe permettant de logguer en base de données les messages provenant de l'application
 * 
 * @author alatorre
 *
 */
public class Logger {
	@EJB
	private LogDao logDao;

	private static Logger INSTANCE = new Logger();
	
	private Logger() {}
	
	/**
	 * <b><i>getInstance</i></b><br>
	 * <pre>private static {@link com.loterie.tools.Logger Logger} getInstance()</pre>
	 * Retourne l'instance unique du Logger d'application
	 * 
	 * @return l'instance du Logger d'application
	 */
	private static Logger getInstance() {
		return INSTANCE;
	}
	
	/**
	 * <b><i>creerLog</i></b><br>
	 * <pre>private void creerLog({@link com.loterie.entities.Log Log} log)</pre>
	 * Crée un nouveau log en base de données
	 * 
	 * @param log - message à insérer en BDD
	 */
	private void creerLog(Log log) {
		logDao.creer(log);
	}
	
	/**
	 * <b><i>lireLogs</i></b><br>
	 * <pre>private {@link java.util.List List}<{@link com.loterie.entities.Log Log}> lireLogs()</pre>
	 * Lit tous les logs de la base de données
	 * 
	 * @return la liste des logs trouvés en BDD
	 */
	private List<Log> lireLogs() {
		return logDao.trouver();
	}
	
	/**
	 * <b><i>log</i></b><br>
	 * <pre>private static void log({@link com.loterie.dao.LogDao LogDao} logDao, {@link java.lang.String String} message, {@link java.lang.String String} niveau, {@link java.lang.String String} type, {@link com.loterie.entities.Utilisateur Utilisateur} declencheur)</pre>
	 * Donne l'ordre de créer un nouveau log en BDD
	 * 
	 * @param logDao EntityManager en charge de contrôler le Logger
	 * @param message message à insérer en base de données
	 * @param niveau niveau du message à insérer en base de données (exemple : {@link com.loterie.config.Constants Constants}.LOG_SERVEUR)
	 * @param type type de message à insérer en base de données (exemple : {@link com.loterie.config.Constants Constants}.LOG_INFO)
	 * @param declencheur entité {@link com.loterie.entities.Utilisateur Utilisateur} déclenchant l'écriture du log 
	 */
	public static void log(LogDao logDao, String message, String niveau, String type, Utilisateur declencheur) {
		log(logDao, message, niveau, type, declencheur, null);
	}

	/**
	 * <b><i>log</i></b><br>
	 * <pre>private static void log({@link com.loterie.dao.LogDao LogDao} logDao, {@link java.lang.String String} message, {@link java.lang.String String} niveau, {@link com.loterie.entities.Utilisateur Utilisateur} declencheur)</pre>
	 * Donne l'ordre de créer un nouveau log en BDD
	 * 
	 * @param logDao EntityManager en charge de contrôler le Logger
	 * @param message message à insérer en base de données
	 * @param niveau niveau du message à insérer en base de données (exemple : {@link com.loterie.config.Constants Constants}.LOG_SERVEUR)
	 * @param declencheur entité {@link com.loterie.entities.Utilisateur Utilisateur} déclenchant l'écriture du log 
	 */
	public static void log(LogDao logDao, String message, String niveau, Utilisateur declencheur) {
		log(logDao, message, niveau, Constants.LOG_SERVEUR, declencheur, null);
		
	}

	/**
	 * <b><i>log</i></b><br>
	 * <pre>private static void log({@link com.loterie.dao.LogDao LogDao} logDao, {@link java.lang.String String} message, {@link com.loterie.entities.Utilisateur Utilisateur} declencheur)</pre>
	 * Donne l'ordre de créer un nouveau log en BDD
	 * 
	 * @param logDao EntityManager en charge de contrôler le Logger
	 * @param message message à insérer en base de données
	 * @param declencheur entité {@link com.loterie.entities.Utilisateur Utilisateur} déclenchant l'écriture du log 
	 */
	public static void log(LogDao logDao, String message, Utilisateur declencheur) {
		log(logDao, message, Constants.LOG_INFO, Constants.LOG_SERVEUR, declencheur, null);
	}

	/**
	 * <b><i>log</i></b><br>
	 * <pre>private static void log({@link com.loterie.dao.LogDao LogDao} logDao, {@link java.lang.String String} message, {@link java.lang.String String} niveau, {@link java.lang.String String} type)</pre>
	 * Donne l'ordre de créer un nouveau log en BDD
	 * 
	 * @param logDao EntityManager en charge de contrôler le Logger
	 * @param message message à insérer en base de données
	 * @param niveau niveau du message à insérer en base de données (exemple : {@link com.loterie.config.Constants Constants}.LOG_SERVEUR)
	 * @param type type de message à insérer en base de données (exemple : {@link com.loterie.config.Constants Constants}.LOG_INFO) 
	 */
	public static void log(LogDao logDao, String message, String niveau, String type) {
		log(logDao, message, niveau, type, null, null);
	}

	/**
	 * <b><i>log</i></b><br>
	 * <pre>private static void log({@link com.loterie.dao.LogDao LogDao} logDao, {@link java.lang.String String} message, {@link java.lang.String String} niveau)</pre>
	 * Donne l'ordre de créer un nouveau log en BDD
	 * 
	 * @param logDao EntityManager en charge de contrôler le Logger
	 * @param message message à insérer en base de données
	 * @param niveau niveau du message à insérer en base de données (exemple : {@link com.loterie.config.Constants Constants}.LOG_SERVEUR) 
	 */
	public static void log(LogDao logDao, String message, String niveau) {
		log(logDao, message, niveau, Constants.LOG_SERVEUR, null, null);
	}

	/**
	 * <b><i>log</i></b><br>
	 * <pre>private static void log({@link com.loterie.dao.LogDao LogDao} logDao, {@link java.lang.String String} message)</pre>
	 * Donne l'ordre de créer un nouveau log en BDD
	 * 
	 * @param logDao EntityManager en charge de contrôler le Logger
	 * @param message message à insérer en base de données 
	 */
	public static void log(LogDao logDao, String message) {
		log(logDao, message, Constants.LOG_INFO, Constants.LOG_SERVEUR, null, null);
	}

	/**
	 * <b><i>log</i></b><br>
	 * <pre>private static void log({@link com.loterie.dao.LogDao LogDao} logDao, {@link java.lang.String String} message, {@link java.lang.String String} niveau, {@link java.lang.String String} type, {@link com.loterie.entities.Utilisateur Utilisateur} declencheur, {@link com.loterie.entities.Utilisateur Utilisateur} utilisateurLie, {@link com.loterie.entities.Grille Grille} grilleLiee)</pre>
	 * Donne l'ordre de créer un nouveau log en BDD
	 * 
	 * @param logDao EntityManager en charge de contrôler le Logger
	 * @param message message à insérer en base de données
	 * @param niveau niveau du message à insérer en base de données (exemple : {@link com.loterie.config.Constants Constants}.LOG_SERVEUR)
	 * @param type type de message à insérer en base de données (exemple : {@link com.loterie.config.Constants Constants}.LOG_INFO)
	 * @param declencheur entité {@link com.loterie.entities.Utilisateur Utilisateur} déclenchant l'écriture du log
	 * @param utilisateurLie entité {@link com.loterie.entities.Utilisateur Utilisateur} référencée dans le message
	 * @param grilleLiee entité {@link com.loterie.entities.Grille Grille} référencée dans le message 
	 */
	public static void log(LogDao logDao, String message, String niveau, String type, Utilisateur declencheur, 
			Utilisateur utilisateurLie, Grille grilleLiee) {
		Logger logger = Logger.getInstance();
		logger.setLogDao(logDao);
		Log l = new Log();
		l.LogInit(message, niveau, type, declencheur, utilisateurLie, grilleLiee);
		logger.creerLog(l);
	}

	/**
	 * <b><i>log</i></b><br>
	 * <pre>private static void log({@link com.loterie.dao.LogDao LogDao} logDao, {@link java.lang.String String} message, {@link java.lang.String String} niveau, {@link java.lang.String String} type, {@link com.loterie.entities.Utilisateur Utilisateur} declencheur, {@link java.lang.Object Object} entiteLiee)</pre>
	 * Donne l'ordre de créer un nouveau log en BDD
	 * 
	 * @param logDao EntityManager en charge de contrôler le Logger
	 * @param message message à insérer en base de données
	 * @param niveau niveau du message à insérer en base de données (exemple : {@link com.loterie.config.Constants Constants}.LOG_SERVEUR)
	 * @param type type de message à insérer en base de données (exemple : {@link com.loterie.config.Constants Constants}.LOG_INFO)
	 * @param declencheur entité {@link com.loterie.entities.Utilisateur Utilisateur} déclenchant l'écriture du log
	 * @param entiteLiee entité {@link java.lang.Object Object} référencée dans le message 
	 */
	public static void log(LogDao logDao, String message, String niveau, String type, Utilisateur declencheur, 
			Object entiteLiee) {
		Logger logger = Logger.getInstance();
		logger.setLogDao(logDao);
		Log l = new Log();
		l.LogInit(message, niveau, type, declencheur, entiteLiee);
		logger.creerLog(l);
	}
	
	/**
	 * <b><i>lire</i></b><br>
	 * <pre>private {@link java.util.List List}<{@link com.loterie.entities.Log Log}> lire()</pre>
	 * Lit tous les logs de la base de données
	 * 
	 * @return la liste des logs trouvés en BDD
	 */
	public static List<Log> lire() {
		return Logger.getInstance().lireLogs();
	}
	
	private void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
}
