package com.loterie.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.config.Constants;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.LienGUDao;
import com.loterie.entities.Grille;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;
import com.loterie.tools.Tools;

@WebServlet(urlPatterns = {
		Constants.URL_MEMBRE_PROFIL,
		Constants.URL_MEMBRE_AFFICHER_GRILLES,
		Constants.URL_MEMBRE_CREER_GRILLE, 
		Constants.URL_MEMBRE_MODIFIER_GRILLE, 
		Constants.URL_MEMBRE_SUPPRIMER_GRILLE,
		Constants.URL_MEMBRE_REJOINDRE_GRILLE,
		Constants.URL_MEMBRE_QUITTER_GRILLE
		})
public class GrilleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	@EJB
	private GrilleDao grilleDao;
	@EJB
	private LienGUDao lienGUDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		// TODO: Créer une 403 membre
		String cible = Constants.URN_ADMIN_403;
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		if (utilisateur != null) {
			if (utilisateur.estMembre()) {
				if (uri.equals(Constants.URL_MEMBRE_AFFICHER_GRILLES)) {
					List<Grille> grillesRejointes = grilleDao.trouverParUtilisateur(utilisateur);
					List<Long> grillesIds = new ArrayList<Long>();
					List<Grille> grillesCreees = grilleDao.trouverParCreateur(utilisateur);
					List<Grille> grilles = new ArrayList<Grille>();

					for (Grille grille : grillesRejointes) {
						grillesIds.add(grille.getId());
						grille.setRejoindre(false);
						grilles.add(grille);
					}
					
					for (Grille grille : grillesCreees) {
						if (!grillesIds.contains(grille.getId())) {
							grille.setRejoindre(true);
							grilles.add(grille);
						}
					}
					req.setAttribute("grilles", grilles);
					cible = Constants.URN_MEMBRE_AFFICHER_GRILLES;
				} else if (uri.equals(Constants.URL_MEMBRE_CREER_GRILLE)) {
					int nbLignesNumeros = 5;
					int numerosParLigne = 13;
					int nbNumeros = 0;
					List<List<Integer>> tableNumeros = new ArrayList<List<Integer>>();
					
					for (int i = 0; i < nbLignesNumeros; i++) {
						List<Integer> ligne = new ArrayList<Integer>();
						
						for (int j = 1; j <= numerosParLigne; j++) {
							nbNumeros++;
							
							if (nbNumeros <= Constants.EUROMILLIONS_NUMEROS) {
								ligne.add((i * numerosParLigne) + j);
							}
						}
						tableNumeros.add(ligne);
					}
					req.setAttribute("tableNumeros", tableNumeros);
					
					int nbLignesEtoiles = 5;
					int etoilesParLigne = 13;
					int nbEtoiles = 0;
					List<List<Integer>> tableEtoiles = new ArrayList<List<Integer>>();
					
					for (int i = 0; i < nbLignesEtoiles; i++) {
						List<Integer> ligne = new ArrayList<Integer>();
						
						for (int j = 1; j <= etoilesParLigne; j++) {
							nbEtoiles++;
							
							if (nbEtoiles <= Constants.EUROMILLIONS_ETOILES) {
								ligne.add((i * etoilesParLigne) + j);
							}
						}
						tableEtoiles.add(ligne);
					}
					req.setAttribute("tableEtoiles", tableEtoiles);
					
					cible = Constants.URN_MEMBRE_CREER_GRILLE;
				} else if (uri.equals(Constants.URL_MEMBRE_SUPPRIMER_GRILLE)) {
					String id = req.getParameter("id");
					Grille grille = grilleDao.trouverParId(Long.valueOf(id));
					grilleDao.supprimerGrille(grille);
					Tools.redirigerVers(req, resp, Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
				} else if (uri.equals(Constants.URL_MEMBRE_MODIFIER_GRILLE)) {
					String id = req.getParameter("id");
					Grille grille = grilleDao.trouverParId(Long.valueOf(id));
					//TODO: Factoriser les numéros et étoiles
					int nbLignesNumeros = 5;
					int numerosParLigne = 13;
					int nbNumeros = 0;
					List<List<Integer>> tableNumeros = new ArrayList<List<Integer>>();
					
					for (int i = 0; i < nbLignesNumeros; i++) {
						List<Integer> ligne = new ArrayList<Integer>();
						
						for (int j = 1; j <= numerosParLigne; j++) {
							nbNumeros++;
							
							if (nbNumeros <= Constants.EUROMILLIONS_NUMEROS) {
								ligne.add((i * numerosParLigne) + j);
							}
						}
						tableNumeros.add(ligne);
					}
					req.setAttribute("tableNumeros", tableNumeros);
					
					int nbLignesEtoiles = 5;
					int etoilesParLigne = 13;
					int nbEtoiles = 0;
					List<List<Integer>> tableEtoiles = new ArrayList<List<Integer>>();
					
					for (int i = 0; i < nbLignesEtoiles; i++) {
						List<Integer> ligne = new ArrayList<Integer>();
						
						for (int j = 1; j <= etoilesParLigne; j++) {
							nbEtoiles++;
							
							if (nbEtoiles <= Constants.EUROMILLIONS_ETOILES) {
								ligne.add((i * etoilesParLigne) + j);
							}
						}
						tableEtoiles.add(ligne);
					}
					req.setAttribute("tableEtoiles", tableEtoiles);
					
					req.setAttribute("grille", grille);
					cible = Constants.URN_MEMBRE_MODIFIER_GRILLE;
				} else if (uri.equals(Constants.URL_MEMBRE_REJOINDRE_GRILLE)) {
					String id = req.getParameter("id");
					Grille grille = grilleDao.trouverParId(Long.valueOf(id));
					LienGrilleUtilisateur lienGU = new LienGrilleUtilisateur(); 

					lienGU.setUtilisateur(utilisateur);
					lienGU.setGrille(grille);
					lienGUDao.enregistrerLienGrilleUtilisateur(lienGU);

					Tools.redirigerVers(req, resp, Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
				} else if (uri.equals(Constants.URL_MEMBRE_QUITTER_GRILLE)) {
					String id = req.getParameter("id");
					System.out.println("ok1");
					Grille grille = grilleDao.trouverParId(Long.valueOf(id));
					System.out.println("ok2");
					LienGrilleUtilisateur lienGU = lienGUDao.trouverParGrille(grille);
					System.out.println("ok3");
					lienGUDao.supprimerLienGU(lienGU);
					System.out.println("ok4");

					Tools.redirigerVers(req, resp, Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
				} else {
					cible = Constants.URN_MEMBRE_ACCUEIL;
				}
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cible = Constants.URN_MEMBRE_AFFICHER_GRILLES;
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		if (uri.equals(Constants.URL_MEMBRE_CREER_GRILLE)) {
			List<String> numeros = Arrays.asList(req.getParameterValues("numeros[]"));
			List<String> etoiles = Arrays.asList(req.getParameterValues("etoiles[]"));
			Grille grille = new Grille();

			if (numeros.size() >= Constants.EUROMILLIONS_NUMEROS_SELECTION_MIN 
					&& numeros.size() <= Constants.EUROMILLIONS_NUMEROS_SELECTION_MAX
					&& etoiles.size() >= Constants.EUROMILLIONS_ETOILES_SELECTION_MIN
					&& etoiles.size() <= Constants.EUROMILLIONS_ETOILES_SELECTION_MAX) {
				grille.setNumeros(numeros);
				grille.setEtoiles(etoiles);
				grille.setEtoilePlus(false);
				grille.setMyMillion(null);
				grille.setUtilisateur(utilisateur);
				grilleDao.enregistrerGrille(grille);
			}
			Tools.redirigerVers(req, resp, Constants.URL_MEMBRE_AFFICHER_GRILLES);
			return;
		} else if (uri.equals(Constants.URL_MEMBRE_MODIFIER_GRILLE)) {
			String id = req.getParameter("id");
			List<String> numeros = Arrays.asList(req.getParameterValues("numeros[]"));
			List<String> etoiles = Arrays.asList(req.getParameterValues("etoiles[]"));
			Grille grille = grilleDao.trouverParId(Long.valueOf(id));

			if (numeros.size() >= Constants.EUROMILLIONS_NUMEROS_SELECTION_MIN 
					&& numeros.size() <= Constants.EUROMILLIONS_NUMEROS_SELECTION_MAX
					&& etoiles.size() >= Constants.EUROMILLIONS_ETOILES_SELECTION_MIN
					&& etoiles.size() <= Constants.EUROMILLIONS_ETOILES_SELECTION_MAX) {
				grille.setNumeros(numeros);
				grille.setEtoiles(etoiles);
				/*grille.setEtoilePlus(false);
				grille.setMyMillion(null);
				grille.setUtilisateur(utilisateur);*/
				grilleDao.modifierGrille(grille);
			}
			Tools.redirigerVers(req, resp, Constants.URL_MEMBRE_AFFICHER_GRILLES);
			return;
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

}