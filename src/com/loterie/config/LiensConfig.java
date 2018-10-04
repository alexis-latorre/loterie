package com.loterie.config;

import java.util.HashMap;
import java.util.Map;

public class LiensConfig {
	public static final String LIEN_UTILISATEUR 				= "lienUtilisateur";
	public static final String LIEN_UTILISATEUR_HREF 			= "<a href=\"" + Constants.CONTEXTE + 
			Constants.URL_ADMIN_DETAILS_UTILISATEUR + "?id=:id\"" + 
			" title=\"Voir les détails de l'utilisateur\">:pseudo</a>";
	public static final String[] LIEN_UTILISATEUR_REP			= {":id", ":pseudo"}; 
	
	private Map<String, LienStruct> liensBruts = new HashMap<String, LienStruct>();
	private static LiensConfig INSTANCE = new LiensConfig();
	
	private class LienStruct {
		private String href;
		private String[] remplacements;
		
		public LienStruct(String href, String[] remplacements) {
			this.href = href;
			this.remplacements = remplacements;
		}
		
		public String getHref() {
			return href;
		}
		
		public String[] getRemplacements() {
			return remplacements;
		}
	}
	
	private LiensConfig() {
		LienStruct struct = new LienStruct(LIEN_UTILISATEUR_HREF, LIEN_UTILISATEUR_REP);
		liensBruts.put(LIEN_UTILISATEUR, struct);
	}
	
	private static LiensConfig getInstance() {
		return INSTANCE;
	}
	
	public static Map<String, Object> getLienConfig(String l) {
		LiensConfig lc = getInstance();
		Map<String, Object> retour = new HashMap<String, Object>();
		LienStruct ls = lc.liensBruts.get(l);
		retour.put("url", ls.getHref());
		retour.put("rep", ls.getRemplacements());
		
		return retour;
	}

	public static String getLienConfigFormatte(String lien, String[] params) {
		Map<String, Object> urlParts = LiensConfig.getLienConfig(lien);
		String url = (String) urlParts.get("url");
		String[] reps = (String[]) urlParts.get("rep");
		
		if (url == null || reps == null || params == null || reps.length != params.length) {
			//TODO: Implémenter erreur
			System.out.println("erreur");
			return "";
		} else {
			int i = 0;
			
			for (String rep : reps) {
				url = url.replace(rep, params[i]);
				i++;
			}
			return url;
		}
	}
}
