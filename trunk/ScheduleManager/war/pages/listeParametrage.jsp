<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<% String url = (String) request.getAttribute("javax.servlet.forward.request_uri"); %>
		<ul class="nav nav-tabs">
			<% if(url.contains("programme")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/programme/list"><img src="/images/getProgramme.png" height="15px"/> <t:get name="param.title.programmes" /></a>
				</li>
				
			<% if(url.contains("article")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/article/list"><img src="/images/fichier.png" height="15px"/> <t:get name="param.title.articles" /></a>
				</li>
			
			<% if(url.contains("lieu")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/lieu/list"><img src="/images/lieu.png" height="15px"/> <t:get name="param.title.lieux" /></a>
				</li>
				
			<% if(url.contains("photo")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/photo/list"><img src="/images/image.png" height="15px"/> <t:get name="param.title.photos" /></a>
				</li>
				
			<% if(url.contains("video")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/video/list"><img src="/images/video.png" height="15px"/> <t:get name="param.title.videos" /></a>
				</li>
		</ul>
