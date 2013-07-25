package fr.eemcs.schedulemanager.entity;

import java.util.Date;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

@PersistenceCapable (identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class ObjectVO {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;
	
	@Persistent
	private User creationUser;
	
	@Persistent
	private User modificationUser;
	
	@Persistent
	private Date creationDate;
	
	@Persistent
	private Date modificationDate;

	public ObjectVO() {
		this.creationDate = new Date();
		this.modificationDate = new Date();
	}
	
	public Key getId() {
		return id;
	}

	public User getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(User creationUser) {
		this.creationUser = creationUser;
	}

	public User getModificationUser() {
		return modificationUser;
	}

	public void setModificationUser(User modificationUser) {
		this.modificationUser = modificationUser;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	public void setCreation(Date date, User user) {
		this.creationDate = new Date();
		this.modificationDate = new Date();
		this.creationUser = user;
		this.modificationUser = user;
	}
	
	public void setModification(Date date, User user) {
		this.modificationDate = new Date();
		this.modificationUser = user;
	}
}
