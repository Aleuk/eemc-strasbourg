package fr.eemcs.schedulemanager.action;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoggerAction {
	User user = null;
	
	public boolean isLogged() {
		UserService userService = UserServiceFactory.getUserService();
		user = userService.getCurrentUser();
		
		return (user != null);
	}
	
}
