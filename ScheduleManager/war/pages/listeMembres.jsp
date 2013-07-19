<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<div class="fond_chemin" id="chemin">
		&nbsp;<a href="javascript:ajouterContact();"><img src="../images/ajouterContact.png" height="20px" width="20px"/> Créer</a>
	</div>
	<div class="fond_corps" id="corps">
		<s:if test="%{listeMembres.size() == 0}">
			<p>Il n'y a pas de membres pour le moment.</p>
		</s:if>
		<s:else>
			<display:table id="dataTable" name="listeMembres"
				pagesize="15" decorator="fr.eemcs.schedulemanager.decorator.ContactDecorator">
				<display:column property="nom" title="table.title.nom" />
				<display:column property="nomKH" title="table.title.nomKH" />
				<display:column property="dateNaissance" title="table.title.dateNaissance" />
				<display:column property="email" title="table.title.email" />
				<display:column property="telephone" title="table.title.telephone" />
				<display:column property="actions" title="table.title.actions" />
			</display:table>
		
			<s:iterator value="listeMembres">
				<p><s:property value="nom"/> <s:property value="prenom"/></p>
			</s:iterator>
		</s:else>
	</div>
	<script>
		function supprimerContact(id, nom) {
			if(confirm('Êtes-vous sûr de vouloir supprimer ' + nom + ' ?')) {
				window.location.href = "/membres_delete?idContact=" + id;
			}
		}
		function modifierContact(id) {
			window.location.href = "/membres_modif?idContact=" + id;
		}
		function ajouterContact() {
			window.location.href = "/membres_add";
		}
	</script>