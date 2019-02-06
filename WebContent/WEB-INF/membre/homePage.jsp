<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Profil</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body class="container">
	<c:import url="/WEB-INF/commun/header.jsp" />

	<h3>Profil</h3>
	
	<ul>
		<li><a href="<c:url value="/membre/afficherGrilles" />">Afficher mes grilles</a>
		<li><a href="<c:url value="/membre/creerGrille" />">Créer une grille</a>
		<!--li><a href="<c:url value="/membre/monPortefeuille" />">Accéder à mon portefeuille</a-->
	</ul>
	<c:import url="/WEB-INF/commun/footer.jsp" />
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>