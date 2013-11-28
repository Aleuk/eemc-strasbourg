package fr.eemcs.schedulemanager.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.constants.IConstants;
import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.EmailVO;
import fr.eemcs.schedulemanager.entity.EvenementVO;
import fr.eemcs.schedulemanager.entity.LieuVO;
import fr.eemcs.schedulemanager.entity.ProgrammeVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

@Controller
public class ParamEvenementController extends LoggerController {
 
	@RequestMapping("/parametrage/programme/list")
	public ModelAndView programmeList(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			List<ProgrammeVO> programmes = new ArrayList<ProgrammeVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String query = "select from " + EvenementVO.class.getName() + " order by date desc";
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
	
	@RequestMapping("/parametrage/evenement/add")
	public ModelAndView addEvenement(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			EvenementVO evenement = new EvenementVO();
			model.addAttribute("eventForm", evenement);
			
			loadProgrammeForm(model);
			return new ModelAndView(IResponse.EVENEMENT_FORM, "event", evenement);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/evenement/save")
	public ModelAndView save(HttpServletRequest request, @ModelAttribute("eventForm") EvenementVO event, BindingResult result) {
		if(super.isLogged()) {
			if(event != null) {
				PersistenceManager pm = PMF.get().getPersistenceManager();
				String idEvent = (String) request.getParameter("id");
				if(idEvent != null && !"".equals(idEvent)) {
					try {
						pm.currentTransaction().begin();
						
						Key k = KeyFactory.createKey("EvenementVO", Long.parseLong(FormatHelper.getId(idEvent)));
						EvenementVO modifEvent = pm.getObjectById(EvenementVO.class, k);
						modifEvent.setLieu(event.getLieu());
						modifEvent.setPresidence(event.getPresidence());
						modifEvent.setPredicateur(event.getPredicateur());
						modifEvent.setTraducteur(event.getTraducteur());
						modifEvent.setOffrande(event.getOffrande());
						modifEvent.setDivers(event.getDivers());
						
						modifEvent.setModification(new Date(), user);
						
						pm.currentTransaction().commit();
					} catch (Exception e) {
						e.printStackTrace();
						pm.currentTransaction().rollback();
					} finally {
						pm.close();
					}
				} else {
					try {
						event.setCreation(new Date(), user);
						pm.makePersistent(event);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						pm.close();
					}
				}
			}
			return new ModelAndView("redirect:/controller/parametrage/programme/list");
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	public void loadProgrammeForm(ModelMap model) {
		//Liste des lieux
		Map<String,String> mapLieux = new LinkedHashMap<String, String>();
		List<LieuVO> lieux = new ArrayList<LieuVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + LieuVO.class.getName();
			lieux = (List<LieuVO>)pm.newQuery(query).execute();
			for(LieuVO lieu : lieux) {
				mapLieux.put(lieu.getKey(), lieu.getNom());
			}
			model.addAttribute("mapLieux", mapLieux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Liste pour la présidence, prédicateur, traducteur, offrande
		Map<String,String> mapPresidence = new LinkedHashMap<String, String>();
		for(ContactVO contact : getPresidence()) {
			mapPresidence.put(contact.getKey(), contact.getPrenom());
		}
		model.addAttribute("mapPresidence", mapPresidence);
		
		Map<String,String> mapPredicateurs = new LinkedHashMap<String, String>();
		for(ContactVO contact : getPredicateurs()) {
			mapPredicateurs.put(contact.getKey(), contact.getPrenom());
		}
		model.addAttribute("mapPredicateurs", mapPredicateurs);
		
		Map<String,String> mapTraducteurs = new LinkedHashMap<String, String>();
		for(ContactVO contact : getTraducteurs()) {
			mapTraducteurs.put(contact.getKey(), contact.getPrenom());
		}
		model.addAttribute("mapTraducteurs", mapTraducteurs);
		
		Map<String,String> mapOffrande = new LinkedHashMap<String, String>();
		for(ContactVO contact : getOffrande()) {
			mapOffrande.put(contact.getKey(), contact.getPrenom());
		}
		model.addAttribute("mapOffrande", mapOffrande);
		
		Map<String,String> mapResponsables = new LinkedHashMap<String, String>();
		for(ContactVO contact : getResponsables()) {
			mapResponsables.put(contact.getKey(), contact.getPrenom());
		}
		model.addAttribute("mapResponsables", mapResponsables);
	}
	
	public List<ContactVO> getPresidence() {
		//List<ContactVO> contacts = baseDAO.getContacts();
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + ContactVO.class.getName() + " where conducteurLouange != null";
			contacts = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return contacts;
	}
	
	public List<ContactVO> getPredicateurs() {
		//List<ContactVO> contacts = baseDAO.getContacts();
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + ContactVO.class.getName() + " where predicateur != null";
			contacts = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return contacts;
	}
	
	public List<ContactVO> getTraducteurs() {
		//List<ContactVO> contacts = baseDAO.getContacts();
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + ContactVO.class.getName() + " where traducteur != null";
			contacts = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return contacts;
	}
	
	public List<ContactVO> getOffrande() {
		//List<ContactVO> contacts = baseDAO.getContacts();
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + ContactVO.class.getName() + " where offrande != null";
			contacts = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return contacts;
	}
	
	public List<ContactVO> getResponsables() {
		//List<ContactVO> contacts = baseDAO.getContacts();
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + ContactVO.class.getName() + " where responsable != null";
			contacts = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return contacts;
	}
}
