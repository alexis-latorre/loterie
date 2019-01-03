<label for="input-motDePasse">Mot de passe</label>
<br />
<input type="password" name="motDePasse" id="input-motDePasse" />
<c:if test="${not empty erreurs and not empty erreurs.motDePasse}">
<span class="text-danger"><c:out value="${erreurs.motDePasse}"></c:out></span>
</c:if>
<br />
<label for="input-motDePasseConfirmation">Confirmation</label>
<br />
<input type="password" name="motDePasseConfirmation" id="input-motDePasseConfirmation" />
<c:if test="${not empty erreurs and not empty erreurs.motDePasseConfirmation}">
<span class="text-danger"><c:out value="${erreurs.motDePasseConfirmation}"></c:out></span>
</c:if>