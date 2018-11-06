<h3 class="main-text-blue">Choisir les joueurs</h3>
<div class="col-md-12" style="margin: 0; padding: 0">
	<div class="col-md-3" style="margin: 0; padding: 0">
		<select style="display: block; width: 100%; min-height: 160px" multiple="multiple" id="input-utilisateurs">
			<c:forEach items="${utilisateurs}" var="joueur">
			<c:if test="${joueur.id != utilisateur.id}">
			<option value="${joueur.id}"><c:out value="${joueur.pseudo}"></c:out></option>
			</c:if>
			</c:forEach>
		</select>
	</div>
	<div class="col-md-1" style="display: inline-block; min-height: 160px; margin-top: 6px; margin-bottom: 2px">
		<input class="btn btn-blue" type="button" onclick="retirerTousJoueurs()" style="width: 36px; " value="<< " /><br />
		<input class="btn btn-blue" type="button" onclick="retirerJoueurs()" style="width: 36px; " value="< " /><br />
		<input class="btn btn-blue" type="button" onclick="ajouterJoueurs()" style="width: 36px; " value=">" /><br />
		<input class="btn btn-blue" type="button" onclick="ajouterTousJoueurs()" style="width: 36px; " value=">>" />
	</div>
	<div class="col-md-3" style="margin: 0; padding: 0">
		<select style="display: block; min-width: 100%; min-height: 160px" multiple="multiple" name="joueurs[]" id="input-joueurs">
			<c:forEach items="${utilisateurs}" var="joueur">
			<c:if test="${joueur.id == utilisateur.id}">
			<option value="${joueur.id}"><c:out value="${joueur.pseudo}"></c:out></option>
			</c:if>
			</c:forEach>
		</select>
	</div>
	<div class="col-md-5">&nbsp;</div>
</div>
<br />
	<p>Nombre de joueurs associés à la grille : <span id="nbJoueurs"></span></p>