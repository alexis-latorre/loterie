<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Détails des utilisateurs</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
</head>
<body bgcolor="white">
	<c:import url="/WEB-INF/commun/header.jsp" />

	<h3>Administration - gérer les utilisateurs</h3>
	<table id="table_detailsUtilisateurs" class="table table-bordered table-striped align-middle">
		<thead>
			<tr>
				<th id="header_role">Rôle</th>
				<th id="header_nom">Nom</th>
				<th id="header_pseudo">Pseudo</th>
				<th id="header_fonds">Fonds</th>
				<th id="header_grilles">Grilles</th>
				<th id="header_actions">Actions</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${joueurs}" var="joueur">
			<c:set scope="application" var="joueur" value="${joueur}" />
			<c:import url="/WEB-INF/parts/role.jsp"></c:import>
			<c:set var="classeFonds" value="text-normal" />
			<c:choose>
				<c:when test="${joueur.portefeuille.fonds > 0}">
					<c:set var="classeFonds" value="success text-success" />
				</c:when>
				<c:when test="${joueur.portefeuille.fonds < 0}">
					<c:set var="classeFonds" value="danger text-danger" />
				</c:when>
				<c:when test="${joueur.portefeuille.fonds == 0}">
					<c:set var="classeFonds" value="warning text-warning" />
				</c:when>
			</c:choose>
			<tr>
				<td><span class="label label-${role}"><c:out value="${joueur.niveau}: ${joueur.nomRole}" /></span></td>
				<td><c:out value="${joueur.nom} ${joueur.prenom}" /></td>
				<td><c:out value="${joueur.pseudo}" /></td>
				<td class="${classeFonds}"><c:out value="${joueur.portefeuille.fonds}" /></td>
				<td>
				<c:forEach items="${joueur.grilles}" var="grille">
					<c:out value="${grille.nom}" /><br />
				</c:forEach>
				</td>
				<td>
					<a href="<c:url value="/admin/detailsUtilisateur"><c:param name="id" value="${joueur.id}" /></c:url>">Éditer</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
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