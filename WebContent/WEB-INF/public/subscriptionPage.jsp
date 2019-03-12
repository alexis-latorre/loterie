<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Loterie | Inscription</title>
  	<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  	<c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body class="container-fluid">
	<c:import url="/WEB-INF/commun/header.jsp"></c:import>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				Formulaire d'inscription
			</h3>
		</div>
		<div class="panel-body">
			<form action="<c:url value="/inscription" />" method="post" accept-charset="ISO-8859-1">
				<c:import url="/WEB-INF/forms/utilisateur/accountForm.jsp"></c:import>
				<c:import url="/WEB-INF/forms/utilisateur/passwordForm.jsp"></c:import>
				<p id="erreursMDP"></p>
				<p id="erreursConfirmationMDP"></p>
				<br />
				<input class="btn btn-primary" type="submit" value="Valider" />
			</form>
		</div>
	</div>
	<c:import url="/WEB-INF/commun/footer.jsp" />
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
	<c:import url="/WEB-INF/js/form.js.jsp" />
</body>
</html>