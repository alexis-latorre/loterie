<div class="form-group">
	<label for="input-nom">Nom de la grille</label>
	<c:set var="nomDisabled" value=' disabled' />
	<c:if test='${utilisateur.checkPrivilege("grille-modifier-nom")}'>
		<c:set var="nomDisabled" value='' />
	</c:if>
	<input class="form-control"${nomDisabled} type="text" name="nom" id="input-nom" value="${requestScope.grille.nom}" />
</div>
<div class="form-group">
	<label>Choisir les numéros</label>
</div>
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
					<c:set var="numerosDisabled" value=' disabled' />
					<c:if test='${utilisateur.checkPrivilege("grille-modifier-numeros")}'>
						<c:set var="numerosDisabled" value='' />
					</c:if>
					<input type="checkbox"${numerosDisabled} name="numeros[]" id="input-numero${numero}" value="${numero}" <c:out value="${checked}" /> />
					<span class="checkmark-boule"></span>
					<span class="numero${numerosDisabled}"><c:out value="${numero}" /></span>
				</label>
			</div>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>
<div class="form-group">
	<label>Choisir les étoiles</label>
</div>
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
					<c:set var="etoilesDisabled" value=' disabled' />
					<c:if test='${utilisateur.checkPrivilege("grille-modifier-etoiles")}'>
						<c:set var="etoilesDisabled" value='' />
					</c:if>
					<input type="checkbox"${etoilesDisabled} name="etoiles[]" id="input-etoile${etoile}" value="${etoile}" <c:out value="${checked}" /> />
					<span class="checkmark-etoile"></span>
					<span class="numero${etoilesDisabled}"><c:out value="${etoile}" /></span>
				</label>
			</div>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>
<c:set var="etoilePlus" value=""></c:set>
<c:set var="etoilePlusDisabled" value=' disabled' />
<c:if test='${utilisateur.checkPrivilege("grille-modifier-etoile_plus")}'>
	<c:set var="etoilePlusDisabled" value='' />
</c:if>
<c:if test="${requestScope.grille.etoilePlus}">
	<c:set var="etoilePlus" value='checked="checked"'></c:set>
</c:if>
<div class="checkbox">
	<label>
		<input type="checkbox"${etoilePlusDisabled} name="etoilePlus" id="input-etoilePlus" <c:out value="${etoilePlus}" /> />Étoile +
	</label>
</div>
<div class="form-group">
	<label for="input-mymillion">Code MyMillion</label>
	<c:set var="myMillionDisabled" value=' disabled' />
	<c:if test='${utilisateur.checkPrivilege("grille-modifier-mymillion")}'>
		<c:set var="myMillionDisabled" value='' />
	</c:if>
	<input type="text"${myMillionDisabled} class="form-control" name="myMillion" id="input-mymillion" value="${requestScope.grille.myMillion}" />
</div>