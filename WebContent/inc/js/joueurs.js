var listJoueurs = null;

function validerJoueurs() {
	$('#input-joueurs').children().each(function() {
		$(this).attr('selected', 'selected');
	});
	
	$('#form-grille').submit();
}

function ajouterJoueurs() {
	$('#input-utilisateurs').children(":selected").each(function(index) {
		var id = $(this).val();
		var pseudo = $(this).html();
		$('#input-joueurs').append('<option value="' + id + '">' + pseudo + '</option>');
		$(this).remove();
	});
	majNbJoueursGrille();
}

function retirerJoueurs() {
	$('#input-joueurs').children(":selected").each(function(index) {
		var id = $(this).val();
		var pseudo = $(this).html();
		$('#input-utilisateurs').append('<option value="' + id + '">' + pseudo + '</option>');
		$(this).remove();
	});
	majNbJoueursGrille();
} 

function ajouterTousJoueurs() {
	$('#input-utilisateurs').children().each(function(index) {
		var id = $(this).val();
		var pseudo = $(this).html();
		$('#input-joueurs').append('<option value="' + id + '">' + pseudo + '</option>');
		$(this).remove();
	});
	majNbJoueursGrille();
}

function retirerTousJoueurs() {
	$('#input-joueurs').children().each(function(index) {
		var id = $(this).val();
		var pseudo = $(this).html();
		$('#input-utilisateurs').append('<option value="' + id + '">' + pseudo + '</option>');
		$(this).remove();
	});
	majNbJoueursGrille();
}

function majNbJoueursGrille() {
	var nbJoueurs = $('#input-joueurs').children().size();
	$('#nbJoueurs').html(nbJoueurs);
}

majNbJoueursGrille();