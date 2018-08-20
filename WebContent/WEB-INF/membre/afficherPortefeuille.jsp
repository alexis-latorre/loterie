<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loterie | Mon portefeuille</title>
</head>
<body>
	<c:import url="/WEB-INF/commun/header.jsp" />
	<h3>Mon portefeuille</h3>
	<c:choose>
	<c:when test="${utilisateur.portefeuille != null}">
	<h4><c:out value="Solde : ${utilisateur.portefeuille.fonds}"></c:out> &euro;</h4>
	</c:when>
	<c:otherwise>
		<p>Votre portefeuille n'est pas encore créé. Commencez à ajouter des fonds pour qu'il soit créé automatiquement.</p>
	</c:otherwise>
	</c:choose>
	<form action="<c:url value="/membre/ajouterFonds" />" method="post">
		<label>Ajouter des fonds : <input type="text" name="fonds" id="input-fonds" /> &euro;</label><br />
		<c:if test="${not empty erreurs}">
			<c:out value="${erreurs.fonds}"></c:out><br />
		</c:if>
		<input type="submit" value="Confirmer" />		
	</form>
</body>
</html>