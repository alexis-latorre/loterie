<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Accueil</title>
  <c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/calendrier.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/commun/header.jsp" />
		
		<c:choose>
			<c:when test="${not loggedIn}">
			<h4>Accédez à votre espace membre en vous connectant ci-dessous :</h4>
			<div class="well" style="margin: auto; max-width: 500px;">
				<form method="post" action="<c:url value="/connexion" />">
					<c:import url="/WEB-INF/forms/loginForm.jsp" />
				</form>
			</div>
			</c:when>
			<c:otherwise>
				<h4 style="text-align: center;">Résumé de mon compte</h4>
				<c:import url="/WEB-INF/membre/resumePortefeuille.jsp" />
				<c:import url="/WEB-INF/commun/afficherMois.jsp" />
			</c:otherwise>
		</c:choose>
	</div>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>