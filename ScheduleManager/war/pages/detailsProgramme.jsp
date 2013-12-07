<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<jsp:include page="/pages/listeParametrage.jsp" /> 

		
		<display:table id="dataTable" name="listeEvenements"
			pagesize="40" requestURI="/controller/parametrage/programme" class="table table-striped table-bordered table-hover" decorator="fr.eemcs.schedulemanager.decorator.ProgrammeDecorator">
			<display:column property="date" keyTitle="table.title.date" />
		</display:table>
		
	<script>
		function getProgramme(id) {
			window.location.href = "/controller/parametrage/programme/get?moisProgramme=" + id;
		}
		function editProgramme(id) {
			window.location.href = "/controller/parametrage/programme/modif?moisProgramme=" + id;
		}
		function deleteProgramme(id) {
			window.location.href = "/controller/parametrage/programme/delete?moisProgramme=" + id;
		}
	</script>
