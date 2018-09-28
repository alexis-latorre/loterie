package com.loterie.config;

import java.util.HashMap;
import java.util.Map;

public class RolesConfig {
	private Map<Long, String> roles_L_S = new HashMap<Long, String>();
	private Map<String, Long> roles_S_L = new HashMap<String, Long>();
	private static RolesConfig INSTANCE = new RolesConfig();
	
	private RolesConfig() {
		roles_L_S.put(Constants.L_UTILISATEUR_ROLE_ADMIN, Constants.S_UTILISATEUR_ROLE_ADMIN);
		roles_L_S.put(Constants.L_UTILISATEUR_ROLE_MODERATEUR, Constants.S_UTILISATEUR_ROLE_MODERATEUR);
		roles_L_S.put(Constants.L_UTILISATEUR_ROLE_MEMBRE, Constants.S_UTILISATEUR_ROLE_MEMBRE);
		roles_L_S.put(Constants.L_UTILISATEUR_ROLE_BASIQUE, Constants.S_UTILISATEUR_ROLE_BASIQUE);

		roles_S_L.put(Constants.S_UTILISATEUR_ROLE_ADMIN, Constants.L_UTILISATEUR_ROLE_ADMIN);
		roles_S_L.put(Constants.S_UTILISATEUR_ROLE_MODERATEUR, Constants.L_UTILISATEUR_ROLE_MODERATEUR);
		roles_S_L.put(Constants.S_UTILISATEUR_ROLE_MEMBRE, Constants.L_UTILISATEUR_ROLE_MEMBRE);
		roles_S_L.put(Constants.S_UTILISATEUR_ROLE_BASIQUE, Constants.L_UTILISATEUR_ROLE_BASIQUE);
	}
	
	private static RolesConfig getInstance() {
		return INSTANCE;
	}
	
	public static String getRole(Long r) {
		RolesConfig rc = getInstance();
		return rc.roles_L_S.get(r);
	}
	
	public static Long getNiveau(String r) {
		RolesConfig rc = getInstance();
		return rc.roles_S_L.get(r);
	}
}
