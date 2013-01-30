package fr.eemcs.schedulemanager.decorator;

import org.displaytag.decorator.TableDecorator;

import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

public class ContactDecorator extends TableDecorator {
	public String getNom() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		return contact.getNom() + " " + contact.getPrenom();
	}
	
	public String getNomKH() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		return (null != contact.getNomKH()) ? contact.getNomKH() + " " : (null != contact.getPrenomKH()) ? contact.getPrenomKH() : "";
	}
	
	public String getDateNaissance() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		return FormatHelper.formatDate(contact.getDateNaissance(), "dd/MM/yyyy");
	}
	
	public String getActions() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		String actions = "";
		if("Monsieur".equals(contact.getCivilite())) {
			actions += "<a href=\"javascript:modifierContact('" + contact.getId() + "');\"><img src=\"../images/modifierHomme.png\" /></a>";
		} else {
			actions += "<a href=\"javascript:modifierContact('" + contact.getId() + "');\"><img src=\"../images/modifierFemme.png\" /></a>";
		}
		actions += "<a href=\"javascript:supprimerContact('" + contact.getId() + "', '" + contact.getNom() + " " + contact.getPrenom() + "');\"><img src=\"../images/supprimer.png\" /></a>";
		return actions;
	}
}
