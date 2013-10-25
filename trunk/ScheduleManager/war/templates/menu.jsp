   	<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
	<%@page import="com.google.appengine.api.users.UserService"%>

    <% UserService userService = UserServiceFactory.getUserService();
        		System.out.println(request.getRequestURI());
    %>
    
    <div class="row">
   		<div class="span12 text-align">
		    <div id="menu">
		    	<ul>
			    	<li><a href="/controller/enter">&nbsp;Accueil</a></li>
			    	<li><a href="/controller/eglise/list">Eglise</a>
			    		<ul>
			    			<li><a href="/controller/historique/list">&nbsp;Historique</a></li>
			    			<li><a href="/controller/activite/list">Activit�s</a></li>
			    			<li><a href="/controller/planAcces/list">Plan d'acc�s</a></li>
			    		</ul>
			    	</li>
			    	<li><a href="/controller/media/list">M�dias</a>
			    		<ul>
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
			    	<li><a href='<%= userService.createLogoutURL("/") %>' style="background-image:none;background-color:#FFFFFF;"><img src="/images/deconnexion.png" /></a></li>
		    	</ul>
		    </div>
		</div>
	</div>
