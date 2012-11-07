package fr.eemcs.schedulemanager.DAO.interfaces;

import java.util.List;

import fr.eemcs.schedulemanager.entity.ContactVO;

public interface IContactDAO {

	public List<ContactVO> getContacts();
}
