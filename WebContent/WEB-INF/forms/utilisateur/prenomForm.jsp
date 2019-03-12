<div class="form-group">
	<label for="input-prenom">Prénom</label>
	<input type="text" class="form-control" name="prenom" id="input-prenom" value="${champs.prenom}" />
</div>
<c:if test="${not empty erreurs and not empty erreurs.prenom}">
<span class="text-danger"><c:out value="${erreurs.prenom}"></c:out></span>
</c:if>