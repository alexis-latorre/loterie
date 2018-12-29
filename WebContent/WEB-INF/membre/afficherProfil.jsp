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
		<form role="form" data-toggle="validator" id="formSubmit" class="form-inline" accept-charset="ISO-8859-1" action="<c:url value="/membre/modifierInformation"></c:url>" method="post">
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
										<td class="col-md-3">Nom : </td>
										<c:choose>
											<c:when test='${utilisateur.checkPrivilege("utilisateur-prop-modifier-nom")}'>
											<td id="nom" class="modifiable col-md-9">
												<span class="placeholder" id="valeurInitiale-nom"><c:out value="${utilisateur.nom}" /></span>
												<span id="inputContainer-nom" style="display: none">
													<input class="form-control" type="text" id="input-nom" name="nom" value="<c:out value="${utilisateur.nom}" />" />
													<span id="confirmer-nom" class="btn btn-s btn-primary btn-confirm">Confirmer</span>
													<span id="cancel-nom" class="btn btn-xs btn-danger btn-cancel">Annuler</span>
												</span>
												<span class="btn-edit-container">
													<span id="iconeEdition-nom" class="btn-edit glyphicon glyphicon-pencil"></span>
												</span>
											</td>
											</c:when>
											<c:otherwise>
											<td class="col-md-9">
												<c:out value="${utilisateur.nom}" />
											</td>
											</c:otherwise>
										</c:choose>
									</tr>
									<tr>
										<td class="col-md-3">Prénom : </td>
										<c:choose>
											<c:when test='${utilisateur.checkPrivilege("utilisateur-prop-modifier-prenom")}'>
											<td id="prenom" class="modifiable col-md-9">
												<span class="placeholder" id="valeurInitiale-prenom"><c:out value="${utilisateur.prenom}" /></span>
												<span id="inputContainer-prenom" style="display: none">
													<input class="form-control" type="text" id="input-prenom" name="prenom" value="<c:out value="${utilisateur.prenom}" />" />
													<span id="confirmer-prenom" class="btn btn-s btn-primary btn-confirm">Confirmer</span>
													<span id="cancel-prenom" class="btn btn-xs btn-danger btn-cancel">Annuler</span>
												</span>
												<span class="btn-edit-container">
													<span id="iconeEdition-prenom" class="btn-edit glyphicon glyphicon-pencil"></span>
												</span>
											</td>
											</c:when>
											<c:otherwise>
											<td class="col-md-9">
												<c:out value="${utilisateur.prenom}" />
											</td>
											</c:otherwise>
										</c:choose>
									</tr>
									<tr>
										<td class="col-md-3">E-mail : </td>
										<c:choose>
											<c:when test='${utilisateur.checkPrivilege("utilisateur-prop-modifier-email")}'>
											<td id="email" class="modifiable col-md-9">
												<span class="placeholder" id="valeurInitiale-email"><c:out value="${utilisateur.email}" /></span>
												<span id="inputContainer-email" style="display: none" >
													<input class="form-control" type="text" id="input-email" name="email" value="<c:out value="${utilisateur.email}" />" />
													<span id="confirmer-email" class="btn btn-s btn-primary btn-confirm">Confirmer</span>
													<span id="cancel-email" class="btn btn-xs btn-danger btn-cancel">Annuler</span>
												</span>
												<span class="btn-edit-container">
													<span id="iconeEdition-email" class="btn-edit glyphicon glyphicon-pencil"></span>
												</span>
											</td>
											</c:when>
											<c:otherwise>
											<td class="col-md-9">
												<c:out value="${utilisateur.email}" />
											</td>
											</c:otherwise>
										</c:choose>
									</tr>
									<c:if test='${utilisateur.checkPrivilege("utilisateur-prop-modifier-mot_de_passe")}'>
									<tr>
										<td class="col-md-3">Mot de passe : </td>
										<td id="motDePasse" class="col-md-9">
											<input class="form-control" type="password" id="input-motDePasse" name="motDePasse" value="" />
										</td>
									</tr>
									<tr>
										<td class="col-md-3">Confirmation du mot de passe : </td>
										<td id="motDePasse" class="col-md-9">
											<input class="form-control" type="password" name="motDePasseConfirmation" id="input-motDePasseConfirmation" />
										</td>
									</tr>
									</c:if>
									<!--tr>
										<td>Inscrit depuis le  : </td><td><c:out value="" /></td>
									</tr-->
								</tbody>
							</table>
							<br />
							<input class="btn btn-blue" type="submit" value="Mettre à jour mes informations" />
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</form>
	</div>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
	<c:import url="/WEB-INF/js/form.js.jsp" />
	<c:import url="/WEB-INF/js/validator.js.jsp" />
</body>
</html>