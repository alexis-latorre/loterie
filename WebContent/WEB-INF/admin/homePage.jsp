<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Administration</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
</head>
<body bgcolor="white">
	<c:import url="/WEB-INF/commun/header.jsp" />

	<h3>Administration</h3>
	<h4>Fonctions membre</h4>
	<ul>
		<li><a href="<c:url value="/membre/afficherGrilles" />">Afficher mes grilles</a>
		<li><a href="<c:url value="/membre/creerGrille" />">Créer une grille</a>
	</ul>
	<h4>Fonctions administrateur</h4>
	<ul>
		<li><a href="<c:url value="/admin/crediterJoueur" />">Créditer un joueur</a></li>
		<li><a href="<c:url value="/admin/detailsUtilisateurs" />">Afficher les utilisateurs</a></li>
		<li><a href="<c:url value="/admin/logs" />">Consulter les logs</a></li>
	</ul>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>