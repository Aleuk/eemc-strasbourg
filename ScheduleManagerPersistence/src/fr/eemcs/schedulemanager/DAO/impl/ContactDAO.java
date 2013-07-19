package fr.eemcs.schedulemanager.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import fr.eemcs.schedulemanager.DAO.interfaces.IContactDAO;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;

public class ContactDAO implements IContactDAO{

	@SuppressWarnings("unchecked")
	public List<ContactVO> getContacts() {
		List<ContactVO> result = new ArrayList<ContactVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + ContactVO.class.getName();
			result = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public ContactVO getContact(String id) {
		ContactVO contact = null;
		PersistenceManager pm = PMF.get().getPersistenceManager(); //TODO use getObjectById
		try {
			String query = "select from " + ContactVO.class.getName() + "where id = " + id;
			contact = (ContactVO)pm.newQuery(query).execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return contact;
	}
}
