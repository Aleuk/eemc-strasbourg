package fr.eemcs.schedulemanager.entity;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.format.annotation.DateTimeFormat;

import fr.eemcs.schedulemanager.helper.FormatHelper;

@PersistenceCapable
public class ContactVO extends ObjectVO{
	@Persistent
	private String nom;
	
	@Persistent
	private String prenom;
	
	@Persistent
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateNaissance;
	
	@Persistent
	private String nomKH;
	
	@Persistent
	private String prenomKH;
	
	@Persistent
	private String civilite;
	
	@Persistent
	private String email;
	
	@Persistent
	private String adresse;
	
	@Persistent
	private String codePostal;
	
	@Persistent
	private String ville;
	
	@Persistent
	private String telephone1;
	
	@Persistent
	private String telephone2;
	
	@Persistent
	private boolean predicateur = false;
	
	@Persistent
	private boolean conducteurLouange = false;
	
	@Persistent
	private boolean responsable = false;
	
	@Persistent
	private boolean membre = false;
	
	public ContactVO(String nom, String prenom, Date dateNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}
	
	
	public String toString() {
		return nom + " " + prenom; 
	}
	
	public String getNomPrenom() {
		return nom + " " + prenom;
	}
	
	// GETTERS & SETTERS
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = FormatHelper.getDate(dateNaissance, "dd/MM/yyyy");
	}

	public String getNomKH() {
		return nomKH;
	}

	public void setNomKH(String nomKH) {
		this.nomKH = nomKH;
	}

	public String getPrenomKH() {
		return prenomKH;
	}

	public void setPrenomKH(String prenomKH) {
		this.prenomKH = prenomKH;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public boolean isPredicateur() {
		return predicateur;
	}

	public void setPredicateur(boolean predicateur) {
		this.predicateur = predicateur;
	}

	public boolean isConducteurLouange() {
		return conducteurLouange;
	}

	public void setConducteurLouange(boolean conducteurLouange) {
		this.conducteurLouange = conducteurLouange;
	}

	public boolean isResponsable() {
		return responsable;
	}

	public void setResponsable(boolean responsable) {
		this.responsable = responsable;
	}
	
	public boolean isMembre() {
		return membre;
	}

	public void setMembre(boolean membre) {
		this.membre = membre;
	}
}
