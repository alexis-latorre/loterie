<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loterie | Erreur 403</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body class="container">
	<h1>INTERDIT !</h1>
	<div class="well">
		<p>Vous essayez d'acc�der � une page qui n�cessite d'�tre connect�.</p>
		<a href="<c:url value="/accueil" />">Retourner � l'accueil</a>
	</div>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>