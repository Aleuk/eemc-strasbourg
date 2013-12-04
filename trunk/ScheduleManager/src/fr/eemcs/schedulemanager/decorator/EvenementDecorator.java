package fr.eemcs.schedulemanager.decorator;

import java.util.Calendar;

import org.displaytag.decorator.TableDecorator;

import com.ibm.icu.util.GregorianCalendar;

import fr.eemcs.schedulemanager.entity.EvenementVO;
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
		
		String heure = "--:--";
		if(event.exists()) {
			heure = FormatHelper.addPaddingToTD(FormatHelper.formatDate(cal.getTime(), "HH:mm"));
		}
		return heure;
	}
	
	public String getActions() {
		EvenementVO event = (EvenementVO)getCurrentRowObject();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(event.getDate());
		
		String actions = "";
		if(event.exists()) {
			//L'événement existe, il s'agit d'une modification-Suppresion
			actions += "<a href=\"javascript:editEvent(" + event.getKey() + ");\"><img height=\"25px\" src=\"/images/getProgramme.png\" /></a>";
			actions += "<a href=\"javascript:deleteEvent(" + event.getKey() + ", '" + FormatHelper.formatDate(cal.getTime(), "E dd") + "');\"><img height=\"25px\" src=\"/images/modifierProgramme.png\" /></a>";
		} else {
			//L'événement n'existe pas, il s'agit d'un ajout
			String hDate = FormatHelper.formatDate(cal.getTime(), "dd MMMM");
			String addButton = "<button class='openNewEvent btn btn-primary btn-xs' data-hidden='" + hDate + "' data-id='" + cal.get(Calendar.DAY_OF_MONTH) + "' data-toggle='modal' data-target='#eventModal'>+</button>";
			//actions += "<a href=\"javascript:addEvent(" + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.MONTH) + ", " + cal.get(Calendar.YEAR) + ");\"><img height=\"25px\" src=\"/images/getProgramme.png\" /></a>";
			actions = addButton;
		}
		
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
