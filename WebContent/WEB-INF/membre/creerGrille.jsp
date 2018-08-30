<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loterie | Créer une grille</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
</head>
<body>
	<c:import url="/WEB-INF/commun/header.jsp" />
	<h3>Ajouter une nouvelle grille</h3>
	<form id="form-nouvelleGrille" action="<c:url value="/membre/creerGrille" />" method="post">
		<c:import url="/WEB-INF/forms/grilleForm.jsp" />
		<c:import url="/WEB-INF/forms/choisirParmiMembresForm.jsp" />
		<input type="button" onclick="validerJoueurs()" value="Créer la grille" />
		<!--input type="submit" value="Créer la grille" /-->
	</form>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
	<c:import url="/WEB-INF/js/calendrier.js.jsp" />
</body>
</html>