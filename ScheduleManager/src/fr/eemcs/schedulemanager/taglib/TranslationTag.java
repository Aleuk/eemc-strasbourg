package fr.eemcs.schedulemanager.taglib;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class TranslationTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5490216433867073332L;
	private String name;
	public int doStartTag() throws JspException { 
		try {
			ResourceBundle RB = ResourceBundle.getBundle("package");
			pageContext.getOut().println(RB.getString(name));
		} catch (Exception e) {
			throw new JspException ("I/O Error", e); 
		} 
		return Tag.SKIP_BODY; 
	}
	public void setName(String name) {
		this.name = name;
	} 
}
