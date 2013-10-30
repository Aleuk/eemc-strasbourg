package fr.eemcs.schedulemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.eemcs.schedulemanager.constants.IResponse;

@Controller
public class EnterController {
	
	private String url;
	 
	public String getUrl() {
		return url;
	}
 
	public void setUrl(String _url) {
		this.url = _url;
	}
 
	@RequestMapping("/enter")
	public ModelAndView execute() {
		//TODO récupération des infos User
		//return IResponse.SUCCESS;
		return new ModelAndView("home.def");
	}
	
}
