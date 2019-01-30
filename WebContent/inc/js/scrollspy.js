var currentY = 5;

$(window).scroll(function (ss) {
	var y = ss.originalEvent.pageY;
	
	if (window.innerWidth < 768) {
	
		if (y > currentY) {
			$("#navbarHeader").hide();
		} else {
			$("#navbarHeader").show();
		}
	}
	currentY = y;
});

