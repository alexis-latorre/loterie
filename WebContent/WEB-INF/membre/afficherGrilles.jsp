<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loterie | Mes grilles</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body class="container">
	<c:import url="/WEB-INF/commun/header.jsp" />
	<h3>Mes grilles</h3>
	<c:choose>
		<c:when test="${not empty grilles}">
			<c:forEach items="${grilles}" var="grille">
				<c:if test="${grille.visible or utilisateur.estAdministrateur()}">
				<div>
					<p>
						<!-- <c:choose>
							<c:when test="${grille.rejoindre == false}">
							<a title="Quitter cette grille" href="<c:url value="/membre/quitterGrille"><c:param name="id" value="${grille.id}" /></c:url>">quitter</a> |
							</c:when>
							<c:otherwise>
							<a title="Rejoindre cette grille" href="<c:url value="/membre/rejoindreGrille"><c:param name="id" value="${grille.id}" /></c:url>">rejoindre</a> |
							</c:otherwise>
						</c:choose> -->
						<c:if test="${utilisateur.estAdministrateur() or utilisateur.id == grille.utilisateur.id}">
						<a title="Editer cette grille" href="<c:url value="/membre/editerGrille"><c:param name="id" value="${grille.id}" /></c:url>">éditer</a> |
							<c:choose>
								<c:when test="${grille.active}"> 
								<a title="Désactiver cette grille" href="<c:url value="/membre/desactiverGrille"><c:param name="id" value="${grille.id}" /></c:url>">désactiver</a>
								</c:when>
								<c:otherwise>
								<a title="Activer cette grille" href="<c:url value="/membre/activerGrille"><c:param name="id" value="${grille.id}" /></c:url>">activer</a>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:choose>
							<c:when test="${grille.visible and utilisateur.id == grille.utilisateur.id}">
							 | <a title="Supprimer cette grille" href="<c:url value="/membre/supprimerGrille"><c:param name="id" value="${grille.id}" /></c:url>">supprimer</a>
							</c:when>
							<c:when test="${not grille.visible and utilisateur.estAdministrateur()}">
							 | <a title="Supprimer cette grille" href="<c:url value="/admin/retablirGrille"><c:param name="id" value="${grille.id}" /></c:url>">rétablir</a>
							</c:when>
						</c:choose>
					</p>
					<h4><a title="Accéder aux détails de cette grille" href="<c:url value="/membre/afficherGrille"><c:param name="id" value="${grille.id}" /></c:url>">${grille.nom}</a></h4>
				</div>
				</c:if>
			</c:forEach>
			<p><a href="<c:url value="/membre/creerGrille" />">Ajouter une grille</a></p>
		</c:when>
		<c:otherwise>
			Vous ne participez à aucune grille.
			<p><a href="<c:url value="/membre/creerGrille" />">Créer une grille</a></p>
		</c:otherwise>
	</c:choose>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>