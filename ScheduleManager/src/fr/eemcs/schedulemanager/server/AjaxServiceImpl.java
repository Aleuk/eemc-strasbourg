package fr.eemcs.schedulemanager.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.eemcs.schedulemanager.client.EvenementInfo;
import fr.eemcs.schedulemanager.client.IAjaxService;
import fr.eemcs.schedulemanager.client.LieuInfo;
import fr.eemcs.schedulemanager.database.PMF;
import fr.eemcs.schedulemanager.entity.EvenementVO;

@SuppressWarnings("serial")
public class AjaxServiceImpl extends RemoteServiceServlet implements IAjaxService {

	@Override
    public List<EvenementInfo> getEventsServer(String sDate) {
        /*String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");
        return "Hello, " + input + "!<br><br>I am running " + serverInfo
                + ".<br><br>It looks like you are using:<br>" + userAgent;*/
		List<EvenementInfo> list = new ArrayList<EvenementInfo>();
		List<EvenementVO> events = new ArrayList<EvenementVO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + EvenementVO.class.getName() + " order by date desc";
			events = (List<EvenementVO>)pm.newQuery(query).execute();
			
			for(EvenementVO event : events) {
				LieuInfo l = null;
				if(event.getLieu() != null) {
					l = new LieuInfo();
					l.setNom(event.getLieu().getNom());
				}
				EvenementInfo e = new EvenementInfo();
				e.setDate(event.getDate());
				e.setHeure(event.getHeure());
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
