<h3>Loterie</h3>
<ul>
	<li><a href="<c:url value="/accueil" />">Accueil</a></li>
	<li><a href="<c:url value="/resultats" />">Résultats</a></li>
	<c:if test="${loggedIn == true}">
		<li><a href="<c:url value="/membre/profil" />">Mon profil</a></li>
		<c:if test="${utilisateur.niveau <= 0}">
			<li><a href="<c:url value="/admin" />">Administration</a></li>
		</c:if>
	</c:if>
	<c:choose>
		<c:when test="${not loggedIn}">
			<li><a href="<c:url value="/inscription" />">S'inscrire</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="<c:url value="/deconnexion" />">Se déconnecter</a></li>
		</c:otherwise>
	</c:choose>
</ul>