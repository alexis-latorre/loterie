package com.loterie.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.loterie.entities.Banque;
import com.loterie.entities.Grille;
import com.loterie.entities.Jeu;
import com.loterie.entities.Jour;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Portefeuille;
import com.loterie.entities.Utilisateur;

class TestEntities {
	private Banque banque = null;
	private long banqueId = 12345L;
	private String banqueDateCreation = "2018-09-17";
	private DateTime banqueDateCreationDT = new DateTime().withYear(2018).withMonthOfYear(9).withDayOfMonth(17);
	private Double banqueFonds = 123D;
	private Double banqueMises = 50D;
	private Double banqueGains = 4D;
	
	private Jeu jeu = null;
	private long jeuId = 12345L;
	private String jeuNom = "Euromillions";
	private long jeuTirageParSemaine = 2L;
	private String[] jeuJourDeTirage = {"2", "5"};
	private Double jeuPrixTirage = 2.5;
	private String jeuHeureValidation = "20:00:00";
	
	private Portefeuille portefeuille = null;
	private long portefeuilleId = 12345L;
	private String portefeuilleDateCreation = "2018-09-17";
	private DateTime portefeuilleDateCreationDT = new DateTime().withYear(2018).withMonthOfYear(9).withDayOfMonth(17);
	private Double portefeuilleFonds = 0.5D;
	
	private Grille grille = null;
	private long grilleId = 12345L;
	private String grilleNom = "Grille sans nom";
	private String[] grilleNumeros = {"1", "2", "3", "4", "5"};
	private String[] grilleEtoiles = {"1", "2"};
	private String grilleMyMillion = "AB12345678";
	private boolean grilleEtoilePlus = false;
	private boolean grillePublique = false;
	private boolean grilleRejoindre = false;
	private boolean grillePaye = false;

	private Utilisateur utilisateur = null;
	private Utilisateur administrateur = null;
	private long utilisateurId = 12345L;
	private String utilisateurPseudo = "john_doe";	
	private String utilisateurNom = "Doe";	
	private String utilisateurPrenom = "John";	
	private String utilisateurEmail = "john.doe@example.com";
	private String utilisateurGrainDeSel = "12345";
	private String utilisateurMotDePasse = "ce n'est pas un mot de passe sécurisé";
	private long utilisateurNiveau = 6L;
	private long administrateurNiveau = 0L;
	
	private LienGrilleUtilisateur lgu = null;
	private long lguId = 12345L;
	
	private Jour jour = null;
	private long jourId = 12345L;
	private String jourDateJour = "2018-09-17";
	private DateTime jourDateJourDT = new DateTime().withYear(2018).withMonthOfYear(9).withDayOfMonth(17);
	private boolean jourPaye = true;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Date jourDate = sdf.parse("2018-09-17");

	@BeforeEach
	void setUp() throws Exception {		
		banque = new Banque();
		banque.setId(banqueId);
		banque.setDateCreation(banqueDateCreation);
		banque.setFonds(banqueFonds);
		banque.setMises(banqueMises);
		banque.setGains(banqueGains);
		
		jeu = new Jeu();
		jeu.setId(jeuId);
		jeu.setNom(jeuNom);
		jeu.setTirageParSemaine(jeuTirageParSemaine);
		jeu.setJourDeTirage(jeuJourDeTirage);
		jeu.setPrixTirage(jeuPrixTirage);
		jeu.setHeureValidation(jeuHeureValidation);
		
		portefeuille = new Portefeuille();
		portefeuille.setId(portefeuilleId);
		portefeuille.setDateCreation(portefeuilleDateCreation);
		portefeuille.setFonds(portefeuilleFonds);
		
		utilisateur = new Utilisateur();
		utilisateur.setId(utilisateurId);
		utilisateur.setPseudo(utilisateurPseudo);
		utilisateur.setNom(utilisateurNom);
		utilisateur.setPrenom(utilisateurPrenom);
		utilisateur.setEmail(utilisateurEmail);
		utilisateur.setGrainDeSel(utilisateurGrainDeSel);
		utilisateur.setMotDePasse(utilisateurMotDePasse);
		utilisateur.setNiveau(utilisateurNiveau);
		utilisateur.setPortefeuille(portefeuille);
		
		administrateur = new Utilisateur();
		administrateur.setId(utilisateurId);
		administrateur.setPseudo(utilisateurPseudo);
		administrateur.setNom(utilisateurNom);
		administrateur.setPrenom(utilisateurPrenom);
		administrateur.setEmail(utilisateurEmail);
		administrateur.setGrainDeSel(utilisateurGrainDeSel);
		administrateur.setMotDePasse(utilisateurMotDePasse);
		administrateur.setNiveau(administrateurNiveau);
		
		grille = new Grille();
		grille.setId(grilleId);
		grille.setNom(grilleNom);
		grille.setNumeros(Arrays.asList(grilleNumeros));
		grille.setEtoiles(Arrays.asList(grilleEtoiles));
		grille.setMyMillion(grilleMyMillion);
		grille.setEtoilePlus(grilleEtoilePlus);
		grille.setUtilisateur(utilisateur);
		grille.setJeu(jeu);
		grille.setBanque(banque);
		grille.setPublique(grillePublique);
		grille.setRejoindre(grilleRejoindre);
		grille.setPaye(grillePaye);
		
		lgu = new LienGrilleUtilisateur();
		lgu.setId(lguId);
		lgu.setGrille(grille);
		lgu.setUtilisateur(utilisateur);
		
		jour = new Jour();
		jour.setId(jourId);
		jour.setDateJour(jourDateJour);
		jour.setLgu(lgu);
		jour.setPaye(jourPaye);
	}

	@Test
	public void test() {
		banque.ajouterFonds(5D);
		banque.ajouterMises(1D);
		banque.ajouterGains(6D);
		testBanque(banqueFonds + 5D, banqueMises + 1D, banqueGains + 6D);
		banque.retirerFonds(10D);
		banque.setDateCreation(banqueDateCreationDT);
		testBanque(banqueFonds - 5D, banqueMises + 1D, banqueGains + 6D);
		
		testJeu(jeuJourDeTirage);
		jeu.setJourDeTirage(null);
		testJeu(null);
		
		portefeuille.ajouterFonds(5D);
		testPortefeuille(portefeuilleFonds + 5D);
		portefeuille.retirerFonds(10D);
		portefeuille.setDateCreation(portefeuilleDateCreationDT);
		testPortefeuille(portefeuilleFonds - 5D);
		
		testUtilisateur();
		
		testAdministrateur();
		
		testGrille(grilleNumeros, grilleEtoiles);
		grille.setNumeros(null);
		grille.setEtoiles(null);
		testGrille(null, null);

		testLGU();
		
		testJour(jourPaye);
		jour.setPaye(false);
		jour.setDateJour(jourDateJourDT);
		testJour(false);
	}

	private void testJour(boolean paye) {
		Object[] expected = {
				jourId,
				jourDateJour,
				lgu,
				paye,
				jourDate
		};
		Object[] actual = {
				jour.getId(),
				jour.getDateJour(),
				jour.getLgu(),
				jour.getPaye(),
				jour.getDate()
		};
		assertArrayEquals(expected, actual);
	}

	private void testLGU() {
		Object[] expected = {
				lguId,
				grille,
				utilisateur
		};
		Object[] actual = {
				lgu.getId(),
				lgu.getGrille(),
				lgu.getUtilisateur()
		};
		assertArrayEquals(expected, actual);
	}

	private void testUtilisateur() {		
		Object[] expected = {
				utilisateurId,
				utilisateurPseudo,
				utilisateurNom,
				utilisateurPrenom,
				utilisateurEmail,
				utilisateurGrainDeSel,
				utilisateurMotDePasse,
				utilisateurNiveau,
				false,
				false,
				false,
				false,
				portefeuille
		};
		Object[] actual = {
				utilisateur.getId(),
				utilisateur.getPseudo(),
				utilisateur.getNom(),
				utilisateur.getPrenom(),
				utilisateur.getEmail(),
				utilisateur.getGrainDeSel(),
				utilisateur.getMotDePasse(),
				utilisateur.getNiveau(),
				utilisateur.estBasique(),
				utilisateur.estMembre(),
				utilisateur.estModerateur(),
				utilisateur.estAdministrateur(),
				utilisateur.getPortefeuille()
		};
		assertArrayEquals(expected, actual);
	}

	private void testAdministrateur() {		
		Object[] expected = {
				utilisateurId,
				utilisateurPseudo,
				utilisateurNom,
				utilisateurPrenom,
				utilisateurEmail,
				utilisateurGrainDeSel,
				utilisateurMotDePasse,
				administrateurNiveau,
				true,
				true,
				true,
				true
		};
		Object[] actual = {
				administrateur.getId(),
				administrateur.getPseudo(),
				administrateur.getNom(),
				administrateur.getPrenom(),
				administrateur.getEmail(),
				administrateur.getGrainDeSel(),
				administrateur.getMotDePasse(),
				administrateur.getNiveau(),
				administrateur.estBasique(),
				administrateur.estMembre(),
				administrateur.estModerateur(),
				administrateur.estAdministrateur()
		};
		assertArrayEquals(expected, actual);
	}

	private void testGrille(Object numeros, Object etoiles) {
		Object[] expected = {
				grilleId,
				grilleNom,
				numeros,
				etoiles,
				grilleMyMillion,
				grilleEtoilePlus,
				utilisateur,
				jeu,
				banque,
				grillePublique,
				grilleRejoindre,
				grillePaye
		};
		Object[] actual = {
				grille.getId(),
				grille.getNom(),
				grille.getNumeros(),
				grille.getEtoiles(),
				grille.getMyMillion(),
				grille.getEtoilePlus(),
				grille.getUtilisateur(),
				grille.getJeu(),
				grille.getBanque(),
				grille.getPublique(),
				grille.getRejoindre(),
				grille.getPaye()
		};
		assertArrayEquals(expected, actual);
	}

	private void testPortefeuille(Double fonds) {		
		Object[] expected = {
				portefeuilleId,
				portefeuilleDateCreation,
				fonds
		};
		Object[] actual = {
				portefeuille.getId(),
				portefeuille.getDateCreation(),
				portefeuille.getFonds()
		};
		assertArrayEquals(expected, actual);
	}

	private void testJeu(String[] joursTirage) {		
		Object[] expected = {
				jeuId,
				jeuNom,
				jeuTirageParSemaine,
				joursTirage,
				jeuPrixTirage,
				jeuHeureValidation
		};
		Object[] actual = {
				jeu.getId(),
				jeu.getNom(),
				jeu.getTirageParSemaine(),
				jeu.getJourDeTirage(),
				jeu.getPrixTirage(),
				jeu.getHeureValidation()
		};
		assertArrayEquals(expected, actual);
	}

	private void testBanque(Double fonds, Double mises, Double gains) {		
		Object[] expected = {
				banqueId,
				banqueDateCreation,
				fonds,
				mises,
				gains
		};
		Object[] actual = {
				banque.getId(),
				banque.getDateCreation(),
				banque.getFonds(),
				banque.getMises(),
				banque.getGains()
		};
		assertArrayEquals(expected, actual);
	}
	
	public TestEntities() throws Exception {
		setUp();
	}
}
