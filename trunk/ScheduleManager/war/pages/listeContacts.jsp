<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<div class="fond_chemin" id="chemin">
		&nbsp;<a href="javascript:ajouterContact();"><img src="../images/ajouterContact.png" height="25px" width="25px" style="vertical-align:middle"/> Créer</a>
	</div>
	<div class="fond_corps" id="corps">
		<s:if test="%{listeContacts.size() == 0}">
			<p>Il n'y a pas de contacts pour le moment.</p>
		</s:if>
		<s:else>
				<display:table id="dataTable" name="listeContacts"
					pagesize="15" decorator="fr.eemcs.schedulemanager.decorator.ContactDecorator">
					<display:column property="nom" keyTitle="table.title.nom" />
					<display:column property="nomKH" class="inputKH"  keyTitle="table.title.nomKH" />
					<display:column property="dateNaissance" keyTitle="table.title.dateNaissance" />
					<display:column property="email" keyTitle="table.title.email" />
					<display:column property="telephone" keyTitle="table.title.telephone" />
					<display:column property="actions" keyTitle="table.title.actions" />
				</display:table>
		
			<s:iterator value="listeContacts">
				<p><s:property value="nom"/> <s:property value="prenom"/></p>
			</s:iterator>
		</s:else>
	</div>
	<script>
		function supprimerContact(id, nom) {
			if(confirm('Êtes-vous sûr de vouloir supprimer ' + nom + ' ?')) {
				window.location.href = "/contact_delete?idContact=" + id;
			}
		}
		function modifierContact(id) {
			window.location.href = "/contact_modif?idContact=" + id;
		}
		function ajouterContact() {
			window.location.href = "/contact_add";
		}
	</script>