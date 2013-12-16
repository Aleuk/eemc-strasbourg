package fr.eemcs.schedulemanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.dao.MainDAO;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.LieuVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

@Controller
public class ParamLieuController extends LoggerController{
	
	@RequestMapping("/parametrage/lieu/list")
	public ModelAndView lieuList(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			List<LieuVO> lieux = new ArrayList<LieuVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				lieux = MainDAO.getLieux(pm);
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
	
	@RequestMapping("/parametrage/lieu/add")
	public ModelAndView addLieu(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			LieuVO lieu = new LieuVO();
			model.addAttribute("lieuForm", lieu);
			
			return new ModelAndView(IResponse.LIEU_FORM, "lieu", new LieuVO());
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/lieu/modif")
	public ModelAndView modifLieu(ModelMap model, @RequestParam(value="idLieu", required=true) String idLieu, HttpServletRequest request,  
            HttpServletResponse response) {
		if(super.isLogged()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			LieuVO lieu = null;
			try {
				lieu = MainDAO.getLieu(pm, idLieu);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			if(lieu == null) {
				return new ModelAndView(IResponse.ERROR);
			} else {
				model.addAttribute("lieuForm", lieu);
			}
			return new ModelAndView(IResponse.LIEU_FORM, "lieu", lieu);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/lieu/delete")
	public ModelAndView deleteLieu(ModelMap model, @RequestParam(value="idLieu", required=true) String idLieu, HttpServletRequest request) {
		if(super.isLogged()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			LieuVO lieu = null;
			try {
				lieu = MainDAO.getLieu(pm, idLieu);
				pm.deletePersistent(lieu);
				pm.refreshAll();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			return new ModelAndView("redirect:/controller/parametrage/lieu/list");
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView(IResponse.LOGIN);
		}
	}
	
	@RequestMapping("/parametrage/lieu/save")
	public ModelAndView saveLieu(HttpServletRequest request, @ModelAttribute("lieuForm") LieuVO lieu, BindingResult result) {
		if(super.isLogged()) {
			if(lieu != null) {
				PersistenceManager pm = PMF.get().getPersistenceManager();
				String idLieu = (String) request.getParameter("id");
				if(idLieu != null && !"".equals(idLieu)) {
					try {
						pm.currentTransaction().begin();
						
						LieuVO modifLieu = MainDAO.getLieu(pm, FormatHelper.getId(idLieu));
						modifLieu.setNom(lieu.getNom());
						modifLieu.setNomKH(lieu.getNomKH());
						modifLieu.setAdresse(lieu.getAdresse());
						modifLieu.setCodePostal(lieu.getCodePostal());
						modifLieu.setVille(lieu.getVille());
						modifLieu.setModification(new Date(), user);
						
						pm.currentTransaction().commit();
						pm.refreshAll();
					} catch (Exception e) {
						e.printStackTrace();
						pm.currentTransaction().rollback();
					} finally {
						pm.close();
					}
				} else {
					try {
						lieu.setCreation(new Date(), user);
						pm.makePersistent(lieu);
						pm.refreshAll();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						pm.close();
					}
				}
			}
			return new ModelAndView("redirect:/controller/parametrage/lieu/list");
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
}
