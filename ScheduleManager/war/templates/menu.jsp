   	<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
	<%@page import="com.google.appengine.api.users.UserService"%>

    <% UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String urlConnexion = userService.createLoginURL("/controller/enter");
		
        		System.out.println("called page : " + request.getAttribute("javax.servlet.forward.request_uri"));
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
			    			<li><a href="/controller/activite/blog">Activit�s</a></li>
			    			<li><a href="/controller/planAcces">Plan d'acc�s</a></li>
                        </ul>
                      </li>
                      <li class="dropdown">
                      		<a href="/controller/media/list" class="dropdown-toggle" data-toggle="dropdown">M�dias <b class="caret"></b></a>
				    		<ul class="dropdown-menu">
				    			<li><a href="/controller/message/blog">Messages</a></li>
				    			<li><a href="/controller/video/blog">Vid�os</a></li>
				    			<li><a href="/controller/musique/blog">Musique</a></li>
				    			<li><a href="/controller/photo/list">Photos</a></li>
				    		</ul>
				    	</li>
				    	<li><a href="/controller/projet/blog">Projets</a></li>
				    	<li><a href="/controller/lecture/blog">&nbsp;Versets du jour&nbsp;</a></li>
				    	<li><a href="/controller/lien/blog">&nbsp;Liens&nbsp;</a></li>
				    	<li><a href="/controller/nousContacter">&nbsp;Nous contacter&nbsp;</a></li><!-- sujets de pri�res ici -->
				    	<% if(user != null) {%>
					    	<li><a href="/controller/contact/list">Contacts</a></li>
					    	<li><a href="/controller/parametrage/list">Param�trage</a></li>
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
              
              <div class="content">
              <h1 class="title">Slide Down Box Menu with jQuery and CSS3</h1>
              		<a href="<%= urlConnexion %>">Mon compte</a>
                   	<% if(user != null) {%>
		    			<a href='<%= userService.createLogoutURL("/") %>' ><img src="/images/deconnexion.png" /></a>
		    		<% }%>
			<ul id="sdt_menu" class="sdt_menu">
				
				<li>
					<a href="#">
						<img src="/images/menu_eglise.jpg" alt=""/>
						<span class="sdt_active"></span>
						<span class="sdt_wrap">
							<span class="sdt_link">Eglise</span>
							<span class="sdt_descr">Nous conna�tre</span>
						</span>
					</a>
					<div class="sdt_box">
						<a href="/controller/historique/blog">Historique</a>
						<a href="#">Comit� Directeur</a>
						<a href="/controller/activite/blog">Activit�s</a>
						<a href="/controller/planAcces">Plan d'acc�s</a>
					</div>
				</li>
				<li>
					<a href="#">
						<img src="/images/menu_medias.jpg" alt=""/>
						<span class="sdt_active"></span>
						<span class="sdt_wrap">
							<span class="sdt_link">M�dias</span>
							<span class="sdt_descr">Nos activit�s</span>
						</span>
					</a>
					<div class="sdt_box">
							<a href="/controller/message/blog">Messages</a>
							<a href="/controller/photo/list">Photos</a>
							<a href="/controller/video/blog">Vid�os</a>
							<a href="/controller/musique/blog">Musique</a>
					</div>
				</li>
				<li>
					<a href="/controller/projet/blog">
						<img src="/images/menu_projets.jpg" alt=""/>
						<span class="sdt_active"></span>
						<span class="sdt_wrap">
							<span class="sdt_link">Projets</span>
							<span class="sdt_descr">Notre projet de construction</span>
						</span>
					</a>
				</li>
				<li>
					<a href="/controller/lecture/blog">
						<img src="/images/menu_bible.png" alt=""/>
						<span class="sdt_active"></span>
						<span class="sdt_wrap">
							<span class="sdt_link">La Bible</span>
							<span class="sdt_descr">Versets du jour</span>
						</span>
					</a>
				</li>
				<li>
					<a href="/controller/lien/blog">
						<img src="/images/menu_liens.jpg" alt=""/>
						<span class="sdt_active"></span>
						<span class="sdt_wrap">
							<span class="sdt_link">Liens</span>
							<span class="sdt_descr">Partenaires</span>
						</span>
					</a>
				</li>
				<li>
					<a href="/controller/nousContacter">
						<img src="/images/menu_contact.png" alt=""/>
						<span class="sdt_active"></span>
						<span class="sdt_wrap">
							<span class="sdt_link">Contact</span>
							<span class="sdt_descr">Nous �crire</span>
						</span>
					</a>
				</li>
				<% if(user != null) {%>
					<li>
						<a href="/controller/contact/list">
							<img src="/images/menu_repertoire.png" alt=""/>
							<span class="sdt_active"></span>
							<span class="sdt_wrap">
								<span class="sdt_link">Adresses</span>
								<span class="sdt_descr">Carnet d'adresses</span>
							</span>
						</a>
					</li>
					<li>
						<a href="/controller/parametrage/list">
							<img src="/images/menu_parametrage.png" alt=""/>
							<span class="sdt_active"></span>
							<span class="sdt_wrap">
								<span class="sdt_link">Admin</span>
								<span class="sdt_descr">Param�trage</span>
							</span>
						</a>
					</li>
				<% } %>
			</ul>
		</div>
		
		<!-- The JavaScript -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
		<script type="text/javascript" src="/js/jquery.easing.1.3.js"></script>
        <script type="text/javascript">
            $(function() {
				/**
				* for each menu element, on mouseenter, 
				* we enlarge the image, and show both sdt_active span and 
				* sdt_wrap span. If the element has a sub menu (sdt_box),
				* then we slide it - if the element is the last one in the menu
				* we slide it to the left, otherwise to the right
				*/
                $('#sdt_menu > li').bind('mouseenter',function(){
					var $elem = $(this);
					$elem.find('img')
						 .stop(true)
						 .animate({
							'width':'146px',
							'height':'146px',
							'left':'0px'
						 },400,'easeOutBack')
						 .andSelf()
						 .find('.sdt_wrap')
					     .stop(true)
						 .animate({'top':'140px'},500,'easeOutBack')
						 .andSelf()
						 .find('.sdt_active')
					     .stop(true)
						 .animate({'height':'146px'},300,function(){
						var $sub_menu = $elem.find('.sdt_box');
						if($sub_menu.length){
							var left = '146px';
							if($elem.parent().children().length == $elem.index()+1)
								left = '-146px';
							$sub_menu.show().animate({'left':left},200);
						}	
					});
				}).bind('mouseleave',function(){
					var $elem = $(this);
					var $sub_menu = $elem.find('.sdt_box');
					if($sub_menu.length)
						$sub_menu.hide().css('left','0px');
					
					$elem.find('.sdt_active')
						 .stop(true)
						 .animate({'height':'0px'},300)
						 .andSelf().find('img')
						 .stop(true)
						 .animate({
							'width':'0px',
							'height':'0px',
							'left':'85px'},400)
						 .andSelf()
						 .find('.sdt_wrap')
						 .stop(true)
						 .animate({'top':'25px'},500);
				});
            });
        </script>
