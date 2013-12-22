<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	
		<div class="well">
			<s:form modelAttribute="eventForm" action="/controller/parametrage/evenement/save" validate="true" theme="xhtml" class="form-horizontal">
			<fieldset>
				<legend><t:get name="form.title.nouvel.evenement" /></legend>
				<s:hidden path="id" id="idEvent" />
				<div class="form-group">
					<label for="inputDate" class="col-lg-2 control-label"><t:get name="form.label.date" /></label>
					<div class="col-lg-10">
						<s:input path="date" type="date" tabindex="5" required="true" value="${date}"/>
					</div>
				</div>
				<div class="form-group">
					<label for="inputHeure" class="col-lg-2 control-label"><t:get name="form.label.heure" /></label>
					<div class="col-lg-10">
						<s:input path="heure" type="time" tabindex="5" required="true" value="${heure}"/>
					</div>
				</div>
				<div class="form-group">
					<label for="selectLieux" class="col-lg-2 control-label"><t:get name="form.label.lieu" /></label>
					<div class="col-lg-10">
						<s:select label="Lieu" id="lieu_"
										headerKey="-1" headerValue="Lieu" 
										name="lieu"
										path="lieu"
										items="${mapLieux}" 
										class="lieu_ form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label for="selectPresidence" class="col-lg-2 control-label"><t:get name="form.label.presidence" /></label>
					<div class="col-lg-10">
						<s:select id="presidence_" class="presidence_ form-control" name="presidence" path="">
							<s:option value="-1" label="-------"/>
							<s:options items="${mapPresidence}"/>
						</s:select>
					</div>
				</div>
				<div class="form-group">
					<label for="selectPredicateurs" class="col-lg-2 control-label"><t:get name="form.label.predicateur" /></label>
					<div class="col-lg-10">
						<s:select id="predicateur_" class="predicateur_ form-control" name="predicateur" path="">
							<s:option value="-1" label="-------"/>
							<s:options items="${mapPredicateurs}"/>
						</s:select>
					</div>
				</div>
				<div class="form-group">
					<label for="selectTraducteurs" class="col-lg-2 control-label"><t:get name="form.label.traducteur" /></label>
					<div class="col-lg-10">
						<s:select id="traducteur_" class="traducteur_ form-control" name="traducteur" path="">
							<s:option value="-1" label="-------"/>
							<s:options items="${mapTraducteurs}"/>
						</s:select>
					</div>
				</div>
				<div class="form-group">
					<label for="selectOffrande" class="col-lg-2 control-label"><t:get name="form.label.offrande" /></label>
					<div class="col-lg-10">
						<s:select id="offrande_" class="offrande_ form-control" name="offrande" path="">
							<s:option value="-1" label="-------"/>
							<s:options items="${mapOffrande}"/>
						</s:select>
					</div>
				</div>
				<div class="form-group">
					<label for="selectResponsables" class="col-lg-2 control-label"><t:get name="form.label.responsables" /></label>
					<div class="col-lg-10">
						<s:select multiple="true" id="responsables_" class="responsables_ form-control" name="responsables" path="responsables">
							<s:options items="${mapResponsables}"/>
						</s:select>
					</div>
				</div>
				<div class="form-group">
					<label for="inputDivers" class="col-lg-2 control-label"><t:get name="form.label.divers" /></label>
					<div class="col-lg-10">
						<s:input	id="divers" 
										name="divers" 
										path="divers"
										maxlength="50" 
										tabindex="1" 
										label="Divers"
										class="form-control"/>
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
	</script>
	