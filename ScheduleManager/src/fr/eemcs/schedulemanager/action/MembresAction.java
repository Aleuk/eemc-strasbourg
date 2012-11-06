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

import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

public class MembresAction extends LoggerAction{
	private String url;
	
	private List<ContactVO> listeMembres = new ArrayList<ContactVO>();
	 
	public String getUrl() {
		return url;
	}
 
	public void setUrl(String _url) {
		this.url = _url;
	}
 
	public String execute() {
		boolean logged = super.isLogged();
		if(logged) {
			Date dateNaissance = FormatHelper.getDate("02/08/1986", "dd/MM/yyyy");
			ContactVO contact = new ContactVO("CHAO", "Gabriel", dateNaissance);
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.makePersistent(contact);
			} finally {
				pm.close();
			}
			return IResponse.LIST;
		} else {
			HttpServletRequest req = ServletActionContext.getRequest();
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String list() {
		HttpServletRequest req = ServletActionContext.getRequest();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "select from " + ContactVO.class.getName();
			listeMembres = (List<ContactVO>)pm.newQuery(query).execute();
			pm.close();
			return IResponse.LIST;
		} else {
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
	public String add() {
		HttpServletRequest req = ServletActionContext.getRequest();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			return IResponse.LIST;
		} else {
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
}
