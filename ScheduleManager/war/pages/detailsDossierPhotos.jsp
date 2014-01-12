<%@page import="fr.eemcs.schedulemanager.entity.ImageVO"%>
<%@page import="java.util.List"%>
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
		List<ImageVO> listePhotos = (List<ImageVO>)request.getAttribute("listePhotos");
	%>
		
		<table class="table table-striped table-bordered table-hover">
		<tbody>
			<tr>
				<% int i = 0;
				for(ImageVO img : listePhotos) {
					String url = imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(img.getImage())) + "=s120";
					if(url.contains("0.0.0.0")) {
						url = url.substring(19);
					}
					%>
					
						<td><img src="<%= url%>" /></td>
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
		
	</script>
