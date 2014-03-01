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
		
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			<div class="carousel-inner">
		<% int active=0; 
			for(ImageVO img : listePhotos) {
				String url = imagesService.getServingUrl(ServingUrlOptions.Builder.withBlobKey(img.getImage())) + "=s1024";
				if(url.contains("0.0.0.0")) {
					url = url.substring(19);
				} %>
				<!-- Wrapper for slides -->
				<%if(active==0) { %>
			    	<div class="item active">
			    		<img src="<%= url %>" />
						<div class="carousel-caption">
					        <!-- <%= img.getName() %>-->
						</div>
					</div>
			    <%} else {%>
			    	<div class="item">
			    		<img src="<%= url %>" />
						<div class="carousel-caption">
						<!-- <%= img.getName() %>-->
						</div>
					</div>
			    <%} %>
				      
			<%
			active++;
			}
			%>
			</div>
			  <!-- Controls -->
			  <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
			    &lt;
			  </a>
			  <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
			    &gt;
			  </a>
		</div>

		<!--<table class="table table-striped table-bordered table-hover">
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
		</table>-->
	<script>
		$('.carousel').carousel();
	</script>
