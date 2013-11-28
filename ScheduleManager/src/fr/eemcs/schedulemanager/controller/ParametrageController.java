package fr.eemcs.schedulemanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Controller
public class ParametrageController extends LoggerController{
	@RequestMapping("/parametrage/list")
	public ModelAndView list(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			return new ModelAndView("redirect:/controller/parametrage/programme/list");
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
}
