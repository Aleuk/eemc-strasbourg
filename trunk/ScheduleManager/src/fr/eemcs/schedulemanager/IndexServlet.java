package fr.eemcs.schedulemanager;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.users.UserService;

public class IndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8247838940045334749L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			res.sendRedirect(userService.createLoginURL("/accueil"));
		} else {
			res.sendRedirect(userService.createLoginURL(req.getRequestURI()));
		}
    }

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
}
