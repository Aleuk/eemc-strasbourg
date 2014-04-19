<%@page import="fr.eemcs.schedulemanager.entity.ImageVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.google.appengine.api.images.ServingUrlOptions"%>
<%@page import="com.google.appengine.api.images.ImagesServiceFactory"%>
<%@page import="com.google.appengine.api.images.ImagesService"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	    ImagesService imagesService = ImagesServiceFactory.getImagesService(); // Récupération du service d'images

		// Récupération de l'URL de l'image
		Map<String, List<ImageVO>> listePhotos = (Map<String, List<ImageVO>>)request.getAttribute("listePhotos");
	%>
		
		<table class="table table-striped table-bordered table-hover">
		<tbody>
			<tr>
				<% int i = 0;
				for(String key : listePhotos.keySet()) {
					ImageVO img = listePhotos.get(key).get(0);
					String url = imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(img.getImage())) + "=s120";
					if(url.contains("0.0.0.0")) {
						url = url.substring(19);
					}
					%>
					
						<td><a href="javascript:ouvrirDossier('<%= img.getKey()%>')"><img src="<%= url%>" class="dossierPhotos"/><br /><%= img.getDossier()%></a></td>
				<%
					i++;
					if(i > 3) {
						i = 0;
						%></tr><tr><%
					}
				}
				%>
			</tr>
		</tbody>
		</table>
	<script>
		function ouvrirDossier(id) {
			window.location.href = "/controller/photo/openFolder?idImage=" + id;
		}
	</script>
