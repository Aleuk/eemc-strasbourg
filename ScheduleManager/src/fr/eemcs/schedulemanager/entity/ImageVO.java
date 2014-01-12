package fr.eemcs.schedulemanager.entity;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Blob;

@PersistenceCapable
public class ImageVO extends ObjectVO{
	
	@Persistent
	private String dossier;
	
	@Persistent
    private String name;
	
	@Persistent
	private BlobKey image;

	public ImageVO() {
		
	}
	
	// GETTERS & SETTERS
	
	public String getDossier() {
		return dossier;
	}

	public void setDossier(String dossier) {
		this.dossier = dossier;
	}

	public BlobKey getImage() {
		return image;
	}

	public void setImage(BlobKey image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
