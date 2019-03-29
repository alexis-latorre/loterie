<div class="form-group">
	<label for="input-email">E-mail</label>
	<input type="text" class="form-control" name="email" id="input-email" value="${champs.email}" />
</div>
<c:if test="${not empty erreurs and not empty erreurs.email}">
<span class="text-danger"><c:out value="${erreurs.email}"></c:out></span>
</c:if>