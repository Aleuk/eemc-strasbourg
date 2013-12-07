package fr.eemcs.schedulemanager.decorator;

import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import fr.eemcs.schedulemanager.entity.EvenementVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

public class ProgrammeDecorator extends TableDecorator {
	/************* DETAILS_PROGRAMME ************/
	public String getDate() {
		EvenementVO e = (EvenementVO)getCurrentRowObject();
		
		return FormatHelper.formatDate(e.getDate(), "E MM yyyy").toUpperCase();
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
