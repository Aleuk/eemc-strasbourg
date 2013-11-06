package fr.eemcs.schedulemanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.DAO.interfaces.IBaseDAO;
import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.LieuVO;

public class LieuController extends LoggerController{
	
	/*public String list(HttpServletRequest request) {
		boolean logged = super.isLogged();
		if(logged) {
			List<LieuVO> lieux = baseDAO.getLieux();
			if(lieux != null && lieux.size() > 0) {
				listeLieux = lieux;
			}
			return IResponse.LIEU_LIST;
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	public String add(HttpServletRequest request) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			lieu = new LieuVO();
			listeContacts = baseDAO.getContacts();
			return IResponse.LIEU_FORM;
		} else {
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	public String save(HttpServletRequest request) {
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
			return "SUCCESS";
		} else {
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	public String modif(HttpServletRequest request) {
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
			return IResponse.LIEU_FORM;
		} else {
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	public String delete(HttpServletRequest request) {
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
			return IResponse.LIEU_LIST;
		} else {
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return IResponse.LOGIN;
		}
	}*/
	
}
