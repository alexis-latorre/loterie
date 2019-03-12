<div class="form-group">
	<label for="input-pseudo">Utilisateur</label>
	<input type="text" class="form-control" name="pseudo" id="input-pseudo" value="" />
</div>
<div class="form-group">
	<label for="input-motDePasse">Mot de passe</label>
	<input type="password" class="form-control" name="motDePasse" id="input-motDePasse" value="" />
</div>
<p class="text-danger">
&nbsp;
<c:if test="${not empty erreursUtilisateur}">
	<c:if test="${not empty erreursUtilisateur.mdpInvalide}">
	<c:out value="${erreursUtilisateur.mdpInvalide}" />
	</c:if>
</c:if>
</p>
<p>
	<input class="btn btn-primary" type="submit" value="Connexion" />
	<a class="main-text-blue" style="margin-left: 24px;" href="<c:url value="/inscription" />">Inscription</a>
</p>