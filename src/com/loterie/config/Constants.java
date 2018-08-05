package com.loterie.config;

public interface Constants {
	public static final String CONTEXTE								= "/loterie";
	public static final int EUROMILLIONS_NUMEROS					= 50;
	public static final int EUROMILLIONS_ETOILES					= 12;
	public static final int EUROMILLIONS_NUMEROS_SELECTION_MIN		= 5;
	public static final int EUROMILLIONS_NUMEROS_SELECTION_MAX		= 6;
	public static final int EUROMILLIONS_ETOILES_SELECTION_MIN		= 2;
	public static final int EUROMILLIONS_ETOILES_SELECTION_MAX		= 12;
	
	/** 
	 *  Constantes liées aux entités 
	 */
	public static final Long UTILISATEUR_ROLE_ADMIN		 			= 0L;
	public static final Long UTILISATEUR_ROLE_MODERATEUR			= 1L;
	public static final Long UTILISATEUR_ROLE_MEMBRE				= 3L;
	public static final Long UTILISATEUR_ROLE_BASIQUE 				= 5L; 
	
	/** 
	 *  Constantes liées aux Servlets 
	 */
	public static final String URI_MEMBRE_PROFIL 					= "/membre/profil";
	public static final String URI_MEMBRE_AFFICHER_GRILLES			= "/membre/afficherGrilles";
	public static final String URI_MEMBRE_CREER_GRILLE				= "/membre/creerGrille";
	public static final String URI_MEMBRE_MODIFIER_GRILLE			= "/membre/editerGrille";
	public static final String URI_MEMBRE_SUPPRIMER_GRILLE			= "/membre/supprimerGrille";
	
	/** 
	 *  Constantes liées aux URL 
	 */
	public static final String URL_ACCUEIL 							= "/WEB-INF/publique/homePage.jsp";
	public static final String URL_CREER_MDP 						= "/WEB-INF/set-password.jsp";
	public static final String URL_INSCRIPTION						= "/WEB-INF/publique/subscriptionPage.jsp";
	public static final String URL_MEMBRE_ACCUEIL					= "/WEB-INF/membre/homePage.jsp";
	public static final String URL_MEMBRE_AFFICHER_GRILLES			= "/WEB-INF/membre/afficherGrilles.jsp";
	public static final String URL_MEMBRE_CREER_GRILLE				= "/WEB-INF/membre/creerGrille.jsp";
	public static final String URL_MEMBRE_MODIFIER_GRILLE			= "/WEB-INF/membre/modifierGrille.jsp";
	//TODO: Créer page confirmation inscription
	public static final String URL_ADMIN_403						= "/WEB-INF/admin/403.jsp";
	public static final String URL_INSCRIPTION_OK					= "/WEB-INF/publique/homePage.jsp";
	public static final String URL_ADMIN_ACCUEIL					= "/WEB-INF/admin/homePage.jsp";

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
	public static final String SELECT_GRILLE_PAR_UTILISATEUR		= "SELECT g FROM Grille g JOIN g.utilisateur u WHERE u = :utilisateur";
	public static final String SELECT_GRILLE_PAR_ID					= "SELECT g FROM Grille g WHERE g.id = :id";
}
