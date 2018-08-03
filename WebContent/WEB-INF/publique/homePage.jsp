<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Accueil</title>
</head>
<body bgcolor="white">
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
	
	<c:if test="${not loggedIn}">
		<h4>Accédez à votre espace membre en vous connectant ci-dessous :</h4>
		<form method="post" action="<c:url value="/login" />">
			<c:import url="/WEB-INF/forms/loginForm.jsp" />
		</form>
	</c:if>
</body>
</html>