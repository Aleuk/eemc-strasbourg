
<%@ taglib prefix="s" uri="/struts-tags" %>

	<div class="fond_corps" id="corps">
		
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