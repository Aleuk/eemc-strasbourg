<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		
		<h2><t:get name="details.title.programme" /> ${ titleProgramme}</h2>
		<display:table id="dataTable" name="listeEvenements"
			pagesize="40" requestURI="/controller/parametrage/programme" class="table table-striped table-bordered table-hover" decorator="fr.eemcs.schedulemanager.decorator.ProgrammeDecorator">
			<display:setProperty name="basic.msg.empty_list" value="" />
			<display:setProperty name="paging.banner.onepage" value="" />
			<display:setProperty name="paging.banner.one_item_found" value="" />
			<display:setProperty name="paging.banner.some_items_found" value="" />
			<display:setProperty name="paging.banner.all_items_found" value="" />
			<display:column property="date" keyTitle="table.title.date" />
			<display:column property="heure" keyTitle="table.title.heure" />
			<display:column property="lieu" keyTitle="table.title.lieu" />
			<display:column property="presidence" keyTitle="table.title.presidence" />
			<display:column property="predicateur" keyTitle="table.title.predicateur" />
			<display:column property="traducteur" keyTitle="table.title.traducteur" />
			<display:column property="offrande" keyTitle="table.title.offrande" />
			<display:column property="responsables" keyTitle="table.title.ecoleDimanche" />
			<display:column property="divers" keyTitle="table.title.divers" />
		</display:table>
		
		<a class="btn btn-default" onclick="javascript:retour();"><t:get name="button.retour" /></a>
	<script>
		function retour() {
			window.location.href = "/controller/parametrage/programme/list";
		}
	</script>
