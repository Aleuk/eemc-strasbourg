package fr.eemcs.schedulemanager.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.appengine.api.datastore.Key;

import fr.eemcs.schedulemanager.helper.FormatHelper;

@PersistenceCapable
public class EvenementVO extends ObjectVO{
	
	@Persistent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	private String heure;
	
	@Persistent
	private LieuVO lieu;
	
	@Persistent
	private List<Key> responsables; //Dans l'ordre : Présidence, Prédicateur, Traducteur, Offrande, Ecole du dimanche
	
	@Persistent
	private String divers;

	public EvenementVO() {
		
	}
	
	// GETTERS & SETTERS
	
	public Date getDate() {
		return date;
	}

	//Formatage de la date dans le tableau
	@Deprecated
	public String getDateEvent() {
		return FormatHelper.formatDate(date, "E dd");
	}
	
	public String getHeureEvent() {
		return FormatHelper.formatDate(date, "HH:mm");
	}
	
	public String getJourEvent() {
		return FormatHelper.formatDate(date, "d");
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getClassRow() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(this.date);
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return "sunday";
		} else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			return "saturday";
		}
		return null;
	}

	public LieuVO getLieu() {
		return lieu;
	}

	public void setLieu(LieuVO lieu) {
		this.lieu = lieu;
	}

	public List<Key> getResponsables() {
		return responsables;
	}

	public void setResponsables(List<Key> responsables) {
		this.responsables = responsables;
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
