package fr.eemcs.schedulemanager.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.DAO.interfaces.IBaseDAO;
import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

@Controller
public class ContactAction extends LoggerAction{
	private String url;
	
	private IBaseDAO baseDAO;
	private List<ContactVO> listeContacts = new ArrayList<ContactVO>();
	private List<String> listeCivilites = new ArrayList<String>();
	private String dateNaissance;
	private int idContact = -1;
	 
	public String getUrl() {
		return url;
	}
 
	public void setUrl(String _url) {
		this.url = _url;
	}
	
	@RequestMapping("/contact/list")
	public ModelAndView list() {
		boolean logged = super.isLogged();
		if(logged) {
			//List<ContactVO> contacts = baseDAO.getContacts();
			List<ContactVO> contacts = new ArrayList<ContactVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String query = "select from " + ContactVO.class.getName();
				contacts = (List<ContactVO>)pm.newQuery(query).execute();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if(contacts != null && contacts.size() > 0) {
				listeContacts = contacts;
			}
			return new ModelAndView(IResponse.CONTACT_LIST, "listeContacts", listeContacts);
		} else {
			HttpServletRequest req = ServletActionContext.getRequest();
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return new ModelAndView(IResponse.LOGIN);
		}
	}
	
	@RequestMapping("/contact/add")
	public ModelAndView add(Model model) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			ContactVO contact = new ContactVO("", "", null);
			model.addAttribute("contactForm", contact);
			listeCivilites.add("Monsieur");
			listeCivilites.add("Madame");
			listeCivilites.add("Mademoiselle");
			Map<String,String> mapCivilites = new LinkedHashMap();
			mapCivilites.put("Monsieur", "Monsieur");
			mapCivilites.put("Madame", "Madame");
			mapCivilites.put("Mademoiselle", "Mademoiselle");
			model.addAttribute("mapCivilites", mapCivilites);
			return new ModelAndView(IResponse.CONTACT_FORM, "contact", new ContactVO("", "", null));
		} else {
			HttpServletRequest req = ServletActionContext.getRequest();
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return new ModelAndView(IResponse.LOGIN);
		}
	}
	
	@RequestMapping("/contact/save")
	public ModelAndView save(@ModelAttribute("contactForm") ContactVO contact, BindingResult result) {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			if(contact != null) {
				PersistenceManager pm = PMF.get().getPersistenceManager();
				if(idContact > 0) {
					try {
						pm.currentTransaction().begin();
						
						ContactVO modifContact = pm.getObjectById(ContactVO.class, idContact);
						modifContact.setCivilite(contact.getCivilite());
						modifContact.setNom(contact.getNom());
						modifContact.setPrenom(contact.getPrenom());
						modifContact.setNomKH(contact.getNomKH());
						modifContact.setPrenomKH(contact.getPrenomKH());
						if(!"".equals(dateNaissance)) { //AAAA-mm-dd
							modifContact.setDateNaissance(FormatHelper.getDate(dateNaissance, "yyyy-MM-dd"));
						}
						modifContact.setEmail(contact.getEmail());
						modifContact.setAdresse(contact.getAdresse());
						modifContact.setCodePostal(contact.getCodePostal());
						modifContact.setVille(contact.getVille());
						modifContact.setTelephone1(contact.getTelephone1());
						modifContact.setTelephone2(contact.getTelephone2());
						modifContact.setPredicateur(contact.isPredicateur());
						modifContact.setConducteurLouange(contact.isConducteurLouange());
						modifContact.setResponsable(contact.isResponsable());
						
						modifContact.setModification(new Date(), user);
						
						pm.currentTransaction().commit();
					} catch (Exception e) {
						pm.currentTransaction().rollback();
					} finally {
						pm.close();
					}
				} else {
					try {
						if(!"".equals(dateNaissance)) { //AAAA-mm-dd
							//contact.setDateNaissance(FormatHelper.getDate(dateNaissance, "yyyy-MM-dd"));
						}
						contact.setCreation(new Date(), user);
						pm.makePersistent(contact);
					} finally {
						pm.close();
					}
				}
			}
			return new ModelAndView("redirect:/controller/contact/list");
		} else {
			HttpServletRequest req = ServletActionContext.getRequest();
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return new ModelAndView(IResponse.LOGIN);
		}
	}
	
	@RequestMapping("contact/modif")
	public ModelAndView modif() {
		HttpServletRequest req = ServletActionContext.getRequest();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			listeCivilites.add("Monsieur");
			listeCivilites.add("Madame");
			listeCivilites.add("Mademoiselle");
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			ContactVO contact = null;
			try {
				contact = pm.getObjectById(ContactVO.class, idContact);
			} finally {
				pm.close();
			}
			if(contact == null) {
				return new ModelAndView(IResponse.ERROR);
			} else {
				dateNaissance = FormatHelper.formatDate(contact.getDateNaissance(), "yyyy-MM-dd");
			}
			return new ModelAndView(IResponse.CONTACT_FORM);
		} else {
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return new ModelAndView(IResponse.LOGIN);
		}
	}
	
	@RequestMapping("/contact/delete")
	public ModelAndView delete() {
		HttpServletRequest req = ServletActionContext.getRequest();
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			ContactVO contact = null;
			try {
				contact = pm.getObjectById(ContactVO.class, idContact);
				pm.deletePersistent(contact);
			} finally {
				pm.close();
			}
			return new ModelAndView(IResponse.CONTACT_LIST);
		} else {
			setUrl(userService.createLoginURL(req.getRequestURI()));
			return new ModelAndView(IResponse.LOGIN);
		}
	}

	public List<ContactVO> getListeContacts() {
		return listeContacts;
	}

	public void setListeContacts(List<ContactVO> listeContacts) {
		this.listeContacts = listeContacts;
	}

	public void setBaseDAO(IBaseDAO contactDAO) {
		this.baseDAO = contactDAO;
	}

	public List<String> getListeCivilites() {
		return listeCivilites;
	}

	public void setListeCivilites(List<String> listeCivilites) {
		this.listeCivilites = listeCivilites;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public int getIdContact() {
		return idContact;
	}

	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}
	
}
