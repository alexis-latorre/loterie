<div class="well well-sm">
	<div class="curseur-mois">
		<a href='<c:url value="/accueil">
			<c:param name="mois" value="${mois.moisPrecedent.monthOfYear}"></c:param>
			<c:param name="annee" value="${mois.moisPrecedent.year}"></c:param>
		</c:url>'><span class="glyphicon glyphicon-triangle-left main-text-blue" aria-hidden="true"></span></a>
		<span style="display: inline-block; width: 200px">${mois.nom} ${mois.annee}</span>
		<a href='<c:url value="/accueil">
			<c:param name="mois" value="${mois.moisSuivant.monthOfYear}"></c:param>
			<c:param name="annee" value="${mois.moisSuivant.year}"></c:param>
		</c:url>'><span class="glyphicon glyphicon-triangle-right main-text-blue" aria-hidden="true"></span></a>
	</div>
	<table class="calendrier">
		<tr>
			<td class="calendrier-case-header">lun</td>
			<td class="calendrier-case-header">mar</td>
			<td class="calendrier-case-header">mer</td>
			<td class="calendrier-case-header">jeu</td>
			<td class="calendrier-case-header">ven</td>
			<td class="calendrier-case-header">sam</td>
			<td class="calendrier-case-header">dim</td>
		</tr>
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
			<td class="calendrier-case ${moisCourant}${aujourdhui}">
				<div class="card">
					<div class="calendrier-case-contenu">
						<div class="calendrier-case-jour">
							<c:out value="${jour.numeroDansMois}"></c:out>
						</div>
					</div>
				</div>
			</td>
		</c:forEach>
		</tr>
		<c:set var="ligneCourante" value="${ligneCourante + 1}"></c:set>
	</c:forEach>
	</table>
</div>
<br />