<!doctype html>
<!-- The DOCTYPE declaration above will set the     -->
<!-- browser's rendering engine into                -->
<!-- "Standards Mode". Replacing this declaration   -->
<!-- with a "Quirks Mode" doctype is not supported. -->
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=windows-1252">
    
    <link type="text/css" rel="stylesheet" href="/css/screens.css">
    <title>EEMCS Schedule Manager</title>
    
    <!--                                           -->
    <!-- This script loads your compiled module.   -->
    <!-- If you add any GWT meta tags, they must   -->
    <!-- be added before this line.                -->
    <!--                                           -->
    <script type="text/javascript" src="/schedulemanager/schedulemanager.nocache.js"></script>
  </head>

  <!--                                           -->
  <!-- The body can have arbitrary html, or      -->
  <!-- you can leave the body empty if you want  -->
  <!-- to create a completely dynamic UI.        -->
  <!--                                           -->
  <body>
  
	<div id="conteneur">
		<tiles:insert attribute="header"/>
		<tiles:insert attribute="menu"/>
		<tiles:insert attribute="body"/>
		<tiles:insert attribute="footer"/>
    </div>
  </body>
</html>