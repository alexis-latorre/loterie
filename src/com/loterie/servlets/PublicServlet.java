package com.loterie.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.loterie.business.ResultatHTML;
import com.loterie.config.Constants;
import com.loterie.dao.GainDao;
import com.loterie.dao.JourDao;
import com.loterie.dao.LienGUDao;
import com.loterie.dao.ResultatDao;
import com.loterie.entities.Gain;
import com.loterie.entities.Resultat;
import com.loterie.entities.Utilisateur;
import com.loterie.forms.ResultatRecuperationForm;

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
	@EJB
	private GainDao gainDao;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("titrePage", "results");
		HttpSession session = req.getSession();
		Utilisateur utilisateur = null;

		if (null != session && null != session.getAttribute("loggedIn") && (boolean) session.getAttribute("loggedIn")) {
			utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		}
		
		ResultatRecuperationForm rrf = new ResultatRecuperationForm(resultatDao, jourDao, lienGuDao, utilisateur);
		
		ResultatHTML r = new ResultatHTML();
		r.setDernier(rrf.dernierResultat());
			
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

		try {
			if (null != session && null != session.getAttribute("loggedIn") && (boolean) session.getAttribute("loggedIn") && null != utilisateur) {
				int from = (idPage - 1) * Constants.NB_HISTO_PAR_PAGE;
				int to = idPage * Constants.NB_HISTO_PAR_PAGE;
				String d1 = historique.get(to - 1).getDate();
				String d2 = historique.get(from).getDate();
				String dateD = d1.substring(6, 10) + "-" + d1.substring(3, 5) + "-" + d1.substring(0, 2) + " 12:00:00";
				String dateF = d2.substring(6, 10) + "-" + d2.substring(3, 5) + "-" + d2.substring(0, 2) + " 12:00:00";
				List<Gain> gains = gainDao.trouverParPeriodeEtUtilisateur(dateD, dateF, utilisateur.getId());
				
				for (int i = from; i < to; i++) {
					Resultat res = historique.get(i);
					String d = res.getDate();
					String date = d.substring(6, 10) + "-" + d.substring(3, 5) + "-" + d.substring(0, 2) + " 12:00:00";
					Double totalGains = 0D;

					if (null != gains) {						
						for (Gain gain : gains) {
							if (gain.getDateJour().substring(0, 10).equals(date.substring(0, 10))) {
								totalGains += gain.getGains();
							}
						}
					}
					res.setGains(totalGains);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		r.setNbPages(nbPages);
		r.setIdPage(idPage);
		req.setAttribute("r", r);
		req.getServletContext().getRequestDispatcher(Constants.URN_RESULTATS).forward(req, resp);
	}	
}