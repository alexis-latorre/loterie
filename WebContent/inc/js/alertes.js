var nbNotifications = 0;

setNbNotifications = function (notifs) {
	nbNotifications = notifs;
}

fermerNotifications = function() {
	$("#notificationsFrame").fadeOut();
}

fermerNotification = function(id) {
	nbNotifications--;
	$("#nbNotifs").text(nbNotifications);
	
	if (nbNotifications == 0) {
		fermerNotifications();
	} else {
		$("#alerte-" + id).fadeOut();
	}
}