<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<div class="fond_corps" id="corps">
		<display:table id="dataTable" name="listeMembres"
			pagesize="15" decorator="fr.eemcs.schedulemanager.decorator.ContactDecorator">
			<display:column property="nom" title="table.title.nom" />
			<display:column property="prenom" title="table.title.prenom" />
		</display:table>
		<s:if test="%{listeMembres.size() == 0}">
			<p>La liste est vide</p>
		</s:if>
		<s:else>
			<s:iterator value="listeMembres">
				<p><s:property value="nom"/> <s:property value="prenom"/></p>
			</s:iterator>
			<p>La liste n'est pas vide</p>
		</s:else>
	</div>