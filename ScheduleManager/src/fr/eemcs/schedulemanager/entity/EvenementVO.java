package fr.eemcs.schedulemanager.entity;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class EvenementVO extends ObjectVO{
	@Persistent
	private ProgrammeVO programme;
	
	@Persistent
	private Date date;
	
	@Persistent
	private LieuVO lieu;

	public EvenementVO() {
		
	}
	
	// GETTERS & SETTERS
	public ProgrammeVO getProgramme() {
		return programme;
	}

	public void setProgramme(ProgrammeVO programme) {
		this.programme = programme;
	}
	
}
