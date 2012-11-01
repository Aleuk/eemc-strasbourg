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
}
