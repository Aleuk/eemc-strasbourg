package fr.eemcs.schedulemanager.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.constants.IResponse;

public class MembresAction {
	private String url;
	 
	public String getUrl() {
		return url;
	}
 
	public void setUrl(String _url) {
		this.url = _url;
	}
 
	public String execute() {
		HttpServletRequest req = ServletActionContext.getRequest();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			return IResponse.SUCCESS;
		} else {
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return IResponse.LOGIN;
		}
	}
	
}
