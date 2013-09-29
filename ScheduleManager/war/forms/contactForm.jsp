<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<div class="fond_chemin" id="chemin">
		&nbsp;<t:get name="path.contact.form" />
	</div>
	<div class="fond_corps" id="corps">
		<s:form modelAttribute="contactForm" action="/controller/contact/save" validate="true" theme="xhtml">
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
					<td align="right"><t:get name="form.label.civilite" /> :</td>
					<td><s:select label="Civilité" 
								headerKey="-1" headerValue="Civilité" 
								name="civilite"
								path="civilite"
								items="${mapCivilites}" />
					</td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.label.nom" /> :</td>
					<td><s:input	id="nom" 
										name="nom" 
										path="nom"
										size="20" 
										maxlength="50" 
										tabindex="1" 
										required="true"
										label="Nom"/>
					</td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.label.prenom" /> :</td>
					<td><s:input 	id="prenom" 
										name="prenom"
										path="prenom" 
										size="20" 
										maxlength="50" 
										tabindex="2"  
										required="true"
										label="Prénom"/>
					</td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.label.nom.cambodgien" /> :</td>
					<td><input type="text" 	class="inputKH" id="nomKH" 
										name="nomKH" 
										value="${nomKH}"
										size="26" 
										maxlength="50" 
										tabindex="3"/></td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.label.prenom.cambodgien" /> :</td>
					<td><input type="text" 	class="inputKH" id="prenomKH" 
										name="prenomKH" 
										value="${prenomKH}"
										size="26" 
										maxlength="50" 
										tabindex="4"/></td>
				</tr>
				<tr>
					<td class="tdLabel"><t:get name="form.label.dateNaissance" /> :</td>
					<td><input type="date" id="dateNaissance" name="dateNaissance" value="${dateNaissance}" tabindex="5"></td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.label.adresse" /> :</td>
					<td><s:input 	id="adresse" 
										name="adresse" 
										path="adresse"
										size="20" 
										maxlength="50" 
										tabindex="6"
										label="Adresse"/>
					</td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.label.codePostal" /> :</td>
					<td><s:input 	id="codePostal" 
										name="codePostal" 
										path="codePostal"
										size="20" 
										maxlength="50" 
										tabindex="7"
										label="Code Postal"/>
					</td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.label.ville" /> :</td>
					<td><s:input 	id="ville" 
										name="ville" 
										path="ville"
										size="20" 
										maxlength="50" 
										tabindex="8"
										label="Ville"/>
					</td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.label.email" /> :</td>
					<td><s:input 	id="email" 
										name="email" 
										path="email"
										size="20" 
										maxlength="50" 
										tabindex="9"
										label="Email"/>
					</td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.label.telephone1" /> :</td>
					<td><s:input 	id="telephone1" 
										name="telephone1" 
										path="telephone1"
										size="20" 
										maxlength="50" 
										tabindex="10"
										label="Téléphone fixe"/>
					</td>
				</tr>
				<tr>
					<td align="right"><t:get name="form.label.telephone2" /> :</td>
					<td><s:input 	id="telephone2" 
										name="telephone2"
										path="telephone2" 
										size="20" 
										maxlength="50" 
										tabindex="11"
										label="Téléphone portable"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:checkbox name="predicateur" path="predicateur" label="Prédicateur"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:checkbox name="conducteurLouange" path="conducteurLouange" label="Conducteur de louange"/>
					</td>
				</tr>
				<tr>
					<td>
						<s:checkbox name="responsable" path="responsable" label="Responsable"/>
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
			window.location.href = "/controller/contact/list";
		}
		
		function save() {
			document.forms[0].submit();
		}
	</script>
	