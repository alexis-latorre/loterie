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
					<p>
						<c:choose>
							<c:when test="${grille.rejoindre == false}">
							<a title="Quitter cette grille" href="<c:url value="/membre/quitterGrille"><c:param name="id" value="${grille.id}" /></c:url>">quitter</a> 
							</c:when>
							<c:otherwise>
							<a title="Rejoindre cette grille" href="<c:url value="/membre/rejoindreGrille"><c:param name="id" value="${grille.id}" /></c:url>">rejoindre</a>
							</c:otherwise>
						</c:choose>
						<c:if test="${utilisateur.id == grille.utilisateur.id}">
						| <a title="Editer cette grille" href="<c:url value="/membre/editerGrille"><c:param name="id" value="${grille.id}" /></c:url>">éditer</a> | 
						<a title="Supprimer cette grille" href="<c:url value="/membre/supprimerGrille"><c:param name="id" value="${grille.id}" /></c:url>">suppr.</a>
						</c:if>
					</p>
					<h4>${grille.id} : ${fn:join(grille.numeros, ', ')} - ${fn:join(grille.etoiles, ', ')}</h4>
				</div>
			</c:forEach>
			<p><a href="<c:url value="/membre/creerGrille" />">Ajouter une grille</a></p>
		</c:when>
		<c:otherwise>
			Vous ne participez à aucune grille.
			<p><a href="<c:url value="/membre/creerGrille" />">Créer une grille</a></p>
		</c:otherwise>
	</c:choose>
</body>
</html>