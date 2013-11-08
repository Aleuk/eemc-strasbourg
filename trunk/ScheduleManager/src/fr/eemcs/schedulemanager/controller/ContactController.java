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

import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

@Controller
public class ContactController extends LoggerController{
	
	@RequestMapping("/contact/list")
	public ModelAndView list(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			//List<ContactVO> contacts = baseDAO.getContacts();
			List<ContactVO> contacts = new ArrayList<ContactVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String query = "select from " + ContactVO.class.getName();
				contacts = (List<ContactVO>)pm.newQuery(query).execute();
				model.addAttribute("listeContacts", contacts);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return new ModelAndView(IResponse.CONTACT_LIST);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/contact/add")
	public ModelAndView add(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			ContactVO contact = new ContactVO("", "", null);
			model.addAttribute("contactForm", contact);
			
			loadForm(model);
			return new ModelAndView(IResponse.CONTACT_FORM, "contact", new ContactVO("", "", null));
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/contact/modif")
	public ModelAndView modif(ModelMap model, @RequestParam(value="idContact", required=true) String idContact, HttpServletRequest request,  
            HttpServletResponse response) {
		if(super.isLogged()) {
			loadForm(model);
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			ContactVO contact = null;
			try {
				if(!"".equals(idContact)) {
					Key cle = KeyFactory.createKey(ContactVO.class.getSimpleName(), Long.parseLong(idContact));
					contact = pm.getObjectById(ContactVO.class, cle);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			if(contact == null) {
				return new ModelAndView(IResponse.ERROR);
			} else {
				String dateNaissance = FormatHelper.formatDate(contact.getDateNaissance(), "yyyy-MM-dd");
				model.addAttribute("contactForm", contact);
				model.addAttribute("dateNaissance", dateNaissance);
			}
			return new ModelAndView(IResponse.CONTACT_FORM, "contact", contact);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/contact/delete")
	public ModelAndView delete(ModelMap model, @RequestParam(value="idContact", required=true) String idContact, HttpServletRequest request) {
		if(super.isLogged()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			ContactVO contact = null;
			try {
				contact = pm.getObjectById(ContactVO.class, Long.parseLong(idContact));
				pm.deletePersistent(contact);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			return new ModelAndView(IResponse.CONTACT_LIST);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView(IResponse.LOGIN);
		}
	}
	
	@RequestMapping("/contact/save")
	public ModelAndView save(HttpServletRequest request, @ModelAttribute("contactForm") ContactVO contact, BindingResult result) {
		if(super.isLogged()) {
			if(contact != null) {
				PersistenceManager pm = PMF.get().getPersistenceManager();
				String idContact = (String) request.getParameter("id");
				if(idContact != null && !"".equals(idContact)) {
					try {
						pm.currentTransaction().begin();
						
						ContactVO modifContact = pm.getObjectById(ContactVO.class, Long.parseLong(request.getParameter("id")));
						modifContact.setCivilite(contact.getCivilite());
						modifContact.setNom(contact.getNom());
						modifContact.setPrenom(contact.getPrenom());
						modifContact.setNomKH(contact.getNomKH());
						modifContact.setPrenomKH(contact.getPrenomKH());
						modifContact.setDateNaissance(contact.getDateNaissance());
						modifContact.setEmail(contact.getEmail());
						modifContact.setAdresse(contact.getAdresse());
						modifContact.setCodePostal(contact.getCodePostal());
						modifContact.setVille(contact.getVille());
						modifContact.setTelephone1(contact.getTelephone1());
						modifContact.setTelephone2(contact.getTelephone2());
						modifContact.setPredicateur(contact.getPredicateur());
						modifContact.setConducteurLouange(contact.getConducteurLouange());
						modifContact.setResponsable(contact.getResponsable());
						
						modifContact.setModification(new Date(), user);
						
						pm.currentTransaction().commit();
					} catch (Exception e) {
						e.printStackTrace();
						pm.currentTransaction().rollback();
					} finally {
						pm.close();
					}
				} else {
					try {
						contact.setCreation(new Date(), user);
						pm.makePersistent(contact);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						pm.close();
					}
				}
			}
			return new ModelAndView("redirect:/controller/contact/list");
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	public void loadForm(ModelMap model) {
		Map<String,String> mapCivilites = new LinkedHashMap<String, String>();
		mapCivilites.put("Monsieur", "Monsieur"); //TODO le passer en enum
		mapCivilites.put("Madame", "Madame");
		mapCivilites.put("Mademoiselle", "Mademoiselle");
		model.addAttribute("mapCivilites", mapCivilites);
	}

}
