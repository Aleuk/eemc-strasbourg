<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="Cache-Control" content="no-cache" max-age="1" must-revalidate>
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache" content="no store">
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="last-modified" content="" />
    
    <link type="text/css" rel="stylesheet" href="/css/screens.css">
    <link type="text/css" rel="stylesheet" href="/css/styles.css">
    <title><tiles:insertAttribute name="title" ignore="true" /></title>
    
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
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu"/>
		<tiles:insertAttribute name="body"/>
		<tiles:insertAttribute name="footer"/>
    </div>
  </body>
</html>
