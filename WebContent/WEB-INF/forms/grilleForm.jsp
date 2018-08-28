<label>Nom de la grille :<input type="text" name="nom" id="input-nom" value="${requestScope.grille.nom}" /></label><br />
<h4>Choisir les numéros</h4>
<table>
<c:forEach items="${tableNumeros}" var="ligneNumeros">
	<tr>		
	<c:forEach items="${ligneNumeros}" var="numero">
		<td>
			<label>
				<c:set var="checked" value=""></c:set>
				<c:if test="${not empty requestScope.grille}">
					<c:forEach items="${requestScope.grille.numeros}" var="numeroGrille">
						<c:if test="${numero == numeroGrille}">
							<c:set var="checked" value='checked="checked"'></c:set>
						</c:if>
					</c:forEach>
				</c:if>
				<input type="checkbox" name="numeros[]" id="input-numero${numero}" value="${numero}" <c:out value="${checked}" /> />
				<c:out value="${numero}" />
			</label>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>
<table>
<c:forEach items="${tableEtoiles}" var="ligneEtoiles">
	<tr>		
	<c:forEach items="${ligneEtoiles}" var="etoile">
		<td>
			<label>
				<c:set var="checked" value=""></c:set>
				<c:if test="${not empty requestScope.grille}">
					<c:forEach items="${requestScope.grille.etoiles}" var="etoileGrille">
						<c:if test="${etoile == etoileGrille}">
							<c:set var="checked" value='checked="checked"'></c:set>
						</c:if>
					</c:forEach>
				</c:if>
				<input type="checkbox" name="etoiles[]" id="input-etoile${etoile}" value="${etoile}" <c:out value="${checked}" /> />
				<c:out value="${etoile}" />
			</label>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>
<label>Étoile + :<input type="checkbox" name="etoilePlus" id="input-etoilePlus" <c:out value="${checked}" /> /></label><br />
<label>MyMillion : <input type="text" name="mymillion" id="input-mymillion" value="${requestScope.grille.myMillion}" /></label><br />
<br />