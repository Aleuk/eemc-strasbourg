<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	
		<div class="well">
			<s:form modelAttribute="programmeForm" action="/controller/parametrage/programme/save" validate="true" theme="xhtml" class="form-horizontal">
			<fieldset>
				<legend><t:get name="form.title.programmeDuMois" /></legend>
				<s:hidden path="id" id="idProgramme" />
				<div class="form-group">
					<label for="selectMois" class="col-lg-2 control-label"><t:get name="form.label.mois" /></label>
					<div class="col-lg-10">
						<s:select label="Mois" 
									headerKey="-1" headerValue="Mois" 
									name="mois"
									path="mois"
									items="${mapMois}" 
									class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-12">
						<table id="dataTable" class="table table-bordered">
						<thead>
							<tr>
								<th><t:get name="table.title.date" /></th>
								<th><t:get name="table.title.heure" /></th>
								<th><t:get name="table.title.lieu" /></th>
								<th><t:get name="table.title.presidence" /></th>
								<th><t:get name="table.title.predicateur" /></th>
								<th><t:get name="table.title.traducteur" /></th>
								<th><t:get name="table.title.offrande" /></th>
								<th><t:get name="table.title.ecoleDimanche" /></th>
								<th><t:get name="table.title.divers" /></th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="event" items="${listeEvenements}">
							<tr class="${event.classRow}">
								<td>${event.dateEvent}</td>
								<td><input type="time" name="${event.jourEvent}_heure" value="${event.heureEvent}" /></td>
								<td>
									<s:select
											headerKey="-1" headerValue="Lieu" 
											name="${event.jourEvent}_lieu"
											path=""
											items="${mapLieux}" 
											class="form-control"/>
								</td>
								<td>
									<s:select
											headerKey="-1" headerValue="Lieu" 
											name="${event.presidence}_lieu"
											path=""
											items="${mapPresidence}" 
											class="form-control"/>
								</td>
							</tr>
						</c:forEach>
						</tbody>
						</table>
						<display:table id="dataTable" name="listeEvenements"
							pagesize="31" requestURI="/controller/parametrage/programme" class="table table-bordered" decorator="fr.eemcs.schedulemanager.decorator.EvenementDecorator">
							<display:setProperty name="basic.msg.empty_list" value="" />
							<display:setProperty name="paging.banner.onepage" value="" />
							<display:setProperty name="paging.banner.one_item_found" value="" />
							<display:setProperty name="paging.banner.all_items_found" value="" />
							<display:column property="date" keyTitle="table.title.date" />
							<display:column property="heure" keyTitle="table.title.heure" />
							<display:column property="lieu" keyTitle="table.title.lieu" />
						</display:table>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-8 col-lg-offset-4">
						<a class="btn btn-default" onclick="javascript:annuler();"><t:get name="button.annuler" /></a>
						<button class="btn btn-primary"><t:get name="button.sauvegarder"/></button>
					</div>
				</div>
			</fieldset>
			</s:form>
		</div>
	
	<script>
		
		function annuler() {
			window.location.href = "/controller/parametrage/programme/list";
		}
		function save() {
			document.forms[0].submit();
		}
	</script>
	