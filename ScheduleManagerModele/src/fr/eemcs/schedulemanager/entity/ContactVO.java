package fr.eemcs.schedulemanager.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ContactVO {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;
	
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

	public ContactVO(String nom, String prenom, Date dateNaissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
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
