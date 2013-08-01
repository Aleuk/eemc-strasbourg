<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<div class="fond_chemin" id="chemin">
		&nbsp;<t:get name="path.contact.form" />
	</div>
	<div class="fond_corps" id="corps">
		<s:form action="contact_save" validate="true" theme="xhtml">
			<input type="hidden" id="idContact" name="idContact" value="${idContact}" />
			<table>
				<tr>
					<td colspan="2"><t:get name="form.title.nouveau.contact"/></td>
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
										maxlength="50" 
										tabindex="1" 
										required="true"
										label="Nom"/>
				</tr>
				<tr>
					<s:textfield 	id="contact.prenom" 
										name="contact.prenom" 
										size="20" 
										maxlength="50" 
										tabindex="2"  
										required="true"
										label="Prénom"/>
				</tr>
				<tr>
					<td align="right"><t:get name="form.contact.label.nom.cambodgien" /> :</td>
					<td><input type="text" 	class="inputKH" id="contact.nomKH" 
										name="contact.nomKH" 
										value="${contact.nomKH}"
										size="26" 
										maxlength="50" 
										tabindex="3"/></td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.contact.label.prenom.cambodgien" /> :</td>
					<td><input type="text" 	class="inputKH" id="contact.prenomKH" 
										name="contact.prenomKH" 
										value="${contact.prenomKH}"
										size="26" 
										maxlength="50" 
										tabindex="4"/></td>
				</tr>
				<tr>
					<td class="tdLabel"><t:get name="form.contact.label.dateNaissance" /> :</td>
					<td><input type="date" id="contact.dateNaissance" name="dateNaissance" value="${dateNaissance}" tabindex="5"></td>
				</tr>
				<tr>
					<s:textfield 	id="contact.adresse" 
										name="contact.adresse" 
										size="20" 
										maxlength="50" 
										tabindex="6"
										label="Adresse"/>
				</tr>
				<tr>
					<s:textfield 	id="contact.codePostal" 
										name="contact.codePostal" 
										size="20" 
										maxlength="50" 
										tabindex="7"
										label="Code Postal"/>
				</tr>
				<tr>
					<s:textfield 	id="contact.ville" 
										name="contact.ville" 
										size="20" 
										maxlength="50" 
										tabindex="8"
										label="Ville"/>
				</tr>
				<tr>
					<s:textfield 	id="contact.email" 
										name="contact.email" 
										size="20" 
										maxlength="50" 
										tabindex="9"
										label="Email"/>
				</tr>
				<tr>
					<s:textfield 	id="contact.telephone1" 
										name="contact.telephone1" 
										size="20" 
										maxlength="50" 
										tabindex="10"
										label="Téléphone fixe"/>
				</tr>
				<tr>
					<s:textfield 	id="contact.telephone2" 
										name="contact.telephone2" 
										size="20" 
										maxlength="50" 
										tabindex="11"
										label="Téléphone portable"/>
				</tr>
				<tr>
					<td>
						<s:checkbox name="contact.predicateur" value="contact.predicateur" label="Prédicateur"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:checkbox name="contact.conducteurLouange" value="contact.conducteurLouange" label="Conducteur de louange"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:checkbox name="contact.responsable" value="contact.responsable" label="Responsable"/>
					</td>
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
			window.location.href = "/contact_list";
		}
		
		function save() {
			document.forms[0].submit();
		}
	</script>
	