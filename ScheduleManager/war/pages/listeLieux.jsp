<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

		<ul class="nav nav-tabs">
			<li class="active"><a href="/controller/parametrage/lieu/list"><img src="/images/lieu.png" height="15px"/> <t:get name="table.title.lieux" /></a></li>
			<li><a href="#">Article</a></li>
			<li><a href="#">Message</a></li>
			<li><a href="#">Programme</a></li>
		</ul>

		<display:table id="dataTable" name="listeLieux"
			pagesize="15" requestURI="/controller/parametrage/lieu" class="table table-striped table-bordered table-hover" decorator="fr.eemcs.schedulemanager.decorator.LieuDecorator">
			<display:column property="nom" keyTitle="table.title.nom" />
			<display:column property="nomKH" class="inputKH" keyTitle="table.title.nomKH" />
			<display:column property="adresse" keyTitle="table.title.adresse" />
			<display:column property="actions" keyTitle="table.title.actions" />
		</display:table>
		
	<script>
		function supprimerLieu(id, nom) {
			if(confirm('Êtes-vous sûr de vouloir supprimer ' + nom + ' ?')) {
				window.location.href = "/controller/parametrage/lieu/delete?idLieu=" + id;
			}
		}
		function modifierLieu(id) {
			window.location.href = "/controller/parametrage/lieu/modif?idLieu=" + id;
		}
		function ajouterLieu() {
			window.location.href = "/controller/parametrage/lieu/add";
		}
	</script>