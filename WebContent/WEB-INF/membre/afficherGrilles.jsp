<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loterie | Mes grilles</title>
</head>
<body>
	<c:import url="/WEB-INF/commun/header.jsp" />
	<h3>Mes grilles</h3>
	<c:choose>
		<c:when test="${not empty grilles}">
			<c:forEach items="${grilles}" var="grille">
				<div>
					<p><a title="Editer cette grille" href="<c:url value="/membre/editerGrille?id=${grille.id}" />">éditer</a> | 
					<a title="Supprimer cette grille" href="<c:url value="/membre/supprimerGrille?id=${grille.id}" />">suppr.</a></p>
					<h4>${fn:join(grille.numeros, ', ')} - ${fn:join(grille.etoiles, ', ')}</h4>
				</div>
			</c:forEach>
			<p><a href="<c:url value="/membre/creerGrille" />">Ajouter une grille</a></p>
		</c:when>
		<c:otherwise>
			Vous n'avez créé aucune grille.
			<p><a href="<c:url value="/membre/creerGrille" />">Créer une grille</a></p>
		</c:otherwise>
	</c:choose>
</body>
</html>