		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					Résultats du dernier tirage
				</h3>
			</div>
			<div class="panel-body">
				<p>Tirage effectué le <c:out value="${r.dernier.date}" /></p>
				<p><span class="boule"><c:out value="${r.dernier.b1}" /></span>
				<span class="boule"><c:out value="${r.dernier.b2}" /></span>
				<span class="boule"><c:out value="${r.dernier.b3}" /></span>
				<span class="boule"><c:out value="${r.dernier.b4}" /></span>
				<span class="boule"><c:out value="${r.dernier.b5}" /></span>
				<span class="etoile"><c:out value="${r.dernier.e1}" /></span>
				<span class="etoile"><c:out value="${r.dernier.e2}" /></span></p>
				<p class="mymillion">Code<c:if test="${fn:length(r.dernier.myMillion) > 9}">s</c:if>
				 MyMillion : <span class="code"><c:out value="${r.dernier.myMillion}"></c:out></span></p>
				<c:if test="${sessionScope.loggedIn}">
				<c:if test="${not empty utilisateur.grilles}">
				<a id="lienGains" href="#"><span class="monospace">[<span id="btnLienGains" class="monospace">+</span>] </span>Gains</a>
				<div id="tableauGainsGrilles">
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Nom de la grille</th>
								<th>Gains</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${utilisateur.grilles}" var="grille">
							<c:set var="gains" value=""></c:set>
							<c:if test="${grille.gains > 0}">
							<c:set var="gains" value="success text-success"></c:set>
							</c:if>
							<tr class="${gains}">
								<td><c:out value="${grille.nom}" /></td>
								<c:choose>
								<c:when test="${grille.jouee}">
								<td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${grille.gains}" type="currency"></fmt:formatNumber></td>
								</c:when>
								<c:otherwise>
								<td>Non jouée</td>
								</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				</c:if>
				</c:if>
			</div>
		</div>