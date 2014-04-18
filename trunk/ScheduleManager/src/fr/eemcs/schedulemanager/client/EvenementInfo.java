package fr.eemcs.schedulemanager.client;

import java.io.Serializable;

public class EvenementInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String date;
	private LieuInfo lieu;
	private String divers;

	public EvenementInfo() {
		
	}
	
	// GETTERS & SETTERS
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public LieuInfo getLieu() {
		return lieu;
	}

	public void setLieu(LieuInfo lieu) {
		this.lieu = lieu;
	}

	public String getDivers() {
		return divers;
	}

	public void setDivers(String divers) {
		this.divers = divers;
	}
	
	public boolean exists() {
		return (this.lieu != null);
	}
	
}
