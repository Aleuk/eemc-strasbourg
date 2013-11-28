package fr.eemcs.schedulemanager.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.format.annotation.DateTimeFormat;

import fr.eemcs.schedulemanager.helper.FormatHelper;

@PersistenceCapable
public class EvenementVO extends ObjectVO{
	@Persistent
	private ProgrammeVO programme;
	
	@Persistent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@Persistent
	private LieuVO lieu;
	
	@Persistent
	private ContactVO presidence;
	
	@Persistent
	private ContactVO predicateur;
	
	@Persistent
	private ContactVO traducteur;
	
	@Persistent
	private ContactVO offrande;
	
	@Persistent
	private List<ContactVO> ecoleDimancheGarderie;
	
	@Persistent
	private String divers;

	public EvenementVO() {
		
	}
	
	// GETTERS & SETTERS
	public ProgrammeVO getProgramme() {
		return programme;
	}

	public void setProgramme(ProgrammeVO programme) {
		this.programme = programme;
	}

	public Date getDate() {
		return date;
	}

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

	public ContactVO getPresidence() {
		return presidence;
	}

	public void setPresidence(ContactVO presidence) {
		this.presidence = presidence;
	}

	public ContactVO getPredicateur() {
		return predicateur;
	}

	public void setPredicateur(ContactVO predicateur) {
		this.predicateur = predicateur;
	}

	public ContactVO getTraducteur() {
		return traducteur;
	}

	public void setTraducteur(ContactVO traducteur) {
		this.traducteur = traducteur;
	}

	public List<ContactVO> getEcoleDimancheGarderie() {
		return ecoleDimancheGarderie;
	}

	public void setEcoleDimancheGarderie(List<ContactVO> ecoleDimancheGarderie) {
		this.ecoleDimancheGarderie = ecoleDimancheGarderie;
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

	public ContactVO getOffrande() {
		return offrande;
	}

	public void setOffrande(ContactVO offrande) {
		this.offrande = offrande;
	}
	
}
