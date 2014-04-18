package fr.eemcs.schedulemanager.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.eemcs.schedulemanager.client.EvenementInfo;
import fr.eemcs.schedulemanager.client.IAjaxService;
import fr.eemcs.schedulemanager.client.LieuInfo;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.EvenementVO;
import fr.eemcs.schedulemanager.entity.LieuVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

@SuppressWarnings("serial")
public class AjaxServiceImpl extends RemoteServiceServlet implements IAjaxService {

	@Override
	@SuppressWarnings("unchecked")
    public List<EvenementInfo> getEventsServer(String sDate) {
		List<EvenementInfo> list = new ArrayList<EvenementInfo>();
		List<EvenementVO> events = new ArrayList<EvenementVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(EvenementVO.class);
			query.setFilter("date > today");
			query.declareParameters("java.util.Date today");
			query.setOrdering("date");
			query.setRange(0, 7);
			
			Date today = new Date();
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(today);
			cal.add(Calendar.HOUR_OF_DAY, -3);
			events = (List<EvenementVO>)pm.newQuery(query).execute(cal.getTime());
			
			for(EvenementVO event : events) {
				LieuInfo l = null;
				
				Key k = event.getLieu();
				if(k != null) {
					LieuVO lieu = pm.getObjectById(LieuVO.class, k);
					if(lieu != null) {
						l = new LieuInfo();
						l.setNom(lieu.getNom());
						l.setAdresse(lieu.getAdresse());
						l.setCodePostal(lieu.getCodePostal());
						l.setVille(lieu.getVille());
					}
				}
				EvenementInfo e = new EvenementInfo();
				e.setDate(FormatHelper.formatDate(event.getDate(), "dd/MM/yy - HH:mm"));
				e.setLieu(l);
				e.setDivers(event.getDivers());
				list.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
    	return list;
    }
}
