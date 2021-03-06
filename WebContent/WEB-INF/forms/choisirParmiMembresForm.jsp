<div class="form-group">
	<label>Choisir les joueurs</label>
</div>
<div class="col-md-12" style="margin: 0; padding: 0">
	<div class="col-md-3" style="margin: 0; padding: 0">
		<p style="margin-bottom: 6px" class="main-text-blue">Joueurs disponibles</p>
	</div>
	<div class="col-md-1"></div>
	<div class="col-md-3" style="margin: 0; padding: 0">
		<p style="margin-bottom: 6px" class="main-text-blue">Joueurs participant</p>
	</div>
</div>
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
	<div class="col-md-1" style="display: inline-block; min-height: 176px; margin-left: 6px; margin-top: 6px; margin-bottom: 2px">
		<input class="btn btn-primary" type="button" onclick="retirerTousJoueurs()" style="width: 40px; margin-bottom: 6px" value="<< " /><br />
		<input class="btn btn-primary" type="button" onclick="retirerJoueurs()" style="width: 40px; margin-bottom: 6px" value="< " /><br />
		<input class="btn btn-primary" type="button" onclick="ajouterJoueurs()" style="width: 40px; margin-bottom: 6px" value=">" /><br />
		<input class="btn btn-primary" type="button" onclick="ajouterTousJoueurs()" style="width: 40px; " value=">>" />
	</div>
	<div class="col-md-3" style="margin: 0; padding: 0">
		<select id="input-joueurs" style="display: block; min-width: 100%; min-height: 160px" multiple="multiple" name="joueurs[]">
		<c:choose>
			<c:when test="${not empty joueurs}">
			<c:forEach items="${joueurs}" var="joueur">
			<option value="${joueur.id}"><c:out value="${joueur.pseudo}"></c:out></option>
			</c:forEach>
			</c:when>
			<c:otherwise>
			<c:forEach items="${utilisateurs}" var="joueur">
			<c:if test="${joueur.id == utilisateur.id}">
			<option value="${joueur.id}"><c:out value="${joueur.pseudo}"></c:out></option>
			</c:if>
			</c:forEach>
			</c:otherwise>
		</c:choose>
		</select>
	</div>
	<div class="col-md-5">&nbsp;</div>
</div>
<br />
<p>Nombre de joueurs associ�s � la grille : <span id="nbJoueurs">
<c:choose>
	<c:when test="${not empty joueurs}">${joueurs.size()}</c:when>
	<c:otherwise>1</c:otherwise>
</c:choose>
</span></p>
<c:import url="/WEB-INF/js/joueurs.js.jsp" />