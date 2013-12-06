package fr.eemcs.schedulemanager.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.KeyFactory;

import fr.eemcs.schedulemanager.entity.ArticleVO;
import fr.eemcs.schedulemanager.entity.ContactVO;
import fr.eemcs.schedulemanager.entity.EvenementVO;
import fr.eemcs.schedulemanager.entity.LieuVO;

public final class MainDAO {
	@SuppressWarnings("unchecked")
	public static List<ContactVO> getContacts(PersistenceManager pm) {
		List<ContactVO> result = new ArrayList<ContactVO>();
		try {
			String query = "select from " + ContactVO.class.getName();
			result = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static ContactVO getContact(PersistenceManager pm, String id) {
		ContactVO contact = null;
		try {
			if(!"".equals(id)) {
				contact = pm.getObjectById(ContactVO.class, KeyFactory.createKey("ContactVO", Long.parseLong(id)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contact;
	}
	
	@SuppressWarnings("unchecked")
	public static List<LieuVO> getLieux(PersistenceManager pm) {
		List<LieuVO> result = new ArrayList<LieuVO>();
		try {
			String query = "select from " + LieuVO.class.getName();
			result = (List<LieuVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static LieuVO getLieu(PersistenceManager pm, String id) {
		LieuVO lieu = null;
		try {
			if(!"".equals(id)) {
				lieu = pm.getObjectById(LieuVO.class, KeyFactory.createKey("LieuVO", Long.parseLong(id)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lieu;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ArticleVO> getArticles(PersistenceManager pm) {
		List<ArticleVO> result = new ArrayList<ArticleVO>();
		try {
			String query = "select from " + ArticleVO.class.getName();
			result = (List<ArticleVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static ArticleVO getArticle(PersistenceManager pm, String id) {
		ArticleVO article = null;
		try {
			if(!"".equals(id)) {
				article = pm.getObjectById(ArticleVO.class, KeyFactory.createKey("ArticleVO", Long.parseLong(id)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ArticleVO> getBlog(PersistenceManager pm, String categorie) {
		List<ArticleVO> result = new ArrayList<ArticleVO>();
		try {
			String query = "select from " + ArticleVO.class.getName() + " where categorie=='" + categorie + "'";
			result = (List<ArticleVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Date> getDatesEvenements(PersistenceManager pm) {
		List<Date> result = new ArrayList<Date>();
		try {
			String query = "select date from " + EvenementVO.class.getName() + " order by date desc";
			result = (List<Date>)pm.newQuery(query).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static EvenementVO getEvenement(PersistenceManager pm, String id) {
		EvenementVO evenement = null;
		try {
			if(!"".equals(id)) {
				evenement = pm.getObjectById(EvenementVO.class, KeyFactory.createKey("EvenementVO", Long.parseLong(id)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return evenement;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ContactVO> getPresidence(PersistenceManager pm) {
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		try {
			String query = "select from " + ContactVO.class.getName() + " where conducteurLouange == true";
			contacts = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ContactVO> getPredicateurs(PersistenceManager pm) {
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		try {
			String query = "select from " + ContactVO.class.getName() + " where predicateur == true";
			contacts = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ContactVO> getTraducteurs(PersistenceManager pm) {
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		try {
			String query = "select from " + ContactVO.class.getName() + " where traducteur == true";
			contacts = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ContactVO> getOffrande(PersistenceManager pm) {
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		try {
			String query = "select from " + ContactVO.class.getName() + " where offrande == true";
			contacts = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ContactVO> getResponsables(PersistenceManager pm) {
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		try {
			String query = "select from " + ContactVO.class.getName() + " where responsable == true";
			contacts = (List<ContactVO>)pm.newQuery(query).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}
}
