<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  	<title>Loterie | Profil</title>
	<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  	<c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body>
	<div class="container-fluid">
		<c:import url="/WEB-INF/commun/header.jsp" />
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					Informations de profil
				</h3>
			</div>
			<div class="panel-body">
				<form role="form" data-toggle="validator" id="formSubmit" accept-charset="ISO-8859-1" action="<c:url value="/membre/modifierInformation"></c:url>" method="post">
					<div class="form-group">
						<label for="input-pseudo">Pseudo</label>
						<input readonly type="text" class="form-control" id="input-pseudo" placeholder="<c:out value="${utilisateur.pseudo}" />" />
					</div>
					<div class="form-group">
						<label for="input-nom">Nom</label>
						<c:choose>
						<c:when test='${utilisateur.checkPrivilege("utilisateur-prop-modifier-nom")}'>
							<input type="text" class="form-control" name="nom" id="input-nom" value="<c:out value="${utilisateur.nom}" />" />
						</c:when>
						<c:otherwise>
							<c:out value="${utilisateur.nom}" />
						</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="input-prenom">Prénom</label>
						<c:choose>
						<c:when test='${utilisateur.checkPrivilege("utilisateur-prop-modifier-prenom")}'>
							<input type="text" class="form-control" name="prenom" id="input-prenom" value="<c:out value="${utilisateur.prenom}" />" />
						</c:when>
						<c:otherwise>
							<c:out value="${utilisateur.prenom}" />
						</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="input-email">E-mail</label>
						<c:choose>
						<c:when test='${utilisateur.checkPrivilege("utilisateur-prop-modifier-email")}'>
							<input type="email" class="form-control" name="email" id="input-email" value="<c:out value="${utilisateur.email}" />" />
						</c:when>
						<c:otherwise>
							<c:out value="${utilisateur.email}" />
						</c:otherwise>
						</c:choose>
					</div>
					<c:if test='${utilisateur.checkPrivilege("utilisateur-prop-modifier-mot_de_passe")}'>
					<div class="form-group">
						<label for="input-motDePasse">Mot de passe</label>
						<input type="password" class="form-control" name="motDePasse" id="input-motDePasse" value="" />
						<p id="erreursMDP"></p>
					</div>
					<div class="form-group">
						<label for="input-motDePasseConfirmation">Confirmation du mot de passe</label>
						<input type="password" class="form-control" name="motDePasseConfirmation" id="input-motDePasseConfirmation" value="" />
						<p id="erreursConfirmationMDP"></p>
					</div>
					</c:if>
					<br />
					<input class="btn btn-primary" type="submit" value="Mettre à jour" />
				</form>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/commun/footer.jsp" />
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
	<c:import url="/WEB-INF/js/form.js.jsp" />
	<c:import url="/WEB-INF/js/validator.js.jsp" />
</body>
</html>