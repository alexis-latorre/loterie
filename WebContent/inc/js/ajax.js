chargerDetailJour = function(id) {
	$.ajax({
		url: "https://localhost:8181/loterie/jour/detailsJour?id=" + id
	}).done(function(data) {
		$("#detailJour").html(data);
	}).fail(function(error) {
		$("#detailJour").html("Erreur de chargement");
	});
}