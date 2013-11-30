package fr.eemcs.schedulemanager.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.EvenementVO;

public class LoggerController {
	User user = null;
	
	private String url;
	
	public boolean isLogged() {
		UserService userService = UserServiceFactory.getUserService();
		user = userService.getCurrentUser();
		
		return (user != null);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@InitBinder
    protected void initBinderMultiSelect(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "responsables", new CustomCollectionEditor(List.class)
          {
            @Override
            protected Object convertElement(Object option)
            {
            	 PersistenceManager pm = PMF.get().getPersistenceManager();
                 try {

                     Long id = new Long(String.valueOf(option));
                     Key k = KeyFactory.createKey("ContactVO", id);
                     System.out.println(k);
                     return k;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return option;
            }
          });
    }
	
	public String getUrl() {
		return url;
	}
 
	public void setUrl(String _url) {
		this.url = _url;
	}
}
