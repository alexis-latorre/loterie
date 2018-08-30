<h4>Utilisateurs disponibles</h4>
<select style="display: inline-block; min-width: 150px; min-height: 150px" multiple="multiple" id="input-utilisateurs">
	<c:forEach items="${utilisateurs}" var="joueur">
	<c:if test="${joueur.id != utilisateur.id}">
	<option value="${joueur.id}"><c:out value="${joueur.pseudo}"></c:out></option>
	</c:if>
	</c:forEach>
</select>
<div style="display: inline-block; min-width: 40px; min-height: 150px">
	<input type="button" onclick="retirerTousJoueurs()" style="width: 36px; " value="<< " /><br />
	<input type="button" onclick="retirerJoueurs()" style="width: 36px; " value="< " /><br />
	<input type="button" onclick="ajouterJoueurs()" style="width: 36px; " value=">" /><br />
	<input type="button" onclick="ajouterTousJoueurs()" style="width: 36px; " value=">>" />
</div>
<div style="display: inline-block; min-width: 150px; min-height: 150px">
	<select style="display: block; min-width: 150px; min-height: 150px" multiple="multiple" name="joueurs[]" id="input-joueurs">
		<c:forEach items="${utilisateurs}" var="joueur">
		<c:if test="${joueur.id == utilisateur.id}">
		<option value="${joueur.id}"><c:out value="${joueur.pseudo}"></c:out></option>
		</c:if>
		</c:forEach>
	</select>
	<p>Nombre de joueurs associés à la grille : <span id="nbJoueurs"></span></p>
</div>
<br />