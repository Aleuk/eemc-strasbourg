package fr.eemcs.schedulemanager.entity;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.format.annotation.DateTimeFormat;

import fr.eemcs.schedulemanager.helper.FormatHelper;

@PersistenceCapable
public class ArticleVO extends ObjectVO {
	@Persistent
	private String categorie;
	
	@Persistent
	private String description;
	
	@Persistent
	private String contenu;
	
	@Persistent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCreationArticle = new Date();
	
	@Persistent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateModificationArticle = new Date();
	
	@Persistent
	private String auteur;
	
	
	public ArticleVO() {
	}
	
	public ArticleVO(String auteur) {
		this.auteur = auteur;
	}
	
	public String toString() {
		return auteur; 
	}
	
	// GETTERS & SETTERS
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDateCreationArticle() {
		return dateCreationArticle;
	}

	public void setDateCreationArticle(Date dateCreationArticle) {
		this.dateCreationArticle = dateCreationArticle;
	}

	public Date getDateModificationArticle() {
		return dateModificationArticle;
	}

	public void setDateModificationArticle(Date dateModificationArticle) {
		this.dateModificationArticle = dateModificationArticle;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
}
