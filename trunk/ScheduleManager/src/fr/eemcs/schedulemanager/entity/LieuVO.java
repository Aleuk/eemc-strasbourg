package fr.eemcs.schedulemanager.entity;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class LieuVO extends ObjectVO{
	@Persistent
	private String nom;
	
	@Persistent
	private String nomKH;
	
	@Persistent
	private String adresse;
	
	@Persistent
	private String codePostal;
	
	@Persistent
	private String ville;
	
	@Persistent
	private String idContact;

	public LieuVO() {
		
	}
	
	// GETTERS & SETTERS

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	public String getNomKH() {
		return nomKH;
	}

	public void setNomKH(String nomKH) {
		this.nomKH = nomKH;
	}

	public String getIdContact() {
		return idContact;
	}

	public void setIdContact(String idContact) {
		this.idContact = idContact;
	}
	
}
