package fr.eemcs.schedulemanager.decorator;

import org.displaytag.decorator.TableDecorator;

import fr.eemcs.schedulemanager.entity.LieuVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

public class LieuDecorator extends TableDecorator {
	public String getNom() {
		LieuVO lieu = (LieuVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(lieu.getNom());
	}
	
	public String getNomKH() {
		LieuVO lieu = (LieuVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(lieu.getNomKH());
	}
	
	public String getAdresse() {
		LieuVO lieu = (LieuVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(lieu.getAdresse() + " " + lieu.getCodePostal() + " " + lieu.getVille());
	}
	
	public String getActions() {
		LieuVO lieu = (LieuVO)getCurrentRowObject();
		String actions = "";
		actions += "<a href=\"javascript:modifierLieu(" + lieu.getKey() + ");\"><img height=\"25px\" src=\"/images/modifierLieu.png\" /></a>";
		actions += "<a href=\"javascript:supprimerLieu(" + lieu.getKey() + ", '" + lieu.getNom() + "');\"><img height=\"25px\" src=\"/images/supprimerLieu.png\" /></a>";
		return FormatHelper.addPaddingToTD(actions);
	}
}
