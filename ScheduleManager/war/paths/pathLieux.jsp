<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

<div class="row">
	<div class="col-lg-11 text-right">
		&nbsp;<a href="javascript:ajouterLieu();"><img src="/images/ajouterLieu.png" height="25px" width="25px" style="vertical-align:middle"/> <t:get name="path.lieux" /></a>
	</div>
</div>

<script>
	function ajouterLieu() {
		window.location.href = "/controller/parametrage/lieu/add";
	}
</script>