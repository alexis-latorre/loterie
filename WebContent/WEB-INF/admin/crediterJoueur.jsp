<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
	<c:choose>
		<c:when test="${not empty joueur}">
		<title>Loterie | Créditer ${joueur.u.prenom} ${joueur.u.nom}</title>
		</c:when>
		<c:otherwise>
		<title>Loterie | Créditer un joueur</title>
		</c:otherwise>
	</c:choose>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body>
	<div class="container-fluid">
		<c:import url="/WEB-INF/commun/header.jsp" />
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
				<c:choose>
					<c:when test="${not empty joueur}">
					Administration - créditer ${joueur.u.prenom} ${joueur.u.nom}
					</c:when>
					<c:otherwise>
					Administration - créditer un joueur
					</c:otherwise>
				</c:choose>
				</h3>
			</div>
			<div class="panel-body">
				<form action="<c:url value="/admin/crediterJoueur" />" method="post">
					<c:if test="${empty joueur}">
					<div class="form-group">
						<label for="input-joueur">Sélectionnez le joueur à créditer</label>
						<select class="form-control" name="joueur" id="input-joueur">
						<c:forEach items="${joueurs}" var="joueur">
							<option value="${joueur.u.id}">${joueur.u.prenom} ${joueur.u.nom}</option>
						</c:forEach>
						</select>
					</div>
					</c:if>
					<div class="form-group">
						<label for="input-fonds">Créditer de</label>
	    				<div class="input-group">
							<input  type="number" lang="en" lang="fr" step="0.01" name="fonds" class="form-control" id="input-fonds" />
	      					<div class="input-group-addon">&euro;</div>
      					</div>
					</div>
					<input class="btn btn-primary" type="submit" value="Créditer" />
				</form>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/commun/footer.jsp" />
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>