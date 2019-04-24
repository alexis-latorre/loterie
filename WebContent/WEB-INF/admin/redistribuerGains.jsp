<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Gains</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/euromillions.css.jsp" />
</head>
<body>
	<div class="container-fluid">
		<c:import url="/WEB-INF/commun/header.jsp" />
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					Liste des gains à redistribuer
				</h3>
			</div>
			<div class="panel-body">
				<c:set var="id" value="0" />
				<c:forEach items="${gains}" var="gain">
				<c:set var="id" value="${id + 1}" />
				<div class="well">
					<h4>Gains du ${gain.date}</h4>
					<div class="row">
						<div class="col-md-4">
						</div>
						<div class="col-md-4 col-xs-12">
							Montant total : <span id="montantTotal-${id}" class="montantTotal"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${gain.jour.gains}" /></span> €
						</div>
						<div class="col-md-4">
						</div>
					</div>
					<form action="<c:url value="/admin/redistribuerGains" />" method="post" onsubmit="return popupConfirmFundsChange(this);">
						<div class="row">
							<div class="col-md-1">
							</div>
							<div class="col-md-4 col-xs-12">
								<div class="form-group">
									<label for="repartitionJoueurs-${id}">Montant à répartir entre les joueurs :</label>
		    						<div class="input-group">
										<input id="repartitionJoueurs-${id}" type="number" lang="en" lang="fr" step="${gain.jour.nbJoueurs * 0.01}"  value="${gain.jour.gains}" min="0" max="${gain.jour.gains}" class="form-control" onchange="update(${id}, 0)" />
	      								<div class="input-group-addon">&euro;</div>
	      							</div>
								</div>
							</div>
							<div class="col-md-2 col-xs-1">
							</div>
							<div class="col-md-4 col-xs-12">
								<div class="form-group">
									<label for="repartitionBanque-${id}">Montant à envoyer en banque :</label>
		    						<div class="input-group">
										<input id="repartitionBanque-${id}" type="number" lang="en" lang="fr" step="${gain.jour.nbJoueurs * 0.01}" value="0" min="0" max="${gain.jour.gains}" class="form-control" onchange="update(${id}, 1)" />
		      							<div class="input-group-addon">&euro;</div>
	      							</div>
								</div>
							</div>
							<div class="col-md-1">
							</div>
						</div>
						<div class="row">
							<div class="col-md-1">
							</div>
							<div class="col-md-10 col-xs-12">
								<input type="submit" class="btn btn-primary" value="Distribuer" />
							</div>
							<div class="col-md-1">
							</div>
						</div>
					</form>
				</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/commun/footer.jsp" />
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
	<script type="text/javascript">
		/*var montantTotal = 0;
		
		$(document).ready(function() {
			montantTotal = $(".montantTotal").text().replace('.', ',');
		});*/
	
		update = function(id, e) {
			montantTotalStr = $("#montantTotal-" + id).text().replace(',', '.');
			montantTotalEntier = montantTotalStr;
			montantTotalDec = "00";
			
			if (montantTotalStr.includes(".")) {
				montantTotalParts = (montantTotalStr + "00").split('.');
				montantTotalEntier = montantTotalParts[0];
				montantTotalDec = montantTotalParts[1].substring(0, 2);
			}
			montantTotal = montantTotalEntier + "" + montantTotalDec;
			
			repartitionBanqueStr = $("#repartitionBanque-" + id).val();
			repartitionBanqueDec = "00";
			repartitionBanqueEntier = repartitionBanqueStr;
			
			if (repartitionBanqueStr.includes(".")) {
				repartitionBanqueParts = (repartitionBanqueStr + "00").split('.');
				repartitionBanqueEntier = repartitionBanqueParts[0];
				repartitionBanqueDec = repartitionBanqueParts[1].substring(0, 2);
			}
			repartitionBanque = repartitionBanqueEntier + "" + repartitionBanqueDec;
			
			repartitionJoueursStr = $("#repartitionJoueurs-" + id).val();
			repartitionJoueursDec = "00";
			repartitionJoueursEntier = repartitionJoueursStr;
			
			if (repartitionJoueursStr.includes(".")) {
				repartitionJoueursParts = (repartitionJoueursStr + "00").split('.');
				repartitionJoueursEntier = repartitionJoueursParts[0];
				repartitionJoueursDec = repartitionJoueursParts[1].substring(0, 2);
			}
			repartitionJoueurs = repartitionJoueursEntier + "" + repartitionJoueursDec;
			
			newRepartitionBanque = montantTotal - repartitionJoueurs;
			newRepartitionJoueurs = montantTotal - repartitionBanque;
			/*alert("(" + newRepartitionBanque + " > " + montantTotal + " || " + newRepartitionJoueurs + " < 0) -> " + (newRepartitionBanque > montantTotal || newRepartitionJoueurs < 0) +
			"\r\n(" + newRepartitionJoueurs + " > " + montantTotal + " || " + newRepartitionBanque + " < 0) -> " + (newRepartitionJoueurs > montantTotal || newRepartitionBanque < 0));*/
			if (newRepartitionBanque > montantTotal || newRepartitionJoueurs < 0) {
				newRepartitionBanque = montantTotal;
				newRepartitionJoueurs = 0;
				$("#repartitionJoueurs-" + id).val(0);
				$("#repartitionBanque-" + id).val(montantTotal / 100);
			}

			if (newRepartitionJoueurs > montantTotal || newRepartitionBanque < 0) {
				newRepartitionJoueurs = montantTotal;
				newRepartitionBanque = 0;
				$("#repartitionBanque-" + id).val(0);
				$("#repartitionJoueurs-" + id).val(montantTotal / 100);
			}
			
			switch (e) {
				case 0:
					$("#repartitionBanque-" + id).val(newRepartitionBanque / 100);
					break;
				case 1:
					$("#repartitionJoueurs-" + id).val(newRepartitionJoueurs / 100);
					break;
			}
		}
	</script>
</body>
</html>