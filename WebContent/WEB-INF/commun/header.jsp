<h1>MyLoterie</h1>
<ul class="nav nav-pills nav-justified">
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/accueil" />">Accueil</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/resultats" />">Résultats</a>
	</li>
	<c:if test="${loggedIn == true}">
		<li class="nav-item">
			<a class="nav-link" href="<c:url value="/membre/profil" />">Mon profil</a>
		</li>
		<!--li class="nav-item">
		    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Membre<span class="caret"></span></a>
		    <ul class="dropdown-menu">
				<!--li class="dropdown-item"><a href="<c:url value="/membre/profil" />">Mon profil</a></li->
				<li class="dropdown-item"><a href="<c:url value="/membre/afficherGrilles" />">Afficher mes grilles</a></li>
				<c:if test="${utilisateur.estAdministrateur() or utilisateur.estModerateur()}">
				<li class="dropdown-item"><a href="<c:url value="/membre/creerGrille" />">Créer une grille</a></li>
				</c:if>
				<!--li class="dropdown-item"><a href="<c:url value="/membre/monPortefeuille" />">Accéder à mon portefeuille</a></li->
			</ul>
		</li-->
		<c:if test="${utilisateur.estAdministrateur() or utilisateur.estModerateur()}">
		<li class="nav-item">
		    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Administration<span class="caret"></span></a>
		    <ul class="dropdown-menu">
				<li class="dropdown-item"><a href="<c:url value="/admin/crediterJoueur" />">Créditer un joueur</a></li>
				<li class="dropdown-item"><a href="<c:url value="/admin/detailsUtilisateurs" />">Afficher les utilisateurs</a></li>
				<li class="dropdown-item"><a href="<c:url value="/admin/logs" />">Consulter les logs</a></li>
			</ul>
		</li>
		</c:if>
	</c:if>
	<c:choose>
		<c:when test="${not loggedIn}">
		<li class="nav-item">
			<a class="nav-link" href="<c:url value="/inscription" />">S'inscrire</a>
		</li>
		</c:when>
		<c:otherwise>
		<li class="nav-item">
			<a class="nav-link" href="<c:url value="/deconnexion" />">Se déconnecter</a>
		</li>
		</c:otherwise>
	</c:choose>
</ul>
<hr class="header-hr" />
<c:if test="${not empty messageSucces}">
<div id="popupSucces" class="popupSucces text-success">
	<c:out value="${messageSucces}" />
	<c:import url="/WEB-INF/js/popup.js.jsp" />
</div>
</c:if>
<c:if test="${not empty messageEchec}">
<div id="popupEchec" class="popupEchec text-danger">
	<c:out value="${messageEchec}" />
	<c:import url="/WEB-INF/js/popupError.js.jsp" />
</div>
</c:if>