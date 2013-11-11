<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

		<jsp:include page="/pages/listeParametrage.jsp" /> 
		
			<display:table id="dataTable" name="listeArticles"
				pagesize="15" requestURI="/controller/parametrage/article/list" 
				class="table table-striped table-bordered table-hover" 
				decorator="fr.eemcs.schedulemanager.decorator.ArticleDecorator">
				<display:column property="dateCreationArticle" keyTitle="table.title.nom" />
				<display:column property="auteur" keyTitle="table.title.auteur" />
				<display:column property="description" keyTitle="table.title.description" />
				<display:column property="actions" keyTitle="table.title.actions" />
			</display:table>
		

	<script>
		function supprimerArticle(id) {
			if(confirm('Êtes-vous sûr de vouloir supprimer cet article ?')) {
				window.location.href = "/controller/parametrage/article/delete?idArticle=" + id;
			}
		}
		function modifierArticle(id) {
			window.location.href = "/controller/parametrage/article/modif?idArticle=" + id;
		}
	</script>