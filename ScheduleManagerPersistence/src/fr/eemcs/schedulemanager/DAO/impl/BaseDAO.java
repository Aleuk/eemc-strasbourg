package fr.eemcs.schedulemanager.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import fr.eemcs.schedulemanager.DAO.interfaces.IBaseDAO;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.LieuVO;

public class BaseDAO implements IBaseDAO{

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
	
	@SuppressWarnings("unchecked")
	public List<LieuVO> getLieux() {
		List<LieuVO> result = new ArrayList<LieuVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + LieuVO.class.getName();
			result = (List<LieuVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
