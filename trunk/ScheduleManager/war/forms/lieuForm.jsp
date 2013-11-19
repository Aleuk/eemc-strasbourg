<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<div class="well">
			<s:form modelAttribute="lieuForm" action="/controller/parametrage/lieu/save" validate="true" theme="xhtml" class="form-horizontal">
			<fieldset>
				<legend><t:get name="form.title.nouveau.lieu" /></legend>
					<s:hidden path="id" id="idLieu" />
					
					<div class="form-group">
						<label for="inputNom" class="col-lg-4 control-label"><t:get name="form.label.nom" /></label>
						<div class="col-lg-8">
							<s:input	id="nom" 
												name="nom" 
												path="nom"
												maxlength="50" 
												tabindex="1" 
												required="true"
												label="Nom"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputNomKH" class="col-lg-4 control-label"><t:get name="form.label.nom.cambodgien" /></label>
						<div class="col-lg-8">
							<input type="text" 	id="nomKH" 
												name="nomKH" 
												value="${contact.nomKH}"
												size="26" 
												maxlength="50" 
												tabindex="3"
												class="inputKH"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputAdresse" class="col-lg-4 control-label"><t:get name="form.label.adresse" /></label>
						<div class="col-lg-8">
							<s:input 	id="adresse" 
												name="adresse" 
												path="adresse"
												maxlength="50" 
												tabindex="6"
												label="Adresse"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputCodePostal" class="col-lg-4 control-label"><t:get name="form.label.codePostal" /></label>
						<div class="col-lg-8">
							<s:input 	id="codePostal" 
												name="codePostal" 
												path="codePostal"
												maxlength="50" 
												tabindex="7"
												label="Code Postal"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputVille" class="col-lg-4 control-label"><t:get name="form.label.ville" /></label>
						<div class="col-lg-8">
							<s:input 	id="ville" 
												name="ville" 
												path="ville"
												maxlength="50" 
												tabindex="8"
												label="Ville"
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
			window.location.href = "/controller/parametrage/lieu/list";
		}
		
		function save() {
			document.forms[0].submit();
		}
	</script>
	