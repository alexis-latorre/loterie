<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Détails des utilisateurs</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/commun/header.jsp" />
	
		<h3>Administration - gérer l'utilisateur '<c:out value="${joueur.u.pseudo}"></c:out>'</h3>
		<div class="panel panel-default" style="width: 50%">
	  		<div class="panel-heading">
				<h4>Carte de visite</h4>
			</div>
			<div class="panel-body">
				<table class="table table-bordered table-striped">
					<tbody>
						<tr>
							<td>Pseudo : </td><td><c:out value="${joueur.u.pseudo}" /></td>
						</tr>
						<tr>
							<td>Nom : </td><td><c:out value="${joueur.u.nom}" /></td>
						</tr>
						<tr>
							<td>Prénom : </td><td><c:out value="${joueur.u.prenom}" /></td>
						</tr>
						<tr>
							<td>E-mail : </td>
							<td><c:if test="${not empty joueur.u.email}">
								<a href="mailto:<c:out value="${joueur.u.email}" />"><c:out value="${joueur.u.email}" /></a>
							</c:if></td>
						</tr>
						<tr>
							<td>Inscrit depuis le  : </td><td><c:out value="" /></td>
						</tr>
					</tbody>
				</table>
				<p><span class="label label-${joueur.classRole}"><c:out value="${joueur.u.nomRole}" /></span></p>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
	<c:import url="/WEB-INF/js/tablesorter.js.jsp" />
	<script type="text/javascript">
		var table = $('#table_detailsUtilisateurs');
		$('#header_role, #header_nom, #header_pseudo, #header_fonds')
			.wrapInner('<span title="Trier"/>').each(function() {
				sortCol($(this), table);
			});
	</script>
</body>
</html>