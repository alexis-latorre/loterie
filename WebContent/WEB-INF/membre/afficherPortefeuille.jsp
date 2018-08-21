<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Loterie | Mon portefeuille</title>
</head>
<body>
	<c:import url="/WEB-INF/commun/header.jsp" />
	<h3>Mon portefeuille</h3>
	<c:choose>
	<c:when test="${utilisateur.portefeuille != null}">
		<c:set var="solde" value="${utilisateur.portefeuille.fonds}" />
	</c:when>
	<c:otherwise>
		<c:set var="solde" value="0" />
	</c:otherwise>
	</c:choose>
	<h4>Solde : <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${solde}" type="currency"></fmt:formatNumber></h4>
	<c:if test="${utilisateur.estModerateur()}">
	<form action="<c:url value="/membre/ajouterFonds" />" method="post">
		<label>Ajouter des fonds : <input type="number" lang="en" lang="fr" step="0.5" name="fonds" id="input-fonds" /> &euro;</label><br />
		<c:if test="${not empty erreurs}">
			<c:out value="${erreurs.fonds}"></c:out><br />
		</c:if>
		<input type="submit" value="Confirmer" />		
	</form>
	</c:if>
</body>
</html>