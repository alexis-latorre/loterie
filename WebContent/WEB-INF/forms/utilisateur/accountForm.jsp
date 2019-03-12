<div class="form-group">
	<label for="input-pseudo">Pseudo</label>
	<input type="text" class="form-control" name="pseudo" id="input-pseudo" value="${champs.pseudo}" />
</div>
<c:if test="${not empty erreurs and not empty erreurs.pseudo}">
<span class="text-danger"><c:out value="${erreurs.pseudo}"></c:out></span>
</c:if>
<c:import url="/WEB-INF/forms/utilisateur/nomForm.jsp" />
<c:import url="/WEB-INF/forms/utilisateur/prenomForm.jsp" />
<c:import url="/WEB-INF/forms/utilisateur/emailForm.jsp" />