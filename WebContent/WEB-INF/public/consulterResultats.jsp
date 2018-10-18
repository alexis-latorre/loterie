<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <title>Loterie | Résultats</title>
  <c:import url="/WEB-INF/css/bootstrap.css.jsp" />
  <c:import url="/WEB-INF/css/calendrier.css.jsp" />
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/commun/header.jsp" />
		<h4>Résultats du dernier tirage le <c:out value="${r.dernier.date}" /> :</h4>
		<c:out value="${r.dernier.b1} - ${r.dernier.b2} - ${r.dernier.b3} - ${r.dernier.b4} - ${r.dernier.b5} / ${r.dernier.e1} - ${r.dernier.e2}" /><br />
		MyMillion : <c:out value="${r.dernier.myMillion}"></c:out>
		<h4>Répartition des gains :</h4>
		<table id="table_gagnants" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th id="header_role">Rang</th> 
					<th id="header_role">Condition</th> 
					<th id="header_role">Nombres de gagnants</th> 
				</tr>
			</thead>
			<tbody>
				<tr><td>Rang 1</td><td>5 numéros + 2 étoiles</td><td><c:out value="${r.dernier.rang1}" /></td></tr>
				<tr><td>Rang 2</td><td>5 numéros + 1 étoile</td><td><c:out value="${r.dernier.rang2}" /></td></tr>
				<tr><td>Rang 3</td><td>5 numéros</td><td><c:out value="${r.dernier.rang3}" /></td></tr>
				<tr><td>Rang 4</td><td>4 numéros + 2 étoiles</td><td><c:out value="${r.dernier.rang4}" /></td></tr>
				<tr><td>Rang 5</td><td>4 numéros + 1 étoile</td><td><c:out value="${r.dernier.rang5}" /></td></tr>
				<tr><td>Rang 6</td><td>3 numéros + 2 étoiles</td><td><c:out value="${r.dernier.rang6}" /></td></tr>
				<tr><td>Rang 7</td><td>4 numéros</td><td><c:out value="${r.dernier.rang7}" /></td></tr>
				<tr><td>Rang 8</td><td>2 numéros + 2 étoiles</td><td><c:out value="${r.dernier.rang8}" /></td></tr>
				<tr><td>Rang 9</td><td>3 numéros + 1 étoile</td><td><c:out value="${r.dernier.rang9}" /></td></tr>
				<tr><td>Rang 10</td><td>3 numéros</td><td><c:out value="${r.dernier.rang10}" /></td></tr>
				<tr><td>Rang 11</td><td>1 numéro + 2 étoiles</td><td><c:out value="${r.dernier.rang11}" /></td></tr>
				<tr><td>Rang 12</td><td>2 numéros +  étoile</td><td><c:out value="${r.dernier.rang12}" /></td></tr>
				<tr><td>Rang 13</td><td>2 numéros</td><td><c:out value="${r.dernier.rang13}" /></td></tr>
			</tbody>
		</table>
	<c:import url="/WEB-INF/css/bootstrap.js.jsp" />
</body>
</html>