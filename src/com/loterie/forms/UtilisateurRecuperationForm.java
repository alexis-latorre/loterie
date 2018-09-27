package com.loterie.forms;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.loterie.dao.UtilisateurDao;

public class UtilisateurRecuperationForm {
	private UtilisateurDao utilisateurDao;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private HttpServletRequest req;
	
	public UtilisateurRecuperationForm(UtilisateurDao utilisateurDao, HttpServletRequest req) {
		this.utilisateurDao = utilisateurDao;
		this.req = req;
		majErreurs();
	}
	
	public void recupererRang(Long niveau) {
		try {
			req.setAttribute("joueurs", utilisateurDao.trouverParRoleMinimum(niveau));
		} catch (Exception e) {
			// TODO: Implémenter le message
			erreurs.put("joueurs", "");
			majErreurs();
		}
	}

	private void majErreurs() {
		req.setAttribute("erreurs", erreurs);
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}
}
