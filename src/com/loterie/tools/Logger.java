package com.loterie.tools;

import java.util.List;

import javax.ejb.EJB;

import com.loterie.config.Constants;
import com.loterie.dao.LogDao;
import com.loterie.entities.Log;
import com.loterie.entities.Utilisateur;

public class Logger {
	@EJB
	private LogDao logDao;
	private static Logger INSTANCE = new Logger();
	
	private Logger() {}
	
	private static Logger getInstance() {
		return INSTANCE;
	}
	
	private void privateLog(Log log) {
		logDao.creer(log);
	}
	
	private List<Log> privateLire() {
		return logDao.trouver();
	}
	
	public static void log(String message, String niveau, String type, Utilisateur declencheur, 
			Utilisateur utilisateurLie) {
		Log l = new Log();
		l.LogInit(message, niveau, type, declencheur, utilisateurLie);
		Logger logger = Logger.getInstance();
		logger.privateLog(l);
	}
	
	public static void log(String message, String niveau, String type, Utilisateur declencheur) {
		log(message, niveau, type, declencheur, null);
	}
	
	public static void log(String message, String niveau, Utilisateur declencheur) {
		log(message, niveau, Constants.LOG_SERVEUR, declencheur, null);
		
	}
	
	public static void log(String message, Utilisateur declencheur) {
		log(message, Constants.LOG_INFO, Constants.LOG_SERVEUR, declencheur, null);
	}
	
	public static void log(String message, String niveau, String type) {
		log(message, niveau, type, null, null);
	}
	
	public static void log(String message, String niveau) {
		log(message, niveau, Constants.LOG_SERVEUR, null, null);
	}
	
	public static void log(String message) {
		log(message, Constants.LOG_INFO, Constants.LOG_SERVEUR, null, null);
	}
	
	public static List<Log> lire() {
		Logger logger = Logger.getInstance();
		return logger.privateLire();
	}
}
