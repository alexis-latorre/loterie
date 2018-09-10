<h2 style="text-align: center;">
	<a href='<c:url value="/accueil">
		<c:param name="mois" value="${mois.moisPrecedent.monthOfYear}"></c:param>
		<c:param name="annee" value="${mois.moisPrecedent.year}"></c:param>
	</c:url>'><span class="glyphicon glyphicon-triangle-left" aria-hidden="true"></span></a>
	<span style="display: inline-block; width: 300px">${mois.nom} ${mois.annee}</span>
	<a href='<c:url value="/accueil">
		<c:param name="mois" value="${mois.moisSuivant.monthOfYear}"></c:param>
		<c:param name="annee" value="${mois.moisSuivant.year}"></c:param>
	</c:url>'><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></a>
</h2>
<table class="calendrier">
<c:set var="nbLignes" value="${fn:length(mois.jours) / 7}" />
<c:set var="ligneCourante" value="0"></c:set>
<c:forEach begin="0" end="${nbLignes - 1}" step="1" varStatus="i">
	<tr>
	<c:forEach begin="0" end="6" step="1" varStatus="j">
		<c:set var="jour" value="${mois.jours[(i.index * 7) + j.index]}"></c:set>
		<c:set var="aujourdhui" value=""></c:set>
		<c:choose>
			<c:when test="${jour.numeroMois == mois.numero}">
				<c:set var="moisCourant" value=" mois-courant"></c:set>
				<c:choose>
					<c:when test="${anneeAjd and moisAjd and jour.numeroDansMois == mois.aujourdhui}">
						<c:set var="aujourdhui" value=" aujourdhui"></c:set>
					</c:when>
					<c:otherwise>
						<c:set var="aujourdhui" value=""></c:set>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:set var="moisCourant" value=""></c:set>
				<c:set var="aujourdhui" value=""></c:set>
			</c:otherwise>
		</c:choose>
		<td class="well well-sm calendrier-case ${moisCourant}${aujourdhui}">
			<div class="card">
				<div class="calendrier-case-contenu">
					<c:out value="${jour.nomCourt}"></c:out>
					<div class="calendrier-case-jour">
						<c:out value="${jour.numeroDansMois}"></c:out>
					</div>
					<br />
					<c:if test="${not empty jour.grilles}">
						<c:forEach items="${jour.grilles}" var="grille">
						<c:choose>
						<c:when test="${not grille.paye}">
						<div>
							<a class="text-danger" title="Accéder aux détails de cette grille" href="<c:url value="/membre/afficherGrille"><c:param name="id" value="${grille.id}" /></c:url>">${grille.nom}</a>
							<span title="Non payée" class="text-danger glyphicon glyphicon-remove" aria-hidden="true"></span>
						</div>
						</c:when>
						<c:otherwise>
						<div>
							<a class="text-success" title="Accéder aux détails de cette grille" href="<c:url value="/membre/afficherGrille"><c:param name="id" value="${grille.id}" /></c:url>">${grille.nom}</a>
							<span title="Payée" class="text-success glyphicon glyphicon-ok" aria-hidden="true"></span>
						</div>
						</c:otherwise>
						</c:choose>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</td>
	</c:forEach>
	</tr>
	<c:set var="ligneCourante" value="${ligneCourante + 1}"></c:set>
</c:forEach>
</table>
<br />