<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
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
						<display:table id="dataTable" name="listeEvenements"
							pagesize="31" requestURI="/controller/parametrage/programme" class="table table-bordered" decorator="fr.eemcs.schedulemanager.decorator.EvenementDecorator">
							<display:setProperty name="basic.msg.empty_list" value="" />
							<display:setProperty name="paging.banner.onepage" value="" />
							<display:setProperty name="paging.banner.one_item_found" value="" />
							<display:setProperty name="paging.banner.all_items_found" value="" />
							<display:column property="date" keyTitle="table.title.date" />
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
	