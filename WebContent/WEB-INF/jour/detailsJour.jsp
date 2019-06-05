<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			Détails du ${jour.dateJour}
		</h3>
	</div>
	<div class="panel-body">
		<c:choose>
		<c:when test="${not empty jour.grilles}">
			<c:forEach items="${jour.grilles}" var="grille">
				<div class="well">
					<c:choose>
						<c:when test="${grille.paye}">
							<c:set var="classe" value="text-success" />
						</c:when>
						<c:otherwise>
							<c:set var="classe" value="text-danger" />
						</c:otherwise>
					</c:choose>
					<p class="${classe}">${grille.nom}</p>
					<c:choose>
						<c:when test="${grille.paye}">
						<p>Gains : <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${grille.gains}" type="currency" /></p>
						</c:when>
						<c:otherwise>
						<p>Grille non-jouée pour cette période</p>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>Pas de grille</p>
		</c:otherwise>
		</c:choose>
		<c:if test="${fn:length(jeuxJour) > 0}">
			<p>
				<a href='<c:url value="/membre/creerGrille" />'><span class="glyphicon glyphicon-edit"></span> Créer une grille</a>
			</p>
		</c:if>
	</div>
</div>