package fr.eemcs.schedulemanager.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface IAjaxServiceAsync {
	void getEventsServer(String input, AsyncCallback<List<EvenementInfo>> callback)
			throws IllegalArgumentException;
}
