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
		<form id="formSubmit" class="form-inline" accept-charset="ISO-8859-1" action="<c:url value="/membre/modifierInformation"></c:url>" method="post">
			<div class="col-md-12">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="panel panel-default">
				  		<div class="panel-heading">
							<span class="main-text-blue" style="font-size: 20px; font-weight: bold;">Carte de visite</span>
							<br />${utilisateur.checkPrivilege("change_own_name")}
						</div>
						<div class="panel-body">
							<table class="table table-bordered table-striped">
								<tbody>
									<tr>
										<td>Pseudo : </td><td><c:out value="${utilisateur.pseudo}" /></td>
									</tr>
									<tr>
										<td class="col-md-3">Nom : </td>
										<td id="nom" class="modifiable col-md-9">
											<span class="placeholder" id="valeurInitiale-nom"><c:out value="${utilisateur.nom}" /></span>
											<span id="inputContainer-nom" style="display: none">
												<input class="form-control" type="text" id="input-nom" name="nom" value="<c:out value="${utilisateur.nom}" />" />
												<span id="confirmer-nom" class="btn btn-s btn-primary btn-confirm">Confirmer</span>
												<span id="cancel-nom" class="btn btn-xs btn-danger btn-cancel">Annuler</span>
											</span>
											<span class="btn-edit-container">
												<span id="iconeEdition-nom" style="display: none" class="btn-edit glyphicon glyphicon-pencil"></span>
											</span>
										</td>
									</tr>
									<tr>
										<td class="col-md-3">Prénom : </td>
										<td id="prenom" class="modifiable col-md-9">
											<span class="placeholder" id="valeurInitiale-prenom"><c:out value="${utilisateur.prenom}" /></span>
											<span id="inputContainer-prenom" style="display: none">
												<input class="form-control" type="text" id="input-prenom" name="prenom" value="<c:out value="${utilisateur.prenom}" />" />
												<span id="confirmer-prenom" class="btn btn-s btn-primary btn-confirm">Confirmer</span>
												<span id="cancel-prenom" class="btn btn-xs btn-danger btn-cancel">Annuler</span>
											</span>
											<span class="btn-edit-container">
												<span id="iconeEdition-prenom" style="display: none" class="btn-edit glyphicon glyphicon-pencil"></span>
											</span>
										</td>
									</tr>
									<tr>
										<td class="col-md-3">E-mail : </td>
										<td id="email" class="modifiable col-md-9">
											<span class="placeholder" id="valeurInitiale-email"><c:out value="${utilisateur.email}" /></span>
											<span id="inputContainer-email" style="display: none" >
												<input class="form-control" type="text" id="input-email" name="email" value="<c:out value="${utilisateur.email}" />" />
												<span id="confirmer-email" class="btn btn-s btn-primary btn-confirm">Confirmer</span>
												<span id="cancel-email" class="btn btn-xs btn-danger btn-cancel">Annuler</span>
											</span>
											<span class="btn-edit-container">
												<span id="iconeEdition-email" style="display: none" class="btn-edit glyphicon glyphicon-pencil"></span>
											</span>
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