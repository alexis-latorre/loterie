<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loterie | Modifier une grille</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body>
	<div class="container-fluid">
		<c:import url="/WEB-INF/commun/header.jsp" />
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					Modifier la grille
				</h3>
			</div>
			<div class="panel-body">
				<form role="form" data-toggle="validator" id="form-grille" action="<c:url value="/membre/editerGrille"><c:param name="id" value="${grille.id}"></c:param></c:url>" method="post">
					<c:import url="/WEB-INF/forms/grilleForm.jsp" />
					<c:choose>
					<c:when test='${utilisateur.checkPrivilege("lien_grille_utilisateur-modifier-fk_utilisateur_id")}'>
					<c:import url="/WEB-INF/forms/choisirParmiMembresForm.jsp" />
					<input class="btn btn-primary" type="button" onclick="validerJoueurs()" value="Modifier la grille" />
					</c:when>
					<c:otherwise>
					<input class="btn btn-primary" type="submit" value="Modifier la grille" />
					</c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/commun/footer.jsp" />
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>