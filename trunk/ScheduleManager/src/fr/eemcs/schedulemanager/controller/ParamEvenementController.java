package fr.eemcs.schedulemanager.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import fr.eemcs.schedulemanager.dao.MainDAO;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.EvenementVO;
import fr.eemcs.schedulemanager.entity.LieuVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

@Controller
public class ParamEvenementController extends LoggerController {
 
	@RequestMapping("/parametrage/programme/list")
	public ModelAndView programmeList(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			List<Date> dates = new ArrayList<Date>();
			List<String> mois = new ArrayList<String>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				dates = MainDAO.getDatesEvenements(pm);
				
				for(Date d : dates) {
					if(!mois.contains(FormatHelper.formatDate(d, "yyyy/MM"))) {
						mois.add(FormatHelper.formatDate(d, "yyyy/MM"));
					}
				}
				Collections.sort(mois);
				Collections.reverse(mois);
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
	
	@RequestMapping("/parametrage/programme/get")
	public ModelAndView getProgramme(ModelMap model, @RequestParam(value="moisProgramme", required=true) String moisProgramme, HttpServletRequest request, HttpServletResponse response) {
		if(super.isLogged()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			List<EvenementVO> list = new ArrayList<EvenementVO>();
			try {
				list = MainDAO.getEvenementsByMonthYear(pm, moisProgramme);
				list.size(); // pour corriger "Object Manager has been closed"
				model.addAttribute("listeEvenements", list);
				model.addAttribute("titleProgramme", FormatHelper.getMois(Integer.parseInt(moisProgramme.substring(moisProgramme.indexOf("/") + 1)) - 1) + " " + moisProgramme.substring(0, 4));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			return new ModelAndView(IResponse.PROGRAMME_DETAILS);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/programme/delete")
	public ModelAndView deleteProgramme(ModelMap model, @RequestParam(value="moisProgramme", required=true) String moisProgramme, HttpServletRequest request, HttpServletResponse response) {
		if(super.isLogged()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			List<EvenementVO> list = new ArrayList<EvenementVO>();
			try {
				list = MainDAO.getEvenementsByMonthYear(pm, moisProgramme);
				list.size(); // pour corriger "Object Manager has been closed"
				for(EvenementVO event : list) {
					pm.deletePersistent(event);
				}
				pm.refreshAll();
				model.addAttribute("listeEvenements", list);
				model.addAttribute("titleProgramme", FormatHelper.getMois(Integer.parseInt(moisProgramme.substring(moisProgramme.indexOf("/") + 1)) - 1) + " " + moisProgramme.substring(0, 4));
			} catch (Exception e) {
				e.printStackTrace();
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
	
	@RequestMapping("/parametrage/programme/edit")
	public ModelAndView editProgramme(ModelMap model, @RequestParam(value="moisProgramme", required=true) String moisProgramme, HttpServletRequest request, HttpServletResponse response) {
		if(super.isLogged()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			List<EvenementVO> list = new ArrayList<EvenementVO>();
			try {
				list = MainDAO.getEvenementsByMonthYear(pm, moisProgramme);
				list.size(); // pour corriger "Object Manager has been closed"
				model.addAttribute("listeEvenements", list);
				model.addAttribute("titleProgramme", FormatHelper.getMois(Integer.parseInt(moisProgramme.substring(moisProgramme.indexOf("/") + 1)) - 1) + " " + moisProgramme.substring(0, 4));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			return new ModelAndView(IResponse.PROGRAMME_FORM);
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
			
			loadEvenementForm(model);
			return new ModelAndView(IResponse.EVENEMENT_FORM, "event", evenement);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/evenement/edit")
	public ModelAndView modif(ModelMap model, @RequestParam(value="idEvent", required=true) String idEvent, HttpServletRequest request, HttpServletResponse response) {
		if(super.isLogged()) {
			loadEvenementForm(model);
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			EvenementVO event = null;
			try {
				event = MainDAO.getEvenement(pm, idEvent);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			if(event == null) {
				return new ModelAndView(IResponse.ERROR);
			} else {
				model.addAttribute("eventForm", event);
			}
			return new ModelAndView(IResponse.EVENEMENT_FORM, "event", event);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/evenement/delete")
	public ModelAndView delete(ModelMap model, @RequestParam(value="idEvent", required=true) String idEvent, HttpServletRequest request) {
		if(super.isLogged()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			EvenementVO event = null;
			String dateProgramme = null;
			int nbEvents = 0;
			try {
				event = MainDAO.getEvenement(pm, idEvent);
				dateProgramme = FormatHelper.formatDate(event.getDate(), "yyyy/MM");
				pm.deletePersistent(event);
				pm.refreshAll();
				
				nbEvents = MainDAO.getEvenementsByMonthYear(pm, dateProgramme).size();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			if(nbEvents > 0) {
				return new ModelAndView("redirect:/controller/parametrage/programme/edit?moisProgramme=" + dateProgramme);
			} else {
				return new ModelAndView("redirect:/controller/parametrage/programme/list");
			}
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView(IResponse.LOGIN);
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
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				pm.currentTransaction().begin();
				
				if(event != null) {
					//Modification
					String idEvent = (String) request.getParameter("id");
					if(idEvent != null && !"".equals(idEvent)) {
						EvenementVO modifEvent = MainDAO.getEvenement(pm, FormatHelper.getId(idEvent));
						modifEvent.setLieu(KeyFactory.createKey("LieuVO", Long.parseLong(idLieu)));
						
						if(event.getResponsables() != null) {
							modifEvent.setResponsables(event.getResponsables());
						} else {
							modifEvent.setResponsables(new ArrayList<Key>());
						}
						if(!"-1".equals(presidence)) {
							modifEvent.getResponsables().add(0, KeyFactory.createKey("ContactVO", Long.parseLong(presidence)));
						} else {
							if(modifEvent.getResponsables().size() > 0) {
								modifEvent.getResponsables().set(0, null);
							} else {
								modifEvent.getResponsables().add(0, null);
							}
						}
						if(!"-1".equals(predicateur)) {
							modifEvent.getResponsables().add(1, KeyFactory.createKey("ContactVO", Long.parseLong(predicateur)));
						} else {
							if(modifEvent.getResponsables().size() > 1) {
								modifEvent.getResponsables().set(1, null);
							} else {
								modifEvent.getResponsables().add(1, null);
							}
						}
						if(!"-1".equals(traducteur)) {
							modifEvent.getResponsables().add(2, KeyFactory.createKey("ContactVO", Long.parseLong(traducteur)));
						} else {
							if(modifEvent.getResponsables().size() > 2) {
								modifEvent.getResponsables().set(2, null);
							} else {
								modifEvent.getResponsables().add(2, null);
							}
						}
						if(!"-1".equals(offrande)) {
							modifEvent.getResponsables().add(3, KeyFactory.createKey("ContactVO", Long.parseLong(offrande)));
						} else {
							if(modifEvent.getResponsables().size() > 3) {
								modifEvent.getResponsables().set(3, null);
							} else {
								modifEvent.getResponsables().add(3, null);
							}
						}
						
						modifEvent.setDivers(event.getDivers());
						
						modifEvent.setModification(new Date(), user);
						
						pm.currentTransaction().commit();
						pm.refreshAll();
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
						if(!"-1".equals(presidence)) {
							event.getResponsables().add(0, KeyFactory.createKey("ContactVO", Long.parseLong(presidence)));
						} else {
							event.getResponsables().add(0, null);
						}
						if(!"-1".equals(predicateur)) {
							event.getResponsables().add(1, KeyFactory.createKey("ContactVO", Long.parseLong(predicateur)));
						} else {
							event.getResponsables().add(1, null);
						}
						if(!"-1".equals(traducteur)) {
							event.getResponsables().add(2, KeyFactory.createKey("ContactVO", Long.parseLong(traducteur)));
						} else {
							event.getResponsables().add(2, null);
						}
						if(!"-1".equals(offrande)) {
							event.getResponsables().add(3, KeyFactory.createKey("ContactVO", Long.parseLong(offrande)));
						} else {
							event.getResponsables().add(3, null);
						}
						event.setCreation(new Date(), user);
						pm.makePersistent(event);
						pm.currentTransaction().commit();
						pm.refreshAll();
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
	
	public void loadEvenementForm(ModelMap model) {
		//Liste des lieux
		Map<String,String> mapLieux = new LinkedHashMap<String, String>();
		List<LieuVO> lieux = new ArrayList<LieuVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			lieux = MainDAO.getLieux(pm);
			for(LieuVO lieu : lieux) {
				mapLieux.put(lieu.getKey(), lieu.getNom());
			}
			model.addAttribute("mapLieux", mapLieux);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Liste pour la présidence, prédicateur, traducteur, offrande
		Map<String,String> mapPresidence = new LinkedHashMap<String, String>();
		for(ContactVO contact : MainDAO.getPresidence(pm)) {
			mapPresidence.put(contact.getKey(), contact.getPrenom());
		}
		model.addAttribute("mapPresidence", mapPresidence);
		
		Map<String,String> mapPredicateurs = new LinkedHashMap<String, String>();
		for(ContactVO contact : MainDAO.getPredicateurs(pm)) {
			mapPredicateurs.put(contact.getKey(), contact.getPrenom());
		}
		model.addAttribute("mapPredicateurs", mapPredicateurs);
		
		Map<String,String> mapTraducteurs = new LinkedHashMap<String, String>();
		for(ContactVO contact : MainDAO.getTraducteurs(pm)) {
			mapTraducteurs.put(contact.getKey(), contact.getPrenom());
		}
		model.addAttribute("mapTraducteurs", mapTraducteurs);
		
		Map<String,String> mapOffrande = new LinkedHashMap<String, String>();
		for(ContactVO contact : MainDAO.getOffrande(pm)) {
			mapOffrande.put(contact.getKey(), contact.getPrenom());
		}
		model.addAttribute("mapOffrande", mapOffrande);
		
		Map<String,String> mapResponsables = new LinkedHashMap<String, String>();
		for(ContactVO contact : MainDAO.getResponsables(pm)) {
			mapResponsables.put(contact.getKey(), contact.getPrenom());
		}
		model.addAttribute("mapResponsables", mapResponsables);
	}
	
}
