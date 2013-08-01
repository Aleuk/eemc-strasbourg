package fr.eemcs.schedulemanager.DAO.interfaces;

import java.util.List;

import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.LieuVO;

public interface IBaseDAO {

	public List<ContactVO> getContacts();
	
	public ContactVO getContact(String id);
	
	public List<LieuVO> getLieux();
}
