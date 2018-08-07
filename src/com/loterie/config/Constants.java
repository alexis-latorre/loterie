package com.loterie.config;

public interface Constants {
	/**
	 *  Constantes d'application
	 */
	public static final String CONTEXTE								= "/loterie";
	public static final int EUROMILLIONS_NUMEROS					= 50;
	public static final int EUROMILLIONS_ETOILES					= 12;
	public static final int EUROMILLIONS_NUMEROS_SELECTION_MIN		= 5;
	public static final int EUROMILLIONS_NUMEROS_SELECTION_MAX		= 6;
	public static final int EUROMILLIONS_ETOILES_SELECTION_MIN		= 2;
	public static final int EUROMILLIONS_ETOILES_SELECTION_MAX		= 12;
	public static final int MDP_TAILLE_MIN							= 8;
	public static final int SEL_TAILLE_MIN 							= 8;
	public static final int VARIABILITE_SEL 						= 8;
	
	/** 
	 *  Constantes liées aux entités 
	 */
	public static final Long UTILISATEUR_ROLE_ADMIN		 			= 0L;
	public static final Long UTILISATEUR_ROLE_MODERATEUR			= 1L;
	public static final Long UTILISATEUR_ROLE_MEMBRE				= 3L;
	public static final Long UTILISATEUR_ROLE_BASIQUE 				= 5L; 
	
	/** 
	 *  Mapping URL 
	 */
	public static final String URL_ROOT								= "/";
	public static final String URL_PUBLIC_ACCUEIL					= "/accueil";
	public static final String URL_PUBLIC_CONNEXION					= "/connexion";
	public static final String URL_PUBLIC_DECONNEXION				= "/deconnexion";
	public static final String URL_CREER_MDP 						= "/creerMotDePasse";
	public static final String URL_MEMBRE_PROFIL 					= "/membre/profil";
	public static final String URL_MEMBRE_AFFICHER_GRILLES			= "/membre/afficherGrilles";
	public static final String URL_MEMBRE_CREER_GRILLE				= "/membre/creerGrille";
	public static final String URL_MEMBRE_MODIFIER_GRILLE			= "/membre/editerGrille";
	public static final String URL_MEMBRE_SUPPRIMER_GRILLE			= "/membre/supprimerGrille";
	public static final String URL_MEMBRE_REJOINDRE_GRILLE			= "/membre/rejoindreGrille";
	public static final String URL_MEMBRE_QUITTER_GRILLE			= "/membre/quitterGrille";
	public static final String URL_ADMIN_ACCUEIL					= "/admin/accueil";
	
	/** 
	 *  Ressources JSP 
	 */
	public static final String URN_ACCUEIL 							= "/WEB-INF/public/homePage.jsp";
	public static final String URN_CREER_MDP 						= "/WEB-INF/public/set-password.jsp";
	public static final String URN_INSCRIPTION						= "/WEB-INF/public/subscriptionPage.jsp";
	public static final String URN_INSCRIPTION_OK					= "/WEB-INF/public/subscriptionPage.jsp";
	
	public static final String URN_MEMBRE_ACCUEIL					= "/WEB-INF/membre/homePage.jsp";
	public static final String URN_MEMBRE_AFFICHER_GRILLES			= "/WEB-INF/membre/afficherGrilles.jsp";
	public static final String URN_MEMBRE_CREER_GRILLE				= "/WEB-INF/membre/creerGrille.jsp";
	public static final String URN_MEMBRE_MODIFIER_GRILLE			= "/WEB-INF/membre/modifierGrille.jsp";
	
	public static final String URN_ADMIN_403						= "/WEB-INF/admin/403.jsp";
	public static final String URN_ADMIN_ACCUEIL					= "/WEB-INF/admin/homePage.jsp";

	/** 
	 *  Requêtes SQL
	 */
	public static final String SELECT_UTILISATEUR_PAR_NOM 			= "SELECT u FROM Utilisateur u WHERE u.pseudo = :pseudo";
	public static final String SELECT_UTILISATEUR_PAR_NOM_PRENOM 	= "SELECT u FROM Utilisateur u WHERE u.nom = :nom AND u.prenom = :prenom";
	public static final String SELECT_UTILISATEUR_PAR_EMAIL			= "SELECT u FROM Utilisateur u WHERE u.email = :email";
	public static final String SELECT_GRILLES_PAR_CREATEUR			= "SELECT g FROM Grille g JOIN g.utilisateur u WHERE u = :utilisateur";
	public static final String SELECT_GRILLES_PAR_UTILISATEUR		= "SELECT l.grille FROM LienGrilleUtilisateur l WHERE l.utilisateur = :utilisateur";
	public static final String SELECT_GRILLE_PAR_ID					= "SELECT g FROM Grille g WHERE g.id = :id";
	public static final String SELECT_LIEN_GU_PAR_ID				= "SELECT l FROM LienGrilleUtilisateur l WHERE l.id = :id";
	public static final String SELECT_LIEN_GU_PAR_GRILLE			= "SELECT l FROM LienGrilleUtilisateur l WHERE l.grille = :grille";
}
