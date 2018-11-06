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
<body class="container">
	<c:import url="/WEB-INF/commun/header.jsp" />

	<c:choose>
		<c:when test="${not empty joueur}">
		<h4>Administration - créditer ${joueur.u.prenom} ${joueur.u.nom}</h4>
		</c:when>
		<c:otherwise>
		<h4>Administration - créditer un joueur</h4>
		</c:otherwise>
	</c:choose>
	<div class="well">
		<form action="<c:url value="/admin/crediterJoueur" />" method="post">
			<c:if test="${empty joueur}">
			<p>
				<label for="input-joueur">Sélectionnez le joueur à créditer</label><br />
				<select class="" name="joueur" id="input-joueur">
				<c:forEach items="${joueurs}" var="joueur">
					<option value="${joueur.u.id}">${joueur.u.prenom} ${joueur.u.nom}</option>
				</c:forEach>
				</select>
			</p>
			</c:if>
			<p>
				<label for="input-fonds">Créditer de</label><br />
				<input type="number" lang="en" lang="fr" step="0.01" name="fonds" id="input-fonds" /> &euro;
			</p>
			<input class="btn-blue" type="submit" value="Créditer" />
		</form>
	</div>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>