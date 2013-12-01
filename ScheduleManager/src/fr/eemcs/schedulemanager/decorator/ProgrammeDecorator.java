package fr.eemcs.schedulemanager.decorator;

import java.util.Date;

import org.displaytag.decorator.TableDecorator;

import fr.eemcs.schedulemanager.helper.FormatHelper;

public class ProgrammeDecorator extends TableDecorator {
	public String getMois() {
		String mois = (String)getCurrentRowObject(); //Format MM/yyyy
		
		return FormatHelper.formatDate(FormatHelper.getDate(mois, "MM/yyyy"), "MMMM yyyy").toUpperCase();
	}
	
	public String getActions() {
		String mois = (String)getCurrentRowObject(); //Format MM/yyyy
		Date d = FormatHelper.getDate(mois, "MM/yyyy");
		
		String actions = "";
		actions += "<a href=\"javascript:getProgramme('" + mois + "');\"><img height=\"25px\" src=\"/images/getProgramme.png\" /></a>&nbsp;&nbsp;";
		actions += "<a href=\"javascript:editProgramme('" + mois + "');\"><img height=\"25px\" src=\"/images/modifierProgramme.png\" /></a>&nbsp;&nbsp;";
		actions += "<a href=\"javascript:deleteProgramme('" + mois + "', '" + FormatHelper.formatDate(d, "MMMM yyyy").toUpperCase() + "');\"><img height=\"25px\" src=\"/images/supprimerProgramme.png\" /></a>";
		
		return FormatHelper.addPaddingToTD(actions);
	}
}
