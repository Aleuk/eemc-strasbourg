package fr.eemcs.schedulemanager.client;

import java.util.Date;

public class EvenementInfo {
	
	private Date date;
	private String heure;
	private LieuInfo lieu;
	private String divers;

	public EvenementInfo() {
		
	}
	
	// GETTERS & SETTERS
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
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

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}
	
}
