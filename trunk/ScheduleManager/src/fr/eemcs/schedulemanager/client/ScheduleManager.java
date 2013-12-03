package fr.eemcs.schedulemanager.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScheduleManager implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private IAjaxServiceAsync serviceProxy;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// Create a handler for the sendButton and nameField
		serviceProxy = GWT.create(IAjaxService.class);
		serviceProxy.getEventsServer("02", new AsyncCallback<List<EvenementInfo>> () {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				System.out.println("failure");
			}
 
			public void onSuccess(List<EvenementInfo> result) {
				System.out.println("success");
			}
			});
		
		System.out.println("je suis passé par là");
	

	}
}
