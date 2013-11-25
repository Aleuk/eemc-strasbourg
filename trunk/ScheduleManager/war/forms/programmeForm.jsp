<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	
		<div class="well">
			<s:form modelAttribute="nousContacterForm" action="/controller/nousContacter" validate="true" theme="xhtml" class="form-horizontal">
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
						<button class="btn btn-primary"><t:get name="button.envoyer"/></button>
					</div>
				</div>
			</fieldset>
			</s:form>
		</div>
	
	<script>
		CKEDITOR.replace( 'contenu' ,
				{
					toolbar :
					[
						{ name: 'document', items : [ 'Source' ] },
						{ name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
						'/',
						{ name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
						{ name: 'insert', items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar' ] },
						{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv',
						'-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
						{ name: 'links', items : [ 'Link','Unlink','Anchor' ] },
						'/',
						{ name: 'styles', items : [ 'Styles','Format','Font','FontSize' ] },
						{ name: 'colors', items : [ 'TextColor','BGColor' ] }
					]
				});
		
		function annuler() {
			window.location.href = "/controller/nousContacter";
		}
		function save() {
			document.forms[0].submit();
		}
	</script>
	