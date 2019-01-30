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
					<a class="navbar-brand" href="<c:url value="/accueil" />">MyLoterie</a>
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
								<li><a href="<c:url value="/admin/" />"><span class="glyphicon glyphicon-briefcase">&nbsp;</span>Administration</a></li>
							</c:if>
							<li><a href="<c:url value="/accueil" />"><span class="glyphicon glyphicon-home">&nbsp;</span>Tableau de bord</a></li>
							<li><a href="<c:url value="/resultats" />"><span class="glyphicon glyphicon-time">&nbsp;</span>Résultats</a></li>
							<!--li><a href="<c:url value="/statistiques" />"><span class="glyphicon glyphicon-signal">&nbsp;</span>Statistiques</a></li-->
							<c:if test="${loggedIn}">
								<li><a href="<c:url value="/membre/profil" />"><span class="glyphicon glyphicon-cog">&nbsp;</span>Mon profil</a></li>
							</c:if>
							<c:choose>
								<c:when test="${loggedIn}">
									<li><a class="nav-link" href="<c:url value="/deconnexion" />"><span class="glyphicon glyphicon-log-out">&nbsp;</span>Se déconnecter</a></li>
								</c:when>
								<c:otherwise>
									<li><a class="nav-link" href="<c:url value="/inscription" />"><span class="glyphicon glyphicon-pencil">&nbsp;</span>S'inscrire</a></li>
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
					<c:choose><c:when test="${titrePage == 'administration'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
					<li class="${active}"><a href="<c:url value="/admin/" />"><span class="glyphicon glyphicon-briefcase">&nbsp;</span>Administration</a></li>
				</ul>
				<hr />
			</c:if>
			<ul class="nav nav-sidebar">
				<c:choose><c:when test="${titrePage == 'dashboard'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
				<li class="${active}"><a href="<c:url value="/accueil" />"><span class="glyphicon glyphicon-home">&nbsp;</span>Tableau de bord</a></li>
				<c:choose><c:when test="${titrePage == 'results'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
				<li class="${active}"><a href="<c:url value="/resultats" />"><span class="glyphicon glyphicon-signal">&nbsp;</span>Résultats</a></li>
				<c:if test="${loggedIn}">
					<c:choose><c:when test="${titrePage == 'profile'}"><c:set var="active" value=" active"></c:set></c:when><c:otherwise><c:set var="active" value=""></c:set></c:otherwise></c:choose>
					<li class="${active}"><a href="<c:url value="/membre/profil" />"><span class="glyphicon glyphicon-cog">&nbsp;</span>Mon profil</a></li>
				</c:if>
			</ul>
			<c:if test="${loggedIn}">
				<hr />
				<ul class="nav nav-sidebar">
					<li><a class="nav-link" href="<c:url value="/deconnexion" />"><span class="glyphicon glyphicon-log-out">&nbsp;</span>Se déconnecter</a></li>
				</ul>
			</c:if>
		</div>
		<div id="main" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">