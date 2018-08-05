<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Mon profil</title>
</head>
<body bgcolor="white">
	<c:import url="/WEB-INF/commun/header.jsp" />

	<h3>Mon profil</h3>
	
	<ul>
		<li><a href="<c:url value="/membre/afficherGrilles" />">Afficher mes grilles</a>
		<li><a href="<c:url value="/membre/creerGrille" />">Créer une grille</a>
	</ul>
</body>
</html>