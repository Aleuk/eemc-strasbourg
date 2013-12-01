   	<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
	<%@page import="com.google.appengine.api.users.UserService"%>

    <% UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String urlConnexion = userService.createLoginURL("/controller/enter");
		
        		System.out.println(request.getAttribute("javax.servlet.forward.request_uri"));
    %>
    
    		<div class="navbar navbar-inverse" style="margin-bottom:0;">
                <div class="container">
                  <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/controller/accueil/blog">
				        Accueil
				    </a>
                  </div>
                  <div class="navbar-collapse collapse navbar-inverse-collapse">
                    <ul class="nav navbar-nav">
                      <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="/controller/eglise/list">Eglise <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          	<li><a href="/controller/historique/blog">Historique</a></li>
			    			<li><a href="/controller/activite/blog">Activités</a></li>
			    			<li><a href="/controller/planAcces">Plan d'accès</a></li>
                        </ul>
                      </li>
                      <li class="dropdown">
                      		<a href="/controller/media/list" class="dropdown-toggle" data-toggle="dropdown">Médias <b class="caret"></b></a>
				    		<ul class="dropdown-menu">
				    			<li><a href="/controller/message/blog">Messages</a></li>
				    			<li><a href="/controller/video/blog">Vidéos</a></li>
				    			<li><a href="/controller/musique/blog">Musique</a></li>
				    			<li><a href="/controller/photo/blog">Photos</a></li>
				    		</ul>
				    	</li>
				    	<li><a href="/controller/projet/blog">Projets</a></li>
				    	<li><a href="/controller/lecture/blog">&nbsp;Versets du jour&nbsp;</a></li>
				    	<li><a href="/controller/nousContacter">&nbsp;Nous contacter&nbsp;</a></li><!-- sujets de prières ici -->
				    	<% if(user != null) {%>
					    	<li><a href="/controller/contact/list">Contacts</a></li>
					    	<li><a href="/controller/parametrage/list">Paramétrage</a></li>
					    <% }%>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                    	<li><a href="<%= urlConnexion %>">Mon compte</a></li>
                    	<% if(user != null) {%>
			    			<li><a href='<%= userService.createLogoutURL("/") %>' ><img src="/images/deconnexion.png" /></a></li>
			    		<% }%>
			    	</ul>
                  </div><!-- /.nav-collapse -->
                </div><!-- /.container -->
              </div><!-- /.navbar -->
