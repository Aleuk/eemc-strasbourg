package fr.eemcs.schedulemanager.decorator;

import java.util.Calendar;

import org.displaytag.decorator.TableDecorator;

import com.ibm.icu.util.GregorianCalendar;

import fr.eemcs.schedulemanager.entity.LieuVO;
import fr.eemcs.schedulemanager.entity.ProgrammeVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

public class ProgrammeDecorator extends TableDecorator {
	public String getDate() {
		ProgrammeVO pg = (ProgrammeVO)getCurrentRowObject();
		GregorianCalendar cal = new GregorianCalendar(pg.getAnnee(), pg.getMois(), 1);
		return FormatHelper.addPaddingToTD(FormatHelper.formatDate(cal.getTime(), "MM/yyyy"));
	}
	
	public String getActions() {
		ProgrammeVO pg = (ProgrammeVO)getCurrentRowObject();
		GregorianCalendar cal = new GregorianCalendar(pg.getAnnee(), pg.getMois(), 1);
		
		String actions = "";
		actions += "<a href=\"javascript:getProgramme(" + pg.getKey() + ");\"><img height=\"25px\" src=\"/images/getProgramme.png\" /></a>";
		actions += "<a href=\"javascript:modifierProgramme(" + pg.getKey() + ", '" + FormatHelper.formatDate(cal.getTime(), "MM/yyyy") + "');\"><img height=\"25px\" src=\"/images/modifierProgramme.png\" /></a>";
		return FormatHelper.addPaddingToTD(actions);
	}
}
