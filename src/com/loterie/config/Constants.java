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
	public static final String LOG_INFO										= "I";
	public static final String LOG_AVERTISSEMENT							= "A";
	public static final String LOG_ERREUR									= "E";
	public static final String LOG_SEVERE									= "S";
	public static final String LOG_FATALE									= "F";
	public static final String LOG_FINANCE									= "FINANCE";
	public static final String LOG_COMPTE									= "COMPTE";
	public static final String LOG_ADMINISTRATION							= "ADMINISTRATION";
	public static final String LOG_GRILLE									= "GRILLE";
	public static final String LOG_SERVEUR									= "SERVEUR";
	public static final int NB_HISTO_PAR_PAGE								= 10;

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
	public static final int EUROMILLIONS_NUMEROS_NB_PAR_LIGNES 				= 10;
	public static final int EUROMILLIONS_ETOILES_NB_LIGNES 					= 1;
	public static final int EUROMILLIONS_ETOILES_NB_PAR_LIGNES 				= 12;
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

	/**
	 * Constantes liées aux entités
	 */
	public static final Long L_UTILISATEUR_ROLE_ADMIN 						= 0L;
	public static final String S_UTILISATEUR_ROLE_ADMIN						= "administrateur";
	public static final String CLASSE_ADMIN 								= "danger";
	public static final Long L_UTILISATEUR_ROLE_MODERATEUR 					= 1L;
	public static final String S_UTILISATEUR_ROLE_MODERATEUR				= "modérateur";
	public static final String CLASSE_MODERATEUR 							= "warning";
	public static final Long L_UTILISATEUR_ROLE_MEMBRE 						= 3L;
	public static final String S_UTILISATEUR_ROLE_MEMBRE					= "membre";
	public static final String CLASSE_MEMBRE 								= "success";
	public static final Long L_UTILISATEUR_ROLE_BASIQUE						= 5L;
	public static final String S_UTILISATEUR_ROLE_BASIQUE					= "utilisateur";
	public static final String CLASSE_BASIQUE 								= "primary";
	public static final String CLASSE_AUTRE 								= "default";

	/**
	 * Mapping URL
	 */
	public static final String URL_ROOT 									= "/";
	public static final String URL_PUBLIC_ACCUEIL 							= "/accueil";
	public static final String URL_PUBLIC_INSCRIPTION 						= "/inscription";
	public static final String URL_PUBLIC_CONNEXION 						= "/connexion";
	public static final String URL_PUBLIC_DECONNEXION 						= "/deconnexion";
	public static final String URL_PUBLIC_RESULTATS 						= "/resultats";

	public static final String URL_CREER_MDP 								= "/creerMotDePasse";
	public static final String URL_MODIFIER_MDP 							= "/modifierMotDePasse";

	public static final String URL_MEMBRE_PROFIL 							= "/membre/profil";
	public static final String URL_MEMBRE_MODIFIER_PROFIL 					= "/membre/modifierInformation";
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
	public static final String URL_MEMBRE_ACTIVER_GRILLE 					= "/membre/activerGrille";
	public static final String URL_MEMBRE_DESACTIVER_GRILLE 				= "/membre/desactiverGrille";
	
	public static final String URL_ADMIN_ACCUEIL 							= "/admin/accueil";
	public static final String URL_ADMIN_CREDITER							= "/admin/crediterJoueur";
	public static final String URL_ADMIN_DETAILS_UTILISATEUR 				= "/admin/detailsUtilisateur";
	public static final String URL_ADMIN_DETAILS_UTILISATEURS 				= "/admin/detailsUtilisateurs";
	public static final String URL_ADMIN_RETABLIR_GRILLE	 				= "/admin/retablirGrille";
	public static final String URL_ADMIN_MODIFIER_PRIVILEGES 				= "/admin/modifierPrivileges";
	public static final String URL_ADMIN_LOGS				 				= "/admin/logs";

	/**
	 * Ressources JSP
	 */
	public static final String URN_ACCUEIL 									= "/WEB-INF/public/homePage.jsp";
	public static final String URN_CREER_MDP 								= "/WEB-INF/public/set-password.jsp";
	public static final String URN_INSCRIPTION 								= "/WEB-INF/public/subscriptionPage.jsp";
	public static final String URN_INSCRIPTION_OK 							= "/WEB-INF/public/subscriptionPage.jsp";
	public static final String URN_RESULTATS	 							= "/WEB-INF/public/consulterResultats.jsp";

	public static final String URN_MEMBRE_403 								= "/WEB-INF/membre/403.jsp";
	public static final String URN_MEMBRE_ACCUEIL 							= "/WEB-INF/membre/homePage.jsp";
	public static final String URN_MEMBRE_PROFIL 							= "/WEB-INF/membre/afficherProfil.jsp";
	public static final String URN_MEMBRE_AFFICHER_GRILLES 					= "/WEB-INF/membre/afficherGrilles.jsp";
	public static final String URN_MEMBRE_AFFICHER_GRILLE 					= "/WEB-INF/membre/detailsGrille.jsp";
	public static final String URN_MEMBRE_CREER_GRILLE 						= "/WEB-INF/membre/creerGrille.jsp";
	public static final String URN_MEMBRE_MODIFIER_GRILLE 					= "/WEB-INF/membre/modifierGrille.jsp";
	public static final String URN_MEMBRE_PORTEFEUILLE 						= "/WEB-INF/membre/afficherPortefeuille.jsp";

	public static final String URN_ADMIN_403 								= "/WEB-INF/admin/403.jsp";
	public static final String URN_ADMIN_ACCUEIL 							= "/WEB-INF/admin/homePage.jsp";
	public static final String URN_ADMIN_CREDITER 							= "/WEB-INF/admin/crediterJoueur.jsp";
	public static final String URN_ADMIN_DETAILS_UTILISATEUR 				= "/WEB-INF/admin/detailsUtilisateur.jsp";
	public static final String URN_ADMIN_DETAILS_UTILISATEURS 				= "/WEB-INF/admin/detailsUtilisateurs.jsp";
	public static final String URN_ADMIN_LOGS				 				= "/WEB-INF/admin/logs.jsp";

	/**
	 * Requêtes SQL
	 */
	public static final String SELECT_UTILISATEUR_PAR_ID 					= "SELECT u FROM Utilisateur u WHERE u.id = :id";
	public static final String SELECT_UTILISATEUR_PAR_NOM 					= "SELECT u FROM Utilisateur u WHERE u.pseudo = :pseudo";
	public static final String SELECT_UTILISATEUR_PAR_NOM_PRENOM 			= "SELECT u FROM Utilisateur u WHERE u.nom = :nom AND u.prenom = :prenom";
	public static final String SELECT_UTILISATEUR_PAR_EMAIL 				= "SELECT u FROM Utilisateur u WHERE u.email = :email";
	public static final String SELECT_UTILISATEURS_PAR_NIVEAU_SUP 			= "SELECT u FROM Utilisateur u WHERE u.niveau <= :niveau";
	public static final String SELECT_UTILISATEURS_PAR_NIVEAU_SUP_ASC		= "SELECT u FROM Utilisateur u WHERE u.niveau <= :niveau ORDER BY u.niveau ASC";
	public static final String SELECT_UTILISATEURS_PAR_GRILLE 				= "SELECT l.utilisateur FROM LienGrilleUtilisateur l WHERE l.grille = :grille";
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
	public static final String SELECT_LOG		 							= "SELECT l FROM Log l ORDER BY l.utc DESC";
	public static final String SELECT_RESULTATS_DESC 						= "SELECT r FROM Resultat r ORDER BY r.id DESC";
	public static final String SELECT_RETARDS 								= "SELECT r FROM Retard r WHERE r.relance = true";
	public static final String SELECT_RETARD_PAR_UTILISATEUR 				= "SELECT r FROM Retard r WHERE r.utilisateur = :utilisateur";

}
