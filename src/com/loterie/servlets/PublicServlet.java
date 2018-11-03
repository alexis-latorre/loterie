package com.loterie.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.loterie.config.Constants;
import com.loterie.dao.ResultatDao;
import com.loterie.entities.Resultat;

@WebServlet(urlPatterns = {
		Constants.URL_PUBLIC_RESULTATS
		})
public class PublicServlet extends HttpServlet {
	private static final long serialVersionUID = 8L;
	@EJB
	private ResultatDao resultatDao;
	
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
		ResultatStruct r = new ResultatStruct(); 
		r.setDernier(resultatDao.trouverDernier());
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