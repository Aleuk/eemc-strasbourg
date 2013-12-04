package fr.eemcs.schedulemanager.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
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
		//final DialogBox dialogBox = new DialogBox();
		//dialogBox.setText("Remote Procedure Call");
		//dialogBox.setAnimationEnabled(true);
		//final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		//closeButton.getElement().setId("closeButton");
		//final Label textToServerLabel = new Label();
		//final HTML serverResponseLabel = new HTML();
		//VerticalPanel dialogVPanel = new VerticalPanel();
		//dialogVPanel.addStyleName("dialogVPanel");
		//dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		//dialogVPanel.add(textToServerLabel);
		//dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		//dialogVPanel.add(serverResponseLabel);
		//dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		//dialogVPanel.add(closeButton);
		//dialogBox.setWidget(dialogVPanel);
		
		// Create a handler for the sendButton and nameField
		serviceProxy = GWT.create(IAjaxService.class);
		serviceProxy.getEventsServer("02", new AsyncCallback<List<EvenementInfo>> () {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				System.out.println("failure");
			}
 
			public void onSuccess(List<EvenementInfo> result) {
				CellTable<EvenementInfo> table = new CellTable<EvenementInfo>();
				TextColumn<EvenementInfo> dateColumn = new TextColumn<EvenementInfo>() {
					
					@Override
					public String getValue(EvenementInfo object) {
						return object.getDate();
					}
				};
				TextColumn<EvenementInfo> lieuColumn = new TextColumn<EvenementInfo>() {
					
					@Override
					public String getValue(EvenementInfo object) {
						if(object.getLieu() != null) {
							return object.getLieu().getNom();
						} else {
							return "-";
						}
					}
				};
				
				table.addColumn(dateColumn);
				table.addColumn(lieuColumn);
				if(result != null) {
					table.setRowCount(result.size(), true);
				}
				table.setRowData(result);
				
				//dialogBox.setText("Remote Procedure Call");
				//serverResponseLabel
				//		.removeStyleName("serverResponseLabelError");
				//serverResponseLabel.setHTML("kikoulol : " + result.get(0).getDate());
				//dialogBox.center();
				//closeButton.setFocus(true);

				
				RootPanel.get("planning").add(table);
				System.out.println("success");
			}
			});
		
		System.out.println("je suis passé par là");

	}
}
