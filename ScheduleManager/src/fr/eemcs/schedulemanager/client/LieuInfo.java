package fr.eemcs.schedulemanager.client;

import java.io.Serializable;

public class LieuInfo implements Serializable {
	private String nom;

	private String nomKH;
	
	private String adresse;
	
	private String codePostal;
	
	private String ville;
	
	private String idContact;

	public LieuInfo() {
		
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
