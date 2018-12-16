<label for="input-nom">Nom</label><br />
<input type="text" name="nom" id="input-nom" />
<c:if test="${not empty erreurs and not empty erreurs.nom}">
<span class="text-danger"><c:out value="${erreurs.nom}"></c:out></span>
</c:if>