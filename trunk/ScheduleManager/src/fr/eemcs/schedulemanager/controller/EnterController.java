package fr.eemcs.schedulemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.eemcs.schedulemanager.constants.IResponse;

@Controller
public class EnterController {
 
	@RequestMapping("/enter")
	public ModelAndView execute() {
		return new ModelAndView(IResponse.HOME);
	}
	
	@RequestMapping("/planAcces")
	public ModelAndView planAcces() {
		return new ModelAndView(IResponse.PLAN_ACCES);
	}
	
}
