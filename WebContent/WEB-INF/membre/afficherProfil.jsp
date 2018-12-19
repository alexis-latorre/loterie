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
		<h4>Mon profil</h4>
		<form class="form-inline" action="<c:url value="/membre/modifierInformation"><c:param name="id" value="${utilisateur.id}"></c:param></c:url>" method="post">
			<div class="col-md-12">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="panel panel-default">
				  		<div class="panel-heading">
							<span class="main-text-blue" style="font-size: 20px; font-weight: bold;">Carte de visite</span>
						</div>
						<div class="panel-body">
							<table class="table table-bordered table-striped">
								<tbody>
									<tr>
										<td>Pseudo : </td><td><c:out value="${utilisateur.pseudo}" /></td>
									</tr>
									<tr>
										<td>Nom : </td><td id="nom" class="modifiable"><span id="placeholder-nom"><c:out value="${utilisateur.nom}" /></span><span class="btn-edit-container"><span style="display: none" class="btn-edit glyphicon glyphicon-pencil"></span></span></td>
									</tr>
									<tr>
										<td>Prénom : </td><td id="prenom" class="modifiable"><span id="placeholder-prenom"><c:out value="${utilisateur.prenom}" /></span><span class="btn-edit-container"><span style="display: none" class="btn-edit glyphicon glyphicon-pencil"></span></span></td>
									</tr>
									<tr>
										<td>E-mail : </td><td id="email" class="modifiable"><span id="placeholder-email"><c:out value="${utilisateur.email}" /></span><span class="btn-edit-container"><span style="display: none" class="btn-edit glyphicon glyphicon-pencil"></span></span></td>
									</tr>
									<tr>
										<td>Inscrit depuis le  : </td><td><c:out value="" /></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</form>
	</div>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
	<c:import url="/WEB-INF/js/form.js.jsp" />
</body>
</html>