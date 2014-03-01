<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
	
	<!-- <div id="titlePlanning"><h5><b>Prochaines réunions</b></h5></div>-->
	<div id="planning"></div>
	
	<script type="text/javascript">
		$(document).ready(function() {
		  $('span.tips').cluetip({
			    splitTitle: '|', // use the invoking element's title attribute to populate the clueTip...
                				// ...and split the contents into separate divs where there is a "|"
				showTitle: true // hide the clueTip's heading
		});
	</script>
