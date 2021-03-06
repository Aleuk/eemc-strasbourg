package fr.eemcs.schedulemanager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import fr.eemcs.schedulemanager.constants.IResponse;
import fr.eemcs.schedulemanager.dao.MainDAO;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.ImageVO;

@Controller
public class ParamPhotoController extends LoggerController{
	
	@RequestMapping("/parametrage/photo/list")
	public ModelAndView photoList(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			List<ImageVO> photos = new ArrayList<ImageVO>();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				photos = MainDAO.getImages(pm);
				Map<String, List<ImageVO>> maps = new HashMap<String, List<ImageVO>>();
				for(ImageVO img : photos) {
					if(!maps.containsKey(img.getDossier())) {
						maps.put(img.getDossier(), new ArrayList<ImageVO>());
						maps.get(img.getDossier()).add(img);
					} else {
						maps.get(img.getDossier()).add(img);
					}
				}
				model.addAttribute("listePhotos", maps);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView(IResponse.PHOTO_LIST);
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/photo/add")
	public ModelAndView addPhoto(ModelMap model, HttpServletRequest request) {
		if(super.isLogged()) {
			ImageVO img = new ImageVO();
			model.addAttribute("photoForm", img);
			
			return new ModelAndView(IResponse.PHOTO_FORM, "photo", new ImageVO());
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView("redirect:" + getUrl());
		}
	}
	
	@RequestMapping("/parametrage/photo/delete")
	public ModelAndView deletePhoto(ModelMap model, @RequestParam(value="idPhoto", required=true) String idPhoto, HttpServletRequest request) {
		if(super.isLogged()) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			ImageVO image = null;
			try {
				image = MainDAO.getImage(pm, idPhoto);
				//Suppression du blob
				BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
				blobstoreService.delete(image.getImage());
				//Suppression de l'image
				pm.deletePersistent(image);
				pm.refreshAll();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
			return new ModelAndView("redirect:/controller/parametrage/photo/list");
		} else {
			UserService userService = UserServiceFactory.getUserService();
			setUrl(userService.createLoginURL(request.getRequestURI()));
			return new ModelAndView(IResponse.LOGIN);
		}
	}
	
	@RequestMapping("/parametrage/photo/upload")
	public ModelAndView uploadPhoto(HttpServletRequest request, @ModelAttribute("photoForm") ImageVO photo, BindingResult result) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		Map<String, List<BlobKey>> uploads = blobstoreService.getUploads(request);
		
		List<BlobKey> blobKeys = uploads.get("myFile");

        if (blobKeys == null) {
        	return new ModelAndView(IResponse.ERROR);
        } else {
        	try {
	        	for(BlobKey bk : blobKeys) {
	        		ImageVO img = new ImageVO();
	        		img.setDossier(photo.getDossier());
	        		img.setImage(bk);
	        		img.setCreation(new Date(), user);
					pm.makePersistent(img);
	        	}
        		pm.refreshAll();
        	} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pm.close();
			}
        }

		return new ModelAndView("redirect:/controller/parametrage/photo/list");
	}
	
}
