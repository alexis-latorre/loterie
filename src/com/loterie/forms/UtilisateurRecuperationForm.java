package com.loterie.forms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.loterie.business.GrilleHTML;
import com.loterie.business.UtilisateurHTML;
import com.loterie.config.RolesConfig;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.RetardDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Retard;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

public class UtilisateurRecuperationForm {
	private UtilisateurDao utilisateurDao;
	private GrilleDao grilleDao;
	private RetardDao retardDao;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private HttpServletRequest req;
	
	public UtilisateurRecuperationForm(UtilisateurDao utilisateurDao, GrilleDao grilleDao, RetardDao retardDao, HttpServletRequest req) {
		this.utilisateurDao = utilisateurDao;
		this.grilleDao = grilleDao;
		this.retardDao = retardDao;
		this.req = req;
		majErreurs();
	}
	
	public void recupererRang(Long niveau) {
		List<Utilisateur> utilisateurs = utilisateurDao.trouverParRoleMinimum(niveau);
		List<UtilisateurHTML> joueurs = new ArrayList<UtilisateurHTML>();
		
		for (Utilisateur utilisateur : utilisateurs) {
			utilisateur.setNomRole(Tools.titre(RolesConfig.getRole(utilisateur.getNiveau())));
			List<Grille> grilles = grilleDao.trouverParUtilisateur(utilisateur);
			UtilisateurHTML joueur = new UtilisateurHTML(utilisateur);

			if (grilles != null) {
				utilisateur.setGrilles(grilles);
				
				List<GrilleHTML> grillesHTML = new ArrayList<GrilleHTML>();
				
				for (Grille grille : grilles) {
					grillesHTML.add(new GrilleHTML(grille));
				}
				joueur.setGrilles(grillesHTML);
			}
			Retard retard = retardDao.trouverParUtilisateur(utilisateur);
			
			if (retard != null) {
				joueur.setRetard(retard);
			}
			
			joueurs.add(joueur);
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
			joueur.setNomRole(Tools.titre(RolesConfig.getRole(joueur.getNiveau())));
			req.setAttribute("joueur", new UtilisateurHTML(joueur));
		} catch (Exception e) {
			// TODO: Implémenter le message
			e.printStackTrace();
			erreurs.put("joueur", "");
			majErreurs();
		}
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateurDao.trouverParId(Long.valueOf(req.getParameter("id")));
	}
}
