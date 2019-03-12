<c:choose>
<c:when test="${utilisateur.portefeuille != null}">
	<c:choose>
	<c:when test="${utilisateur.portefeuille.fonds >= 0}">
	<c:set var="classeSolde" value="Positif" />
	</c:when>
	<c:otherwise>
	<c:set var="classeSolde" value="Negatif" />
	</c:otherwise>
	</c:choose>
	<c:set var="solde" value="${utilisateur.portefeuille.fonds}" />
</c:when>
<c:otherwise>
	<c:set var="solde" value="0" />
</c:otherwise>
</c:choose>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			Activité
		</h3>
	</div>
	<div class="panel-body">
		<div class="col-md-5">
			<span>Solde</span>
			<br />
			<span class="solde${classeSolde}"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${solde}" type="currency"></fmt:formatNumber></span>
		</div>
		<c:if test="${fn:length(utilisateur.grilles) > 0}">
		<div class="col-md-7">
			<span class="main-text-blue">Grille<c:if test="${fn:length(utilisateur.grilles) > 1}">s</c:if>
			</span>
			<br />
			<c:forEach items="${utilisateur.grilles}" var="grille">
				<a title="Accéder aux détails de cette grille" href="<c:url value="/membre/afficherGrille"><c:param name="id" value="${grille.id}" /></c:url>">
				<c:forEach items="${grille.numeros}" var="numero" varStatus="status">
					<c:if test="${status.index > 0}">
					 - 
					</c:if>
					<c:out value="${numero}" />
				</c:forEach>
				 / 
				<c:forEach items="${grille.etoiles}" var="etoile" varStatus="status">
					<c:if test="${status.index > 0}">
					 - 
					</c:if>
					<c:out value="${etoile}" />
				</c:forEach>
				 : ${grille.nom}
				</a><br />
			</c:forEach>
		</div>
		</c:if>
	</div>
</div>