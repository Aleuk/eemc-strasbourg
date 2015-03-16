package fr.eemcs.schedulemanager.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
		serviceProxy.getEventsServer(new AsyncCallback<List<EvenementInfo>> () {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				System.out.println("failure");
			}
 
			public void onSuccess(List<EvenementInfo> result) {
				
				VerticalPanel vp = new VerticalPanel();
				vp.setStyleName("tablePlanning");
				
				for(EvenementInfo event : result) {
					final Label ligne = new Label(event.getDate() + " " + event.getDivers());
					ligne.setStyleName("labelPlanning");
					vp.add(ligne);
					
					final VerticalPanel vpPopup = new VerticalPanel();
					vpPopup.setWidth("180px");
					Label lbEntete = new Label(event.getDate());
					Label lbDivers = new Label(event.getDivers());
					Label lbHR = new Label("");
					Label lbLieu = new Label(event.getLieu().getNom());
					Label lbAdresse = new Label(event.getLieu().getAdresse());
					Label lbVille = new Label(event.getLieu().getCodePostal() + " " + event.getLieu().getVille());
					lbEntete.setStyleName("entetePlanning");
					lbDivers.setStyleName("entetePlanning");
					lbHR.setStyleName("hrPlanning");
					lbLieu.setStyleName("titlePlanning");
					lbAdresse.setStyleName("titlePlanning");
					lbVille.setStyleName("titlePlanning");
					vpPopup.add(lbEntete);
					vpPopup.add(lbDivers);
					vpPopup.add(lbHR);
					vpPopup.add(lbLieu);
					vpPopup.add(lbAdresse);
					vpPopup.add(lbVille);
					
					final PopupPlanning pp = new PopupPlanning();
					ligne.addMouseOverHandler(new MouseOverHandler() {
						
						@Override
						public void onMouseOver(MouseOverEvent event) {
							pp.setWidget(vpPopup);
							pp.showRelativeTo(ligne);
						}
					});
					
					ligne.addMouseOutHandler(new MouseOutHandler() {
						
						@Override
						public void onMouseOut(MouseOutEvent event) {
							pp.hide();
						}
					});
				}
				
				RootPanel.get("planning").add(vp);
			}
			});

	}
	
	private class PopupPlanning extends PopupPanel {
		public PopupPlanning() {
			super(true);
			setWidget(new Label("test popup ceci est un test"));
		}
	}
}
