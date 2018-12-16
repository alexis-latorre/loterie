<label for="input-pseudo">Pseudo</label><br />
<input type="text" name="pseudo" id="input-pseudo" />
<c:if test="${not empty erreurs and not empty erreurs.pseudo}">
<span class="text-danger"><c:out value="${erreurs.pseudo}"></c:out></span>
</c:if>
<br />
<c:import url="/WEB-INF/forms/utilisateur/nomForm.jsp" />
<br />
<c:import url="/WEB-INF/forms/utilisateur/prenomForm.jsp" />
<br />
<c:import url="/WEB-INF/forms/utilisateur/emailForm.jsp" />