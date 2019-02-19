<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Accueil</title>
  <c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/calendrier.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body>
	<c:import url="/WEB-INF/commun/header.jsp" />		
	<c:choose>
		<c:when test="${not loggedIn}">
		<div class="container-fluid">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Formulaire de connexion
					</h3>
				</div>
				<div class="panel-body">
					<form role="form" data-toggle="validator" accept-charset="ISO-8859-1" method="post" action="<c:url value="/connexion" />">
						<c:import url="/WEB-INF/forms/loginForm.jsp" />
					</form>
				</div>
			</div>
		</div>
		</c:when>
		<c:otherwise>
			<div class="container-fluid">
				<div class="col-md-7">
					<c:import url="/WEB-INF/membre/resumePortefeuille.jsp" />
				</div>
				<div class="col-md-5">
					<c:import url="/WEB-INF/commun/afficherMois.jsp" />
				</div>
				<div id="detailsJour" class="well col-md-7">
					<c:import url="/WEB-INF/jour/detailsJour.jsp" />
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<c:import url="/WEB-INF/commun/footer.jsp" />
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>