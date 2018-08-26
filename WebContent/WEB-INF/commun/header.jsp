<h1>MyLoterie</h1>
<ul class="nav nav-fill">
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/accueil" />">Accueil</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="<c:url value="/resultats" />">Résultats</a>
	</li>
	<c:if test="${loggedIn == true}">
		<li class="nav-item">
		    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Membre</a>
		    <div class="dropdown-menu">
				<a class="dropdown-item" href="<c:url value="/membre/profil" />">Mon profil</a>
				<a class="dropdown-item" href="<c:url value="/membre/afficherGrilles" />">Afficher mes grilles</a>
				<a class="dropdown-item" href="<c:url value="/membre/creerGrille" />">Créer une grille</a>
				<a class="dropdown-item" href="<c:url value="/membre/monPortefeuille" />">Accéder à mon portefeuille</a>
			</div>
		</li>
		<c:if test="${utilisateur.niveau <= 0}">
		<li class="nav-item">
			<a class="nav-link" href="<c:url value="/admin" />">Administration</a>
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