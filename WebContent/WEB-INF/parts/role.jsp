<c:choose>
	<c:when test="${session.utilisateur.nomRole == 'Administrateur'}">
		<c:set scope="page" var="role" value="danger" />
	</c:when>
	<c:when test="${session.utilisateur.nomRole == 'Modérateur'}">
		<c:set scope="page" var="role" value="warning" />
	</c:when>
	<c:when test="${session.utilisateur.nomRole == 'Membre'}">
		<c:set scope="page" var="role" value="success" />
	</c:when>
	<c:when test="${session.utilisateur.nomRole == 'Utilisateur'}">
		<c:set scope="page" var="role" value="primary" />
	</c:when>
	<c:otherwise>
		<c:set scope="page" var="role" value="default" />
	</c:otherwise>
</c:choose>