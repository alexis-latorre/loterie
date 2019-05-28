<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Résultats</title>
  <c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/calendrier.css.jsp" />
  <c:import url="/WEB-INF/css/resultats.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body>
	<div class="container-fluid">
		<c:import url="/WEB-INF/commun/header.jsp" />
		<c:import url="/WEB-INF/public/dernierTirage.jsp" />
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					Répartition des gains
				</h3>
			</div>
			<div class="panel-body">
				<table id="table_gagnants" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th id="header_role">Rang</th> 
							<th id="header_role">Condition</th> 
							<th id="header_role" style="text-align: right">Gains</th> 
							<th id="header_role" style="text-align: right">Nombres de gagnants</th> 
						</tr>
					</thead>
					<tbody>
						<tr><td>Rang 1</td>	<td>5 numéros + 2 étoiles</td>	<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang1}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang1}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 2</td>	<td>5 numéros + 1 étoile</td>	<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang2}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang2}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 3</td>	<td>5 numéros</td>				<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang3}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang3}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 4</td>	<td>4 numéros + 2 étoiles</td>	<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang4}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang4}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 5</td>	<td>4 numéros + 1 étoile</td>	<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang5}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang5}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 6</td>	<td>3 numéros + 2 étoiles</td>	<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang6}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang6}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 7</td>	<td>4 numéros</td>				<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang7}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang7}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 8</td>	<td>2 numéros + 2 étoiles</td>	<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang8}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang8}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 9</td>	<td>3 numéros + 1 étoile</td>	<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang9}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang9}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 10</td><td>3 numéros</td>				<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang10}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang10}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 11</td><td>1 numéro + 2 étoiles</td>	<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang11}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang11}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 12</td><td>2 numéros + 1 étoile</td>	<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang12}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang12}" type="number"></fmt:formatNumber></td></tr>
						<tr><td>Rang 13</td><td>2 numéros</td>				<td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${r.dernier.gainsRang13}" type="currency"></fmt:formatNumber></td>	<td style="text-align: right"><fmt:formatNumber value="${r.dernier.rang13}" type="number"></fmt:formatNumber></td></tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="panel panel-default" id="historique">
			<div class="panel-heading">
				<h3 class="panel-title">
					Historique
				</h3>
			</div>
			<div class="panel-body">
			<c:set var="idPage" value="1" />
			<c:if test="${r.idPage > 0}">
				<c:set var="idPage" value="${r.idPage}" />
			</c:if>
			<c:set var="idPrecedent" value="${idPage - 1}" />
			<c:if test="${idPrecedent < 1}">
				<c:set var="idPrecedent" value="1" />
			</c:if>
			<c:set var="idSuivant" value="${idPage + 1}" />
			<c:set var="i" value="1" />
			<c:set var="premierHisto" value="${(idPage - 1) * 10 + 1}" />
			<c:set var="dernierHisto" value="${(idPage - 1) * 10 + 10}" />		
			<p class="pagination">
				<a href="<c:url value="/resultats"><c:param name="p" value="1" /></c:url>#historique"><span class="glyphicon glyphicon-step-backward"></span></a>
				<a href="<c:url value="/resultats"><c:param name="p" value="${idPrecedent}" /></c:url>#historique"><span class="glyphicon glyphicon-triangle-left"></span></a>
				<span>Page <c:out value="${idPage} / ${r.nbPages}" /></span>
				<a href="<c:url value="/resultats"><c:param name="p" value="${idSuivant}" /></c:url>#historique"><span class="glyphicon glyphicon-triangle-right"></span></a>
				<a href="<c:url value="/resultats"><c:param name="p" value="${r.nbPages}" /></c:url>#historique"><span class="glyphicon glyphicon-step-forward"></span></a>
			</p>			
			<c:forEach items="${r.historique}" var="resultat">
			<c:if test="${i >= premierHisto and i <= dernierHisto}">
			<div id="histo-${resultat.id}" class="well resultatsWell">
				<h5 class="main-text-blue pointer">Résultats du <c:out value="${resultat.date}" />
					<c:if test="${not empty resultat.gains}">
					<c:if test="${resultat.gains > 0}">
					<span class="success text-success" style="margin-left: 2em"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${resultat.gains}" type="currency"></fmt:formatNumber></span>
					</c:if>
					</c:if>
				<span class="monospace detailHistorique">[<span id="detailHisto-${resultat.id}" class="monospace">+</span>] </span>
				</h5>
				<div id="r-${resultat.id}" style="display: none;">
					<p><span class="boule"><c:out value="${resultat.b1}" /></span>
					<span class="boule"><c:out value="${resultat.b2}" /></span>
					<span class="boule"><c:out value="${resultat.b3}" /></span>
					<span class="boule"><c:out value="${resultat.b4}" /></span>
					<span class="boule"><c:out value="${resultat.b5}" /></span>
					<span class="etoile"><c:out value="${resultat.e1}" /></span>
					<span class="etoile"><c:out value="${resultat.e2}" /></span></p>
					<p class="mymillion">Code<c:if test="${fn:length(resultat.myMillion) > 9}">s</c:if>
				 MyMillion : <span class="code"><c:out value="${resultat.myMillion}"></c:out></span></p>
				</div>
			</div>
			</c:if>
			<c:set var="i" value="${i + 1}" />
			</c:forEach>		
			<p class="pagination">
				<a href="<c:url value="/resultats"><c:param name="p" value="1" /></c:url>#historique"><span class="glyphicon glyphicon-step-backward"></span></a>
				<a href="<c:url value="/resultats"><c:param name="p" value="${idPrecedent}" /></c:url>#historique"><span class="glyphicon glyphicon-triangle-left"></span></a>
				<span>Page <c:out value="${idPage} / ${r.nbPages}" /></span>
				<a href="<c:url value="/resultats"><c:param name="p" value="${idSuivant}" /></c:url>#historique"><span class="glyphicon glyphicon-triangle-right"></span></a>
				<a href="<c:url value="/resultats"><c:param name="p" value="${r.nbPages}" /></c:url>#historique"><span class="glyphicon glyphicon-step-forward"></span></a>
			</p>
		</div>
	</div>
	<c:import url="/WEB-INF/commun/footer.jsp" />
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
	<c:import url="/WEB-INF/js/resultats.js.jsp" />
	<script type="text/javascript">
		<c:forEach items="${r.historique}" var="resultat">
			$("#histo-${resultat.id}").on("click", function() {
				afficherHistorique('${resultat.id}');
			});
		</c:forEach>
	</script>
</body>
</html>