<c:choose>
<c:when test="${utilisateur.portefeuille != null}">
	<c:choose>
	<c:when test="${utilisateur.portefeuille.fonds >= 0}">
	<c:set var="classeSolde" value="text-success" />
	</c:when>
	<c:otherwise>
	<c:set var="classeSolde" value="text-danger" />
	</c:otherwise>
	</c:choose>
	<c:set var="solde" value="${utilisateur.portefeuille.fonds}" />
</c:when>
<c:otherwise>
	<c:set var="solde" value="0" />
</c:otherwise>
</c:choose>
<div class="well col-md-12">
	<div class="col-md-6 main-text-blue">
		<span>Mon solde</span>
		<br />
		<span class="${classeSolde}"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${solde}" type="currency"></fmt:formatNumber></span>
	</div>
	<c:if test="${fn:length(utilisateur.grilles) > 0}">
	<div class="col-md-6 main-text-blue">
		<span>Mes grilles</span>
		<br />
		<c:forEach items="${utilisateur.grilles}" var="grille">
			<p><a title="Accéder aux détails de cette grille" href="<c:url value="/membre/afficherGrille"><c:param name="id" value="${grille.id}" /></c:url>">${grille.nom}</a></p>
		</c:forEach>
	</div>
	</c:if>
</div>