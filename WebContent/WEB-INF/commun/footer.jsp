		</div>
	</div>
</div>	
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
<c:import url="/WEB-INF/js/scrollspy.js.jsp" />
<c:import url="/WEB-INF/js/alertes.js.jsp" />
<script type="text/javascript">
	$(function () {
		$('[data-toggle="tooltip"]').tooltip();
	})
</script>