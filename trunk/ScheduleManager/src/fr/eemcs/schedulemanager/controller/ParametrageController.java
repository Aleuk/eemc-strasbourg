package fr.eemcs.schedulemanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.LieuVO;
import fr.eemcs.schedulemanager.entity.ProgrammeVO;

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
	
	@RequestMapping("/parametrage/programme/list")
	public ModelAndView programmeList(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			List<ProgrammeVO> programmes = new ArrayList<ProgrammeVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String query = "select from " + ProgrammeVO.class.getName();
				programmes = (List<ProgrammeVO>)pm.newQuery(query).execute();
				model.addAttribute("listeProgrammes", programmes);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView(IResponse.PROGRAMME_LIST);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/lieu/list")
	public ModelAndView lieuList(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			//List<ContactVO> contacts = baseDAO.getContacts();
			List<LieuVO> lieux = new ArrayList<LieuVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String query = "select from " + LieuVO.class.getName();
				lieux = (List<LieuVO>)pm.newQuery(query).execute();
				model.addAttribute("listeLieux", lieux);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView(IResponse.LIEU_LIST);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/article/list")
	public ModelAndView articleList(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			//List<ContactVO> contacts = baseDAO.getContacts();
			List<LieuVO> lieux = new ArrayList<LieuVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String query = "select from " + LieuVO.class.getName();
				lieux = (List<LieuVO>)pm.newQuery(query).execute();
				model.addAttribute("listeLieux", lieux);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView(IResponse.LIEU_LIST);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/message/list")
	public ModelAndView messageList(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			//List<ContactVO> contacts = baseDAO.getContacts();
			List<LieuVO> lieux = new ArrayList<LieuVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String query = "select from " + LieuVO.class.getName();
				lieux = (List<LieuVO>)pm.newQuery(query).execute();
				model.addAttribute("listeLieux", lieux);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView(IResponse.LIEU_LIST);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
}
