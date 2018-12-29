var regChiffre = /[0-9]+/g;
var regMin = /[a-z]+/g;
var regMaj = /[A-Z]+/g;
var regTaille = /.{8}.*/gi;

var valeurInitiales = [];
var editionEnCours = [];
var selection = "";

// Initialisation de chaque conteneur de champ modifiable
$(".modifiable").each(function() {
	var id = $(this).attr("id");
	
	// On stocke la valeur initiale de chaque champ (pour l'annulation)
	valeurInitiales[id] = $("#valeurInitiale-" + id).text();
	editionEnCours[id] = false;
});

$(".placeholder").click(function() {
	var id = $(this).attr("id").substr("valeurInitiale-".length);
	$("#iconeEdition-" + id).hide();
	
	// Si l'édition est en cours, cliquer sur le champ n'a aucun effet
	if (!editionEnCours[id]) {
		// Tous les autres champs sont ré-initialisés
		$(".placeholder").each(function() {
			var idCancel = $(this).attr("id").substr("valeurInitiale-".length);
			annulerEdition(idCancel);
		});
		afficherEdition(id);
	}
});

$(".btn-cancel").click(function() {
	var id = $(this).attr("id").substr("cancel-".length);
	annulerEdition(id);
	$("#iconeEdition-" + id).show();
});

$(".btn-confirm").click(function() {
	$("#formSubmit").submit();
});

var annulerEdition = function(id) {
	$("#inputContainer-" + id).hide();
	$("#valeurInitiale-" + id).show();
	$("#input-" + id).val(valeurInitiales[id]);
	editionEnCours[id] = false;
};

var afficherEdition = function(id) {
	$("#valeurInitiale-" + id).hide();
	$("#inputContainer-" + id).show();
	editionEnCours[id] = true;
};

$(".form-control").keypress(function(key) {
	if (key.which == 13) {
		$("#formSubmit").submit();
	}
});

$("#input-motDePasse").keyup(function() {
	var mdp = $(this).val();
	var regex = mdp.match(regChiffre) && mdp.match(regMin) && 
		mdp.match(regMaj) && mdp.match(regTaille);
	var erreurs = "";

	if (mdp.length > 0) {
		if (!mdp.match(regChiffre)) {
			erreurs += "<span class='text-danger'>Il faut au moins un chiffre dans le mot de passe</span><br />";
		} else {
			erreurs += "<span class='text-success'>Il faut au moins un chiffre dans le mot de passe</span><br />";
		}
		if (!mdp.match(regMin)) {
			erreurs += "<span class='text-danger'>Il faut au moins une minuscule dans le mot de passe</span><br />";
		} else {
			erreurs += "<span class='text-success'>Il faut au moins une minuscule dans le mot de passe</span><br />";
		}
		if (!mdp.match(regMaj)) {
			erreurs += "<span class='text-danger'>Il faut au moins une majuscule dans le mot de passe</span><br />";
		} else {
			erreurs += "<span class='text-success'>Il faut au moins une majuscule dans le mot de passe</span><br />";
		}
		if (!mdp.match(regTaille)) {
			erreurs += "<span class='text-danger'>Le mot de passe doit faire 8 caractères au minimum</span><br />";
		} else {
			erreurs += "<span class='text-success'>Le mot de passe doit faire 8 caractères au minimum</span><br />";
		}
	}
	$("#erreursMDP").html(erreurs);
});

$("#input-motDePasseConfirmation").keyup(function() {
	var mdp = $("#input-motDePasse").val();
	var confirm = $(this).val();
	var erreurs = "";

	if (confirm.length > 0) {
		if (confirm != mdp) {
			erreurs = "<span class='text-danger'>Le mot de passe de confirmation doit être égal au mot de passe saisi</span>";
		} else {
			erreurs = "<span class='text-success'>Le mot de passe de confirmation doit être égal au mot de passe saisi</span>";
		}
	}
	$("#erreursConfirmationMDP").html(erreurs);
});