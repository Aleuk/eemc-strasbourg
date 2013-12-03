package fr.eemcs.schedulemanager.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("ajax")
public interface IAjaxService extends RemoteService {
	public List<EvenementInfo> getEventsServer(String sDate) throws IllegalArgumentException;
}
