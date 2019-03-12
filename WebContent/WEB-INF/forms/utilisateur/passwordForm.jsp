<div class="form-group">
	<label for="input-motDePasse">Mot de passe</label>
	<input type="password" class="form-control" name="motDePasse" id="input-motDePasse" value="${champs.motDePasse}" />
</div>
<c:if test="${not empty erreurs and not empty erreurs.motDePasse}">
<span class="text-danger"><c:out value="${erreurs.motDePasse}"></c:out></span>
</c:if>
<div class="form-group">
	<label for="input-motDePasseConfirmation">Confirmation du mot de passe</label>
	<input type="password" class="form-control" name="motDePasseConfirmation" id="input-motDePasseConfirmation" value="${champs.motDePasseConfirmation}" />
</div>
<c:if test="${not empty erreurs and not empty erreurs.motDePasseConfirmation}">
<span class="text-danger"><c:out value="${erreurs.motDePasseConfirmation}"></c:out></span>
</c:if>