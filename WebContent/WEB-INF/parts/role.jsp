<c:choose>
	<c:when test="${joueur.nomRole == 'Administrateur'}">
		<c:set scope="application" var="role" value="danger" />
	</c:when>
	<c:when test="${joueur.nomRole == 'Modérateur'}">
		<c:set scope="application" var="role" value="warning" />
	</c:when>
	<c:when test="${joueur.nomRole == 'Membre'}">
		<c:set scope="application" var="role" value="success" />
	</c:when>
	<c:when test="${joueur.nomRole == 'Utilisateur'}">
		<c:set scope="application" var="role" value="primary" />
	</c:when>
	<c:otherwise>
		<c:set scope="application" var="role" value="default" />
	</c:otherwise>
</c:choose>