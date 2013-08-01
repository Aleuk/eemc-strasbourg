<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<div class="fond_chemin" id="chemin">
		&nbsp;<a href="javascript:ajouterLieu();"><img src="../images/ajouterLieu.png" height="20px" width="20px"/> Créer</a>
	</div>
	<div class="fond_corps" id="corps">
		<s:if test="%{listeLieux.size() == 0}">
			<p>Il n'y a pas de lieux pour le moment.</p>
		</s:if>
		<s:else>
				<display:table id="dataTable" name="listeLieux"
					pagesize="15" decorator="fr.eemcs.schedulemanager.decorator.LieuDecorator">
					<display:column property="nom" keyTitle="table.title.nom" />
					<display:column property="adresse" keyTitle="table.title.adresse" />
					<display:column property="actions" keyTitle="table.title.actions" />
				</display:table>
		
			<s:iterator value="listeLieux">
				<p><s:property value="nom"/></p>
			</s:iterator>
		</s:else>
	</div>
	<script>
		function supprimerLieu(id, nom) {
			if(confirm('Êtes-vous sûr de vouloir supprimer ' + nom + ' ?')) {
				window.location.href = "/lieu_delete?idLieu=" + id;
			}
		}
		function modifierLieu(id) {
			window.location.href = "/lieu_modif?idLieu=" + id;
		}
		function ajouterLieu() {
			window.location.href = "/lieu_add";
		}
	</script>