chargerDetailJour = function(id) {
	$.ajax({
		url: "https://localhost:8181/loterie/jour/detailsJour?id=" + id
	}).done(function(data) {
		$("#detailsJour").html(data);
	}).fail(function(error) {
		$("#detailsJour").html("Erreur de chargement");
	});
}