package fr.eemcs.schedulemanager.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatHelper {
	/**
	 * 
	 * @param date Date
	 * @param pattern Pattern
	 * @return Date
	 */
	public static Date getDate(String date, String pattern) {
		Date parsed = null;
	
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
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
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} else {
			return null;
		}
	}
}
