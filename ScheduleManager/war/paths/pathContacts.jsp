<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

<div class="row">
	<div class="col-lg-9 text-right">
		&nbsp;<a href="javascript:ajouterContact();"><img src="/images/ajouterContact.png" height="25px" width="25px" style="vertical-align:middle"/> <t:get name="path.contacts" /></a>
	</div>
</div>

<script>
	function ajouterContact() {
		window.location.href = "/controller/contact/add";
	}
</script>