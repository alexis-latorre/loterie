$("#popupSucces").fadeIn("slow");

$("#popupSucces").click(function() {
	$(this).fadeOut("slow");
});

function popupConfirmFundsChange(form) {
	var action = 'créditer';
	var funds = $("#input-fonds").val();
	var uId = $("#input-joueur").val();
	var user = $("#u" + uId).text();
	
	if (funds < 0) {
		action = 'débiter';
	}
	var c = confirm("Êtes-vous sûr de vouloir " + action + " '" + user + "' de " + funds.replace('-', '') + " € ? (" + funds + ")");
	
	if (c) {
		form.submit();
	} else {
		return false
	}
}