package com.loterie.config;

public interface Constants {	
	/** 
	 *  Constantes liées aux entités 
	 */
	public static final Long UTILISATEUR_ROLE_ADMIN		 			= new Long(0);
	public static final Long UTILISATEUR_ROLE_MODERATEUR			= new Long(1);
	public static final Long UTILISATEUR_ROLE_BASIQUE 				= new Long(5); 
	
	/** 
	 *  Constantes liées aux URL 
	 */
	public static final String URL_ACCUEIL 							= "/WEB-INF/publique/homePage.jsp";
	public static final String URL_CREER_MDP 						= "/WEB-INF/set-password.jsp";
	public static final String URL_INSCRIPTION						= "/WEB-INF/publique/subscriptionPage.jsp";
	//TODO: Créer page confirmation inscription
	public static final String URL_INSCRIPTION_OK					= "/WEB-INF/publique/homePage.jsp";

	/** 
	 *  Constantes liées aux DAO 
	 */	
	public static final int MDP_TAILLE_MIN							= 8;
	public static final int SEL_TAILLE_MIN 							= 8;
	public static final int VARIABILITE_SEL 						= 8;

	/** 
	 *  Constantes liées au SGBD
	 */
	public static final String SELECT_UTILISATEUR_PAR_NOM 			= "SELECT u FROM Utilisateur u WHERE u.pseudo = :pseudo";
	public static final String SELECT_UTILISATEUR_PAR_NOM_PRENOM 	= "SELECT u FROM Utilisateur u WHERE u.nom = :nom AND u.prenom = :prenom";
	public static final String SELECT_UTILISATEUR_PAR_EMAIL			= "SELECT u FROM Utilisateur u WHERE u.email = :email";
}
