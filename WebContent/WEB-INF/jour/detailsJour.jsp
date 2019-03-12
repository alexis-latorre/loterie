<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			D�tails du ${jour.dateJour}
		</h3>
	</div>
	<div class="panel-body">
		<c:choose>
		<c:when test="${not empty jour.grilles}">
			<c:forEach items="${jour.grilles}" var="grille">
				<c:choose>
					<c:when test="${grille.paye}">
						<c:set var="classe" value="text-success" />
					</c:when>
					<c:otherwise>
						<c:set var="classe" value="text-danger" />
					</c:otherwise>
				</c:choose>		
				<p class="${classe}">${grille.nom}</p>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>Pas de grille</p>
		</c:otherwise>
		</c:choose>
	</div>
</div>