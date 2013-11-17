package fr.eemcs.schedulemanager.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.eemcs.schedulemanager.constants.IConstants;
import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.entity.EmailVO;

@Controller
public class NousContacterController {
 
	@RequestMapping("/nousContacter")
	public ModelAndView execute(ModelMap model) {
		EmailVO email = new EmailVO();
		model.addAttribute("nousContacterForm", email);
		
		loadForm(model);
		return new ModelAndView(IResponse.NOUS_CONTACTER);
	}
	
	public void loadForm(ModelMap model) {
		Map<String,String> mapCategories = new LinkedHashMap<String, String>();
		mapCategories.put(IConstants.CATEGORIE_PRIERE, IConstants.CATEGORIE_PRIERE);
		mapCategories.put(IConstants.CATEGORIE_SUGGESTION, IConstants.CATEGORIE_SUGGESTION);
		mapCategories.put(IConstants.CATEGORIE_DIVERS, IConstants.CATEGORIE_DIVERS);
		mapCategories.put(IConstants.CATEGORIE_BUG, IConstants.CATEGORIE_BUG);
		model.addAttribute("mapCategories", mapCategories);
	}
}
