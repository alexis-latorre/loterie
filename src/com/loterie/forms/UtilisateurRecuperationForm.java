package com.loterie.forms;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.loterie.config.RolesConfig;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

public class UtilisateurRecuperationForm {
	private UtilisateurDao utilisateurDao;
	private GrilleDao grilleDao;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private HttpServletRequest req;
	
	public UtilisateurRecuperationForm(UtilisateurDao utilisateurDao, GrilleDao grilleDao, HttpServletRequest req) {
		this.utilisateurDao = utilisateurDao;
		this.grilleDao = grilleDao;
		this.req = req;
		majErreurs();
	}
	
	public void recupererRang(Long niveau) {
		List<Utilisateur> joueurs = utilisateurDao.trouverParRoleMinimum(niveau);
		
		for (Utilisateur joueur : joueurs) {
			joueur.setNomRole(Tools.title(RolesConfig.getRole(joueur.getNiveau())));
			List<Grille> grilles = grilleDao.trouverParUtilisateur(joueur);

			if (grilles != null) {
				joueur.setGrilles(grilles);
			}
		}
		
		try {
			req.setAttribute("joueurs", joueurs);
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

	public void recupererId() {
		try {
			Utilisateur joueur = utilisateurDao.trouverParId(Long.valueOf(req.getParameter("id")));
			joueur.setNomRole(Tools.title(RolesConfig.getRole(joueur.getNiveau())));
			req.setAttribute("joueur", joueur);
		} catch (Exception e) {
			// TODO: Implémenter le message
			e.printStackTrace();
			erreurs.put("joueur", "");
			majErreurs();
		}
	}
}
