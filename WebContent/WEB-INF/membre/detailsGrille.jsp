<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Loterie | Grille N° <c:out value="${grille.id}"></c:out></title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body class="container-fluid">
	<c:import url="/WEB-INF/commun/header.jsp" />
	<c:choose>
	<c:when test="${empty erreurs}">
	<div class="col-lg-7">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Résumé de la grille n° <c:out value="${grille.id}" /></h3>
			</div>
			<div class="panel-body">
				<div class="row col-lg-12">
					Nom de la grille : <c:out value="${grille.nom}" /> 
				</div>
				<div class="row">
					<div class="col-lg-5">
						<h3 class="main-text-blue">Numéros</h3>
						<c:forEach items="${grille.numeros}" var="boule">
							<span class="boule"><c:out value="${boule}"></c:out></span>
						</c:forEach>
					</div>
					<div class="col-lg-7">
						<h3 class="main-text-blue">Étoiles</h3>
						<c:forEach items="${grille.etoiles}" var="etoile">
							<span class="etoile"><c:out value="${etoile}"></c:out></span>
						</c:forEach>
					</div>
				</div>
				<br />
				<div class="row col-lg-12">
					<c:if test="${not empty jour.date}">Grille jouée jusqu'au <fmt:formatDate pattern="dd/MM/yyyy" value="${jour.date}" /></c:if>
				</div>
			</div>
		</div>
		<c:if test="${utilisateur.estAdministrateur() or utilisateur.id == grille.utilisateur.id}">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Actions de la grille</h3>
			</div>
			<div class="panel-body">
				<p><a title="Modifier cette grille" href="<c:url value="/membre/editerGrille"><c:param name="id" value="${grille.id}" /></c:url>"><span class="glyphicon glyphicon-edit">&nbsp;</span>Modifier la grille</a></p>
				<p><span class="glyphicon glyphicon-repeat">&nbsp;</span>Jouer la grille</p>
				<c:if test="${utilisateur != null and utilisateur.estModerateur()}">
				<form role="form" data-toggle="validator" action='<c:url value="/membre/jouerGrille" />' method="post">
					<label class="col-xs-12 col-md-2"><input type="radio" name="periode" id="input-periode-1j" value="1j" /> 1 jour</label>
					<label class="col-xs-12 col-md-2"><input type="radio" name="periode" id="input-periode-1s" value="1s" /> 1 semaine</label>
					<label class="col-xs-12 col-md-2"><input type="radio" name="periode" id="input-periode-2s" value="2s" /> 2 semaines</label>
					<label class="col-xs-12 col-md-2"><input type="radio" name="periode" id="input-periode-3s" value="3s" /> 3 semaines</label>
					<label class="col-xs-12 col-md-2"><input type="radio" name="periode" id="input-periode-4s" value="4s" /> 4 semaines</label>
					<label class="col-xs-12 col-md-2"><input type="radio" name="periode" id="input-periode-5s" value="5s" /> 5 semaines</label>
					<c:if test="${not empty erreurs}">
						<c:if test="${not empty erreurs.format}">
							<span class="text-danger">${erreurs.format}</span><br />
						</c:if>
						<c:if test="${not empty erreurs.grille}">
							<span class="text-danger">${erreurs.grille}</span><br />
						</c:if>
					</c:if>
					<div class="form-group row">
						<div class="col-md-12 col-lg-3">
							Grille validée par
						</div>
						<div class="col-md-4 col-lg-4">
							<select name="joueur" id="input-joueur" class="form-control">
								<c:forEach items="${grille.joueurs}" var="joueur">
								<c:set var="selected" value="" />
								<c:choose>
									<c:when test="${joueur.id == utilisateur.id}">
									<c:set var="selected" value=' selected="selected"' />
									</c:when>
									<c:otherwise>
									<c:set var="selected" value='' />
									</c:otherwise>
								</c:choose>
								<option id="input-joueur-${joueur.id}" value="${joueur.id}" ${selected}>${joueur.prenom} ${joueur.nom}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<br />
					<button type="submit" class="btn btn-primary">Jouer</button>
				</form>
				</c:if>
			</div>
		</div>
		</c:if>
	</div>
	<div class="col-lg-5">
		<div class="panel panel-default">
			<div class="panel-heading">
				<c:set var="s" value="" />
				<c:if test="${fn:length(grille.joueurs) > 1}">
					<c:set var="s" value="s" />
				</c:if>
				<h3 class="panel-title">Joueur${s} lié${s} à la grille</h3>
			</div>
			<div class="panel-body">
				<table class="table">
					<tbody>
						<c:forEach items="${grille.joueurs}" var="joueur">
						<c:choose>
							<c:when test="${joueur.portefeuille != null}">
								<c:choose>
								<c:when test="${joueur.portefeuille.fonds >= 0}">
								<c:set var="classeSolde" value="text-success" />
								</c:when>
								<c:otherwise>
								<c:set var="classeSolde" value="danger text-danger" />
								</c:otherwise>
								</c:choose>
								<c:set var="solde" value="${joueur.portefeuille.fonds}" />
							</c:when>
							<c:otherwise>
								<c:set var="solde" value="0" />
							</c:otherwise>
						</c:choose>
						<tr>
							<td class="${classeSolde}">
							<c:choose>
								<c:when test="${utilisateur.estAdministrateur()}">
								<a href="<c:url value="/admin/detailsUtilisateur"><c:param name="id" value="${joueur.id}" /></c:url>">
								<c:out value="${joueur.prenom} ${joueur.nom}" />
								</a>
								</c:when>
								<c:otherwise>
								<c:out value="${joueur.prenom} ${joueur.nom}" />
								</c:otherwise>
							</c:choose>
							</td>
							<td class="${classeSolde}">
								<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${solde}" type="currency"></fmt:formatNumber>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</c:when>
	<c:otherwise>
		<c:if test="${not empty erreurs.grille}">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h3 class="panel-title">Erreur concernant la grille</h3>
			</div>
			<div class="panel-body">
				<c:out value="${erreurs.grille}" />
			</div>
		</div>
		</c:if>
	</c:otherwise>
	</c:choose>
	<c:import url="/WEB-INF/commun/footer.jsp" />
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>