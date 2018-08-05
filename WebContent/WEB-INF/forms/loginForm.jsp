		<label>Utilisateur : <input type="text" name="pseudo" id="input-pseudo" value="alatorre" /></label>
		<br />
		<label>Mot de passe : <input type="password" name="motDePasse" id="input-motDePasse" value="useruser" /></label>
		<br />
		<c:if test="${not empty erreursUtilisateur}">
			<c:if test="${not empty erreursUtilisateur.mdpInvalide}">
				<p class="error-text">${erreursUtilisateur.mdpInvalide}</p>
			</c:if>
		</c:if>
		<input type="submit" value="Se connecter" />