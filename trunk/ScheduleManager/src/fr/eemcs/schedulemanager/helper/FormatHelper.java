package fr.eemcs.schedulemanager.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatHelper {
	/**
	 * 
	 * @param date Date
	 * @param pattern Pattern
	 * @return Date
	 */
	public static Date getDate(String date, String pattern) {
		Date parsed = null;
	
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.FRENCH);
		try {
			parsed = dateFormat.parse(date);
		} catch (ParseException e) {
			
		}

		return parsed;
	}
	
	/**
	 * 
	 * @param date date à formatter
	 * @param pattern format de la date
	 * @return string
	 */
	public static String formatDate(Date date, String pattern) {
		if(date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.FRENCH);
			return sdf.format(date);
		} else {
			return null;
		}
	}
	
	public static String getId(String oId) {
		return oId.substring(oId.indexOf("(")+1, oId.indexOf(")"));
	}
	
	public static String addPaddingToTD(String s) {
		if(s != null) {
			return "<span style=\"padding-left:7px;padding-right:7px\">" + s + "</span>";
		}
		return s;
	}
	
	public static String getMois(int _mois) {
		String retour = "";
		if(_mois == 0) {
			retour = "Janvier";
		} else if(_mois == 1) {
			retour = "Février";
		} else if(_mois == 2) {
			retour = "Mars";
		} else if(_mois == 3) {
			retour = "Avril";
		} else if(_mois == 4) {
			retour = "Mai";
		} else if(_mois == 5) {
			retour = "Juin";
		} else if(_mois == 6) {
			retour = "Juillet";
		} else if(_mois == 7) {
			retour = "Août";
		} else if(_mois == 8) {
			retour = "Septembre";
		} else if(_mois == 9) {
			retour = "Octobre";
		} else if(_mois == 10) {
			retour = "Novembre";
		} else if(_mois == 11) {
			retour = "Décembre";
		}
		
		return retour;
	}
}
