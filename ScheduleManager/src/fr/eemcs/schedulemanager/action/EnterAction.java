package fr.eemcs.schedulemanager.action;

import com.opensymphony.xwork2.ActionSupport;

import fr.eemcs.schedulemanager.constants.IResponse;

public class EnterAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2313385370589598365L;
	
	private String url;
	 
	public String getUrl() {
		return url;
	}
 
	public void setUrl(String _url) {
		this.url = _url;
	}
 
	public String execute() {
		//TODO r�cup�ration des infos User
		return IResponse.SUCCESS;
	}
	
}
