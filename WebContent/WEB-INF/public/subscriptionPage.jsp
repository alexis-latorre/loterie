<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Loterie | Inscription</title>
  	<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  	<c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body class="container">
	<c:import url="/WEB-INF/commun/header.jsp"></c:import>
	<h4 style="text-align: center; margin: 40px;">Créez votre compte en remplissant le formulaire ci-dessous :</h4>
	<div class="well" style="margin: auto; max-width: 500px;">
		<form action="<c:url value="/inscription" />" method="post" accept-charset="ISO-8859-1">
			<c:import url="/WEB-INF/forms/utilisateur/accountForm.jsp"></c:import>
			<br />
			<c:import url="/WEB-INF/forms/utilisateur/passwordForm.jsp"></c:import>
			<p id="erreursMDP"></p>
			<p id="erreursConfirmationMDP"></p>
			<br />
			<br />
			<input class="btn btn-blue" type="submit" value="Valider mes informations" />
		</form>
	</div>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
	<c:import url="/WEB-INF/js/form.js.jsp" />
</body>
</html>