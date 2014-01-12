<%@page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<%
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	%>
		<div class="well">
			<s:form modelAttribute="photoForm" action='<%= blobstoreService.createUploadUrl("/controller/parametrage/photo/upload") %>' validate="true" theme="xhtml" enctype="multipart/form-data" class="form-horizontal">
			<fieldset>
				<legend><t:get name="form.title.upload.photo" /></legend>
				<div class="form-group">
					<label for="inputFile" class="col-lg-2 control-label"><t:get name="form.label.dossier" /></label>
					<div class="col-lg-10">
						<s:input	id="dossier" 
											name="dossier" 
											path="dossier"
											maxlength="50" 
											tabindex="1" 
											required="true"
											label="dossier"
											class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label for="inputFile" class="col-lg-2 control-label"><t:get name="form.label.dossier" /></label>
					<div class="col-lg-10">
			       		<input type="file" name="myFile">
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
		function annuler() {
			window.location.href = "/controller/parametrage/photo/list";
		}
	</script>
	