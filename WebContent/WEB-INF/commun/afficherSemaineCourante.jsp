<table>
<c:set var="nbLignes" value="${fn:length(mois.jours) / 7}" />
<c:set var="ligneCourante" value="0"></c:set>
<c:forEach begin="0" end="${nbLignes - 1}" step="1" varStatus="i">
	<tr>
	<c:forEach begin="0" end="6" step="1" varStatus="j">
		<c:set var="jour" value="${mois.jours[(i.index * 7) + j.index]}"></c:set>
		<td>
			<c:out value="${jour.nomCourt}"></c:out>
			<br />
			<c:out value="${jour.numeroDansMois}"></c:out>
			<br />
			<c:if test="${not empty jour.grilles}">
				<c:forEach items="${jour.grilles}" var="grille">
				<c:choose>
				<c:when test="${not jour.paye}"><c:set var="paye" value=" class='error'" /></c:when>
				<c:otherwise><c:set var="paye" value=" class='success'" /></c:otherwise>
				</c:choose>
				<h4><c:out value="${grille.jeu.nom}"></c:out> <sup${paye}><c:out value="(${grille.id})"></c:out></sup></h4>
				</c:forEach>
			</c:if>
		</td>
	</c:forEach>
	</tr>
	<c:set var="ligneCourante" value="${ligneCourante + 1}"></c:set>
</c:forEach>
</table>
<br />