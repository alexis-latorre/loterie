<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Loterie | Grille N° <c:out value="${grille.id}"></c:out></title>
</head>
<body>
	<c:import url="/WEB-INF/commun/header.jsp" />
	<h3>Grille N° <c:out value="${grille.id}"></c:out></h3>
	<div>Numéros : <c:out value="${fn:join(grille.numeros, ', ')}"></c:out><br />
	Étoiles : <c:out value="${fn:join(grille.etoiles, ', ')}"></c:out><br />
	<c:if test="${grille.banque != null}">
	Cette grille est créditée de <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${grille.banque.fonds}" type="currency" />
	</c:if> 
	</div>
	<c:if test="${utilisateur != null and utilisateur.estModerateur()}">
		<form action='<c:url value="/membre/crediterGrille" />' method="post">
		Créditer la grille de <input type="number" lang="en" lang="fr" step="0.5" name="fonds" id="input-fonds" /> &euro; (vous avez <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${utilisateur.portefeuille.fonds}" type="currency" />)<br />
		<c:if test="${not empty erreurs}">
			<c:out value="${erreurs.fonds}"></c:out><br />
		</c:if>
		<input type="submit" value="Confirmer" />	
		</form> 
	</c:if>
</body>
</html>