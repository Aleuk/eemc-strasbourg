<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<div class="fond_chemin" id="chemin">
		&nbsp;<a href="javascript:ajouterContact();"><img src="../images/ajouterContact.png" height="20px" width="20px"/> Créer</a>
	</div>
	<div class="fond_corps" id="corps">
		<s:if test="%{listeMembres.size() == 0}">
			<p>La liste est vide</p>
		</s:if>
		<s:else>
			<display:table id="dataTable" name="listeMembres"
				pagesize="15" decorator="fr.eemcs.schedulemanager.decorator.ContactDecorator">
				<display:column property="nom" title="table.title.nom" />
				<display:column property="nomKH" title="table.title.nomKH" />
				<display:column property="dateNaissance" title="table.title.dateNaissance" />
				<display:column property="actions" title="table.title.actions" />
			</display:table>
		
			<s:iterator value="listeMembres">
				<p><s:property value="nom"/> <s:property value="prenom"/></p>
			</s:iterator>
			<p>La liste n'est pas vide</p>
		</s:else>
	</div>
	<script>
		function supprimerContact(id, nom) {
			if(confirm('Êtes-vous sûr de vouloir supprimer ' + nom + ' ?')) {
				
			}
		}
		function modifierContact(id) {
			alert('TODO à modifier');
		}
		function ajouterContact() {
			window.location.href = "/membres_add";
		}
	</script>