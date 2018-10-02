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
</head>
<body bgcolor="white">
	<c:import url="/WEB-INF/commun/header.jsp" />

	<c:choose>
		<c:when test="${not empty joueur}">
		<h3>Administration - créditer ${joueur.u.prenom} ${joueur.u.nom}</h3>
		</c:when>
		<c:otherwise>
		<h3>Administration - créditer un joueur</h3>
		</c:otherwise>
	</c:choose>
	<form action="<c:url value="/admin/crediterJoueur" />" method="post">
		<c:if test="${empty joueur}">
		<p>
			<label for="input-joueur">Sélectionnez le joueur à créditer :</label>
			<select class="" name="joueur" id="input-joueur">
			<c:forEach items="${joueurs}" var="joueur">
				<option value="${joueur.u.id}">${joueur.u.prenom} ${joueur.u.nom}</option>
			</c:forEach>
			</select>
		</p>
		</c:if>
		<p>
			<label for="input-fonds">Créditer de : </label>
			<input type="number" lang="en" lang="fr" step="0.01" name="fonds" id="input-fonds" /> &euro;
		</p>
		<input type="submit" value="Créditer" />
	</form>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>