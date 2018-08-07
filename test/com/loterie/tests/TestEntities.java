package com.loterie.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.loterie.entities.Grille;
import com.loterie.entities.LienGrilleUtilisateur;
import com.loterie.entities.Utilisateur;

class TestEntities {	
	private Grille grille = null;
	private long grilleId = 12345L; 
	private String[] grilleNumeros = {"1", "2", "3", "4", "5"};
	private String[] grilleEtoiles = {"1", "2"};
	private String grilleMyMillion = "AB12345678";
	private boolean grilleEtoilePlus = false;
	private boolean grilleRejoindre = false;

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

	@BeforeEach
	void setUp() throws Exception {		
		utilisateur = new Utilisateur();
		utilisateur.setId(utilisateurId);
		utilisateur.setPseudo(utilisateurPseudo);
		utilisateur.setNom(utilisateurNom);
		utilisateur.setPrenom(utilisateurPrenom);
		utilisateur.setEmail(utilisateurEmail);
		utilisateur.setGrainDeSel(utilisateurGrainDeSel);
		utilisateur.setMotDePasse(utilisateurMotDePasse);
		utilisateur.setNiveau(utilisateurNiveau);
		
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
		grille.setNumeros(Arrays.asList(grilleNumeros));
		grille.setEtoiles(Arrays.asList(grilleEtoiles));
		grille.setMyMillion(grilleMyMillion);
		grille.setEtoilePlus(grilleEtoilePlus);
		grille.setUtilisateur(utilisateur);
		grille.setRejoindre(grilleRejoindre);
		
		lgu = new LienGrilleUtilisateur();
		lgu.setId(lguId);
		lgu.setGrille(grille);
		lgu.setUtilisateur(utilisateur);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void test() {
		testUtilisateur();
		testAdministrateur();
		testGrille(grilleNumeros, grilleEtoiles);
		grille.setNumeros(null);
		grille.setEtoiles(null);
		testGrille(null, null);
		testLGU();
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
				false
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
				utilisateur.estAdministrateur()
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
				numeros,
				etoiles,
				grilleMyMillion,
				grilleEtoilePlus,
				utilisateur,
				grilleRejoindre
		};
		Object[] actual = {
				grille.getId(),
				grille.getNumeros(),
				grille.getEtoiles(),
				grille.getMyMillion(),
				grille.getEtoilePlus(),
				grille.getUtilisateur(),
				grille.getRejoindre()
		};
		assertArrayEquals(expected, actual);
	}
	
	public TestEntities() throws Exception {
		setUp();
	}
}
