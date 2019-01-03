package com.loterie.config;

public interface Privileges {
	public static final String BANQUE_CREER = "banque-creer";
	public static final String BANQUE_SUPPRIMER = "banque-supprimer";
	public static final String BANQUE_MODIFIER_FONDS = "banque-modifier-fonds";
	public static final String BANQUE_MODIFIER_MISES = "banque-modifier-mises";
	public static final String BANQUE_MODIFIER_GAINS = "banque-modifier-gains";
	public static final String GRILLE_CREER = "grille-creer";
	public static final String GRILLE_PROP_SUPPRIMER = "grille-prop-supprimer";
	public static final String GRILLE_SUPPRIMER = "grille-supprimer";
	public static final String GRILLE_PROP_MODIFIER_NOM = "grille-prop-modifier-nom";
	public static final String GRILLE_PROP_MODIFIER_NUMEROS = "grille-prop-modifier-numeros";
	public static final String GRILLE_PROP_MODIFIER_ETOILES = "grille-prop-modifier-etoiles";
	public static final String GRILLE_PROP_MODIFIER_ETOILE_PLUS = "grille-prop-modifier-etoile_plus";
	public static final String GRILLE_PROP_MODIFIER_MYMILLION = "grille-prop-modifier-mymillion";
	public static final String GRILLE_PROP_MODIFIER_FK_CREATEUR = "grille-prop-modifier-fk_createur";
	public static final String GRILLE_PROP_MODIFIER_FK_JEU_ID = "grille-prop-modifier-fk_jeu_id";
	public static final String GRILLE_PROP_MODIFIER_FK_BANQUE_ID = "grille-prop-modifier-fk_banque_id";
	public static final String GRILLE_PROP_MODIFIER_PUBLIQUE = "grille-prop-modifier-publique";
	public static final String GRILLE_PROP_MODIFIER_VISIBLE = "grille-prop-modifier-visible";
	public static final String GRILLE_PROP_MODIFIER_ACTIVE = "grille-prop-modifier-active";
	public static final String GRILLE_MODIFIER_NOM = "grille-modifier-nom";
	public static final String GRILLE_MODIFIER_NUMEROS = "grille-modifier-numeros";
	public static final String GRILLE_MODIFIER_ETOILES = "grille-modifier-etoiles";
	public static final String GRILLE_MODIFIER_ETOILE_PLUS = "grille-modifier-etoile_plus";
	public static final String GRILLE_MODIFIER_MYMILLION = "grille-modifier-mymillion";
	public static final String GRILLE_MODIFIER_FK_CREATEUR = "grille-modifier-fk_createur";
	public static final String GRILLE_MODIFIER_FK_JEU_ID = "grille-modifier-fk_jeu_id";
	public static final String GRILLE_MODIFIER_FK_BANQUE_ID = "grille-modifier-fk_banque_id";
	public static final String GRILLE_MODIFIER_PUBLIQUE = "grille-modifier-publique";
	public static final String GRILLE_MODIFIER_VISIBLE = "grille-modifier-visible";
	public static final String GRILLE_MODIFIER_ACTIVE = "grille-modifier-active";
	public static final String JEU_CREER = "jeu-creer";
	public static final String JEU_SUPPRIMER = "jeu-supprimer";
	public static final String JEU_MODIFIER_NOM = "jeu-modifier-nom";
	public static final String JEU_MODIFIER_TIRAGE_PAR_SEMAINE = "jeu-modifier-tirage_par_semaine";
	public static final String JEU_MODIFIER_JOUR_DE_TIRAGE = "jeu-modifier-jour_de_tirage";
	public static final String JEU_MODIFIER_PRIX_PAR_TIRAGE = "jeu-modifier-prix_par_tirage";
	public static final String JEU_MODIFIER_HEURE_VALIDATION = "jeu-modifier-heure_validation";
	public static final String JOUR_CREER = "jour-creer";
	public static final String JOUR_SUPPRIMER = "jour-supprimer";
	public static final String JOUR_MODIFIER_DATE_JOUR = "jour-modifier-date_jour";
	public static final String JOUR_MODIFIER_FK_LIEN_GU_ID = "jour-modifier-fk_lien_gu_id";
	public static final String JOUR_MODIFIER_PAYE = "jour-modifier-paye";
	public static final String LIEN_GRILLE_UTILISATEUR_CREER = "lien_grille_utilisateur-creer";
	public static final String LIEN_GRILLE_UTILISATEUR_SUPPRIMER = "lien_grille_utilisateur-supprimer";
	public static final String LIEN_GRILLE_UTILISATEUR_MODIFIER_FK_GRILLE_ID = "lien_grille_utilisateur-modifier-fk_grille_id";
	public static final String LIEN_GRILLE_UTILISATEUR_MODIFIER_FK_UTILISATEUR_ID = "lien_grille_utilisateur-modifier-fk_utilisateur_id";
	public static final String LOG_CREER = "log-creer";
	public static final String LOG_SUPPRIMER = "log-supprimer";
	public static final String LOG_MODIFIER_UTC = "log-modifier-utc";
	public static final String LOG_MODIFIER_JOUR = "log-modifier-jour";
	public static final String LOG_MODIFIER_HEURE = "log-modifier-heure";
	public static final String LOG_MODIFIER_NIVEAU = "log-modifier-niveau";
	public static final String LOG_MODIFIER_TYPE = "log-modifier-type";
	public static final String LOG_MODIFIER_FK_DECLENCHEUR = "log-modifier-fk_declencheur";
	public static final String LOG_MODIFIER_FK_JOUEUR_LIE = "log-modifier-fk_joueur_lie";
	public static final String LOG_MODIFIER_FK_GRILLE_LIEE = "log-modifier-fk_grille_liee";
	public static final String LOG_MODIFIER_MESSAGE = "log-modifier-message";
	public static final String PORTEFEUILLE_CREER = "portefeuille-creer";
	public static final String PORTEFEUILLE_SUPPRIMER = "portefeuille-supprimer";
	public static final String PORTEFEUILLE_MODIFIER_DATE_CREATION = "portefeuille-modifier-date_creation";
	public static final String PORTEFEUILLE_MODIFIER_FONDS = "portefeuille-modifier-fonds";
	public static final String PRIVILEGE_CREER = "privilege-creer";
	public static final String PRIVILEGE_SUPPRIMER = "privilege-supprimer";
	public static final String PRIVILEGE_MODIFIER_PRIVILEGES = "privilege-modifier-privileges";
	public static final String RESULTAT_EUROMILLIONS_CREER = "resultat_euromillions-creer";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_NUMERO_TIRAGE = "resultat_euromillions-modifier-numero_tirage";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_DATE_TIRAGE = "resultat_euromillions-modifier-date_tirage";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_BOULE1 = "resultat_euromillions-modifier-boule1";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_BOULE2 = "resultat_euromillions-modifier-boule2";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_BOULE3 = "resultat_euromillions-modifier-boule3";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_BOULE4 = "resultat_euromillions-modifier-boule4";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_BOULE5 = "resultat_euromillions-modifier-boule5";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_ETOILE1 = "resultat_euromillions-modifier-etoile1";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_ETOILE2 = "resultat_euromillions-modifier-etoile2";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG1 = "resultat_euromillions-modifier-gagnant_rang1";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG1 = "resultat_euromillions-modifier-gains_rang1";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG2 = "resultat_euromillions-modifier-gagnant_rang2";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG2 = "resultat_euromillions-modifier-gains_rang2";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG3 = "resultat_euromillions-modifier-gagnant_rang3";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG3 = "resultat_euromillions-modifier-gains_rang3";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG4 = "resultat_euromillions-modifier-gagnant_rang4";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG4 = "resultat_euromillions-modifier-gains_rang4";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG5 = "resultat_euromillions-modifier-gagnant_rang5";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG5 = "resultat_euromillions-modifier-gains_rang5";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG6 = "resultat_euromillions-modifier-gagnant_rang6";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG6 = "resultat_euromillions-modifier-gains_rang6";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG7 = "resultat_euromillions-modifier-gagnant_rang7";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG7 = "resultat_euromillions-modifier-gains_rang7";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG8 = "resultat_euromillions-modifier-gagnant_rang8";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG8 = "resultat_euromillions-modifier-gains_rang8";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG9 = "resultat_euromillions-modifier-gagnant_rang9";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG9 = "resultat_euromillions-modifier-gains_rang9";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG10 = "resultat_euromillions-modifier-gagnant_rang10";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG10 = "resultat_euromillions-modifier-gains_rang10";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG11 = "resultat_euromillions-modifier-gagnant_rang11";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG11 = "resultat_euromillions-modifier-gains_rang11";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG12 = "resultat_euromillions-modifier-gagnant_rang12";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG12 = "resultat_euromillions-modifier-gains_rang12";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAGNANT_RANG13 = "resultat_euromillions-modifier-gagnant_rang13";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_GAINS_RANG13 = "resultat_euromillions-modifier-gains_rang13";
	public static final String RESULTAT_EUROMILLIONS_MODIFIER_MYMILLION = "resultat_euromillions-modifier-mymillion";
	public static final String RESULTAT_EUROMILLIONS_SUPPRIMER = "resultat_euromillions-supprimer";
	public static final String RETARD_CREER = "retard-creer";
	public static final String RETARD_SUPPRIMER = "retard-supprimer";
	public static final String RETARD_MODIFIER_FK_JOUEUR_ID = "retard-modifier-fk_joueur_id";
	public static final String RETARD_MODIFIER_DATE_CONSTAT = "retard-modifier-date_constat";
	public static final String RETARD_MODIFIER_DATE_RELANCE = "retard-modifier-date_relance";
	public static final String RETARD_MODIFIER_RELANCE = "retard-modifier-relance";
	public static final String UTILISATEUR_CREER = "utilisateur-creer";
	public static final String UTILISATEUR_SUPPRIMER = "utilisateur-supprimer";
	public static final String UTILISATEUR_PROP_MODIFIER_ID = "utilisateur-prop-modifier-id";
	public static final String UTILISATEUR_PROP_MODIFIER_PSEUDO = "utilisateur-prop-modifier-pseudo";
	public static final String UTILISATEUR_PROP_MODIFIER_NOM = "utilisateur-prop-modifier-nom";
	public static final String UTILISATEUR_PROP_MODIFIER_PRENOM = "utilisateur-prop-modifier-prenom";
	public static final String UTILISATEUR_PROP_MODIFIER_EMAIL = "utilisateur-prop-modifier-email";
	public static final String UTILISATEUR_PROP_MODIFIER_GRAIN_DE_SEL = "utilisateur-prop-modifier-grain_de_sel";
	public static final String UTILISATEUR_PROP_MODIFIER_MOT_DE_PASSE = "utilisateur-prop-modifier-mot_de_passe";
	public static final String UTILISATEUR_PROP_MODIFIER_NIVEAU = "utilisateur-prop-modifier-niveau";
	public static final String UTILISATEUR_PROP_MODIFIER_FK_PORTEFEUILLE_ID = "utilisateur-prop-modifier-fk_portefeuille_id";
	public static final String UTILISATEUR_PROP_MODIFIER_FK_PRIVILEGE_ID = "utilisateur-prop-modifier-fk_privilege_id";
	public static final String UTILISATEUR_MODIFIER_ID = "utilisateur-modifier-id";
	public static final String UTILISATEUR_MODIFIER_PSEUDO = "utilisateur-modifier-pseudo";
	public static final String UTILISATEUR_MODIFIER_NOM = "utilisateur-modifier-nom";
	public static final String UTILISATEUR_MODIFIER_PRENOM = "utilisateur-modifier-prenom";
	public static final String UTILISATEUR_MODIFIER_EMAIL = "utilisateur-modifier-email";
	public static final String UTILISATEUR_MODIFIER_GRAIN_DE_SEL = "utilisateur-modifier-grain_de_sel";
	public static final String UTILISATEUR_MODIFIER_MOT_DE_PASSE = "utilisateur-modifier-mot_de_passe";
	public static final String UTILISATEUR_MODIFIER_NIVEAU = "utilisateur-modifier-niveau";
	public static final String UTILISATEUR_MODIFIER_FK_PORTEFEUILLE_ID = "utilisateur-modifier-fk_portefeuille_id";
	public static final String UTILISATEUR_MODIFIER_FK_PRIVILEGE_ID = "utilisateur-modifier-fk_privilege_id";
	
	public static final String[] BANQUE = { 
			"banque-creer", "banque-supprimer", "banque-modifier-fonds", "banque-modifier-mises", 
			"banque-modifier-gains"
	};
	
	public static final String[] GRILLE = {
			"grille-creer", "grille-prop-supprimer", "grille-supprimer", 
			"grille-prop-modifier-nom", "grille-prop-modifier-numeros", "grille-prop-modifier-etoiles", 
			"grille-prop-modifier-etoile_plus", "grille-prop-modifier-mymillion", "grille-prop-modifier-fk_createur", 
			"grille-prop-modifier-fk_jeu_id", "grille-prop-modifier-fk_banque_id", "grille-prop-modifier-publique", 
			"grille-prop-modifier-visible", "grille-prop-modifier-active", "grille-modifier-nom", 
			"grille-modifier-numeros", "grille-modifier-etoiles", "grille-modifier-etoile_plus", 
			"grille-modifier-mymillion", "grille-modifier-fk_createur", "grille-modifier-fk_jeu_id", 
			"grille-modifier-fk_banque_id", "grille-modifier-publique", "grille-modifier-visible", 
			"grille-modifier-active"
	};
	
	public static final String[] JEU = {
			"jeu-creer", "jeu-supprimer", "jeu-modifier-nom", 
			"jeu-modifier-tirage_par_semaine", "jeu-modifier-jour_de_tirage", "jeu-modifier-prix_par_tirage", 
			"jeu-modifier-heure_validation"
	};
	
	public static final String[] JOUR = {
			"jour-creer", "jour-supprimer", "jour-modifier-date_jour", 
			"jour-modifier-fk_lien_gu_id", "jour-modifier-paye"
	};
	
	public static final String[] LGU = {
			"lien_grille_utilisateur-creer", 
			"lien_grille_utilisateur-supprimer", "lien_grille_utilisateur-modifier-fk_grille_id", 
			"lien_grille_utilisateur-modifier-fk_utilisateur_id"
	};
	
	public static final String[] LOG = {
			"log-creer", "log-supprimer", "log-modifier-utc", 
			"log-modifier-jour", "log-modifier-heure", "log-modifier-niveau", "log-modifier-type", 
			"log-modifier-fk_declencheur", "log-modifier-fk_joueur_lie", "log-modifier-fk_grille_liee", 
			"log-modifier-message"
	};
	
	public static final String[] PORTEFEUILLE = {
			"portefeuille-creer", "portefeuille-supprimer", 
			"portefeuille-modifier-date_creation", "portefeuille-modifier-fonds"
	};
	
	public static final String[] PRIVILEGE = {
			"privilege-creer", 
			"privilege-supprimer", "privilege-modifier-privileges"
	};
	
	public static final String[] RESULTAT = {
			"resultat_euromillions-creer", 
			"resultat_euromillions-modifier-numero_tirage", "resultat_euromillions-modifier-date_tirage", 
			"resultat_euromillions-modifier-boule1", "resultat_euromillions-modifier-boule2", 
			"resultat_euromillions-modifier-boule3", "resultat_euromillions-modifier-boule4", 
			"resultat_euromillions-modifier-boule5", "resultat_euromillions-modifier-etoile1", 
			"resultat_euromillions-modifier-etoile2", "resultat_euromillions-modifier-gagnant_rang1", 
			"resultat_euromillions-modifier-gains_rang1", "resultat_euromillions-modifier-gagnant_rang2", 
			"resultat_euromillions-modifier-gains_rang2", "resultat_euromillions-modifier-gagnant_rang3", 
			"resultat_euromillions-modifier-gains_rang3", "resultat_euromillions-modifier-gagnant_rang4", 
			"resultat_euromillions-modifier-gains_rang4", "resultat_euromillions-modifier-gagnant_rang5", 
			"resultat_euromillions-modifier-gains_rang5", "resultat_euromillions-modifier-gagnant_rang6", 
			"resultat_euromillions-modifier-gains_rang6", "resultat_euromillions-modifier-gagnant_rang7", 
			"resultat_euromillions-modifier-gains_rang7", "resultat_euromillions-modifier-gagnant_rang8", 
			"resultat_euromillions-modifier-gains_rang8", "resultat_euromillions-modifier-gagnant_rang9", 
			"resultat_euromillions-modifier-gains_rang9", "resultat_euromillions-modifier-gagnant_rang10", 
			"resultat_euromillions-modifier-gains_rang10", "resultat_euromillions-modifier-gagnant_rang11", 
			"resultat_euromillions-modifier-gains_rang11", "resultat_euromillions-modifier-gagnant_rang12", 
			"resultat_euromillions-modifier-gains_rang12", "resultat_euromillions-modifier-gagnant_rang13", 
			"resultat_euromillions-modifier-gains_rang13", "resultat_euromillions-modifier-mymillion", 
			"resultat_euromillions-supprimer"
	};
	
	public static final String[] RETARD = {
			"retard-creer", "retard-supprimer", "retard-modifier-fk_joueur_id", 
			"retard-modifier-date_constat", "retard-modifier-date_relance", "retard-modifier-relance"
	};
	
	public static final String[] UTILISATEUR = {
			"utilisateur-creer", "utilisateur-supprimer", "utilisateur-prop-modifier-id", 
			"utilisateur-prop-modifier-pseudo", "utilisateur-prop-modifier-nom", "utilisateur-prop-modifier-prenom", 
			"utilisateur-prop-modifier-email", "utilisateur-prop-modifier-grain_de_sel", 
			"utilisateur-prop-modifier-mot_de_passe", "utilisateur-prop-modifier-niveau", 
			"utilisateur-prop-modifier-fk_portefeuille_id", "utilisateur-prop-modifier-fk_privilege_id", 
			"utilisateur-modifier-id", "utilisateur-modifier-pseudo", "utilisateur-modifier-nom", 
			"utilisateur-modifier-prenom", "utilisateur-modifier-email", "utilisateur-modifier-grain_de_sel", 
			"utilisateur-modifier-mot_de_passe", "utilisateur-modifier-niveau", 
			"utilisateur-modifier-fk_portefeuille_id", "utilisateur-modifier-fk_privilege_id"
	};
	
	public static final String PRIVILEGES_UTILISATEUR = "banque-creer=0;banque-supprimer=0;banque-modifier-fonds=0;"
			+ "banque-modifier-mises=0;banque-modifier-gains=0;grille-creer=0;grille-prop-supprimer=0;"
			+ "grille-supprimer=0;grille-prop-modifier-nom=0;grille-prop-modifier-numeros=0;"
			+ "grille-prop-modifier-etoiles=0;grille-prop-modifier-etoile_plus=0;grille-prop-modifier-mymillion=0;"
			+ "grille-prop-modifier-fk_createur=0;grille-prop-modifier-fk_jeu_id=0;grille-prop-modifier-fk_banque_id=0;"
			+ "grille-prop-modifier-publique=0;grille-prop-modifier-visible=0;grille-prop-modifier-active=0;"
			+ "grille-modifier-nom=0;grille-modifier-numeros=0;grille-modifier-etoiles=0;grille-modifier-etoile_plus=0;"
			+ "grille-modifier-mymillion=0;grille-modifier-fk_createur=0;grille-modifier-fk_jeu_id=0;"
			+ "grille-modifier-fk_banque_id=0;grille-modifier-publique=0;grille-modifier-visible=0;"
			+ "grille-modifier-active=0;jeu-creer=0;jeu-supprimer=0;jeu-modifier-nom=0;"
			+ "jeu-modifier-tirage_par_semaine=0;jeu-modifier-jour_de_tirage=0;jeu-modifier-prix_par_tirage=0;"
			+ "jeu-modifier-heure_validation=0;jour-creer=0;jour-supprimer=0;jour-modifier-date_jour=0;"
			+ "jour-modifier-fk_lien_gu_id=0;jour-modifier-paye=0;lien_grille_utilisateur-creer=0;"
			+ "lien_grille_utilisateur-supprimer=0;lien_grille_utilisateur-modifier-fk_grille_id=0;"
			+ "lien_grille_utilisateur-modifier-fk_utilisateur_id=0;log-creer=0;log-supprimer=0;log-modifier-utc=0;"
			+ "log-modifier-jour=0;log-modifier-heure=0;log-modifier-niveau=0;log-modifier-type=0;"
			+ "log-modifier-fk_declencheur=0;log-modifier-fk_joueur_lie=0;log-modifier-fk_grille_liee=0;"
			+ "log-modifier-message=0;portefeuille-creer=0;portefeuille-supprimer=0;"
			+ "portefeuille-modifier-date_creation=0;portefeuille-modifier-fonds=0;privilege-creer=0;"
			+ "privilege-supprimer=0;privilege-modifier-privileges=0;resultat_euromillions-creer=0;"
			+ "resultat_euromillions-modifier-numero_tirage=0;resultat_euromillions-modifier-date_tirage=0;"
			+ "resultat_euromillions-modifier-boule1=0;resultat_euromillions-modifier-boule2=0;"
			+ "resultat_euromillions-modifier-boule3=0;resultat_euromillions-modifier-boule4=0;"
			+ "resultat_euromillions-modifier-boule5=0;resultat_euromillions-modifier-etoile1=0;"
			+ "resultat_euromillions-modifier-etoile2=0;resultat_euromillions-modifier-gagnant_rang1=0;"
			+ "resultat_euromillions-modifier-gains_rang1=0;resultat_euromillions-modifier-gagnant_rang2=0;"
			+ "resultat_euromillions-modifier-gains_rang2=0;resultat_euromillions-modifier-gagnant_rang3=0;"
			+ "resultat_euromillions-modifier-gains_rang3=0;resultat_euromillions-modifier-gagnant_rang4=0;"
			+ "resultat_euromillions-modifier-gains_rang4=0;resultat_euromillions-modifier-gagnant_rang5=0;"
			+ "resultat_euromillions-modifier-gains_rang5=0;resultat_euromillions-modifier-gagnant_rang6=0;"
			+ "resultat_euromillions-modifier-gains_rang6=0;resultat_euromillions-modifier-gagnant_rang7=0;"
			+ "resultat_euromillions-modifier-gains_rang7=0;resultat_euromillions-modifier-gagnant_rang8=0;"
			+ "resultat_euromillions-modifier-gains_rang8=0;resultat_euromillions-modifier-gagnant_rang9=0;"
			+ "resultat_euromillions-modifier-gains_rang9=0;resultat_euromillions-modifier-gagnant_rang10=0;"
			+ "resultat_euromillions-modifier-gains_rang10=0;resultat_euromillions-modifier-gagnant_rang11=0;"
			+ "resultat_euromillions-modifier-gains_rang11=0;resultat_euromillions-modifier-gagnant_rang12=0;"
			+ "resultat_euromillions-modifier-gains_rang12=0;resultat_euromillions-modifier-gagnant_rang13=0;"
			+ "resultat_euromillions-modifier-gains_rang13=0;resultat_euromillions-modifier-mymillion=0;"
			+ "resultat_euromillions-supprimer=0;retard-creer=0;retard-supprimer=0;retard-modifier-fk_joueur_id=0;"
			+ "retard-modifier-date_constat=0;retard-modifier-date_relance=0;retard-modifier-relance=0;"
			+ "utilisateur-creer=0;utilisateur-supprimer=0;utilisateur-prop-modifier-id=0;"
			+ "utilisateur-prop-modifier-pseudo=0;utilisateur-prop-modifier-nom=1;utilisateur-prop-modifier-prenom=1;"
			+ "utilisateur-prop-modifier-email=1;utilisateur-prop-modifier-grain_de_sel=0;"
			+ "utilisateur-prop-modifier-mot_de_passe=1;utilisateur-prop-modifier-niveau=0;"
			+ "utilisateur-prop-modifier-fk_portefeuille_id=0;utilisateur-prop-modifier-fk_privilege_id=0;"
			+ "utilisateur-modifier-id=0;utilisateur-modifier-pseudo=0;utilisateur-modifier-nom=0;"
			+ "utilisateur-modifier-prenom=0;utilisateur-modifier-email=0;utilisateur-modifier-grain_de_sel=0;"
			+ "utilisateur-modifier-mot_de_passe=0;utilisateur-modifier-niveau=0;"
			+ "utilisateur-modifier-fk_portefeuille_id=0;utilisateur-modifier-fk_privilege_id=0";
}
