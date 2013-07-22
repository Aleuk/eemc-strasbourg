<!-- OPTIONAL: include this if you want history support -->
    <%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>


<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>
    
    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>
    <script>
    <% UserService userService = UserServiceFactory.getUserService();
        		System.out.println(request.getRequestURI());
    %>
    </script>
    	<div id="header">
    		<div id="header_left"></div><div id="header_center"><a href='<%= userService.createLogoutURL("/") %>'><img src="../images/deconnexion.png" /></a></div><div id="header_right"></div>
    	</div>