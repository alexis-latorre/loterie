<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Loterie | Grille N° <c:out value="${grille.id}"></c:out></title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body class="container">
	<c:import url="/WEB-INF/commun/header.jsp" />
	<c:choose>
	<c:when test="${empty erreurs}">
	<h4>Grille N° <c:out value="${grille.id}"></c:out>
	<c:if test="${not empty jour.date}"> (jouée jusqu'au <fmt:formatDate pattern="dd/MM/yyyy" value="${jour.date}" />)</c:if>
	<c:if test="${utilisateur.estAdministrateur() or utilisateur.id == grille.utilisateur.id}"><sup><a class="main-text-gold" title="Editer cette grille" href="<c:url value="/membre/editerGrille"><c:param name="id" value="${grille.id}" /></c:url>">éditer</a></sup></c:if>
	</h4>
	<div class="well">
		<span class="titre-grille main-text-gold"><c:out value="${grille.nom}"></c:out></span>
		<h3 class="main-text-blue">Numéros</h3>
		<c:forEach items="${grille.numeros}" var="boule">
			<span class="boule"><c:out value="${boule}"></c:out></span>
		</c:forEach>
		<h3 class="main-text-blue">Étoiles</h3>
		<c:forEach items="${grille.etoiles}" var="etoile">
			<span class="etoile"><c:out value="${etoile}"></c:out></span>
		</c:forEach>
		<c:if test="${false and grille.banque != null}">
			<!-- TODO -->
		Cette grille est créditée de <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${grille.banque.fonds}" type="currency" />
		</c:if> 
		<c:if test="${utilisateur != null and utilisateur.estModerateur()}">
			<c:if test="${false}">
			<form action='<c:url value="/membre/crediterGrille" />' method="post">
			Créditer la grille de <input type="number" lang="en" lang="fr" step="0.5" name="fonds" id="input-fonds" /> &euro; (vous avez <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${utilisateur.portefeuille.fonds}" type="currency" />)<br />
			<c:if test="${not empty erreurs}">
				<c:out value="${erreurs.fonds}"></c:out><br />
			</c:if>
			<input type="submit" value="Confirmer" />	
			</form>
			<br />
			</c:if>
			<c:if test="${utilisateur != null and utilisateur.estModerateur()}">
			<form action='<c:url value="/membre/jouerGrille" />' method="post">
				<h3>Jouer la grille</h3> 
				<label class="radio premier"><input type="radio" name="periode" id="input-periode" value="1j" checked="checked" /> 1 jour</label>
				<label class="radio"><input type="radio" name="periode" id="input-periode" value="1s" /> 1 semaine</label>
				<label class="radio"><input type="radio" name="periode" id="input-periode" value="2s" /> 2 semaines</label>
				<label class="radio"><input type="radio" name="periode" id="input-periode" value="3s" /> 3 semaines</label>
				<label class="radio"><input type="radio" name="periode" id="input-periode" value="4s" /> 4 semaines</label>
				<label class="radio"><input type="radio" name="periode" id="input-periode" value="5s" /> 5 semaines</label>
				<br />
				<br />
				<c:if test="${not empty erreurs}">
					<c:if test="${not empty erreurs.format}">
						<span class="text-danger">${erreurs.format}</span><br />
					</c:if>
					<c:if test="${not empty erreurs.grille}">
						<span class="text-danger">${erreurs.grille}</span><br />
					</c:if>
				</c:if>
				<input class="btn-blue" type="submit" value="Jouer" />
			</form>
			</c:if>
		</c:if>
	</div>
	</c:when>
	<c:otherwise>
		<c:if test="${not empty erreurs.grille}">
		<h4>Grille introuvable</h4>
		<div class="well">
			<span class="danger text-danger"><c:out value="${erreurs.grille}"></c:out></span>
		</div>
		</c:if>
	</c:otherwise>
	</c:choose>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>