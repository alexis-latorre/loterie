package com.loterie.forms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.loterie.config.Constants;
import com.loterie.dao.BanqueDao;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.JeuDao;
import com.loterie.dao.JeuDeclinaisonDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Banque;
import com.loterie.entities.Grille;
import com.loterie.entities.Jeu;
import com.loterie.entities.JeuDeclinaison;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;

public class GrilleCreationForm {	
	private Map<String, String> erreurs;
	private GrilleDao grilleDao;
	private UtilisateurDao utilisateurDao;
	private JeuDao jeuDao;
	private BanqueDao banqueDao;
	private Utilisateur utilisateur;
	private LienGUDao lienGUDao;
	private JeuDeclinaisonDao jeuDeclinaisonDao;
	private Jeu jeu;
	private JeuDeclinaison jeuDeclinaison;
	private Banque banque;
	private Grille grille;
	private HttpServletRequest req;
	private HttpSession session;
	
	public GrilleCreationForm(GrilleDao grilleDao, HttpServletRequest req) {
		this.grilleDao = grilleDao;
		this.req = req;
	}
	
	public GrilleCreationForm(UtilisateurDao utilisateurDao, HttpServletRequest req) {
		this.utilisateurDao = utilisateurDao;
		this.req = req;
	}
	
	public GrilleCreationForm(GrilleDao grilleDao, UtilisateurDao utilisateurDao, JeuDeclinaisonDao jeuDeclinaisonDao, 
			HttpServletRequest req) {
		this.grilleDao = grilleDao;
		this.utilisateurDao = utilisateurDao;
		this.jeuDeclinaisonDao = jeuDeclinaisonDao;
		this.req = req;
	}

	public GrilleCreationForm(GrilleDao grilleDao, JeuDao jeuDao, BanqueDao banqueDao, LienGUDao lienGUDao,
			JeuDeclinaisonDao jeuDeclinaisonDao, UtilisateurDao utilisateurDao, HttpServletRequest req) {
		this.grilleDao = grilleDao;
		this.jeuDao = jeuDao;
		this.banqueDao = banqueDao;
		this.lienGUDao = lienGUDao;
		this.utilisateurDao = utilisateurDao;
		this.jeuDeclinaisonDao = jeuDeclinaisonDao;
		this.req = req;
		erreurs = new HashMap<>();
		session = this.req.getSession();
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		jeu = this.jeuDao.trouverParNom(Constants.EUROMILLIONS_NOM);
		BanqueCreationForm cbf = new BanqueCreationForm(this.banqueDao);
		banque = cbf.getBanque();
		
		validerForm();
	}
	
	public GrilleCreationForm(GrilleDao grilleDao, JeuDao jeuDao, BanqueDao banqueDao, JeuDeclinaisonDao jeuDeclinaisonDao, 
			HttpServletRequest req) {
		this.grilleDao = grilleDao;
		this.jeuDao = jeuDao;
		this.banqueDao = banqueDao;
		this.jeuDeclinaisonDao = jeuDeclinaisonDao;
		this.req = req;
		erreurs = new HashMap<>();
		session = this.req.getSession();
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		jeu = this.jeuDao.trouverParNom(Constants.EUROMILLIONS_NOM);
		BanqueCreationForm cbf = new BanqueCreationForm(this.banqueDao);
		banque = cbf.getBanque();
		
		validerForm();
	}

	public void validerForm() {		
		String nom = this.req.getParameter("nom");
		String myMillion = this.req.getParameter("myMillion");
		List<String> numeros = new ArrayList<>();
		List<String> etoiles = new ArrayList<>();
		
		if (nom != null) {
			nom = nom.trim();
			
			if (nom.isEmpty()) {
				nom = "Grille sans nom";
			}
		} else {
			nom = "Grille sans nom";
		}
		
		if (this.req.getParameterValues("numeros[]") != null) {
			numeros = Arrays.asList(this.req.getParameterValues("numeros[]"));
		}
		
		if (this.req.getParameterValues("etoiles[]") != null) {
			etoiles = Arrays.asList(this.req.getParameterValues("etoiles[]"));
		}
		boolean etoilePlus = this.req.getParameter("etoilePlus") != null 
				&& this.req.getParameter("etoilePlus").equals("on");
		Grille grille = new Grille();
		Map<String, String> indexes = new HashMap<String, String>();
		indexes.put("index1", "nb_numeros:" + numeros.size());
		indexes.put("index2", "nb_etoiles:" + etoiles.size());
		jeuDeclinaison = jeuDeclinaisonDao.trouverParIndex(indexes);

		if (null != jeuDeclinaison) {
			grille.setNom(nom);
			grille.setNumeros(numeros);
			grille.setEtoiles(etoiles);
			grille.setEtoilePlus(etoilePlus);
			grille.setMyMillion(myMillion);
			grille.setJeu(jeu);
			grille.setJeuDeclinaison(jeuDeclinaison);
			grille.setUtilisateur(utilisateur);
			grille.setBanque(banque);
			this.grille = grille;
			ajouter();
		} else {
			erreurs.put("declinaison", "Cette déclinaison n'existe pas");
		}
	}
	
	public void ajouter() {
		this.grilleDao.creer(grille);
	}
	
	public void maj() {
		this.grilleDao.maj(grille);
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public Grille getGrille() {
		return grille;
	}
	
	public HttpServletRequest getFormulaire() {
		return getFormulaire(null);
	}

	public HttpServletRequest getFormulaire(String idStr) {
		Grille grille = null;
		Long id = null;
				
		try {
			if (idStr != null) {
				id = Long.valueOf(idStr);
			}
		} catch (Exception e) {
			// Ne rien faire
		}
		
		if (id != null) {
			grille = grilleDao.trouverParId(id);
		}
		
		int nbNumeros = 0;
		List<List<Integer>> tableNumeros = new ArrayList<List<Integer>>();
		// Seuls les membres peuvent jouer
		List<Utilisateur> utilisateurs = utilisateurDao.trouverParRoleMinimum(Constants.L_UTILISATEUR_ROLE_MEMBRE);
		List<Utilisateur> utilisateursEligibles = new ArrayList<Utilisateur>();
		List<Utilisateur> joueurs = utilisateurDao.trouverParGrille(grille);
		
		for (Utilisateur joueur : utilisateurs) {
			if (null == lienGUDao || null == lienGUDao.trouverParGrilleEtUtilisateur(grille, joueur)) {
				utilisateursEligibles.add(joueur);
			}
		}
		req.setAttribute("utilisateurs", utilisateursEligibles);
		
		for (int i = 0; i < Constants.EUROMILLIONS_NUMEROS_NB_LIGNES; i++) {
			List<Integer> ligne = new ArrayList<Integer>();
			
			for (int j = 1; j <= Constants.EUROMILLIONS_NUMEROS_NB_PAR_LIGNES; j++) {
				nbNumeros++;
				
				if (nbNumeros <= Constants.EUROMILLIONS_NUMEROS) {
					ligne.add((i * Constants.EUROMILLIONS_NUMEROS_NB_PAR_LIGNES) + j);
				}
			}
			tableNumeros.add(ligne);
		}
		req.setAttribute("tableNumeros", tableNumeros);
		
		int nbEtoiles = 0;
		List<List<Integer>> tableEtoiles = new ArrayList<List<Integer>>();
		
		for (int i = 0; i < Constants.EUROMILLIONS_ETOILES_NB_LIGNES; i++) {
			List<Integer> ligne = new ArrayList<Integer>();
			
			for (int j = 1; j <= Constants.EUROMILLIONS_ETOILES_NB_PAR_LIGNES; j++) {
				nbEtoiles++;
				
				if (nbEtoiles <= Constants.EUROMILLIONS_ETOILES) {
					ligne.add((i * Constants.EUROMILLIONS_ETOILES_NB_PAR_LIGNES) + j);
				}
			}
			tableEtoiles.add(ligne);
		}
		req.setAttribute("tableEtoiles", tableEtoiles);
		
		if (id != null) {
			req.setAttribute("grille", grille);
			req.setAttribute("joueurs", joueurs);
		}
		
		return req;
	}

	public Map<String, Object> creer() {
		Map<String, Object> retour = new HashMap<String, Object>();
		List<String> joueurs = new ArrayList<>();
		List<Utilisateur> joueursRetour = new ArrayList<>();
		
		if (req.getParameterValues("joueurs[]") != null) {
			joueurs = Arrays.asList(req.getParameterValues("joueurs[]"));
		}
		
		for (String joueurStr : joueurs) {
			Utilisateur joueur = utilisateurDao.trouverParId(Long.valueOf(joueurStr));
			joueursRetour.add(joueur);
			LienGrilleUtilisateur lienGU = new LienGrilleUtilisateur(); 
			lienGU.setUtilisateur(joueur);
			lienGU.setGrille(grille);
			lienGUDao.creer(lienGU);
		}
		retour.put("joueurs", joueursRetour);
		retour.put("grille", grille);
		
		return retour;
	}

	public Map<String, Object> modifier() {
		Map<String, Object> retour = new HashMap<String, Object>();
		String id = req.getParameter("id");
		String nom = req.getParameter("nom");
		List<String> numeros = Arrays.asList(req.getParameterValues("numeros[]"));
		List<String> etoiles = Arrays.asList(req.getParameterValues("etoiles[]"));
		List<String> joueursIds = Arrays.asList(req.getParameterValues("joueurs[]"));
		String myMillion = req.getParameter("myMillion");
		boolean etoilePlus = this.req.getParameter("etoilePlus") != null 
				&& this.req.getParameter("etoilePlus").equals("on");
		Grille grille = grilleDao.trouverParId(Long.valueOf(id));
		List<String> joueursGrille = new ArrayList<String>(); 
		List<LienGrilleUtilisateur> lgus = lienGUDao.trouverParGrille(grille);
		
		for (LienGrilleUtilisateur lgu : lgus) {
			joueursGrille.add(lgu.getUtilisateur().getId().toString());
		}
		
		if (nom != null) {
			nom = nom.trim();
			
			if (nom.isEmpty()) {
				nom = "Grille sans nom";
			}
		} else {
			nom = "Grille sans nom";
		}

		if (numeros.size() >= Constants.EUROMILLIONS_NUMEROS_SELECTION_MIN 
				&& numeros.size() <= Constants.EUROMILLIONS_NUMEROS_SELECTION_MAX
				&& etoiles.size() >= Constants.EUROMILLIONS_ETOILES_SELECTION_MIN
				&& etoiles.size() <= Constants.EUROMILLIONS_ETOILES_SELECTION_MAX) {
			grille.setNom(nom);
			grille.setNumeros(numeros);
			grille.setEtoiles(etoiles);
			grille.setEtoilePlus(etoilePlus);
			grille.setMyMillion(myMillion);
			grilleDao.maj(grille);
		}

		List<Utilisateur> joueursAjoutes = new ArrayList<Utilisateur>();
		List<Utilisateur> joueursRetires = new ArrayList<Utilisateur>();
		
		for (String joueurGrille : joueursGrille) {
			if (!joueursIds.contains(joueurGrille)) {
				Utilisateur joueurRetire = utilisateurDao.trouverParId(Long.valueOf(joueurGrille));
				lienGUDao.supprimer(lienGUDao.trouverParGrilleEtUtilisateur(grille, joueurRetire));
				joueursRetires.add(joueurRetire);
			}
		}
		
		for (String idJoueur : joueursIds) {
			if (!joueursGrille.contains(idJoueur)) {
				Utilisateur joueurAjoute = utilisateurDao.trouverParId(Long.valueOf(idJoueur));
				LienGrilleUtilisateur lgu = new LienGrilleUtilisateur();
				lgu.setGrille(grille);
				lgu.setUtilisateur(joueurAjoute);
				lienGUDao.creer(lgu);
				joueursAjoutes.add(joueurAjoute);
			}
		}

		retour.put("joueursRetires", joueursRetires);
		retour.put("joueursAjoutes", joueursAjoutes);
		retour.put("grille", grille);
		
		return retour;
	}

	public void supprimer() {
		String id = req.getParameter("id");
		Grille grille = grilleDao.trouverParId(Long.valueOf(id));
		grille.setVisible(false);
		grilleDao.maj(grille);
	}
}
