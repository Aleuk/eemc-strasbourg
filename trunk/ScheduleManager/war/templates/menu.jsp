   	<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
	<%@page import="com.google.appengine.api.users.UserService"%>

    <% UserService userService = UserServiceFactory.getUserService();
        		System.out.println(request.getRequestURI());
    %>
    
    		<div class="navbar navbar-inverse" style="margin-bottom:0;">
                <div class="container">
                  <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/controller/enter">
				        Accueil
				    </a>
                  </div>
                  <div class="navbar-collapse collapse navbar-inverse-collapse">
                    <ul class="nav navbar-nav">
                      <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="/controller/eglise/list">Eglise <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          	<li><a href="/controller/historique/list">Historique</a></li>
			    			<li><a href="/controller/activite/list">Activit�s</a></li>
			    			<li><a href="/controller/planAcces/list">Plan d'acc�s</a></li>
                        </ul>
                      </li>
                      <li class="dropdown">
                      		<a href="/controller/media/list" class="dropdown-toggle" data-toggle="dropdown">M�dias <b class="caret"></b></a>
				    		<ul class="dropdown-menu">
				    			<li><a href="/controller/message/list">Messages</a></li>
				    			<li><a href="/controller/video/list">Vid�os</a></li>
				    			<li><a href="/controller/musique/list">Musique</a></li>
				    			<li><a href="/controller/photo/list">Photos</a></li>
				    		</ul>
				    	</li>
				    	<li><a href="/controller/projet/list">Projets</a></li>
				    	<li><a href="/controller/lecture/list">&nbsp;Versets du jour&nbsp;</a></li>
				    	<li><a href="/controller/parametrage/list">&nbsp;Nous contacter&nbsp;</a></li><!-- sujets de pri�res ici -->
				    	<li><a href="/controller/contact/list">Contacts</a></li>
				    	<li><a href="/controller/parametrage/list">Param�trage</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                    	<li><a href="/controller/message/list">Mon compte</a></li>
			    		<li><a href='<%= userService.createLogoutURL("/") %>' ><img src="/images/deconnexion.png" /></a></li>
			    	</ul>
                  </div><!-- /.nav-collapse -->
                </div><!-- /.container -->
              </div><!-- /.navbar -->
