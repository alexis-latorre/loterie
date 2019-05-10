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

$("#jeuGrilleForm").submit(function(e) {
	var mymillion = "";
	var valid = false;
	var validCode = /[A-Z]{2}[0-9]{7}/;
	
	while (!valid) {
		mymillion = prompt("Entrez le code MyMillion :");
		valid = ((mymillion == "") || mymillion.match(validCode));
	}
	$("<input />")
		.attr("type", "hidden")
		.attr("name", "mymillion")
		.attr("value", mymillion)
		.appendTo(this);
	return true;
});