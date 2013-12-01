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
import fr.eemcs.schedulemanager.helper.FormatHelper;

@Controller
public class ParamArticleController extends LoggerController {
 
	@RequestMapping("/parametrage/article/list")
	public ModelAndView articleList(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			List<ArticleVO> articles = new ArrayList<ArticleVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String query = "select from " + ArticleVO.class.getName();
				articles = (List<ArticleVO>)pm.newQuery(query).execute();
				model.addAttribute("listeArticles", articles);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView(IResponse.ARTICLE_LIST);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/article/add")
	public ModelAndView addArticle(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			ArticleVO article = new ArticleVO(user.getEmail());
			model.addAttribute("articleForm", article);
			
			loadArticleForm(model);
			return new ModelAndView(IResponse.ARTICLE_FORM, "article", new ArticleVO(user.getEmail()));
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/article/modif")
	public ModelAndView modifArticle(ModelMap model, @RequestParam(value="idArticle", required=true) String idArticle, HttpServletRequest request,  
            HttpServletResponse response) {
		if(super.isLogged()) {
			loadArticleForm(model);
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			ArticleVO article = null;
			try {
				if(!"".equals(idArticle)) {
					article = pm.getObjectById(ArticleVO.class, Long.parseLong(idArticle));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			if(article == null) {
				return new ModelAndView(IResponse.ERROR);
			} else {
				model.addAttribute("articleForm", article);
			}
			return new ModelAndView(IResponse.ARTICLE_FORM, "article", article);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/article/delete")
	public ModelAndView deleteArticle(ModelMap model, @RequestParam(value="idArticle", required=true) String idArticle, HttpServletRequest request) {
		if(super.isLogged()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			ArticleVO article = null;
			try {
				article = pm.getObjectById(ArticleVO.class, Long.parseLong(idArticle));
				pm.deletePersistent(article);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			return new ModelAndView("redirect:/controller/parametrage/article/list");
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView(IResponse.LOGIN);
		}
	}
	
	@RequestMapping("/parametrage/article/save")
	public ModelAndView saveArticle(HttpServletRequest request, @ModelAttribute("articleForm") ArticleVO article, BindingResult result) {
		if(super.isLogged()) {
			if(article != null) {
				PersistenceManager pm = PMF.get().getPersistenceManager();
				String idArticle = (String) request.getParameter("id");
				if(idArticle != null && !"".equals(idArticle)) {
					try {
						pm.currentTransaction().begin();
						
						Key k = KeyFactory.createKey("ArticleVO", Long.parseLong(FormatHelper.getId(idArticle)));
						ArticleVO modifArticle = pm.getObjectById(ArticleVO.class, k);
						modifArticle.setDateCreationArticle(article.getDateCreationArticle());
						modifArticle.setDescription(article.getDescription());
						modifArticle.setContenu(article.getContenuString());
						modifArticle.setDateModificationArticle(new Date());
						modifArticle.setModification(new Date(), user);
						
						pm.currentTransaction().commit();
					} catch (Exception e) {
						e.printStackTrace();
						pm.currentTransaction().rollback();
					} finally {
						pm.close();
					}
				} else {
					try {
						article.setDateCreationArticle(new Date());
						article.setAuteur(user.getEmail());
						article.setCreation(new Date(), user);
						pm.makePersistent(article);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						pm.close();
					}
				}
			}
			return new ModelAndView("redirect:/controller/parametrage/article/list");
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	public void loadArticleForm(ModelMap model) {
		Map<String,String> mapCategories = new LinkedHashMap<String, String>();
		mapCategories.put(IConstants.CATEGORIE_ACCUEIL, IConstants.CATEGORIE_ACCUEIL);
		mapCategories.put(IConstants.CATEGORIE_HISTORIQUE, IConstants.CATEGORIE_HISTORIQUE);
		mapCategories.put(IConstants.CATEGORIE_ACTIVITES, IConstants.CATEGORIE_ACTIVITES);
		mapCategories.put(IConstants.CATEGORIE_MESSAGES, IConstants.CATEGORIE_MESSAGES);
		mapCategories.put(IConstants.CATEGORIE_MUSIQUE, IConstants.CATEGORIE_MUSIQUE);
		mapCategories.put(IConstants.CATEGORIE_VIDEOS, IConstants.CATEGORIE_VIDEOS);
		mapCategories.put(IConstants.CATEGORIE_PHOTOS, IConstants.CATEGORIE_PHOTOS);
		mapCategories.put(IConstants.CATEGORIE_PROJETS, IConstants.CATEGORIE_PROJETS);
		mapCategories.put(IConstants.CATEGORIE_VERSETS, IConstants.CATEGORIE_VERSETS);
		model.addAttribute("mapCategories", mapCategories);
	}
}
