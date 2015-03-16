<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	&nbsp;<a href="javascript:ajouterArticle();"><img src="/images/ajouterArticle.png" height="25px" width="25px" style="vertical-align:middle"/> <t:get name="path.articles" /></a>

<script>
	function ajouterArticle() {
		window.location.href = "/controller/parametrage/article/add";
	}
</script>