package fr.eemcs.schedulemanager.client;

import java.util.List;

import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

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
				CellTable<EvenementInfo> table = new CellTable<EvenementInfo>();
				
				Column<EvenementInfo, SafeHtml> col = new Column<EvenementInfo, SafeHtml>(new SafeHtmlCell()) {
					
					@Override
					public SafeHtml getValue(EvenementInfo object) {
						String lieu = "-";
						String tooltip = "";
						String td = "";
						if(object.getLieu() != null) {
							lieu =  object.getLieu().getNom();
						}
						td = "<b>" + object.getDate() + "</b> --- " + lieu;
						LieuInfo l = object.getLieu();
						String lNom = "";
						String lAdresse = "";
						String lVille = "";
						if(l != null) {
							lNom = l.getNom();
							lAdresse = l.getAdresse();
							lVille = l.getCodePostal() + " " + l.getVille();
						}
						
						tooltip += object.getDate();
						tooltip += "<b>" + object.getDivers() + "</b>";
						tooltip += "<br />";
						tooltip += lNom;
						tooltip += lAdresse;
						tooltip += lVille;
						
						return new SafeHtmlBuilder().appendHtmlConstant("<span class='tips' title='|" + 
								new SafeHtmlBuilder().appendEscaped(tooltip).toSafeHtml().asString() + "'>"
								+ td + "</span>").toSafeHtml();
					}
				};
				table.addColumn(col);
				
				/*TextColumn<EvenementInfo> eventColumn = new TextColumn<EvenementInfo>() {
					
					@Override
					public String getValue(EvenementInfo object) {
						String lieu = "";
						if(object.getLieu() != null) {
							lieu =  object.getLieu().getNom();
						} else {
							return "-";
						}
						return object.getDate() + "    >     " + lieu;
					}
				};
				
				table.addColumn(eventColumn);*/
				if(result != null) {
					table.setRowCount(result.size(), true);
				}
				table.setRowData(result);
				table.setStyleName("table table-bordered");
				
				/*final SingleSelectionModel<EvenementInfo> ssm = new SingleSelectionModel<EvenementInfo>();
				table.setSelectionModel(ssm);
				ssm.addSelectionChangeHandler(new com.google.gwt.view.client.SelectionChangeEvent.Handler() {
					
					@Override
					public void onSelectionChange(SelectionChangeEvent event) {
						// TODO Auto-generated method stub
						final EvenementInfo e = ssm.getSelectedObject();
						LieuInfo l = e.getLieu();
						String lNom = "";
						String lAdresse = "";
						String lVille = "";
						if(l != null) {
							lNom = l.getNom();
							lAdresse = l.getAdresse();
							lVille = l.getCodePostal() + " " + l.getVille();
						}
						
						final DialogBox dialogBox = new DialogBox();
						dialogBox.setText(e.getDate());
						dialogBox.setAnimationEnabled(true);
						final Button closeButton = new Button("Fermer");
						// We can set the id of a widget by accessing its Element
						closeButton.getElement().setId("closeButton");
						VerticalPanel dialogVPanel = new VerticalPanel();
						dialogVPanel.setStyleName("gwtModal");
						dialogVPanel.addStyleName("modal-dialog");
						dialogVPanel.add(new HTML("<b>" + e.getDivers() + "</b>"));
						dialogVPanel.add(new HTML("<br />"));
						dialogVPanel.add(new HTML(lNom));
						dialogVPanel.add(new HTML(lAdresse));
						dialogVPanel.add(new HTML(lVille));
						dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
						dialogVPanel.add(closeButton);
						dialogBox.setWidget(dialogVPanel);
						
						// Add a handler to close the DialogBox
						closeButton.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								dialogBox.hide();
							}
						});
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});*/
				
				RootPanel.get("planning").add(table);
				System.out.println("success");
			}
			});
		
		System.out.println("je suis passé par là");

	}
}
