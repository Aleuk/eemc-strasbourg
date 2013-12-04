package fr.eemcs.schedulemanager.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import fr.eemcs.schedulemanager.helper.FormatHelper;

@Controller
public class ParamEvenementController extends LoggerController {
 
	@RequestMapping("/parametrage/programme/list")
	public ModelAndView programmeList(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			List<Date> dates = new ArrayList<Date>();
			Set<String> mois = new HashSet<String>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String query = "select date from " + EvenementVO.class.getName() + " order by date desc";
				dates = (List<Date>)pm.newQuery(query).execute();
				
				for(Date d : dates) {
					if(!mois.contains(FormatHelper.formatDate(d, "MM/yyyy"))) {
						mois.add(FormatHelper.formatDate(d, "MM/yyyy"));
					}
				}
				model.addAttribute("listeProgrammes", mois);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
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
			evenement.setResponsables(new ArrayList<Key>());
			model.addAttribute("eventForm", evenement);
			model.addAttribute("heure", "15:00");
			model.addAttribute("presidence", new ContactVO());
			
			loadProgrammeForm(model);
			return new ModelAndView(IResponse.EVENEMENT_FORM, "event", evenement);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/evenement/save")
	public ModelAndView save(HttpServletRequest request, @Valid @ModelAttribute("eventForm") EvenementVO event, BindingResult result) {
		if(super.isLogged()) {
			String idLieu = (String) request.getParameter("lieu");
			String presidence = (String) request.getParameter("presidence");
			String predicateur = (String) request.getParameter("predicateur");
			String traducteur = (String) request.getParameter("traducteur");
			String offrande = (String) request.getParameter("offrande");
			
			LieuVO lieu = null;
			ContactVO contactPresidence = null;
			ContactVO contactPredicateur = null;
			ContactVO contactTraducteur = null;
			ContactVO contactOffrande = null;
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.currentTransaction().begin();
				if(idLieu != null && !"".equals(idLieu)) {
					lieu = pm.getObjectById(LieuVO.class, KeyFactory.createKey("LieuVO", Long.parseLong(idLieu)));
				}
				if(presidence != null && !"".equals(presidence) && !"-1".equals(presidence)) {
					contactPresidence = pm.getObjectById(ContactVO.class, KeyFactory.createKey("ContactVO", Long.parseLong(presidence)));
				}
				if(predicateur != null && !"".equals(predicateur) && !"-1".equals(predicateur)) {
					contactPredicateur = pm.getObjectById(ContactVO.class, KeyFactory.createKey("ContactVO", Long.parseLong(predicateur)));
				}
				if(traducteur != null && !"".equals(traducteur) && !"-1".equals(traducteur)) {
					contactTraducteur = pm.getObjectById(ContactVO.class, KeyFactory.createKey("ContactVO", Long.parseLong(traducteur)));
				}
				if(offrande != null && !"".equals(offrande) && !"-1".equals(offrande)) {
					contactOffrande = pm.getObjectById(ContactVO.class, KeyFactory.createKey("ContactVO", Long.parseLong(offrande)));
				}
				
				if(event != null) {
					//Modification
					String idEvent = (String) request.getParameter("id");
					if(idEvent != null && !"".equals(idEvent)) {
						
						Key k = KeyFactory.createKey("EvenementVO", Long.parseLong(FormatHelper.getId(idEvent)));
						EvenementVO modifEvent = pm.getObjectById(EvenementVO.class, k);
						modifEvent.setLieu(KeyFactory.createKey("LieuVO", Long.parseLong(idLieu)));
						
						modifEvent.setResponsables(event.getResponsables());
						modifEvent.getResponsables().add(0, KeyFactory.createKey("ContactVO", Long.parseLong(presidence)));
						modifEvent.getResponsables().add(1, KeyFactory.createKey("ContactVO", Long.parseLong(predicateur)));
						modifEvent.getResponsables().add(2, KeyFactory.createKey("ContactVO", Long.parseLong(traducteur)));
						modifEvent.getResponsables().add(3, KeyFactory.createKey("ContactVO", Long.parseLong(offrande)));
						
						modifEvent.setDivers(event.getDivers());
						
						modifEvent.setModification(new Date(), user);
						
						pm.currentTransaction().commit();
					} else {
						//Création
						GregorianCalendar cal = new GregorianCalendar();
						cal.setTime(event.getDate());
						cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(event.getHeure().substring(0, event.getHeure().indexOf(":"))));
						cal.set(Calendar.MINUTE, Integer.parseInt(event.getHeure().substring(event.getHeure().indexOf(":") + 1)));
						event.setDate(cal.getTime());
						event.setLieu(KeyFactory.createKey("LieuVO", Long.parseLong(idLieu)));
						if(event.getResponsables() == null) {
							event.setResponsables(new ArrayList<Key>());
						}
						event.getResponsables().add(0, KeyFactory.createKey("ContactVO", Long.parseLong(presidence)));
						event.getResponsables().add(1, KeyFactory.createKey("ContactVO", Long.parseLong(predicateur)));
						event.getResponsables().add(2, KeyFactory.createKey("ContactVO", Long.parseLong(traducteur)));
						event.getResponsables().add(3, KeyFactory.createKey("ContactVO", Long.parseLong(offrande)));
						event.setCreation(new Date(), user);
						pm.makePersistent(event);
						pm.currentTransaction().commit();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				pm.currentTransaction().rollback();
			} finally {
				pm.close();
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
