<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="form-group">
	<label for="input-nom">Nom de la grille</label>
	<c:set var="nomDisabled" value=' disabled' />
	<c:if test='${utilisateur.checkPrivilege("grille-modifier-nom")}'>
		<c:set var="nomDisabled" value='' />
	</c:if>
	<input class="form-control"${nomDisabled} type="text" name="nom" id="input-nom" value="${requestScope.grille.nom}" />
</div>
<div class="form-group">
	<label>Choisir les numéros</label>
</div>
<table class="table">
<c:forEach items="${tableNumeros}" var="ligneNumeros">
	<tr>		
	<c:forEach items="${ligneNumeros}" var="numero">
		<td>
			<c:set var="checked" value=""></c:set>
			<c:if test="${not empty requestScope.grille}">
				<c:forEach items="${requestScope.grille.numeros}" var="numeroGrille">
					<c:if test="${numero == numeroGrille}">
						<c:set var="checked" value='checked="checked"'></c:set>
					</c:if>
				</c:forEach>
			</c:if>
			<div class="checkbox-boule-container" id="cbNumeros">
				<label>
					<c:set var="numerosDisabled" value=' disabled' />
					<c:if test='${utilisateur.checkPrivilege("grille-modifier-numeros")}'>
						<c:set var="numerosDisabled" value='' />
					</c:if>
					<input type="checkbox"${numerosDisabled} name="numeros[]" id="input-numero${numero}" value="${numero}" <c:out value="${checked}" /> />
					<span class="checkmark-boule"></span>
					<span class="numero${numerosDisabled}"><c:out value="${numero}" /></span>
				</label>
			</div>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>
<div class="form-group">
	<label>Choisir les étoiles</label>
</div>
<table class="table">
<c:forEach items="${tableEtoiles}" var="ligneEtoiles">
	<tr>		
	<c:forEach items="${ligneEtoiles}" var="etoile">
		<td>
			<c:set var="checked" value=""></c:set>
			<c:if test="${not empty requestScope.grille}">
				<c:forEach items="${requestScope.grille.etoiles}" var="etoileGrille">
					<c:if test="${etoile == etoileGrille}">
						<c:set var="checked" value='checked="checked"'></c:set>
					</c:if>
				</c:forEach>
			</c:if>
			<div class="checkbox-etoile-container" id="cbEtoiles">
				<label>
					<c:set var="etoilesDisabled" value=' disabled' />
					<c:if test='${utilisateur.checkPrivilege("grille-modifier-etoiles")}'>
						<c:set var="etoilesDisabled" value='' />
					</c:if>
					<input type="checkbox"${etoilesDisabled} name="etoiles[]" id="input-etoile${etoile}" value="${etoile}" <c:out value="${checked}" /> />
					<span class="checkmark-etoile"></span>
					<span class="numero${etoilesDisabled}"><c:out value="${etoile}" /></span>
				</label>
			</div>
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>
<c:set var="etoilePlus" value=""></c:set>
<c:set var="etoilePlusDisabled" value=' disabled' />
<c:if test='${utilisateur.checkPrivilege("grille-modifier-etoile_plus")}'>
	<c:set var="etoilePlusDisabled" value='' />
</c:if>
<c:if test="${requestScope.grille.etoilePlus}">
	<c:set var="etoilePlus" value='checked="checked"'></c:set>
</c:if>
<div class="checkbox">
	<label>
		<input type="checkbox"${etoilePlusDisabled} name="etoilePlus" id="input-etoilePlus" <c:out value="${etoilePlus}" /> />Étoile +
	</label>
	<span> (+<span id="prixEtoilePlus">1</span> &euro;)</span>
</div>
<p>Prix d'un tirage : <span id="prixTirage">0,00</span> &euro;</p>
<div class="form-group">
	<label for="input-mymillion">Code MyMillion</label>
	<c:set var="myMillionDisabled" value=' disabled' />
	<c:if test='${utilisateur.checkPrivilege("grille-modifier-mymillion")}'>
		<c:set var="myMillionDisabled" value='' />
	</c:if>
	<input type="text"${myMillionDisabled} class="form-control" name="myMillion" id="input-mymillion" value="${requestScope.grille.myMillion}" />
</div>
<script type="text/javascript">
$(document).ready(function() {
	var limitesNumeros = [];
	var limitesEtoiles = [];
	var prixTirage = [];
	var prixEtoilePlus = [];
	<c:if test="${not empty limitesNumeros}">
		<c:forEach items="${limitesNumeros}" var="limite"> 
		limitesNumeros[${limite.key}] = '${limite.value}';
		</c:forEach>
	</c:if>
	<c:if test="${not empty limitesEtoiles}">
		<c:forEach items="${limitesEtoiles}" var="limite"> 
		limitesEtoiles[${limite.key}] = '${limite.value}';
		</c:forEach>
	</c:if>
	<c:if test="${not empty tirage}">
		<c:forEach items="${tirage}" var="item"> 
		prixTirage['${item.key}'] = '${item.value}';
		</c:forEach>
	</c:if>
	<c:if test="${not empty mapEtoilePlus}">
		<c:forEach items="${mapEtoilePlus}" var="item"> 
		prixEtoilePlus['${item.key}'] = '${item.value}';
		</c:forEach>
	</c:if>
	
	var numeros = $('#cbNumeros label input[type="checkbox"]');	
	var etoiles = $('#cbEtoiles label input[type="checkbox"]');
	var nbNumeros = 0;
	var nbEtoiles = 0;
	var maxNumeros = 10;
	var maxEtoiles = 12;
	var epCheck = $("#input-etoilePlus");
	
	majSelection = function() {
		nbNumeros = numeros.filter(':checked').length;
		nbEtoiles = etoiles.filter(':checked').length;
		
		maxE = limitesNumeros[nbNumeros];
		maxN = limitesEtoiles[nbEtoiles];
		
		if (maxE !== undefined && nbNumeros < maxNumeros) {
			maxEtoiles = maxE;
		} else if (nbNumeros >= maxNumeros) {
			maxEtoiles = maxE;
		}
		
		if (maxN !== undefined && nbEtoiles < maxEtoiles) {
			maxNumeros = maxN;
		} else if (nbEtoiles >= maxEtoiles) {
			maxNumeros = maxN;
		}
			
		if (nbNumeros < maxNumeros) {
			activerNumeros();
		} else {
			desactiverNumeros();
		}
		
		if (nbEtoiles < maxEtoiles) {
			activerEtoiles();
		} else {
			desactiverEtoiles();
		}
		
		tirage = prixTirage["prix_tirage_" + nbNumeros + "_" + nbEtoiles];
		
		if (tirage === undefined) {
			tirage = "0.00";
		}
		
		ep = prixEtoilePlus["prix_etoile_plus_" + nbNumeros + "_" + nbEtoiles];
		
		if (ep === undefined) {
			ep = "1";
		}
		
		if (epCheck.filter(":checked").length > 0) {
			tirage = parseFloat(tirage) + parseFloat(ep);
		}
		
		$("#prixEtoilePlus").text(ep.replace(".", ","));
		$("#prixTirage").text(tirage.toString().replace(".", ","));
	};
	
	epCheck.change(function () {
		majSelection();
	});
	
	numeros.change(function() {
		majSelection();
	});
	
	etoiles.change(function() {
		majSelection();
	});
	
	activerNumeros = function() {
		numeros.each(function() {
			$(this).removeAttr("disabled");
		});
	}
	
	desactiverNumeros = function() {
		numeros.each(function() {
			if ($(this).filter(':checked').length == 0) {
				$(this).attr("disabled", true);
			}
		});
	}
	
	activerEtoiles = function() {
		etoiles.each(function() {
			$(this).removeAttr("disabled");
		});
	}
	
	desactiverEtoiles = function() {
		etoiles.each(function() {
			if ($(this).filter(':checked').length == 0) {
				$(this).attr("disabled", true);
			}
		});
	}
});
</script>