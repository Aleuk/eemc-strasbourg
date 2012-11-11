package fr.eemcs.schedulemanager.decorator;

import org.displaytag.decorator.TableDecorator;

import fr.eemcs.schedulemanager.entity.ContactVO;

public class ContactDecorator extends TableDecorator {
	public String getNom() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		return contact.getNom();
	}
	
	public String getPrenom() {
		ContactVO contact = (ContactVO)getCurrentRowObject();
		return contact.getPrenom();
	}
}
