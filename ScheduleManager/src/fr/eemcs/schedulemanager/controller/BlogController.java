package fr.eemcs.schedulemanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.constants.IConstants;
import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ArticleVO;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

@Controller
public class BlogController extends LoggerController{
	
	@RequestMapping("/historique/blog")
	public ModelAndView blog(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			List<ArticleVO> articles = new ArrayList<ArticleVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String query = getBlogSqlRequest(IConstants.CATEGORIE_HISTORIQUE);
				articles = (List<ArticleVO>)pm.newQuery(query).execute();
				model.addAttribute("listeArticles", articles);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return new ModelAndView(IResponse.BLOG_HISTORIQUE);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	public String getBlogSqlRequest(String categorie) {
		return "select from " + ArticleVO.class.getName() + " where categorie=='" + categorie + "'";
	}

}
