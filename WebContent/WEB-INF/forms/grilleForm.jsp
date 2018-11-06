<h3 class="main-text-blue"><label for="input-nom">Nom de la grille</label></h3>
<input class="input" type="text" name="nom" id="input-nom" value="${requestScope.grille.nom}" />
<h3 class="main-text-blue">Choisir les numéros</h3>
<table class="table">
<c:forEach items="${tableNumeros}" var="ligneNumeros">
	<tr>		
	<c:forEach items="${ligneNumeros}" var="numero">
		<td>
			<c:set var="checked" value=""></c:set>
			<c:if test="${not empty requestScope.grille}">
				<c:forEach items="${requestScope.grille.numeros}" var="numeroGrille">
					<c:if test="${numero == numeroGrille}">
						<c:set var="checked" value='checked="checked"'></c:set>
					</c:if>
				</c:forEach>
			</c:if>
			<div class="checkbox-boule-container">
				<label>
					<input type="checkbox" name="numeros[]" id="input-numero${numero}" value="${numero}" <c:out value="${checked}" /> />
					<span class="checkmark-boule"></span>
					<span class="numero"><c:out value="${numero}" /></span>
				</label>
			</div>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>
<h3 class="main-text-blue">Choisir les étoiles</h3>
<table class="table">
<c:forEach items="${tableEtoiles}" var="ligneEtoiles">
	<tr>		
	<c:forEach items="${ligneEtoiles}" var="etoile">
		<td>
			<c:set var="checked" value=""></c:set>
			<c:if test="${not empty requestScope.grille}">
				<c:forEach items="${requestScope.grille.etoiles}" var="etoileGrille">
					<c:if test="${etoile == etoileGrille}">
						<c:set var="checked" value='checked="checked"'></c:set>
					</c:if>
				</c:forEach>
			</c:if>
			<div class="checkbox-etoile-container">
				<label>
					<input type="checkbox" name="etoiles[]" id="input-etoile${etoile}" value="${etoile}" <c:out value="${checked}" /> />
					<span class="checkmark-etoile"></span>
					<span class="numero"><c:out value="${etoile}" /></span>
				</label>
			</div>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>
<c:set var="etoilePlus" value=""></c:set>
<c:if test="${requestScope.grille.etoilePlus}">
	<c:set var="etoilePlus" value='checked="checked"'></c:set>
</c:if>
<label>Étoile + <input type="checkbox" name="etoilePlus" id="input-etoilePlus" <c:out value="${etoilePlus}" /> /></label><br />
<h3><label for="input-mymillion">Code MyMillion</label></h3>
<input type="text" name="myMillion" id="input-mymillion" value="${requestScope.grille.myMillion}" /><br />
<br />