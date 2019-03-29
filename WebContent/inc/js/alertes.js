showAlert = function(alertes) {
	var messages = "";

	for (i = 0; i < alertes.length; i++) {
		alerte = alertes[i];
		
		messages += "<p>\"" + alerte + "\"</p>";
	}
	$("#alertes").html(messages);
	
	$("#alertes").show();
}

hideAlert = function() {
	$("#alertes").hide();
}