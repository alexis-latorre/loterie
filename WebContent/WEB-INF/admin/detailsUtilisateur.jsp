<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Détails des utilisateurs</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body>
	<div class="container-fluid">
		<c:import url="/WEB-INF/commun/header.jsp" />
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					Administration - gérer l'utilisateur '<c:out value="${joueur.u.pseudo}"></c:out>'
				</h3>
			</div>
			<div class="panel-body">
				<p><span class="label label-${joueur.classRole}"><c:out value="${joueur.u.nomRole}" /></span></p>
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
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					Gérer les privilèges de '<c:out value="${joueur.u.pseudo}"></c:out>'
				</h3>
			</div>
			<div class="panel-body">
				<form method="post" action='<c:url value="/admin/modifierPrivileges"></c:url>'>
					<table class="table table-bordered table-striped">
						<tr>
							<th>Nom du privilège</th>
							<th>Tout sélectionner <input type="checkbox" /></th>
						</tr>
						<c:forEach items="${privileges}" var="privilege">
						<tr>
							<th>Table '<c:out value="${privilege.key}" />'</th>
							<th>Tout sélectionner <input type="checkbox" /></th>
						</tr>
						<c:forEach items="${privilege.value}" var="nomPrivilege">
						<tr>
							<td><c:out value="${nomPrivilege}"></c:out></td>
							<c:choose>
							<c:when test="${joueur.u.checkPrivilege(nomPrivilege) == true}">
							<td class="success"><input name="${nomPrivilege}" type="checkbox" checked="checked" /></td>
							</c:when>
							<c:otherwise>
							<td class="danger"><input name="${nomPrivilege}" type="checkbox" /></td>
							</c:otherwise>
							</c:choose>
						</tr>
						</c:forEach>
						</c:forEach>
					</table>
					<input class="btn btn-primary" type="submit" value="Mettre à jour" />
				</form>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/commun/footer.jsp" />
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