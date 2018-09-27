<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Créditer un joueur</title>
<c:import url="/WEB-INF/css/bootstrap.css.jsp" />
</head>
<body bgcolor="white">
	<c:import url="/WEB-INF/commun/header.jsp" />

	<h3>Administration - créditer un joueur</h3>
	<form action="<c:url value="/admin/crediterJoueur" />" method="post">
		<p>
			<label for="input-joueur">Sélectionnez le joueur à créditer :</label>
			<select class="" name="joueur" id="input-joueur">
			<c:forEach items="${joueurs}" var="joueur">
				<option value="${joueur.id}">${joueur.prenom} ${joueur.nom}</option>
			</c:forEach>
			</select>
		</p>
		<p>
			<label for="input-fonds">Créditer de : </label>
			<input type="number" lang="en" lang="fr" step="0.01" name="fonds" id="input-fonds" /> &euro;
		</p>
		<input type="submit" value="Créditer" />
	</form>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>