<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<% String url = (String) request.getAttribute("javax.servlet.forward.request_uri"); %>
		<ul class="nav nav-tabs">
			<% if(url.contains("article")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/article/list"><img src="/images/fichier.png" height="15px"/> <t:get name="table.title.articles" /></a>
				</li>
				
			
			<% if(url.contains("lieu")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/lieu/list"><img src="/images/lieu.png" height="15px"/> <t:get name="table.title.lieux" /></a>
				</li>
				
				
			<% if(url.contains("programme")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/programme/list"> <t:get name="table.title.programmes" /></a>
				</li>
		</ul>
