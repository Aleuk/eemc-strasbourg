<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	
		<div class="well">
			<s:form modelAttribute="contactForm" action="/controller/contact/save" validate="true" theme="xhtml" class="form-horizontal">
			<fieldset>
				<legend>Création d'un nouveau contact</legend>
					<s:hidden path="id" id="idContact" />
					<div class="form-group">
						<label for="selectCivilite" class="col-lg-4 control-label"><t:get name="form.label.civilite" /></label>
						<div class="col-lg-8">
							<s:select label="Civilité" 
										headerKey="-1" headerValue="Civilité" 
										name="civilite"
										path="civilite"
										items="${mapCivilites}" 
										class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputNom" class="col-lg-4 control-label"><t:get name="form.label.nom" /></label>
						<div class="col-lg-8">
							<s:input	id="nom" 
												name="nom" 
												path="nom"
												maxlength="50" 
												tabindex="1" 
												required="true"
												label="Nom"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPrenom" class="col-lg-4 control-label"><t:get name="form.label.prenom" /></label>
						<div class="col-lg-8">
							<s:input 	id="prenom" 
												name="prenom"
												path="prenom" 
												maxlength="50" 
												tabindex="2"  
												required="true"
												label="Prénom"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputNomKH" class="col-lg-4 control-label"><t:get name="form.label.nom.cambodgien" /></label>
						<div class="col-lg-8">
							<input type="text" 	id="nomKH" 
												name="nomKH" 
												value="${contact.nomKH}"
												size="26" 
												maxlength="50" 
												tabindex="3"
												class="inputKH"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputPrenomKH" class="col-lg-4 control-label"><t:get name="form.label.prenom.cambodgien" /></label>
						<div class="col-lg-8">
							<input type="text" 	class="inputKH" id="prenomKH" 
												name="prenomKH" 
												value="${contact.prenomKH}"
												size="26" 
												maxlength="50" 
												tabindex="4"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputDateNaissance" class="col-lg-4 control-label"><t:get name="form.label.dateNaissance" /></label>
						<div class="col-lg-8">
							<s:input path="dateNaissance" type="date" tabindex="5" value="${dateNaissance}"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputAdresse" class="col-lg-4 control-label"><t:get name="form.label.adresse" /></label>
						<div class="col-lg-8">
							<s:input 	id="adresse" 
												name="adresse" 
												path="adresse"
												maxlength="50" 
												tabindex="6"
												label="Adresse"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputCodePostal" class="col-lg-4 control-label"><t:get name="form.label.codePostal" /></label>
						<div class="col-lg-8">
							<s:input 	id="codePostal" 
												name="codePostal" 
												path="codePostal"
												maxlength="50" 
												tabindex="7"
												label="Code Postal"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputVille" class="col-lg-4 control-label"><t:get name="form.label.ville" /></label>
						<div class="col-lg-8">
							<s:input 	id="ville" 
												name="ville" 
												path="ville"
												maxlength="50" 
												tabindex="8"
												label="Ville"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail" class="col-lg-4 control-label"><t:get name="form.label.email" /></label>
						<div class="col-lg-8">
							<s:input 	id="email" 
												name="email" 
												path="email"
												maxlength="50" 
												tabindex="9"
												label="Email"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputTelephone1" class="col-lg-4 control-label"><t:get name="form.label.telephone1" /></label>
						<div class="col-lg-8">
							<s:input 	id="telephone1" 
												name="telephone1" 
												path="telephone1"
												maxlength="50" 
												tabindex="10"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputTelephone2" class="col-lg-4 control-label"><t:get name="form.label.telephone2" /></label>
						<div class="col-lg-8">
							<s:input 	id="telephone2" 
												name="telephone2" 
												path="telephone2"
												maxlength="50" 
												tabindex="10"
												class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label">&nbsp;</label>
						<div class="col-lg-8">
							<div class="checkbox">
								<label>
									<s:checkbox name="predicateur" path="predicateur" label="Prédicateur"/>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label">&nbsp;</label>
						<div class="col-lg-8">
							<div class="checkbox">
								<label>
									<s:checkbox name="conducteurLouange" path="conducteurLouange" label="Conducteur de louange"/>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label">&nbsp;</label>
						<div class="col-lg-8">
							<div class="checkbox">
								<label>
									<s:checkbox name="responsable" path="responsable" label="Responsable"/>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label">&nbsp;</label>
						<div class="col-lg-8">
							<div class="checkbox">
								<label>
									<s:checkbox name="traducteur" path="traducteur" label="Traducteur"/>
								</label>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-lg-8 col-lg-offset-4">
							<a class="btn btn-default" onclick="javascript:annuler();"><t:get name="button.annuler" /></a>
							<button class="btn btn-primary"><t:get name="button.sauvegarder"/></button>
						</div>
					</div>
				</fieldset>
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
	