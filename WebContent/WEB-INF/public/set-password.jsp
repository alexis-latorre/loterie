<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
  	<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  	<c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body class="container">
	<form method="post" action="<c:url value="/updatePassword" />">
		<c:import url="/WEB-INF/forms/utilisateur/passwordForm.jsp"></c:import>
		<br />
		<input type="submit" value="Mettre � jour" />
	</form>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>