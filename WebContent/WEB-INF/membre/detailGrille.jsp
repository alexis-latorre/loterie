<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Loterie | Grille N° <c:out value="${grille.id}"></c:out></title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
</head>
<body>
	<c:import url="/WEB-INF/commun/header.jsp" />
	<c:choose>
	<c:when test="${empty erreurs}">
	<h3>Grille N° <c:out value="${grille.id}"></c:out>
	<c:if test="${not empty jour.date}"> (jouée jusqu'au <fmt:formatDate pattern="dd/MM/yyyy" value="${jour.date}" />)</c:if>
	</h3>
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
		<br />
		<form action='<c:url value="/membre/jouerGrille" />' method="post">
			Jouer la grille pour 
			<label><input type="radio" name="periode" id="input-periode" value="1j" /> 1 jour</label>
			<label><input type="radio" name="periode" id="input-periode" value="1s" /> 1 semaine</label>
			<label><input type="radio" name="periode" id="input-periode" value="2s" /> 2 semaines</label>
			<label><input type="radio" name="periode" id="input-periode" value="3s" /> 3 semaines</label>
			<label><input type="radio" name="periode" id="input-periode" value="4s" /> 4 semaines</label>
			<label><input type="radio" name="periode" id="input-periode" value="5s" /> 5 semaines</label>
			<br />
			<c:if test="${not empty erreurs}">
				<c:if test="${not empty erreurs.format}">
					<span class="text-danger">${erreurs.format}</span><br />
				</c:if>
				<c:if test="${not empty erreurs.grille}">
					<span class="text-danger">${erreurs.grille}</span><br />
				</c:if>
			</c:if>
			<input type="submit" value="Jouer" />
		</form>
	</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="${not empty erreurs.grille}">
		<h3>Grille introuvable</h3>
		<span class="danger text-danger"><c:out value="${erreurs.grille}"></c:out></span>
		</c:if>
	</c:otherwise>
	</c:choose>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>