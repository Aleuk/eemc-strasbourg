package fr.eemcs.schedulemanager.decorator;

import java.util.Calendar;

import org.displaytag.decorator.TableDecorator;

import com.ibm.icu.util.GregorianCalendar;

import fr.eemcs.schedulemanager.entity.EvenementVO;
import fr.eemcs.schedulemanager.entity.LieuVO;
import fr.eemcs.schedulemanager.entity.ProgrammeVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

public class EvenementDecorator extends TableDecorator {
	public String getDate() {
		EvenementVO event = (EvenementVO)getCurrentRowObject();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(event.getDate());
		return FormatHelper.addPaddingToTD(FormatHelper.formatDate(cal.getTime(), "E dd"));
	}
	
	public String getHeure() {
		EvenementVO event = (EvenementVO)getCurrentRowObject();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(event.getDate());
		String inputHeure = "<input type='time' id='" + cal.get(Calendar.DAY_OF_MONTH) + "_time'/>";
		return inputHeure;
	}
	
	public String getLieu() {
		EvenementVO event = (EvenementVO)getCurrentRowObject();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(event.getDate());
		String selectLieu = "<select id='" + cal.get(Calendar.DAY_OF_MONTH) + "_lieu' class='form-control'/>";
		return selectLieu;
	}
	
	public String getActions() {
		ProgrammeVO pg = (ProgrammeVO)getCurrentRowObject();
		GregorianCalendar cal = new GregorianCalendar(pg.getAnnee(), pg.getMois(), 1);
		
		String actions = "";
		actions += "<a href=\"javascript:getProgramme(" + pg.getKey() + ");\"><img height=\"25px\" src=\"/images/getProgramme.png\" /></a>";
		actions += "<a href=\"javascript:modifierProgramme(" + pg.getKey() + ", '" + FormatHelper.formatDate(cal.getTime(), "MM/yyyy") + "');\"><img height=\"25px\" src=\"/images/modifierProgramme.png\" /></a>";
		return FormatHelper.addPaddingToTD(actions);
	}
	
	public String addRowClass() {
		EvenementVO event = (EvenementVO)getCurrentRowObject();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(event.getDate());
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return "sunday";
		} else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			return "saturday";
		}
		return null;
	}
}
