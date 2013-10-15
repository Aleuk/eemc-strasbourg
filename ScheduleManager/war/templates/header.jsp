
    <%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>

    <script>
    <% UserService userService = UserServiceFactory.getUserService();
        		System.out.println(request.getRequestURI());
    %>
    </script>
    	<div class="row">
    		<div class="span12">
		    	<div id="header">
		    		<img src="../images/header.png"/><a href='<%= userService.createLogoutURL("/") %>'><img src="/images/deconnexion.png" /></a>
		    	</div>
		    </div>
		</div>