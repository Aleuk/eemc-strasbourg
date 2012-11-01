<!doctype html>
<!-- The DOCTYPE declaration above will set the     -->
<!-- browser's rendering engine into                -->
<!-- "Standards Mode". Replacing this declaration   -->
<!-- with a "Quirks Mode" doctype is not supported. -->

<%@page import="java.util.List"%>
<%@page import="fr.eemcs.schedulemanager.entity.ContactVO"%>
<%@page import="fr.eemcs.schedulemanager.database.PMF"%>
<%@page import="javax.jdo.PersistenceManager"%>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=windows-1252">
    
    <link type="text/css" rel="stylesheet" href="css/screens.css">
    <title>EEMCS Schedule Manager</title>
    
    <!--                                           -->
    <!-- This script loads your compiled module.   -->
    <!-- If you add any GWT meta tags, they must   -->
    <!-- be added before this line.                -->
    <!--                                           -->
    <script type="text/javascript" src="schedulemanager/schedulemanager.nocache.js"></script>
  </head>

  <!--                                           -->
  <!-- The body can have arbitrary html, or      -->
  <!-- you can leave the body empty if you want  -->
  <!-- to create a completely dynamic UI.        -->
  <!--                                           -->
  <body>
  
	<div id="conteneur">
		<jsp:include page="/pages/header.jsp"></jsp:include>
		<jsp:include page="/pages/menu.jsp"></jsp:include>
		<div class="fond_corps" id="corps">
		<a href="/membres_add">Ajouter un membre</a>
		</div>
		<jsp:include page="/pages/footer.jsp"></jsp:include>
    </div>
  </body>
</html>
