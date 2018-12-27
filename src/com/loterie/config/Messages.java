package com.loterie.config;

public interface Messages {
	/**
	 * Messages affichés à l'utilisateur
	 */
	public static final String MSG_UTILISATEUR_INTROUVABLE 			= "utilisateur introuvable";
	public static final String MSG_MDP_INVALIDE			 			= "le mot de passe saisi est invalide ou l'utilisateur n'existe pas";
	public static final String MSG_FONDS_INVALIDES		 			= "le montant entré est incorrect. Veuillez saisir un nombre valide";
	public static final String MSG_ID_INVALIDE			 			= "l'ID renseigné n'est pas valide";
	public static final String MSG_ID_INTROUVABLE 					= "l'objet demandé est introuvable";
	public static final String MSG_CREDIT_INVALIDE 					= "le montant renseigné n'est pas un nombre valide";
	public static final String MSG_CHAMP_VIDE		 				= "le champ ne peut être vide";
	public static final String MSG_PRIVILEGE_MANQUANT 				= "privilèges insuffisants pour modifier ce champ";
			
	/**
	 * Messages de la console
	 */
	public static final String A_RESULTAT_VIDE						= "[AVERT.] : %s : Aucun élément n'a été trouvé en base de données.";
	public static final String E_COMPARER_CHAINES_NULL				= "[ERREUR] : Tools.comparerChainesNonNull : La comparaison a échoué car s1 est null";
	public static final String E_COMPARER_CHAINES_DIFFERENT			= "[ERREUR] : Tools.comparerChainesNonNull : La comparaison a échoué car s2 est différent de s1";
	public static final String E_INTEGRITE_ETAPE_MANQUEE			= "[ERREUR] : Tools.motDePasseValide : Le mot de passe ne respecte pas l'étape %s";
	public static final String E_LISTES_VERIF_MDP_DIFFERENTES		= "[ERREUR] : UtilisateurDao.changerMotDePasse : La liste de paramètres doit contenir le même nombre que la liste de contrôles.";
	public static final String E_LONG_INVALIDE 						= "[ERREUR] : %s : L'argument passé (%s) n'est pas de type Long.";
	public static final String E_DOUBLE_INVALIDE					= "[ERREUR] : %s : L'argument passé (%s) n'est pas de type Double.";
}
