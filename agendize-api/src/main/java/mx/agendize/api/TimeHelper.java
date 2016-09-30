package mx.agendize.api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeHelper {

	final static String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * @param seconds durée en secondes
	 * @return duration written with hours / minutes / seconds.
	 */
	public static String hoursMinutesSeconds(int seconds) {
		int heures = seconds / 3600;
		int minutes = (seconds - heures * 3600) / 60;
		int sec = (seconds - heures * 3600 - minutes * 60);
		return heures + "h" + minutes + "m" + sec;
	}

	/**
	 * @param seconds duration in seconds
	 * @return duration written with minutes / seconds.
	 */
	public static String minutesSeconds(int seconds) {
		int minutes = seconds / 60;
		int sec = (seconds - minutes * 60);
		return minutes + "m" + sec;
	}

	/**
	 * @param seconds duration in seconds
	 * @return duration in minutes rounded up to the next minute (e.g.: a 3 minutes and 15 seconds call will count as 4 minutes communication time) 
	 */
	public static int roundUpMinutes(int seconds){
		if (seconds%60==0){
			return seconds / 60;
		} else {
			return 1 + seconds / 60; 
		}
	}
	
	/**
	 * Returns true if date is valid.
	 * @param date
	 * @return true if date is valid, false if not.
	 */
	public static boolean isDateValid(String date) 
	{
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
	}
	
	public static void checkDate(String date) throws AgendizeException{
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
		} catch (ParseException e) {
			throw new AgendizeException("The date \"" + date + "\" is not valid.");
		}
	}
}
