package com.loterie.servlets;

import java.io.IOException;

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

		public Resultat getDernier() {
			return dernier;
		}

		public void setDernier(Resultat dernier) {
			this.dernier = dernier;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ResultatStruct r = new ResultatStruct(); 
		r.setDernier(resultatDao.trouverDernier());
		req.setAttribute("r", r);
		req.getServletContext().getRequestDispatcher(Constants.URN_RESULTATS).forward(req, resp);
	}	
}