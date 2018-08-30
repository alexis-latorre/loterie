var listJoueurs = null;
var debug = true;

function log(msg) {
	if (debug) {
		console.log(msg);
	}
}

function validerJoueurs() {
	$('#input-joueurs').children().each(function() {
		$(this).attr('selected', 'selected');
	});
	
	$('#form-nouvelleGrille').submit();
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
	majNbJoueursGrille();
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
	majNbJoueursGrille();
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
	majNbJoueursGrille();
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
	majNbJoueursGrille();
}

function majNbJoueursGrille() {
	var nbJoueurs = $('#input-joueurs').children().size();
	$('#nbJoueurs').html(nbJoueurs);
}

majNbJoueursGrille();