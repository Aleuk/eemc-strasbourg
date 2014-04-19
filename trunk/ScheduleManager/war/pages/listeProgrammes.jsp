<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<jsp:include page="/pages/listeParametrage.jsp" /> 

		
		<display:table id="dataTable" name="listeProgrammes"
			pagesize="13" requestURI="/controller/parametrage/programme" class="table" decorator="fr.eemcs.schedulemanager.decorator.ProgrammeDecorator">
			<display:column property="mois" keyTitle="table.title.mois" />
			<display:column property="actions" keyTitle="table.title.actions" />
			<display:setProperty name="paging.banner.onepage" value="" />
			<display:setProperty name="paging.banner.one_item_found" value="" />
			<display:setProperty name="paging.banner.some_items_found" value="" />
			<display:setProperty name="paging.banner.all_items_found" value="" />
		</display:table>
		
	<script>
		function getProgramme(id) {
			window.location.href = "/controller/parametrage/programme/get?moisProgramme=" + id;
		}
		function editProgramme(id) {
			window.location.href = "/controller/parametrage/programme/edit?moisProgramme=" + id;
		}
		function deleteProgramme(id) {
			window.location.href = "/controller/parametrage/programme/delete?moisProgramme=" + id;
		}
	</script>
