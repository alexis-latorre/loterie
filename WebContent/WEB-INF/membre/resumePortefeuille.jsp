<c:set var="soldeTotal" value="0" />
<c:set var="soldePortefeuille" value="0" />
<c:set var="soldeGrilles" value="0" />
<c:if test="${utilisateur.portefeuille != null}">
	<c:set var="soldePortefeuille" value="${utilisateur.portefeuille.fonds}" />
	<c:set var="soldeTotal" value="${soldeTotal + utilisateur.portefeuille.fonds}" />
</c:if>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			Activité
		</h3>
	</div>
	<div class="panel-body">
		<c:if test="${fn:length(utilisateur.lgus) > 0}">
		<c:forEach items="${utilisateur.lgus}" var="lgu">
			<c:if test="${lgu.fonds != null}">
				<c:set var="soldeTotal" value="${soldeTotal + lgu.fonds}" />
				<c:set var="soldeGrilles" value="${soldeGrilles + lgu.fonds}" />
			</c:if>
		</c:forEach>
		</c:if>
		<div class="col-md-5">
			<c:choose>
			<c:when test="${soldeTotal >= 0}">
			<c:set var="classeSoldeTotal" value="Positif" />
			</c:when>
			<c:otherwise>
			<c:set var="classeSoldeTotal" value="Negatif" />
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${soldePortefeuille >= 0}">
			<c:set var="classeSoldePortefeuille" value="Positif" />
			</c:when>
			<c:otherwise>
			<c:set var="classeSoldePortefeuille" value="Negatif" />
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${soldeGrilles >= 0}">
			<c:set var="classeSoldeGrilles" value="Positif" />
			</c:when>
			<c:otherwise>
			<c:set var="classeSoldeGrilles" value="Negatif" />
			</c:otherwise>
			</c:choose>
			<span>Solde</span>
			<br />
			<span style="cursor: help;" class="solde soldePortefeuille ${classeSoldePortefeuille}" data-toggle="tooltip" data-placement="right" title="Solde de réserve">
				<span class="monospace">&nbsp;&nbsp;</span>
				<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${soldePortefeuille}" type="currency"></fmt:formatNumber>
			</span>
			<br />
			<span style="cursor: help;" class="solde soldeGrilles ${classeSoldeGrilles}" data-toggle="tooltip" data-placement="right" title="Solde placé sur des grilles">
				<span class="monospace">+</span>
				<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${soldeGrilles}" type="currency"></fmt:formatNumber>
			</span>
			<br />
			<span style="cursor: help;" class="solde  soldeTotal ${classeSoldeTotal}" data-toggle="tooltip" data-placement="right" title="Solde total"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${soldeTotal}" type="currency"></fmt:formatNumber></span>
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
				 (${grille.nom})
				</a><br />
			</c:forEach>
		</div>
		</c:if>
	</div>
</div>