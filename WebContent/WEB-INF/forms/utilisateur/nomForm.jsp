<div class="form-group">
	<label for="input-nom">Nom</label>
	<input type="text" class="form-control" name="nom" id="input-nom" value="${champs.nom}" />
</div>
<c:if test="${not empty erreurs and not empty erreurs.nom}">
<span class="text-danger"><c:out value="${erreurs.nom}"></c:out></span>
</c:if>