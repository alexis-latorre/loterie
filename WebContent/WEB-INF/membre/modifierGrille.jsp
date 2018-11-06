<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loterie | �diter une grille</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body class="container">
	<c:import url="/WEB-INF/commun/header.jsp" />
	<h4>�diter la grille</h4>
	<div class="well">
		<form action="<c:url value="/membre/editerGrille"><c:param name="id" value="${grille.id}"></c:param></c:url>" method="post">
			<c:import url="/WEB-INF/forms/grilleForm.jsp" />
			<input class="btn btn-blue" type="submit" value="Modifier la grille" />
		</form>
	</div>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>