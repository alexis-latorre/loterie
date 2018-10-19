		<p>
			<label for="input-pseudo">Utilisateur : </label><br />
			<input type="text" name="pseudo" id="input-pseudo" value="alatorre" />
		</p>
		<p>
			<label for="input-motDePasse">Mot de passe : </label><br />
			<input type="password" name="motDePasse" id="input-motDePasse" value="useruser" />
		</p>
		<c:if test="${not empty erreursUtilisateur}">
			<c:if test="${not empty erreursUtilisateur.mdpInvalide}">
				<p>
					${erreursUtilisateur.mdpInvalide}
				</p>
			</c:if>
		</c:if>
		<p>
			<input class="btn btn-primary" type="submit" value="Se connecter" />
		</p>