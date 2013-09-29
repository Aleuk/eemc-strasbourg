<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<div class="fond_chemin" id="chemin">
		&nbsp;<a href="javascript:ajouterContact();"><img src="/images/ajouterContact.png" height="25px" width="25px" style="vertical-align:middle"/> Cr�er</a>
	</div>
	<div class="fond_corps" id="corps">
				<display:table id="dataTable" name="listeContacts"
					pagesize="15" decorator="fr.eemcs.schedulemanager.decorator.ContactDecorator">
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
			if(confirm('�tes-vous s�r de vouloir supprimer ' + nom + ' ?')) {
				window.location.href = "/controller/contact/delete?idContact=" + id;
			}
		}
		function modifierContact(id) {
			window.location.href = "/controller/contact/modif?idContact=" + id;
		}
		function ajouterContact() {
			window.location.href = "/controller/contact/add";
		}
	</script>