<label for="input-prenom">Prénom</label><br />
<input type="text" name="prenom" id="input-prenom" />
<c:if test="${not empty erreurs and not empty erreurs.prenom}">
<span class="text-danger"><c:out value="${erreurs.prenom}"></c:out></span>
</c:if>