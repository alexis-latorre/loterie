<label for="input-email">E-mail</label><br />
<input type="text" name="email" id="input-email" />
<c:if test="${not empty erreurs and not empty erreurs.email}">
<span class="text-danger"><c:out value="${erreurs.email}"></c:out></span>
</c:if>