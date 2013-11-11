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

import fr.eemcs.schedulemanager.constants.IConstants;
import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ArticleVO;

@Controller
public class BlogController extends LoggerController{
	
	@RequestMapping("/*/blog")
	public ModelAndView blog(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			List<ArticleVO> articles = new ArrayList<ArticleVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String response = IResponse.ERROR;
			try {
				String uri = request.getRequestURI();
				if(uri != null) {
					if(uri.contains("historique")) {
						String query = getBlogSqlRequest(IConstants.CATEGORIE_HISTORIQUE);
						articles = (List<ArticleVO>)pm.newQuery(query).execute();
						response = IResponse.BLOG_HISTORIQUE;
					} else if(uri.contains("activite")) {
						String query = getBlogSqlRequest(IConstants.CATEGORIE_ACTIVITES);
						articles = (List<ArticleVO>)pm.newQuery(query).execute();
						response = IResponse.BLOG_ACTIVITES;
					} else if(uri.contains("massage")) {
						String query = getBlogSqlRequest(IConstants.CATEGORIE_MESSAGES);
						articles = (List<ArticleVO>)pm.newQuery(query).execute();
						response = IResponse.BLOG_MESSAGES;
					} else if(uri.contains("photo")) {
						String query = getBlogSqlRequest(IConstants.CATEGORIE_PHOTOS);
						articles = (List<ArticleVO>)pm.newQuery(query).execute();
						response = IResponse.BLOG_PHOTOS;
					} else if(uri.contains("video")) {
						String query = getBlogSqlRequest(IConstants.CATEGORIE_VIDEOS);
						articles = (List<ArticleVO>)pm.newQuery(query).execute();
						response = IResponse.BLOG_VIDEOS;
					} else if(uri.contains("musique")) {
						String query = getBlogSqlRequest(IConstants.CATEGORIE_MUSIQUE);
						articles = (List<ArticleVO>)pm.newQuery(query).execute();
						response = IResponse.BLOG_MUSIQUE;
					} else if(uri.contains("projet")) {
						String query = getBlogSqlRequest(IConstants.CATEGORIE_PROJETS);
						articles = (List<ArticleVO>)pm.newQuery(query).execute();
						response = IResponse.BLOG_PROJETS;
					} else if(uri.contains("verset")) {
						String query = getBlogSqlRequest(IConstants.CATEGORIE_VERSETS);
						articles = (List<ArticleVO>)pm.newQuery(query).execute();
						response = IResponse.BLOG_VERSETS;
					}

					model.addAttribute("listeArticles", articles);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return new ModelAndView(response);
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
