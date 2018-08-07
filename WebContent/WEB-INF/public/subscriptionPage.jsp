<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loterie | Inscription</title>
</head>
<body>
	<form action="<c:url value="/inscription" />" method="post">
		<c:import url="/WEB-INF/commun/header.jsp"></c:import>
		<h3>Inscription</h3>
		<c:import url="/WEB-INF/forms/accountForm.jsp"></c:import>
		<br />
		<c:import url="/WEB-INF/forms/passwordForm.jsp"></c:import>
		<br />
		<input type="submit" value="Valider mes informations" />
	</form>
</body>
</html>