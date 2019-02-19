function toggle(id) {
	$("#" + id).toggle();
}

function afficherGains() {
	$("#lienGains").off("click");	
	$("#btnLienGains").text("-");
	$("#tableauGainsGrilles").show("fadeIn");
	
	$("#lienGains").on("click", function() {
		cacherGains();
	});
}

function cacherGains() {
	$("#lienGains").off("click");	
	$("#btnLienGains").text("+");
	$("#tableauGainsGrilles").hide("fadeOut");
	
	$("#lienGains").on("click", function() {
		afficherGains();
	});
}

function afficherHistorique(id) {
	$("#histo-" + id).off("click");	
	$("#detailHisto-" + id).text("-");
	$("#r-" + id).show("fadeIn");

	$("#histo-" + id).on("click", function() {
		cacherHistorique(id);
	});
}

function cacherHistorique(id) {
	$("#histo-" + id).off("click");	
	$("#detailHisto-" + id).text("+");
	$("#r-" + id).hide("fadeOut");
	
	$("#histo-" + id).on("click", function() {
		afficherHistorique(id);
	});
}

$("#lienGains").on("click", function() {
	afficherGains();
});