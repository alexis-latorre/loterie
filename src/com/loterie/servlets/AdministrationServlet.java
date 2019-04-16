package com.loterie.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.business.GainRedistribuableHTML;
import com.loterie.config.Constants;
import com.loterie.config.Privileges;
import com.loterie.dao.GrilleDao;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.LogDao;
import com.loterie.dao.PortefeuilleDao;
import com.loterie.dao.PrivilegeDao;
import com.loterie.dao.RetardDao;
import com.loterie.dao.UtilisateurDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Jour;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;
import com.loterie.forms.GrilleActivationForm;
import com.loterie.forms.LogRecuperationForm;
import com.loterie.forms.PrivilegesModificationForm;
import com.loterie.forms.UtilisateurCreditForm;
import com.loterie.forms.UtilisateurRecuperationForm;
import com.loterie.tools.Logger;

@WebServlet(urlPatterns = {
		Constants.URL_ADMIN_ACCUEIL,
		Constants.URL_ADMIN_CREDITER,
		Constants.URL_ADMIN_REDISTRIBUER,
		Constants.URL_ADMIN_DETAILS_UTILISATEUR,
		Constants.URL_ADMIN_DETAILS_UTILISATEURS,
		Constants.URL_ADMIN_LOGS,
		Constants.URL_ADMIN_RETABLIR_GRILLE,
		Constants.URL_ADMIN_MODIFIER_PRIVILEGES
		})
public class AdministrationServlet extends HttpServlet {
	private static final long serialVersionUID = 5L;

	@EJB
	private UtilisateurDao utilisateurDao;
	@EJB
	private GrilleDao grilleDao;
	@EJB
	private RetardDao retardDao;
	@EJB
	private PortefeuilleDao portefeuilleDao;
	@EJB
	private LogDao logDao;
	@EJB
	private PrivilegeDao privilegeDao;
	@EJB
	private LienGUDao lguDao;
	@EJB
	private JourDao jourDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		// Si l'utilisateur n'a pas de droit d'aministration, il sera redirigé vers la page d'erreur 403
		String cible = Constants.URN_ADMIN_403;
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		// Vérifie si l'utilisateur existe en session
		if (utilisateur != null) {
			switch (uri) {
				case Constants.URL_ADMIN_CREDITER: {
					if (!utilisateur.estModerateur() && !utilisateur.estAdministrateur()) break;

					req.setAttribute("titrePage", "creditPlayer");
					
					UtilisateurRecuperationForm urf = new UtilisateurRecuperationForm(utilisateurDao, grilleDao, 
							retardDao, req);
					String id = req.getParameter("id");
					
					if (id != null && !id.isEmpty()) {
						req.getSession().setAttribute("joueurAcrediter", id);
						urf.recupererId();
					} else {
						urf.recupererRang(Constants.L_UTILISATEUR_ROLE_MEMBRE);
					}
					cible = Constants.URN_ADMIN_CREDITER;
					break;
				}
				case Constants.URL_ADMIN_DETAILS_UTILISATEURS: {
					if (!utilisateur.estModerateur() && !utilisateur.estAdministrateur()) break;

					req.setAttribute("titrePage", "listUsers");
					UtilisateurRecuperationForm urf = new UtilisateurRecuperationForm(utilisateurDao, grilleDao, 
							retardDao, req);
					urf.recupererRang(Constants.L_UTILISATEUR_ROLE_BASIQUE);
					cible = Constants.URN_ADMIN_DETAILS_UTILISATEURS;
					break;
				}
				case Constants.URL_ADMIN_DETAILS_UTILISATEUR: {
					if (!utilisateur.estModerateur() && !utilisateur.estAdministrateur()) break;
					
					UtilisateurRecuperationForm urf = new UtilisateurRecuperationForm(utilisateurDao, grilleDao, 
							retardDao, req);
					urf.recupererId();
					//TODO: bouger cette portion
					Map<String, String[]> privileges = new HashMap<String, String[]>();
					privileges.put("banque", Privileges.BANQUE);
					privileges.put("grille", Privileges.GRILLE);
					privileges.put("jeu", Privileges.JEU);
					privileges.put("jour", Privileges.JOUR);
					privileges.put("lien-grille_utilisateur", Privileges.LGU);
					privileges.put("log", Privileges.LOG);
					privileges.put("portefeuille", Privileges.PORTEFEUILLE);
					privileges.put("privilege", Privileges.PRIVILEGE);
					privileges.put("resultat_euromillions", Privileges.RESULTAT);
					privileges.put("retard", Privileges.RETARD);
					privileges.put("utilisateur", Privileges.UTILISATEUR);
					req.setAttribute("privileges", privileges);
					session.setAttribute("idJoueur", req.getParameter("id"));
					cible = Constants.URN_ADMIN_DETAILS_UTILISATEUR;
					break;
				}
				case Constants.URL_ADMIN_LOGS: {
					if (!utilisateur.estModerateur() && !utilisateur.estAdministrateur()) break;

					req.setAttribute("titrePage", "viewLogs");
					LogRecuperationForm lrf = new LogRecuperationForm(logDao);
					req.setAttribute("logs", lrf.parseLogs());
					cible = Constants.URN_ADMIN_LOGS;
					break;
				}
				case Constants.URL_ADMIN_REDISTRIBUER: {
					if (!utilisateur.estModerateur() && !utilisateur.estAdministrateur()) break;

					req.setAttribute("titrePage", "redistribute");
					List<GainRedistribuableHTML> gains = new ArrayList<GainRedistribuableHTML>();
					List<LienGrilleUtilisateur> lgusUtilisateur = lguDao.trouverGagnantParUtilisateur(utilisateur);

					for (LienGrilleUtilisateur lguUtilisateur : lgusUtilisateur) {
						Grille grille = lguUtilisateur.getGrille();
						List<Jour> jours = jourDao.trouverParLGU(lguUtilisateur);
						
						for (Jour jour : jours) {
							if (jour.getGains() > 0) {
								List<LienGrilleUtilisateur> lgus = lguDao.trouverParGrille(grille);
								List<Utilisateur> joueurs = new ArrayList<Utilisateur>();
								
								for (LienGrilleUtilisateur lgu : lgus) {
									jourDao.trouverParLGU(lgu);
									joueurs.add(lgu.getUtilisateur());
								}
								GainRedistribuableHTML gain = new GainRedistribuableHTML(jour, grille, joueurs);
								gains.add(gain);
							}
						}
					}
					
					req.setAttribute("gains", gains);
					cible = Constants.URN_ADMIN_REDISTRIBUER;
					break;
				}
				case Constants.URL_ADMIN_RETABLIR_GRILLE: {
					GrilleActivationForm gaf = new GrilleActivationForm(grilleDao, utilisateur, req);
					gaf.retablir();
					
					resp.sendRedirect(req.getServletContext().getContextPath() + Constants.URL_MEMBRE_AFFICHER_GRILLES);
					return;
				}
				default: cible = Constants.URN_ADMIN_ACCUEIL;
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI().replace(Constants.CONTEXTE, "");
		// Si l'utilisateur n'a pas de droit d'aministration, il sera redirigé vers la page d'erreur 403
		String cible = Constants.URN_ADMIN_403;
		HttpSession session = req.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		// Vérifie si l'utilisateur existe en session
		if (utilisateur != null) {
			// Vérifie que l'utilisateur en session est au minimum un modérateur
			if (utilisateur.estModerateur() || utilisateur.estAdministrateur()) {
				switch (uri) {
					case Constants.URL_ADMIN_CREDITER: {
						//DevTools.dumpSession(req);
						String id = req.getParameter("joueurAcrediter");
						
						if (id == null || id.isEmpty()) {
							req.setAttribute("joueurAcrediter", session.getAttribute("joueurAcrediter"));
							req.getSession().setAttribute("joueurAcrediter", null);
						} else {
							req.setAttribute("joueurAcrediter", id);
						}
						UtilisateurCreditForm ucf = new UtilisateurCreditForm(utilisateurDao, portefeuilleDao, req);
						Map<String, Object> retour = ucf.crediter();
						Utilisateur joueurCredite = ucf.getUtilisateur();
						
						if (utilisateur.getId().equals(joueurCredite.getId())) {
							session.setAttribute("utilisateur", joueurCredite);
						}

						if (ucf.getErreurs().isEmpty()) {
							Logger.log(logDao, "%u a " + retour.get("action") + " %j de " + retour.get("fonds") + 
									" euros.", Constants.LOG_INFO, Constants.LOG_FINANCE, utilisateur, 
									(Utilisateur)retour.get("joueur"));
						}

						req.setAttribute("titrePage", "creditPlayer");
						UtilisateurRecuperationForm urf = new UtilisateurRecuperationForm(utilisateurDao, grilleDao, 
								retardDao, req);
						urf.recupererRang(Constants.L_UTILISATEUR_ROLE_MEMBRE);
						cible = Constants.URN_ADMIN_CREDITER;
						break;
					}
					case Constants.URL_ADMIN_MODIFIER_PRIVILEGES: {
						String idJoueur = (String) session.getAttribute("idJoueur");
						Utilisateur joueur = utilisateurDao.trouverParId(Long.valueOf(idJoueur));
						PrivilegesModificationForm pmf = new PrivilegesModificationForm(joueur.getPrivilege(), 
								privilegeDao, req);
						pmf.valider();
						Map<String, String> erreurs = pmf.getErreurs();
						
						if (erreurs.size() == 0) {
							pmf.maj();
							privilegeDao.clearCache();
							utilisateurDao.clearCache();
						}
						
						resp.sendRedirect(req.getServletContext().getContextPath() + 
								Constants.URL_ADMIN_DETAILS_UTILISATEURS);
						return;
					}
					default: cible = Constants.URN_ADMIN_ACCUEIL;
				}
			}
		}
		req.getServletContext().getRequestDispatcher(cible).forward(req, resp);
	}
}
