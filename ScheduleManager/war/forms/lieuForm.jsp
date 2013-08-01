<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<div class="fond_chemin" id="chemin">
		&nbsp;<t:get name="path.lieu.form" />
	</div>
	<div class="fond_corps" id="corps">
		<s:form action="lieu_save" validate="true" theme="xhtml">
			<input type="hidden" id="idLieu" name="idLieu" value="${idLieu}" />
			<table>
				<tr>
					<td colspan="2"><t:get name="form.title.nouveau.lieu"/></td>
				</tr>
				<tr>
					<td align="right">
						&nbsp;
					</td>
					<td >
						&nbsp;
					</td>
				</tr>
				<tr>
					<s:textfield 	id="lieu.nom" 
										name="lieu.nom" 
										size="50" 
										maxlength="50" 
										tabindex="1" 
										required="true"
										label="Nom"/>
				</tr>
				<tr>
					<s:textfield 	id="lieu.adresse" 
										name="lieu.adresse" 
										size="20" 
										maxlength="50" 
										tabindex="6"
										label="Adresse"/>
				</tr>
				<tr>
					<s:textfield 	id="lieu.codePostal" 
										name="lieu.codePostal" 
										size="20" 
										maxlength="50" 
										tabindex="7"
										label="Code Postal"/>
				</tr>
				<tr>
					<s:textfield 	id="lieu.ville" 
										name="lieu.ville" 
										size="20" 
										maxlength="50" 
										tabindex="8"
										label="Ville"/>
				</tr>
				<tr>
					<td class="tdLabel"><input type="button" value="Annuler" onclick="javascript:annuler();"/></td>
					<td><input type="button" value="Enregistrer" onclick="javascript:save();"/></td>
				</tr>
			</table>
		</s:form>
	</div>
	<script>
		function annuler() {
			window.location.href = "/lieu_list";
		}
		
		function save() {
			document.forms[0].submit();
		}
	</script>
	