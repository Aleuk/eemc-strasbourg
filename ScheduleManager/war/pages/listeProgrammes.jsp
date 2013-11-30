<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<jsp:include page="/pages/listeParametrage.jsp" /> 

		<table id="dataTable" class="table table-striped table-bordered table-hover">
			<c:forEach items="${listeProgrammes}" var="mois">
			    <tr>
			    	<td>${mois.key}</td>
			    	<td>${mois.value}</td>
			    	<td>actions</td>
			    </tr>
			</c:forEach>
		</table>
		
		<display:table id="dataTable" name="listeProgrammes"
			pagesize="15" requestURI="/controller/parametrage/programme" class="table table-striped table-bordered table-hover" decorator="fr.eemcs.schedulemanager.decorator.ProgrammeDecorator">
			<display:column property="mois" keyTitle="table.title.mois" />
			<display:column property="actions" keyTitle="table.title.actions" />
		</display:table>
		
	<script>
		function getProgramme(id) {
			window.location.href = "/controller/parametrage/programme/get?idProgramme=" + id;
		}
		function modifierProgramme(id) {
			window.location.href = "/controller/parametrage/programme/modif?idProgramme=" + id;
		}
	</script>
