package fr.eemcs.schedulemanager.entity;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class ContactVO extends ObjectVO{
	
	@Persistent
	private String nom;
	
	@Persistent
	private String prenom;
	
	@Persistent
	private Date dateNaissance;
	
	@Persistent
	private String nomKH;
	
	@Persistent
	private String prenomKH;

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
	
	
}
