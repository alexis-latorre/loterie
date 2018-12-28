package com.loterie.servlets;

import java.io.IOException;
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
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.ResultatDao;
import com.loterie.entities.Grille;
import com.loterie.entities.Jour;
import com.loterie.entities.Resultat;
import com.loterie.entities.Utilisateur;

@WebServlet(urlPatterns = {
		Constants.URL_PUBLIC_RESULTATS
		})
public class PublicServlet extends HttpServlet {
	private static final long serialVersionUID = 8L;
	@EJB
	private ResultatDao resultatDao;
	@EJB
	private JourDao jourDao;
	@EJB
	private LienGUDao lienGuDao;
	
	public class ResultatStruct {
		private Resultat dernier;
		private List<Resultat> historique;
		private int nbPages;
		private int idPage;

		public Resultat getDernier() {
			return dernier;
		}

		public void setDernier(Resultat dernier) {
			this.dernier = dernier;
		}

		public List<Resultat> getHistorique() {
			return historique;
		}

		public void setHistorique(List<Resultat> historique) {
			this.historique = historique;
		}

		public int getNbPages() {
			return nbPages;
		}

		public void setNbPages(int nbPages) {
			this.nbPages = nbPages;
		}

		public int getIdPage() {
			return idPage;
		}

		public void setIdPage(int idPage) {
			this.idPage = idPage;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Resultat dernier = resultatDao.trouverDernier();
		
		HttpSession session = req.getSession();
		Utilisateur utilisateur = null;

		try {			
			if ((boolean) session.getAttribute("loggedIn")) {
				utilisateur = (Utilisateur) session.getAttribute("utilisateur");
				String dateJeu = dernier.getDate().substring(6, 10) + "-" + dernier.getDate().substring(3, 5) + "-" + dernier.getDate().substring(0, 2) + " 12:00:00";
				
				if (utilisateur != null) {
					for (Grille grille : utilisateur.getGrilles()) {
						Jour jour = jourDao.trouverParDateEtLGU(dateJeu, 
								lienGuDao.trouverParGrilleEtUtilisateur(grille, utilisateur));
						
						if (jour != null) {
							List<String> numeros = Arrays.asList(grille.getNumeros());
							List<String> etoiles = Arrays.asList(grille.getEtoiles());
							int nbNumeros = 0;
							int nbEtoiles = 0;
	
							if (numeros.contains(dernier.getB1())) {
								nbNumeros++;
							}
							if (numeros.contains(dernier.getB2())) {
								nbNumeros++;
							}
							if (numeros.contains(dernier.getB3())) {
								nbNumeros++;
							}
							if (numeros.contains(dernier.getB4())) {
								nbNumeros++;
							}
							if (numeros.contains(dernier.getB5())) {
								nbNumeros++;
							}
							if (etoiles.contains(dernier.getE1())) {
								nbEtoiles++;
							}
							if (etoiles.contains(dernier.getE2())) {
								nbEtoiles++;
							}
							
							String condGain = nbNumeros + ":" + nbEtoiles;
							
							if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG13)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang13()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG12)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang12()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG11)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang11()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG10)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang10()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG9)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang9()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG8)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang8()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG7)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang7()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG6)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang6()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG5)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang5()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG4)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang4()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG3)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang3()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG2)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang2()));
							} else if (condGain.equals(Constants.EUROMILLIONS_CONDITION_RANG1)) {
								grille.setGains(Double.parseDouble(dernier.getGainsRang1()));
							} else {
								grille.setGains(0);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ResultatStruct r = new ResultatStruct();
		r.setDernier(dernier);
		List<Resultat> historique = resultatDao.trouverHistorique();
		r.setHistorique(historique);
		int nbPages = (int) Math.floor(historique.size() / Constants.NB_HISTO_PAR_PAGE);
		int idPage = 1;
		String p = req.getParameter("p");
		
		try {
			idPage = Integer.parseInt(p);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (idPage > nbPages) {
			idPage = nbPages;
		} else if (idPage < 1) {
			idPage = 1;
		}
		r.setNbPages(nbPages);
		r.setIdPage(idPage);
		req.setAttribute("r", r);
		req.getServletContext().getRequestDispatcher(Constants.URN_RESULTATS).forward(req, resp);
	}	
}