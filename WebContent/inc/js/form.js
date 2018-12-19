var registered = "";

$('.modifiable').hover(function() {
	var btn = $(this).find('.btn-edit').show();
}, function() {
	$(this).find('.btn-edit').hide();
});

$('.modifiable').click(function() {
	var id = $(this).attr("id");
	
	if (registered != id) {
		var modifiable = $(this);
		var html = $(this).html();
		var val = $(this).text();

		$("#placeholder-" + id).html('<input class="form-control col-md-10" type="text" id="input-' + id + '" name="' + id + '" value="' + val + '" />');
		$("#placeholder-" + id).append('<span id="cancel-' + id + '" class="btn btn-xs btn-danger col-md-2">Annuler</span>');
		$("#cancel-" + id).click(function() {
			$(modifiable).html(html);
		});
		registered = id;
	}
});
