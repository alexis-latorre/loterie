package com.loterie.config;

public interface Constants {
	/**
	 * Constantes d'application
	 */
	public static final String CONTEXTE 									= "/loterie";
	public static final String LETTRES_MAJ 									= "abcdefghijklmnopqrstuvwxyz";
	public static final String LETTRES_MIN 									= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String CHIFFRES 									= "0123456789";
	public static final String SPECIAUX 									= "&-_=$;:!£%?./+#@[] {}";
	public static final int MDP_TAILLE_MIN 									= 8;
	public static final int SEL_TAILLE_MIN 									= 8;
	public static final int VARIABILITE_SEL 								= 8;
	public static final String[] JOURS_LONGS 								= {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"};
	public static final String[] JOURS_COURTS 								= {"lun", "mar", "mer", "jeu", "ven", "sam", "dim"};
	public static final String[] JOURS_INITIALES 							= {"l", "ma", "me", "j", "v", "s", "d"};

	/**
	 * Constantes Euromillions
	 */
	public static final String EUROMILLIONS_NOM 							= "Euromillions";
	public static final String[] EUROMILLIONS_JOURS_TIRAGE 					= {"2", "5"};
	public static final int EUROMILLIONS_NUMEROS 							= 50;
	public static final int EUROMILLIONS_ETOILES 							= 12;
	public static final int EUROMILLIONS_NUMEROS_SELECTION_MIN 				= 5;
	public static final int EUROMILLIONS_NUMEROS_SELECTION_MAX 				= 6;
	public static final int EUROMILLIONS_ETOILES_SELECTION_MIN 				= 2;
	public static final int EUROMILLIONS_ETOILES_SELECTION_MAX 				= 12;
	public static final int EUROMILLIONS_NUMEROS_NB_LIGNES 					= 5;
	public static final int EUROMILLIONS_NUMEROS_NB_PAR_LIGNES 				= 13;
	public static final int EUROMILLIONS_ETOILES_NB_LIGNES 					= 1;
	public static final int EUROMILLIONS_ETOILES_NB_PAR_LIGNES 				= 12;

	/**
	 * Constantes liées aux entités
	 */
	public static final Long UTILISATEUR_ROLE_ADMIN 						= 0L;
	public static final Long UTILISATEUR_ROLE_MODERATEUR 					= 1L;
	public static final Long UTILISATEUR_ROLE_MEMBRE 						= 3L;
	public static final Long UTILISATEUR_ROLE_BASIQUE 						= 5L;

	/**
	 * Mapping URL
	 */
	public static final String URL_ROOT 									= "/";
	public static final String URL_PUBLIC_ACCUEIL 							= "/accueil";
	public static final String URL_PUBLIC_INSCRIPTION 						= "/inscription";
	public static final String URL_PUBLIC_CONNEXION 						= "/connexion";
	public static final String URL_PUBLIC_DECONNEXION 						= "/deconnexion";

	public static final String URL_CREER_MDP 								= "/creerMotDePasse";
	public static final String URL_MODIFIER_MDP 							= "/modifierMotDePasse";

	public static final String URL_MEMBRE_PROFIL 							= "/membre/profil";
	public static final String URL_MEMBRE_AFFICHER_GRILLES 					= "/membre/afficherGrilles";
	public static final String URL_MEMBRE_CREER_GRILLE 						= "/membre/creerGrille";
	public static final String URL_MEMBRE_AFFICHER_GRILLE 					= "/membre/afficherGrille";
	public static final String URL_MEMBRE_MODIFIER_GRILLE 					= "/membre/editerGrille";
	public static final String URL_MEMBRE_SUPPRIMER_GRILLE 					= "/membre/supprimerGrille";
	public static final String URL_MEMBRE_REJOINDRE_GRILLE 					= "/membre/rejoindreGrille";
	public static final String URL_MEMBRE_QUITTER_GRILLE 					= "/membre/quitterGrille";
	public static final String URL_MEMBRE_BANQUE_AJOUT 						= "/membre/crediterGrille";
	public static final String URL_MEMBRE_JOUER_GRILLE 						= "/membre/jouerGrille";
	public static final String URL_MEMBRE_PORTEFEUILLE 						= "/membre/monPortefeuille";
	public static final String URL_MEMBRE_PORTEFEUILLE_AJOUT				= "/membre/ajouterFonds";
	public static final String URL_ADMIN_ACCUEIL 							= "/admin/accueil";

	/**
	 * Ressources JSP
	 */
	public static final String URN_ACCUEIL 									= "/WEB-INF/public/homePage.jsp";
	public static final String URN_CREER_MDP 								= "/WEB-INF/public/set-password.jsp";
	public static final String URN_INSCRIPTION 								= "/WEB-INF/public/subscriptionPage.jsp";
	public static final String URN_INSCRIPTION_OK 							= "/WEB-INF/public/subscriptionPage.jsp";

	public static final String URN_MEMBRE_403 								= "/WEB-INF/membre/403.jsp";
	public static final String URN_MEMBRE_ACCUEIL 							= "/WEB-INF/membre/homePage.jsp";
	public static final String URN_MEMBRE_AFFICHER_GRILLES 					= "/WEB-INF/membre/afficherGrilles.jsp";
	public static final String URN_MEMBRE_AFFICHER_GRILLE 					= "/WEB-INF/membre/detailGrille.jsp";
	public static final String URN_MEMBRE_CREER_GRILLE 						= "/WEB-INF/membre/creerGrille.jsp";
	public static final String URN_MEMBRE_MODIFIER_GRILLE 					= "/WEB-INF/membre/modifierGrille.jsp";
	public static final String URN_MEMBRE_PORTEFEUILLE 						= "/WEB-INF/membre/afficherPortefeuille.jsp";

	public static final String URN_ADMIN_403 								= "/WEB-INF/admin/403.jsp";
	public static final String URN_ADMIN_ACCUEIL 							= "/WEB-INF/admin/homePage.jsp";

	/**
	 * Requêtes SQL
	 */
	public static final String SELECT_UTILISATEUR_PAR_ID 					= "SELECT u FROM Utilisateur u WHERE u.id = :id";
	public static final String SELECT_UTILISATEUR_PAR_NOM 					= "SELECT u FROM Utilisateur u WHERE u.pseudo = :pseudo";
	public static final String SELECT_UTILISATEUR_PAR_NOM_PRENOM 			= "SELECT u FROM Utilisateur u WHERE u.nom = :nom AND u.prenom = :prenom";
	public static final String SELECT_UTILISATEUR_PAR_EMAIL 				= "SELECT u FROM Utilisateur u WHERE u.email = :email";
	public static final String SELECT_UTILISATEURS_PAR_NIVEAU_SUP 			= "SELECT u FROM Utilisateur u WHERE u.niveau <= :niveau";
	public static final String SELECT_GRILLES_PAR_CREATEUR 					= "SELECT g FROM Grille g JOIN g.utilisateur u WHERE u = :utilisateur";
	public static final String SELECT_GRILLES_PAR_UTILISATEUR 				= "SELECT l.grille FROM LienGrilleUtilisateur l WHERE l.utilisateur = :utilisateur";
	public static final String SELECT_GRILLES_PAR_DATE_ET_UTILISATEUR 		= "SELECT l.grille FROM LienGrilleUtilisateur l, Jour j WHERE l.utilisateur = :utilisateur AND j.dateJour = :dateJour";
	public static final String SELECT_GRILLES_PAR_INTERVALLE_ET_UTILISATEUR = "SELECT l.grille FROM LienGrilleUtilisateur l, Jour j WHERE l.utilisateur = :utilisateur AND j.dateJour BETWEEN :date_debut AND :date_fin";
	public static final String SELECT_GRILLE_PAR_ID 						= "SELECT g FROM Grille g WHERE g.id = :id";
	public static final String SELECT_LIEN_GU_PAR_ID 						= "SELECT l FROM LienGrilleUtilisateur l WHERE l.id = :id";
	public static final String SELECT_LIEN_GU_PAR_GRILLE 					= "SELECT l FROM LienGrilleUtilisateur l WHERE l.grille = :grille";
	public static final String SELECT_LIEN_GU_PAR_GRILLE_ET_UTILISATEUR 	= "SELECT l FROM LienGrilleUtilisateur l WHERE l.grille = :grille AND l.utilisateur = :utilisateur";
	public static final String SELECT_JOUR_PAR_DATE 						= "SELECT j FROM Jour j WHERE j.dateJour = :dateJour";
	public static final String SELECT_JOUR_PAR_LGU 							= "SELECT j FROM Jour j WHERE j.lgu = :lgu";
	public static final String SELECT_JOUR_PAR_DATE_ET_LGU 					= "SELECT j FROM Jour j WHERE j.dateJour = :dateJour AND j.lgu = :lgu";
	public static final String SELECT_JOUR_PAR_DATE_ET_UTILISATEUR 			= "SELECT j FROM Jour j JOIN LienGrilleUtilisateur l WHERE j.dateJour = :dateJour AND l.utilisateur = :utilisateur";
	public static final String SELECT_JOUR_PAR_INTERVALLE_ET_UTILISATEUR 	= "SELECT DISTINCT j FROM Jour j JOIN LienGrilleUtilisateur l WHERE (j.dateJour BETWEEN :date_debut AND :date_fin) AND l.utilisateur = :utilisateur AND j.lgu = l";
	public static final String SELECT_JOUR_DERNIER_JOUE_PAR_GRILLE			= "SELECT j FROM Jour j JOIN LienGrilleUtilisateur l ON j.lgu = l JOIN Grille g WHERE l.grille = :grille ORDER BY j.dateJour DESC";
	public static final String SELECT_JEU_PAR_NOM 							= "SELECT j FROM Jeu j WHERE j.nom = :nom";

}
