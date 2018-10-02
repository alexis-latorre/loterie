<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Logs</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
</head>
<body class="container">
	<c:import url="/WEB-INF/commun/header.jsp" />

	<h3>Administration - Consulter les logs</h3>
	<!--span class="label label-${joueur.classRole}"><c:out value="${utilisateur.nomRole}" /></span-->
	<table id="table_logs" class="table table-bordered table-striped">
		<thead>
			<tr>
				<th id="header_utc">UTC</th>
				<th id="header_date">Date</th>
				<th id="header_heure">Heure</th>
				<th id="header_niveau">Niveau</th>
				<th id="header_type">Type</th>
				<th id="header_message">Message</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${logs}" var="log">
			<c:set var="classe" value="text-normal" />
			<c:choose>
				<c:when test="${log.l.niveau == 'A'}">
					<c:set var="classe" value="warning text-warning" />
				</c:when>
				<c:when test="${log.l.niveau == 'E' or log.l.niveau == 'S' or log.l.niveau == 'F'}">
					<c:set var="classe" value="danger text-danger" />
				</c:when>
			</c:choose>
			<tr>
				<td class="${classe}"><c:out value="${log.l.utc}" /></td>
				<td class="${classe}"><c:out value="${log.l.jour}" /></td>
				<td class="${classe}"><c:out value="${log.l.heure}" /></td>
				<td class="${classe}"><c:out value="${log.l.niveau}" /></td>
				<td class="${classe}"><c:out value="${log.l.type}" /></td>
				<td class="${classe}">
					${log.message}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
	<c:import url="/WEB-INF/js/tablesorter.js.jsp" />
	<script type="text/javascript">
		var table = $('#table_logs');
		$('#header_utc, #header_date, #header_heure, #header_niveau, #header_type')
			.wrapInner('<span title="Trier"/>').each(function() {
				sortCol($(this), table);
			});
	</script>
</body>
</html>