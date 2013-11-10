<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	
		<div class="well">
			<s:form modelAttribute="articleForm" action="/controller/parametrage/article/save" validate="true" theme="xhtml" class="form-horizontal">
			<fieldset>
				<legend><t:get name="form.title.nouvel.article" /></legend>
				<s:hidden path="id" id="idArticle" />
				<div class="form-group">
					<label for="selectCategorie" class="col-lg-2 control-label"><t:get name="form.label.categorie" /></label>
					<div class="col-lg-10">
						<s:select label="Catégorie" 
									headerKey="-1" headerValue="Catégorie" 
									name="categorie"
									path="categorie"
									items="${mapCategories}" 
									class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label for="inputDescription" class="col-lg-2 control-label"><t:get name="form.label.description" /></label>
					<div class="col-lg-10">
						<s:input	id="description" 
											name="description" 
											path="description"
											maxlength="50" 
											tabindex="1" 
											required="true"
											label="description"
											class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label for="inputContenu" class="col-lg-2 control-label"><t:get name="form.label.contenu" /></label>
					<div class="col-lg-10">
						<textarea class="ckeditor" id="contenu" name="contenu">&lt;p&gt;${article.contenuString}&lt;/p&gt;</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<a class="btn btn-default" onclick="javascript:annuler();"><t:get name="button.annuler" /></a>
						<button class="btn btn-primary"><t:get name="button.sauvegarder"/></button>
					</div>
				</div>
			</fieldset>
			</s:form>
		</div>
	
	<script>
		CKEDITOR.replace( 'contenu' );
		
		function annuler() {
			window.location.href = "/controller/parametrage/article/list";
		}
		function save() {
			document.forms[0].submit();
		}
	</script>
	