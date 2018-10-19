	<h3>Mon portefeuille</h3>
	<c:choose>
	<c:when test="${utilisateur.portefeuille != null}">
		<c:choose>
		<c:when test="${utilisateur.portefeuille.fonds >= 0}">
		<span>Vert : 
		</c:when>
		<c:otherwise>
		<span>Rouge : 
		</c:otherwise>
		</c:choose>
		<c:set var="solde" value="${utilisateur.portefeuille.fonds}" />
		</span>
	</c:when>
	<c:otherwise>
		<c:set var="solde" value="0" />
	</c:otherwise>
	</c:choose>
	<h4>Mon solde : <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${solde}" type="currency"></fmt:formatNumber></h4>