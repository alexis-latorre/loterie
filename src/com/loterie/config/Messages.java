package com.loterie.config;

public interface Messages {
	/**
	 * Messages affichés à l'utilisateur
	 */
	public static final String MSG_UTILISATEUR_INTROUVABLE 			= "Utilisateur introuvable";
	public static final String MSG_MDP_INVALIDE			 			= "Le mot de passe saisi est invalide ou l'utilisateur n'existe pas.";
			
	/**
	 * Messages de la console
	 */
	public static final String E_COMPARER_CHAINES_NULL				= "[ERROR]: Tools.comparerChainesNonNull : La comparaison a échoué car s1 est null";
	public static final String E_COMPARER_CHAINES_DIFFERENT			= "[ERROR]: Tools.comparerChainesNonNull : La comparaison a échoué car s2 est différent de s1";
	public static final String E_INTEGRITE_ETAPE_MANQUEE			= "[ERROR]: Tools.motDePasseValide : Le mot de passe ne respecte pas l'étape %s";
}
