<%@ taglib prefix="s" uri="/struts-tags" %>

	<div class="fond_chemin" id="chemin">
		&nbsp;Création d'un profil
	</div>
	<div class="fond_corps" id="corps">
		<s:form action="membres_save" validate="true" theme="xhtml">
			<table>
				<tr>
					<td colspan="2"><s:text name="title.new.membre"/></td>
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
					<s:select label="Civilité" 
								headerKey="-1" headerValue="Civilité"
								list="listeCivilites" 
								name="contact.civilite" />
				</tr>
				<tr>
					<s:textfield 	id="contact.nom" 
										name="contact.nom" 
										size="20" 
										maxlength="32" 
										tabindex="1" 
										required="true"
										label="Nom"/>
				</tr>
				<tr>
					<s:textfield 	id="contact.prenom" 
										name="contact.prenom" 
										size="20" 
										maxlength="32" 
										tabindex="2"  
										required="true"
										label="Prénom"/>
				</tr>
				<tr>
					<td align="right">Nom en khmer :</td>
					<td><input type="text" 	class="inputKH" id="contact.nomKH" 
										name="contact.nomKH" 
										size="26" 
										maxlength="32" 
										tabindex="1"/></td>
				</tr>
				<tr>
					<td align="right">Prénom en khmer :</td>
					<td><input type="text" 	class="inputKH" id="contact.prenomKH" 
										name="contact.prenomKH" 
										size="26" 
										maxlength="32" 
										tabindex="1"/></td>
				</tr>
				<tr>
					<td class="tdLabel">Date de naissance :</td>
					<td><input type="date" id="contact.dateNaissance" name="dateNaissance"></td>
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
			window.location.href = "/membres_list";
		}
		
		function save() {
			document.forms[0].submit();
		}
	</script>
	