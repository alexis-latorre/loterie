<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loterie | Créer une grille</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body class="container-fluid">
	<c:import url="/WEB-INF/commun/header.jsp" />
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				Créer une nouvelle grille
			</h3>
		</div>
		<div class="panel-body">
			<form role="form" data-toggle="validator" id="form-grille" action="<c:url value="/membre/creerGrille" />" method="post">
				<c:import url="/WEB-INF/forms/grilleForm.jsp" />
				<c:import url="/WEB-INF/forms/choisirParmiMembresForm.jsp" />
				<input class="btn btn-primary" type="button" onclick="validerJoueurs()" value="Créer la grille" />
				<!--input type="submit" value="Créer la grille" /-->
			</form>
		</div>
	</div>
	<c:import url="/WEB-INF/commun/footer.jsp" />
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>