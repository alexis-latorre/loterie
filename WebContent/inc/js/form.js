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

// Affichage du stylo d'édition au survol d'un champ modifiable
$(".placeholder").hover(function() {
	var id = $(this).attr("id").substr("valeurInitiale-".length);

	// On cache l'icône d'édition si le champ est déjà en cours de modification
	if (editionEnCours[id]) {
		$("#iconeEdition-" + id).hide();
	} else {
		$("#iconeEdition-" + id).show();
	}
// Icône de modification cachée lorsqu'on arrête de survoler le champ modifiable
}, function() {
	var id = $(this).attr("id").substr("valeurInitiale-".length);
	$("#iconeEdition-" + id).hide();
});

$(".placeholder").click(function() {
	var id = $(this).attr("id").substr("valeurInitiale-".length);
	
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