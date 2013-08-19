package fr.eemcs.schedulemanager.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.DAO.interfaces.IBaseDAO;
import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.LieuVO;

public class LieuAction extends LoggerAction{
	private String url;
	
	private IBaseDAO baseDAO;
	private LieuVO lieu;
	private List<LieuVO> listeLieux = new ArrayList<LieuVO>();
	private List<ContactVO> listeContacts = new ArrayList<ContactVO>();
	private int idLieu = -1;
	 
	public String getUrl() {
		return url;
	}
 
	public void setUrl(String _url) {
		this.url = _url;
	}
 
	public String execute() {
		boolean logged = super.isLogged();
		if(logged) {
			return IResponse.LIST;
		} else {
			HttpServletRequest req = ServletActionContext.getRequest();
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	public String list() {
		boolean logged = super.isLogged();
		if(logged) {
			List<LieuVO> lieux = baseDAO.getLieux();
			if(lieux != null && lieux.size() > 0) {
				listeLieux = lieux;
			}
			return IResponse.LIST;
		} else {
			HttpServletRequest req = ServletActionContext.getRequest();
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	public String add() {
		HttpServletRequest req = ServletActionContext.getRequest();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			lieu = new LieuVO();
			listeContacts = baseDAO.getContacts();
			return IResponse.FORM;
		} else {
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	public String save() {
		HttpServletRequest req = ServletActionContext.getRequest();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			if(lieu != null) {
				PersistenceManager pm = PMF.get().getPersistenceManager();
				if(idLieu > 0) {
					try {
						pm.currentTransaction().begin();
						
						LieuVO modifLieu = pm.getObjectById(LieuVO.class, idLieu);
						
						modifLieu.setIdContact(lieu.getIdContact());
						modifLieu.setNom(lieu.getNom());
						modifLieu.setNomKH(lieu.getNomKH());
						modifLieu.setAdresse(lieu.getAdresse());
						modifLieu.setCodePostal(lieu.getCodePostal());
						modifLieu.setVille(lieu.getVille());
						
						modifLieu.setModification(new Date(), user);
						
						pm.currentTransaction().commit();
					} catch (Exception e) {
						pm.currentTransaction().rollback();
					} finally {
						pm.close();
					}
				} else {
					try {
						lieu.setCreation(new Date(), user);
						pm.makePersistent(lieu);
					} finally {
						pm.close();
					}
				}
			}
			return IResponse.SUCCESS;
		} else {
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	public String modif() {
		HttpServletRequest req = ServletActionContext.getRequest();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				lieu = pm.getObjectById(LieuVO.class, idLieu);
				listeContacts = baseDAO.getContacts();
			} finally {
				pm.close();
			}
			if(lieu == null) {
				return IResponse.ERROR;
			}
			return IResponse.FORM;
		} else {
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	public String delete() {
		HttpServletRequest req = ServletActionContext.getRequest();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				lieu = pm.getObjectById(LieuVO.class, idLieu);
				pm.deletePersistent(lieu);
			} finally {
				pm.close();
			}
			return IResponse.LIST;
		} else {
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return IResponse.LOGIN;
		}
	}

	public List<LieuVO> getListeLieux() {
		return listeLieux;
	}

	public void setListeLieux(List<LieuVO> listeLieux) {
		this.listeLieux = listeLieux;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public LieuVO getLieu() {
		return lieu;
	}

	public void setLieu(LieuVO lieu) {
		this.lieu = lieu;
	}

	public int getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(int idLieu) {
		this.idLieu = idLieu;
	}

	public List<ContactVO> getListeContacts() {
		return listeContacts;
	}

	public void setListeContacts(List<ContactVO> listeContacts) {
		this.listeContacts = listeContacts;
	}
	
}
