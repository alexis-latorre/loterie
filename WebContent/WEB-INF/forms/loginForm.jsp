		<p>
			<label for="input-pseudo">Utilisateur</label><br />
			<input type="text" name="pseudo" id="input-pseudo" value="" />
		</p>
		<p>
			<label for="input-motDePasse">Mot de passe</label><br />
			<input type="password" name="motDePasse" id="input-motDePasse" value="" />
		</p>
		<c:if test="${not empty erreursUtilisateur}">
			<c:if test="${not empty erreursUtilisateur.mdpInvalide}">
				<p>
					${erreursUtilisateur.mdpInvalide}
				</p>
			</c:if>
		</c:if>
		<br />
		<p>
			<input class="btn btn-blue" type="submit" value="Se connecter" />
		</p>