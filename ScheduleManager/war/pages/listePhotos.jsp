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
		<jsp:include page="/pages/listeParametrage.jsp" /> 
		
		<table class="table">
		<thead>
			<tr>
				<th>Dossier</th>
				<th>Image</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<% for(String key : listePhotos.keySet()) {
				for(ImageVO img : listePhotos.get(key)) {
					String url = imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(img.getImage())) + "=s80";
					if(url.contains("0.0.0.0")) {
						url = url.substring(19);
					}
					%>
					<tr>
						<td><%= img.getDossier()%></td>
						<td><img src="<%= url%>" /></td>
						<td><a href="javascript:supprimerPhoto('<%= img.getKey()%>');"><img src="/images/supprimerImage.png" height="25px"/></a></td>
					</tr>
				<%
				}
			}
			%>
		</tbody>
		</table>
	<script>
		function supprimerPhoto(id) {
			if(confirm('Êtes-vous sûr de vouloir supprimer la photo ?')) {
				window.location.href = "/controller/parametrage/photo/delete?idPhoto=" + id;
			}
		}
	</script>
