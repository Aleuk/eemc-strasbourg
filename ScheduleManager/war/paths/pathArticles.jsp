<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

<div class="row">
	<div class="col-lg-11 text-right">
		&nbsp;<a href="javascript:ajouterArticle();"><img src="/images/ajouterArticle.png" height="25px" width="25px" style="vertical-align:middle"/> <t:get name="path.articles" /></a>
	</div>
</div>

<script>
	function ajouterArticle() {
		window.location.href = "/controller/parametrage/article/add";
	}
</script>