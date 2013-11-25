<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

<div class="row">
	<div class="col-lg-9 text-right">
		<!-- TODO ne pouvoir créer que le programme du mois suivant -->
		&nbsp;<a href="javascript:ajouterProgramme();"><img src="/images/ajouterProgramme.png" height="25px" width="25px" style="vertical-align:middle"/> <t:get name="path.programmes" /></a>
	</div>
</div>

<script>
	function ajouterProgramme() {
		window.location.href = "/controller/parametrage/programme/add";
	}
</script>