		<p>
			<label for="input-pseudo">Utilisateur</label><br />
			<input type="text" name="pseudo" id="input-pseudo" value="" />
		</p>
		<p>
			<label for="input-motDePasse">Mot de passe</label><br />
			<input type="password" name="motDePasse" id="input-motDePasse" value="" /><br />
			<!--a href="<c:url value="/oubli-mdp" />">mot de passe oublié ?</a-->
		</p>
		<p class="text-danger">
		&nbsp;
		<c:if test="${not empty erreursUtilisateur}">
			<c:if test="${not empty erreursUtilisateur.mdpInvalide}">
			<c:out value="${erreursUtilisateur.mdpInvalide}" />
			</c:if>
		</c:if>
		</p>
		<p>
			<input class="btn btn-blue" type="submit" value="Connexion" />
			<a class="main-text-blue" style="margin-left: 24px; display: inline-block; margin-top: -24px" href="<c:url value="/inscription" />">Inscription</a>
		</p>