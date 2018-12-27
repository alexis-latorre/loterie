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