<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>


		<div class="col-lg-10">
			<display:table id="dataTable" name="listeContacts"
				pagesize="15" class="table table-striped table-bordered table-hover" decorator="fr.eemcs.schedulemanager.decorator.ContactDecorator">
				<display:column property="nom" keyTitle="table.title.nom" />
				<display:column property="nomKH" class="inputKH"  keyTitle="table.title.nomKH" />
				<display:column property="dateNaissance" keyTitle="table.title.dateNaissance" />
				<display:column property="email" keyTitle="table.title.email" />
				<display:column property="telephone" keyTitle="table.title.telephone" />
				<display:column property="actions" keyTitle="table.title.actions" />
			</display:table>
		</div>

	<script>
		function supprimerContact(id, nom) {
			if(confirm('Êtes-vous sûr de vouloir supprimer ' + nom + ' ?')) {
				window.location.href = "/controller/contact/delete?idContact=" + id;
			}
		}
		function modifierContact(id) {
			window.location.href = "/controller/contact/modif?idContact=" + id;
		}
	</script>