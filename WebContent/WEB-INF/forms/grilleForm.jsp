<h4>Choisir les numéros</h4>
<table>
<c:forEach items="${tableNumeros}" var="ligneNumeros">
	<tr>		
	<c:forEach items="${ligneNumeros}" var="numero">
		<td>
			<label>
				<c:set var="checked" value=""></c:set>
				<c:forEach items="${grille.numeros}" var="numeroGrille">
					<c:if test="${numero == numeroGrille}">
						<c:set var="checked" value='checked="checked"'></c:set>
					</c:if>
				</c:forEach>
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
				<c:forEach items="${grille.etoiles}" var="etoileGrille">
					<c:if test="${etoile == etoileGrille}">
						<c:set var="checked" value='checked="checked"'></c:set>
					</c:if>
				</c:forEach>
				<input type="checkbox" name="etoiles[]" id="input-etoile${etoile}" value="${etoile}" <c:out value="${checked}" /> />
				<c:out value="${etoile}" />
			</label>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>