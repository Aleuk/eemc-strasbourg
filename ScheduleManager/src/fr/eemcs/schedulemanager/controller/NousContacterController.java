package fr.eemcs.schedulemanager.controller;

import java.util.Date;
import java.util.LinkedHashMap;
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

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.constants.IConstants;
import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.EmailVO;

@Controller
public class NousContacterController {
 
	@RequestMapping("/nousContacter")
	public ModelAndView execute(ModelMap model) {
		EmailVO email = new EmailVO();
		model.addAttribute("nousContacterForm", email);
		
		loadForm(model);
		return new ModelAndView(IResponse.NOUS_CONTACTER);
	}
	
	@RequestMapping(value="/nousContacter", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("nousContacterForm") EmailVO email, BindingResult result) {
		//TODO gestion de recaptcha
		String retour = IResponse.ERROR;
		if(email != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				email.setCreation(new Date(), new User(email.getEmail(), ""));
				pm.makePersistent(email);
				
				//Envoi de l'email
				//TODO faire un switch sur la catégorie pour savoir à qui envoyer le mail
				Properties props = new Properties();
		        Session session = Session.getDefaultInstance(props, null);

		        try {
		            Message msg = new MimeMessage(session);
		            msg.setFrom(new InternetAddress(email.getEmail(), email.getEmail()));
		            msg.addRecipient(Message.RecipientType.TO,
		                             new InternetAddress("gabriel_chao@hotmail.com", "Gabriel CHAO"));
		            msg.setSubject(email.getDescription());
		            msg.setText(email.getContenuString());
		            Transport.send(msg);
					retour = IResponse.NOUS_CONTACTER_SUCCES;
		        } catch (AddressException e) {
		            e.printStackTrace();
		        } catch (MessagingException e) {
		            e.printStackTrace();
		        }
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
		}
		return new ModelAndView(retour);
	}
	
	public void loadForm(ModelMap model) {
		Map<String,String> mapCategories = new LinkedHashMap<String, String>();
		mapCategories.put(IConstants.CATEGORIE_PRIERE, IConstants.CATEGORIE_PRIERE);
		mapCategories.put(IConstants.CATEGORIE_SUGGESTION, IConstants.CATEGORIE_SUGGESTION);
		mapCategories.put(IConstants.CATEGORIE_DIVERS, IConstants.CATEGORIE_DIVERS);
		mapCategories.put(IConstants.CATEGORIE_BUG, IConstants.CATEGORIE_BUG);
		model.addAttribute("mapCategories", mapCategories);
	}
}
