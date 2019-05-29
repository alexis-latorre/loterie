<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<nav id="navbarHeader" class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<div class="row">
			<div id="logo" class="col-sm-3 col-md-2">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<c:url value="/accueil" />"><img alt="MyLoterie" src='<c:url value="/inc/img/dollar.png" />' height="24px" /></a>
					<c:if test="${not empty titrePage}">
						<span class="hidden-sm hidden-md hidden-lg navbar-title"><c:import url="/WEB-INF/parts/titre.${titrePage}.jsp" /></span>
					</c:if>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2">
				<div>
					<c:if test="${not empty titrePage}">
						<span class="hidden-xs navbar-title"><c:import url="/WEB-INF/parts/titre.${titrePage}.jsp" /></span>
					</c:if>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="hidden-sm hidden-md hidden-lg nav navbar-nav navbar-right">
							<c:if test="${utilisateur.estAdministrateur() or utilisateur.estModerateur()}">
								<li><span class="glyphicon glyphicon-briefcase">&nbsp;</span>Administration</li>
								<li><a href="<c:url value="/admin/crediterJoueur" />"><span class="glyphicon glyphicon-piggy-bank">&nbsp;</span>Créditer un joueur</a></li>
								<li><a href="<c:url value="/admin/redistribuerGains" />"><span class="glyphicon glyphicon-euro">&nbsp;</span>Redistribuer les gains</a></li>
								<li><a href="<c:url value="/admin/detailsUtilisateurs" />"><span class="glyphicon glyphicon-th-list">&nbsp;</span>Afficher les utilisateurs</a></li>
								<li><a href="<c:url value="/admin/logs" />"><span class="glyphicon glyphicon-duplicate">&nbsp;</span>Consulter les logs</a></li>
								<hr />
							</c:if>
							<li><a href="<c:url value="/accueil" />"><span class="glyphicon glyphicon-home">&nbsp;</span>Tableau de bord</a></li>
							<li><a href="<c:url value="/resultats" />"><span class="glyphicon glyphicon-time">&nbsp;</span>Résultats</a></li>
							<!--li><a href="<c:url value="/statistiques" />"><span class="glyphicon glyphicon-signal">&nbsp;</span>Statistiques</a></li-->
							<c:if test="${loggedIn}">
								<li><a href="<c:url value="/membre/profil" />"><span class="glyphicon glyphicon-cog">&nbsp;</span>Profil</a></li>
							</c:if>
							<c:choose>
								<c:when test="${loggedIn}">
									<li><a class="nav-link" href="<c:url value="/deconnexion" />"><span class="glyphicon glyphicon-off">&nbsp;</span>Déconnexion</a></li>
								</c:when>
								<c:otherwise>
									<li><a class="nav-link" href="<c:url value="/inscription" />"><span class="glyphicon glyphicon-pencil">&nbsp;</span>Inscription</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>
<div id="dashboard" class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<c:if test="${utilisateur.estAdministrateur() or utilisateur.estModerateur()}">
				<ul class="nav nav-sidebar">
					<li>
						<c:set var="expanded" value="false" />
						<c:set var="in" value="" />
						<c:if test="${titrePage == 'creditPlayer' or titrePage == 'redistribute' or titrePage == 'listUsers' or titrePage == 'viewLogs'}">
							<c:set var="expanded" value="true" /><c:set var="in" value=" in" />
						</c:if>
						<a role="button" data-toggle="collapse" href="#adminLinks" aria-expanded="${expanded}" aria-controls="adminLinks"><span class="glyphicon glyphicon-briefcase">&nbsp;</span>Administration</a>
						<div id="adminLinks" class="collapse${in}">
							<ul class="nav nav-sidebar subnav">
								<c:choose><c:when test="${titrePage == 'creditPlayer'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
								<li class="${active}"><a href="<c:url value="/admin/crediterJoueur" />"><span class="glyphicon glyphicon-piggy-bank">&nbsp;</span>Créditer un joueur</a></li>
								<c:choose><c:when test="${titrePage == 'redistribute'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
								<li class="${active}"><a href="<c:url value="/admin/redistribuerGains" />"><span class="glyphicon glyphicon-euro">&nbsp;</span>Redistribuer les gains</a></li>
								<c:choose><c:when test="${titrePage == 'listUsers'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
								<li class="${active}"><a href="<c:url value="/admin/detailsUtilisateurs" />"><span class="glyphicon glyphicon-th-list">&nbsp;</span>Afficher les utilisateurs</a></li>
								<c:choose><c:when test="${titrePage == 'viewLogs'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
								<li class="${active}"><a href="<c:url value="/admin/logs" />"><span class="glyphicon glyphicon-duplicate">&nbsp;</span>Consulter les logs</a></li>
							</ul>
						</div>
					</li>
				</ul>
				<hr />
			</c:if>
			<ul class="nav nav-sidebar">
				<c:choose><c:when test="${titrePage == 'dashboard'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
				<li class="${active}"><a href="<c:url value="/accueil" />"><span class="glyphicon glyphicon-home">&nbsp;</span>Tableau de bord</a></li>
				<c:choose><c:when test="${titrePage == 'results'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
				<li class="${active}"><a href="<c:url value="/resultats" />"><span class="glyphicon glyphicon-time">&nbsp;</span>Résultats</a></li>
				<c:if test="${loggedIn}">
					<c:choose><c:when test="${titrePage == 'profile'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
					<li class="${active}"><a href="<c:url value="/membre/profil" />"><span class="glyphicon glyphicon-cog">&nbsp;</span>Profil</a></li>
				</c:if>
			</ul>
			<c:if test="${loggedIn}">
				<hr />
				<ul class="nav nav-sidebar">
					<li><a class="nav-link" href="<c:url value="/deconnexion" />"><span class="glyphicon glyphicon-off">&nbsp;</span>Déconnexion</a></li>
				</ul>
			</c:if>
		</div>
		<div id="main" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">