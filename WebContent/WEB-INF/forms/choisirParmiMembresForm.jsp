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
<select style="display: inline-block; min-width: 150px; min-height: 150px" multiple="multiple" name="joueurs[]" id="input-joueurs">
	<c:forEach items="${utilisateurs}" var="joueur">
	<c:if test="${joueur.id == utilisateur.id}">
	<option value="${joueur.id}"><c:out value="${joueur.pseudo}"></c:out></option>
	</c:if>
	</c:forEach>
</select>
<br />
<script type="text/javascript">
	var listJoueurs = null;
	var debug = true;
	
	function log(msg) {
		if (debug) {
			console.log(msg);
		}
	}

	function ajouterJoueurs() {
		log('-- deb ajouterJoueurs');
		$('#input-utilisateurs').children(":selected").each(function(index) {
			var id = $(this).val();
			var pseudo = $(this).html();
			$('#input-joueurs').append('<option value="' + id + '">' + pseudo + '</option>');
			$(this).remove();
		});
		log('-- fin ajouterJoueurs');
	}

	function retirerJoueurs() {
		log('-- deb retirerJoueurs');
		$('#input-joueurs').children(":selected").each(function(index) {
			var id = $(this).val();
			var pseudo = $(this).html();
			$('#input-utilisateurs').append('<option value="' + id + '">' + pseudo + '</option>');
			$(this).remove();
		});
		log('-- fin retirerJoueurs');
	} 

	function ajouterTousJoueurs() {
		log('-- deb ajouterTousJoueurs');
		$('#input-utilisateurs').children().each(function(index) {
			var id = $(this).val();
			var pseudo = $(this).html();
			$('#input-joueurs').append('<option value="' + id + '">' + pseudo + '</option>');
			$(this).remove();
		});
		log('-- fin ajouterTousJoueurs');
	}

	function retirerTousJoueurs() {
		log('-- deb retirerTousJoueurs');
		$('#input-joueurs').children().each(function(index) {
			var id = $(this).val();
			var pseudo = $(this).html();
			$('#input-utilisateurs').append('<option value="' + id + '">' + pseudo + '</option>');
			$(this).remove();
		});
		log('-- fin retirerTousJoueurs');
	}
</script>