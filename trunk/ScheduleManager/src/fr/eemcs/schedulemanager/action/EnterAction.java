package fr.eemcs.schedulemanager.action;

import fr.eemcs.schedulemanager.constants.IResponse;

public class EnterAction {
	
	private String url;
	 
	public String getUrl() {
		return url;
	}
 
	public void setUrl(String _url) {
		this.url = _url;
	}
 
	public String execute() {
		//TODO récupération des infos User
		return IResponse.SUCCESS;
	}
	
}
