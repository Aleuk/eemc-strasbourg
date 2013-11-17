package fr.eemcs.schedulemanager.entity;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class EmailVO extends ObjectVO {
	@Persistent
	private String categorie;
	
	@Persistent
	private String description;
	
	@Persistent
	private Text contenu;
	
	@Persistent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCreationEmail = new Date();
	
	@Persistent
	private String email;
	
	@Persistent
	private String telephone;
	
	
	public EmailVO() {
	}
	
	public EmailVO(String email) {
		this.email = email;
	}
	
	public String toString() {
		return email; 
	}
	
	// GETTERS & SETTERS
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContenuString() {
		if(contenu != null) {
			return contenu.getValue();
		} else {
			return null;
		}
	}
	
	public Text getContenu() {
		return contenu;
	}

	public void setContenu(Text contenu) {
		this.contenu = contenu;
	}
	
	public void setContenu(String contenu) {
		this.contenu = new Text(contenu);
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public Date getDateCreationEmail() {
		return dateCreationEmail;
	}

	public void setDateCreationEmail(Date dateCreationEmail) {
		this.dateCreationEmail = dateCreationEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
