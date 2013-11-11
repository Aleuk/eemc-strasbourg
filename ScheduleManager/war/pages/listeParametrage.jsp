<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<% String url = request.getRequestURI().toString(); %>
	<%= url %>
		<ul class="nav nav-tabs">
			<% if(url.contains("lieu")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/lieu/list"><img src="/images/lieu.png" height="15px"/> <t:get name="table.title.lieux" /></a>
				</li>
				
			<% if(url.contains("article")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/article/list"><img src="/images/fichier.png" height="15px"/> <t:get name="table.title.articles" /></a>
				</li>
				
			<% if(url.contains("programme")) {%>
				<li class="active">
			<% } else { %>
				<li>
			<% }%>
					<a href="/controller/parametrage/programme/list">Programme</a>
				</li>
		</ul>
