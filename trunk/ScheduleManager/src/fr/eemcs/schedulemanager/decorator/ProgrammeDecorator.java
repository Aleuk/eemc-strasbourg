package fr.eemcs.schedulemanager.decorator;

import java.util.Date;

import javax.jdo.PersistenceManager;

import org.displaytag.decorator.TableDecorator;

import com.google.appengine.api.datastore.Key;

import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.EvenementVO;
import fr.eemcs.schedulemanager.entity.LieuVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

public class ProgrammeDecorator extends TableDecorator {
	/************* DETAILS_PROGRAMME ************/
	public String getDate() {
		EvenementVO e = (EvenementVO)getCurrentRowObject();
		
		return "<b>" + FormatHelper.formatDate(e.getDate(), "E dd").toUpperCase() + "</b>";
	}
	
	public String getHeure() {
		EvenementVO e = (EvenementVO)getCurrentRowObject();
		
		return FormatHelper.formatDate(e.getDate(), "HH:mm");
	}
	
	public String getLieu() {
		EvenementVO e = (EvenementVO)getCurrentRowObject();
		String retour = "";
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			if(e.getLieu() != null) {
				LieuVO l = pm.getObjectById(LieuVO.class, e.getLieu());
				retour = l.getNom();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retour;
	}
	
	public String getPresidence() {
		EvenementVO e = (EvenementVO)getCurrentRowObject();
		String retour = "";
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			if(e.getResponsables() != null && e.getResponsables().size() > 0) {
				Key kContact = e.getResponsables().get(0);
				if(kContact != null) {
					ContactVO c = pm.getObjectById(ContactVO.class, kContact);
					retour = c.getPrenom();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retour;
	}
	
	public String getPredicateur() {
		EvenementVO e = (EvenementVO)getCurrentRowObject();
		String retour = "";
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			if(e.getResponsables() != null && e.getResponsables().size() > 1) {
				Key kContact = e.getResponsables().get(1);
				if(kContact != null) {
					ContactVO c = pm.getObjectById(ContactVO.class, kContact);
					retour = c.getPrenom();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retour;
	}
	
	public String getTraducteur() {
		EvenementVO e = (EvenementVO)getCurrentRowObject();
		String retour = "";
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			if(e.getResponsables() != null && e.getResponsables().size() > 2) {
				Key kContact = e.getResponsables().get(2);
				if(kContact != null) {
					ContactVO c = pm.getObjectById(ContactVO.class, kContact);
					retour = c.getPrenom();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retour;
	}
	
	public String getOffrande() {
		EvenementVO e = (EvenementVO)getCurrentRowObject();
		String retour = "";
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			if(e.getResponsables() != null && e.getResponsables().size() > 3) {
				Key kContact = e.getResponsables().get(3);
				if(kContact != null) {
					ContactVO c = pm.getObjectById(ContactVO.class, kContact);
					retour = c.getPrenom();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retour;
	}
	
	public String getResponsables() {
		EvenementVO e = (EvenementVO)getCurrentRowObject();
		String retour = "";
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			if(e.getResponsables() != null && e.getResponsables().size() > 4) {
				for(int i = 4; i < e.getResponsables().size(); i++) {
					Key kContact = e.getResponsables().get(i);
					if(kContact != null) {
						ContactVO c = pm.getObjectById(ContactVO.class, kContact);
						retour = c.getPrenom() + " / ";
					}
				}
				retour = retour.substring(0, retour.lastIndexOf(" / "));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return retour;
	}
	
	/************* PROGRAMME_LIST ************/
	public String getMois() {
		String mois = (String)getCurrentRowObject();
		
		return FormatHelper.formatDate(FormatHelper.getDate(mois, "yyyy/MM"), "MMMM yyyy").toUpperCase();
	}
	
	public String getActions() {
		String mois = (String)getCurrentRowObject();
		Date d = FormatHelper.getDate(mois, "yyyy/MM");
		
		String actions = "";
		actions += "<a href=\"javascript:getProgramme('" + mois + "');\"><img height=\"25px\" src=\"/images/getProgramme.png\" /></a>&nbsp;&nbsp;";
		actions += "<a href=\"javascript:editProgramme('" + mois + "');\"><img height=\"25px\" src=\"/images/modifierProgramme.png\" /></a>&nbsp;&nbsp;";
		actions += "<a href=\"javascript:deleteProgramme('" + mois + "', '" + FormatHelper.formatDate(d, "MMMM yyyy").toUpperCase() + "');\"><img height=\"25px\" src=\"/images/supprimerProgramme.png\" /></a>";
		
		return FormatHelper.addPaddingToTD(actions);
	}
}
