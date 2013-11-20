package fr.eemcs.schedulemanager.entity;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.format.annotation.DateTimeFormat;

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

	public void setDate(Date date) {
		this.date = date;
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
	
}
