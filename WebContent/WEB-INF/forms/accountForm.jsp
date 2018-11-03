<label for="input-pseudo">Pseudo</label><br />
<input type="text" name="pseudo" id="input-pseudo" />
<c:if test="${not empty erreurs and not empty erreurs.pseudo}">
<span class="text-danger"><c:out value="${erreurs.pseudo}"></c:out></span>
</c:if>
<br />
<label for="input-nom">Nom</label><br />
<input type="text" name="nom" id="input-nom" />
<c:if test="${not empty erreurs and not empty erreurs.nom}">
<span class="text-danger"><c:out value="${erreurs.nom}"></c:out></span>
</c:if>
<br />
<label for="input-prenom">Prénom</label><br />
<input type="text" name="prenom" id="input-prenom" />
<c:if test="${not empty erreurs and not empty erreurs.prenom}">
<span class="text-danger"><c:out value="${erreurs.prenom}"></c:out></span>
</c:if>
<br />
<label for="input-email">E-mail</label><br />
<input type="text" name="email" id="input-email" />
<c:if test="${not empty erreurs and not empty erreurs.email}">
<span class="text-danger"><c:out value="${erreurs.email}"></c:out></span>
</c:if>