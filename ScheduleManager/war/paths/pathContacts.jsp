<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

		&nbsp;<a href="javascript:ajouterContact();"><img src="/images/ajouterContact.png" height="25px" width="25px" style="vertical-align:middle"/> <t:get name="path.contacts" /></a>

<script>
	function ajouterContact() {
		window.location.href = "/controller/contact/add";
	}
</script>