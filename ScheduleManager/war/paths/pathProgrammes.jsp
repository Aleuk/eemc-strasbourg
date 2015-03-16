<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

		<!-- TODO ne pouvoir créer que le programme du mois suivant -->
		&nbsp;<a href="javascript:addEvent();"><img src="/images/ajouterProgramme.png" height="25px" width="25px" style="vertical-align:middle"/> <t:get name="path.programmes" /></a>

<script>
	function addEvent() {
		window.location.href = "/controller/parametrage/evenement/add";
	}
</script>