package fr.eemcs.schedulemanager.entity;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class ProgrammeVO extends ObjectVO{
	@Persistent
	private int mois;
	
	@Persistent
	private int annee;
	
	@Persistent(mappedBy = "programme")
	private List<EvenementVO> evenements;

	public ProgrammeVO(int _mois, int _annee) {
		this.mois = _mois;
		this.annee = _annee;
	}

	// GETTERS & SETTERS
	public int getMois() {
		return mois;
	}



	public void setMois(int mois) {
		this.mois = mois;
	}


	public int getAnnee() {
		return annee;
	}


	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public List<EvenementVO> getEvenements() {
		return evenements;
	}

	public void setEvenements(List<EvenementVO> evenements) {
		this.evenements = evenements;
	}
	
}
