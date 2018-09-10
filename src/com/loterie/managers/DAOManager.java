package com.loterie.managers;

import com.loterie.dao.BanqueDao;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.JeuDao;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.PortefeuilleDao;
import com.loterie.dao.UtilisateurDao;

public class DAOManager {
	private static BanqueDao banque 				= BanqueDao.getInstance();
	private static GrilleDao grille 				= GrilleDao.getInstance();
	private static JeuDao jeu 						= JeuDao.getInstance();
	private static JourDao jour						= JourDao.getInstance();
	private static LienGUDao lgu 					= LienGUDao.getInstance();
	private static PortefeuilleDao portefeuille 	= PortefeuilleDao.getInstance();
	private static UtilisateurDao utilisateur		= UtilisateurDao.getInstance();
	
	public static BanqueDao getBanque() {
		return banque;
	}
	
	public static GrilleDao getGrille() {
		return grille;
	}
	
	public static JeuDao getJeu() {
		return jeu;
	}
	
	public static JourDao getJour() {
		return jour;
	}
	
	public static LienGUDao getLgu() {
		return lgu;
	}
	
	public static PortefeuilleDao getPortefeuille() {
		return portefeuille;
	}
	
	public static UtilisateurDao getUtilisateur() {
		return utilisateur;
	}
}
