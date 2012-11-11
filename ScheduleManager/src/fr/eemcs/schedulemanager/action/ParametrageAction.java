package fr.eemcs.schedulemanager.action;

public class ParametrageAction extends LoggerAction{
	private String url;
	 
	public String getUrl() {
		return url;
	}
 
	public void setUrl(String _url) {
		this.url = _url;
	}
	
	public String list() {
		return "LIST";
	}
	
	public String add() {
		return "LOGIN";
	}
	
}
