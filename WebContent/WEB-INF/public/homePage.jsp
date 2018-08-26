<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Accueil</title>
  <c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/calendrier.css.jsp" />
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/commun/header.jsp" />
	
		<p>
		<c:choose>
			<c:when test="${not loggedIn}">
				Bienvenue
			</c:when>
			<c:otherwise>
				Bonjour ${utilisateur.prenom}, bienvenue
			</c:otherwise>
		</c:choose>
		sur le suivi de gains du cluster Quadient</p>
		
		<c:choose>
			<c:when test="${not loggedIn}">
			<h4>Accédez à votre espace membre en vous connectant ci-dessous :</h4>
			<form method="post" action="<c:url value="/connexion" />">
				<c:import url="/WEB-INF/forms/loginForm.jsp" />
			</form>
			</c:when>
			<c:otherwise>
				<c:import url="/WEB-INF/commun/afficherSemaineCourante.jsp" />
			</c:otherwise>
		</c:choose>
	</div>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>