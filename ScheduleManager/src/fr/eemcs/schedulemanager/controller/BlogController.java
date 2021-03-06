package fr.eemcs.schedulemanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.eemcs.schedulemanager.constants.IConstants;
import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.dao.MainDAO;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ArticleVO;

@Controller
public class BlogController extends LoggerController{
	
	@RequestMapping("/*/blog")
	public ModelAndView blog(ModelMap model, HttpServletRequest request) {
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String response = IResponse.ERROR;
		try {
			String uri = request.getRequestURI();
			if(uri != null) {
				if(uri.contains("accueil")) {
					articles = MainDAO.getBlog(pm, IConstants.CATEGORIE_ACCUEIL);
					response = IResponse.HOME;
				} else if(uri.contains("historique")) {
					articles = MainDAO.getBlog(pm, IConstants.CATEGORIE_HISTORIQUE);
					response = IResponse.BLOG_HISTORIQUE;
				} else if(uri.contains("activite")) {
					articles = MainDAO.getBlog(pm, IConstants.CATEGORIE_ACTIVITES);
					response = IResponse.BLOG_ACTIVITES;
				} else if(uri.contains("message")) {
					articles = MainDAO.getBlog(pm, IConstants.CATEGORIE_MESSAGES);
					response = IResponse.BLOG_MESSAGES;
				} else if(uri.contains("photo")) {
					articles = MainDAO.getBlog(pm, IConstants.CATEGORIE_PHOTOS);
					response = IResponse.BLOG_PHOTOS;
				} else if(uri.contains("video")) {
					articles = MainDAO.getBlog(pm, IConstants.CATEGORIE_VIDEOS);
					response = IResponse.BLOG_VIDEOS;
				} else if(uri.contains("musique")) {
					articles = MainDAO.getBlog(pm, IConstants.CATEGORIE_MUSIQUE);
					response = IResponse.BLOG_MUSIQUE;
				} else if(uri.contains("projet")) {
					articles = MainDAO.getBlog(pm, IConstants.CATEGORIE_PROJETS);
					response = IResponse.BLOG_PROJETS;
				} else if(uri.contains("verset")) {
					articles = MainDAO.getBlog(pm, IConstants.CATEGORIE_VERSETS);
					response = IResponse.BLOG_VERSETS;
				}  else if(uri.contains("lien")) {
					articles = MainDAO.getBlog(pm, IConstants.CATEGORIE_LIENS);
					response = IResponse.BLOG_LIENS;
				}

				model.addAttribute("listeArticles", articles);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ModelAndView(response);
	}
	
	public String getBlogSqlRequest(String categorie) {
		return "select from " + ArticleVO.class.getName() + " where categorie=='" + categorie + "'";
	}

}
