package com.loterie.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.config.Constants;
import com.loterie.dao.BanqueDao;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.JeuDao;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.PortefeuilleDao;
import com.loterie.entities.Banque;
import com.loterie.entities.Grille;
import com.loterie.entities.Jour;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Portefeuille;
import com.loterie.entities.Utilisateur;
import com.loterie.forms.CreationGrilleForm;
import com.loterie.forms.JeuGrilleForm;

@WebServlet(urlPatterns = {
		Constants.URL_MEMBRE_PROFIL,
		Constants.URL_MEMBRE_AFFICHER_GRILLES,
		Constants.URL_MEMBRE_CREER_GRILLE, 
		Constants.URL_MEMBRE_AFFICHER_GRILLE,
		Constants.URL_MEMBRE_MODIFIER_GRILLE, 
		Constants.URL_MEMBRE_SUPPRIMER_GRILLE,
		Constants.URL_MEMBRE_REJOINDRE_GRILLE,
		Constants.URL_MEMBRE_QUITTER_GRILLE,
		Constants.URL_MEMBRE_BANQUE_AJOUT,
		Constants.URL_MEMBRE_JOUER_GRILLE
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
	@EJB
	private JeuDao jeuDao;
	@EJB
	private BanqueDao banqueDao;
	@EJB
	private PortefeuilleDao portefeuilleDao;
	@EJB
	private JourDao jourDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		String cible = Constants.URN_MEMBRE_403;
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		if (uri.equals(Constants.URL_MEMBRE_AFFICHER_GRILLE)) {
			cible = Constants.URN_MEMBRE_AFFICHER_GRILLE;
			String strId = req.getParameter("id");
			
			if (strId != null) {
				Long id = Long.valueOf(strId);
				Grille grille = grilleDao.trouverParId(id);
				Jour jour = jourDao.trouverDernierJourJoue(grille);
				session.setAttribute("grille", grille);
				req.setAttribute("utilisateur", utilisateur);
				req.setAttribute("grille", grille);
				req.setAttribute("jour", jour);
			}
		} else if (utilisateur != null) {
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
					int nbNumeros = 0;
					List<List<Integer>> tableNumeros = new ArrayList<List<Integer>>();
					
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
					
					cible = Constants.URN_MEMBRE_CREER_GRILLE;
				} else if (uri.equals(Constants.URL_MEMBRE_SUPPRIMER_GRILLE)) {
					String id = req.getParameter("id");
					Grille grille = grilleDao.trouverParId(Long.valueOf(id));
					grilleDao.supprimerGrille(grille);
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
				} else if (uri.equals(Constants.URL_MEMBRE_MODIFIER_GRILLE)) {
					String id = req.getParameter("id");
					Grille grille = grilleDao.trouverParId(Long.valueOf(id));
					int nbNumeros = 0;
					List<List<Integer>> tableNumeros = new ArrayList<List<Integer>>();
					
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
					
					req.setAttribute("grille", grille);
					cible = Constants.URN_MEMBRE_MODIFIER_GRILLE;
				} else if (uri.equals(Constants.URL_MEMBRE_REJOINDRE_GRILLE)) {
					String id = req.getParameter("id");
					Grille grille = grilleDao.trouverParId(Long.valueOf(id));
					LienGrilleUtilisateur lienGU = new LienGrilleUtilisateur(); 

					lienGU.setUtilisateur(utilisateur);
					lienGU.setGrille(grille);
					lienGUDao.enregistrerLienGrilleUtilisateur(lienGU);

					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
				} else if (uri.equals(Constants.URL_MEMBRE_QUITTER_GRILLE)) {
					String id = req.getParameter("id");
					Grille grille = grilleDao.trouverParId(Long.valueOf(id));
					LienGrilleUtilisateur lienGU = lienGUDao.trouverParGrille(grille).get(0);
					lienGUDao.supprimerLienGU(lienGU);

					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
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
		session.getAttribute("utilisateur");
		jeuDao.trouverParNom(Constants.EUROMILLIONS_NOM);
		req.getParameter("etoilePlus");
		//boolean etoilePlus = etoilePlusStr != null && Long.valueOf(etoilePlusStr) == 1L;
		String myMillion = req.getParameter("myMillion");
		
		if (uri.equals(Constants.URL_MEMBRE_CREER_GRILLE)) {
			new CreationGrilleForm(grilleDao, jeuDao, banqueDao, req);
			resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
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
				grille.setEtoilePlus(false);
				grille.setMyMillion(myMillion);
				grilleDao.modifierGrille(grille);
			}
			resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
			return;
		} else if (uri.equals(Constants.URL_MEMBRE_BANQUE_AJOUT)) {
			boolean loggedIn = false;
			String fonds = req.getParameter("fonds");
			
			if (session.getAttribute("loggedIn") != null) {
				loggedIn = (boolean) session.getAttribute("loggedIn");
			}
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			
			if (loggedIn && utilisateur != null && utilisateur.estModerateur()) {
				cible = Constants.URN_MEMBRE_AFFICHER_GRILLE;
				Grille grille = (Grille) session.getAttribute("grille");
				Banque banque = grille.getBanque();
				Portefeuille portefeuille = utilisateur.getPortefeuille();
				
				if (grille != null && fonds != null) {
					Double montant = Double.valueOf(fonds);
					
					if (portefeuille.getFonds() >= montant) { 
						portefeuille.retirerFonds(montant);
						portefeuilleDao.maj(portefeuille);
						
						banque.ajouterFonds(montant);					
						banqueDao.maj(banque);
						
						//if (cpf.getErreurs().isEmpty()) {
							resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLE);
							return;
					} else {
					}
				}
			}
		} else if (uri.equals(Constants.URL_MEMBRE_JOUER_GRILLE)) {
			cible = Constants.URN_MEMBRE_AFFICHER_GRILLE;
			
			JeuGrilleForm jgf = new JeuGrilleForm(lienGUDao, jourDao, req);
			Map<String, String> erreurs = jgf.getErreurs();
			
			if (erreurs.isEmpty())  {
				jgf.jouer();
				resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLE);
				return;
			} else {
				req.setAttribute("erreurs", erreurs);
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

}
