<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

<div class="row">
	<div class="col-lg-11 text-right">
		&nbsp;<a href="javascript:ajouterPhoto();"><img src="/images/ajouterImage.png" height="25px" width="25px" style="vertical-align:middle"/> <t:get name="path.photos" /></a>
	</div>
</div>

<script>
	function ajouterPhoto() {
		window.location.href = "/controller/parametrage/photo/add";
	}
</script>