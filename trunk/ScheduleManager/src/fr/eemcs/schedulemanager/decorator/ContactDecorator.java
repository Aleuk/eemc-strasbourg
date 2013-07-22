package fr.eemcs.schedulemanager.decorator;

import org.displaytag.decorator.TableDecorator;

import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

public class ContactDecorator extends TableDecorator {
	public String getNom() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(contact.getNom() + " " + contact.getPrenom());
	}
	
	public String getNomKH() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(contact.getNomKH() + " " + contact.getPrenomKH());
	}
	
	public String getDateNaissance() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(FormatHelper.formatDate(contact.getDateNaissance(), "dd/MM/yyyy"));
	}
	
	public String getEmail() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(contact.getEmail());
	}
	
	public String getTelephone() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(contact.getTelephone1() + " <b>/</b> " + contact.getTelephone2());
	}
	
	public String getActions() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		String actions = "";
		if("Monsieur".equals(contact.getCivilite())) {
			actions += "<a href=\"javascript:modifierContact(" + FormatHelper.getId(contact.getId().toString()) + ");\"><img height=\"20px\" src=\"../images/modifierHomme.png\" /></a>";
		} else {
			actions += "<a href=\"javascript:modifierContact(" + FormatHelper.getId(contact.getId().toString()) + ");\"><img height=\"20px\" src=\"../images/modifierFemme.png\" /></a>";
		}
		actions += "<a href=\"javascript:supprimerContact(" + FormatHelper.getId(contact.getId().toString()) + ", '" + contact.getNom() + " " + contact.getPrenom() + "');\"><img height=\"20px\" src=\"../images/supprimer.png\" /></a>";
		return FormatHelper.addPaddingToTD(actions);
	}
}
