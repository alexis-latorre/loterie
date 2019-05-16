<c:if test="${not empty alertes}">
<c:set var="nbNotifs" value="${fn:length(alertes)}" />
<div class="panel panel-default" id="notificationsFrame">
	<div class="panel-heading">
		<h3 class="panel-title">
			Notifications <span id="nbNotifs" class="badge text-danger">${nbNotifs}</span>
			<a href="#" style="float: right" onclick="fermerNotifications()">fermer tout</a>
		</h3>
	</div>
	<div class="panel-body" id="notifications">
	<c:forEach items="${alertes}" var="alerte">
		<c:set var="classeAlerte" value="primary" />
		<c:choose>
			<c:when test="${alerte.a.type == 'I'}">
				<c:set var="classeAlerte" value="info" />
			</c:when>
			<c:when test="${alerte.a.type == 'Q'}">
				<c:set var="classeAlerte" value="primary" />
			</c:when>
			<c:when test="${alerte.a.type == 'W'}">
				<c:set var="classeAlerte" value="warning" />
			</c:when>
			<c:when test="${alerte.a.type == 'E'}">
				<c:set var="classeAlerte" value="danger" />
			</c:when>
		</c:choose>
		<div class="alert alert-${classeAlerte}" id="alerte-${alerte.a.id}">
			${alerte.message}
			<a class="alert-link" href="#" style="float: right" onclick="fermerNotification(${alerte.a.id})">X</a>
		</div>
	</c:forEach>
	<script type="text/javascript">
		$(document).ready(function() {
			setNbNotifications(${nbNotifs});
		});
	</script>
	</div>
</div>
</c:if>