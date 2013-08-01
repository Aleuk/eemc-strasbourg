package fr.eemcs.schedulemanager.decorator;

import org.displaytag.decorator.TableDecorator;

import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.LieuVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

public class LieuDecorator extends TableDecorator {
	public String getNom() {
		LieuVO lieu = (LieuVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(lieu.getNom());
	}
	
	public String getAdresse() {
		LieuVO lieu = (LieuVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(lieu.getAdresse() + " " + lieu.getCodePostal() + " " + lieu.getVille());
	}
	
	public String getActions() {
		LieuVO lieu = (LieuVO)getCurrentRowObject();
		String actions = "";
		actions += "<a href=\"javascript:modifierLieu(" + FormatHelper.getId(lieu.getId().toString()) + ");\"><img height=\"20px\" src=\"../images/modifierFemme.png\" /></a>";
		actions += "<a href=\"javascript:supprimerLieu(" + FormatHelper.getId(lieu.getId().toString()) + ", '" + lieu.getNom() + "');\"><img height=\"20px\" src=\"../images/supprimer.png\" /></a>";
		return FormatHelper.addPaddingToTD(actions);
	}
}
