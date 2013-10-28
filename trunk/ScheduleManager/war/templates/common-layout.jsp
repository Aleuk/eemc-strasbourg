<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache" content="no store">
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="last-modified" content="" />
	<meta name="gwt:property" content="locale=fr_FR">
    
    <link type="text/css" rel="stylesheet" href="/css/screens.css">
    <link type="text/css" rel="stylesheet" href="/css/styles.css">
    <tiles:useAttribute id="key" name="title"/>
	<title><t:get name="${key}" /></title>
    <!-- <title><tiles:insertAttribute name="title" ignore="true" /></title>-->
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" media="all">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.min.js"></script>
    
    <!--                                           -->
    <!-- This script loads your compiled module.   -->
    <!-- If you add any GWT meta tags, they must   -->
    <!-- be added before this line.                -->
    <!--                                           -->
    <script type="text/javascript" src="/schedulemanager/schedulemanager.nocache.js"></script>
  </head>
  <body>
  
  <iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position:absolute;width:0;height:0;border:0"></iframe>
    
    <!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
    <noscript>
      <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>
		<tiles:insertAttribute name="menu"/>
		<div class="container">
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="path"/>
			<div class="row">
				<tiles:insertAttribute name="pub"/>
				<tiles:insertAttribute name="body"/>
				<tiles:insertAttribute name="planning"/>
			</div>
			<tiles:insertAttribute name="footer"/>
		</div>
  </body>
</html>
