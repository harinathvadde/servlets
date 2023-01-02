package de.zeroco.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

import de.zeroco.assignment.Utility;

public class DateUtility {
	
	public static final int MAX_VALID_YR = 9999;
	public static final int MIN_VALID_YR = 1800;


	/**
	 * This method is used to convert string date to Date 
	 * @author HARINATH
	 * @since 16/12/2022
	 * @param date
	 * @return Date
	 */
	public static Date stringToDate(String date) {
		if (Utility.isBlank(date)) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date res = null;
		try {
			res = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * This method will used to find difference between two given String dates in years ,in months, in days, in hours, in minutes,
	 * and in sec.
	 * @author HARINATH
	 * @since 15/12/2022
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String findDifference(String startDate, String endDate) {
		if (Utility.isBlank(startDate) || Utility.isBlank(endDate)) return "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String res = "";
		try {
			Date dtone = sdf.parse(startDate);
			Date dttwo = sdf.parse(endDate);
			long diffInTime = dttwo.getTime() - dtone.getTime();
			long diffInSec = (diffInTime / 1000);
			long diffInMin = (diffInSec / 60);
			long diffInHours = (diffInMin / 60);
			long diffInDays = (diffInHours / 24);
			long diffInMonths = (diffInDays / 30);
			long diffInYears = (diffInDays / 365);
			res = (diffInYears + " in years, \n" + diffInMonths + " in months, \n" +  diffInDays + " in days, \n" 
					+ diffInHours + " in hours, \n" + diffInMin + " in minutes, \n" + diffInSec + " in secounds.");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * This method is used to check given date valid or not
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	public static boolean isValidDate(int day, int month, int year){
		if(year > MAX_VALID_YR || year < MIN_VALID_YR) return false;
		if(month < 1 || month > 12) return false;
		if(day < 1 || day > 31) return false;
		if (month == 4 || month == 6 || month == 9 || month == 11) return (day <=30);
		if (month == 2) {
			return (day <= 29);
		} else {
			return (day <= 28);
		}
	}
	/**
	 * This method used to find given date is correct formate(dd/mm/yyyy) or not 
	 * @author HARINATH
	 * @since 15/12/2022
	 * @param date
	 * @return boolean
	 */
	public static boolean isValidDate(String date) {
		return Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$").matcher(date).find();
	}
	
	/**
	 * This metod is used to find given year is leap year or not
	 * @author HARINATH
	 * @since 15/12/2022
	 * @param year
	 * @return boolean
	 */
	public static boolean  isLeap(int year) {
		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 ==0);
	}
	
	/**
	 * this method used to find days between two dates by passing Date input
	 * @author HARINATH
	 * @since 15/12/2022
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String getDays(Date startDate,Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / (1000*60*60*24) + " days";
	}
	
	public static int findAge(String input,LocalDate curreDate) {
		if (Utility.isBlank(input)) {
			return 0;
		}
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(input, dateFormat);
		Period period = Period.between(date, curreDate);
		return period.getYears();
	}
	
	public static void main(String[] args) {
		
		//System.out.println(findDifference("01-01-2019", "01-01-2020"));
		//.out.println(getDays(new Date(2012, 00, 00), new Date(2022, 00, 00)));
		//System.out.println(isLeap(2012));
		//System.out.println(isValidDate("00-00-2019"));
		//System.out.println(isValidDate("01/01/2022"));
		System.out.println(findAge("1997-01-14", LocalDate.now()));
	}
	
	
}
