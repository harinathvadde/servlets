package de.zeroco.assignment;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public class Utility {
	
	public final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public final static String ALPHANUMARIC = ALPHABET + "0123456789";
	/**
	 * This method used to return captcha by passing length and data 
	 * @param length
	 * @param data 
	 * @return captcha
	 */
	public static String captcha(int length, String data) {
		if (length > 4 && length <= 10) {
			char inputdata[] = data.toCharArray();
			char captcha[] = new char[length];
			Random rdm = new Random();
			for (int i = 0; i < captcha.length; i++) {
				captcha[i] = inputdata[rdm.nextInt(inputdata.length)];
			}
			return new String(captcha);
		} else {
			return "Please enter a valid number between 4 to 10";
		}
	}
	/**
	 * This method returns the only alphabetic captcha with length of 5 when passing no argumetns
	 * @return captcha
	 */
	public static String getCaptcha() {
		return getCaptcha(5);
	}
	/**
	 * This method returns the only alphabetic captcha with given input length arguments
	 * @param length
	 * @return captcha
	 */
	public static String getCaptcha(int length) {
		if (isBlank(length)) return "";
		return captcha(length, ALPHABET);
	}
	/**
	 * This method returns alphanumaric captcha with given input length
	 * @param length
	 * @param type
	 * @return captcha
	 */
	public static String getCaptcha(int length, String type) {
		if (isBlank(type)) {
			return getCaptcha(length);
		} else {
			return captcha(length, ALPHANUMARIC);
		}
	}
	/**
	 * This method checks the given password as per condtions 
	 * @param password
	 * @return
	 * @exception NullPointerException
	 */
	public static boolean isValidPassword(String password) {
		if (isBlank(password)) {
			return false;
		} else {
			return Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$#@&!]).{8,15}$").matcher(password).find();
		}
	}
	/**
	 * This method returns the data within the given inputs 
	 * @param data
	 * @param startchar
	 * @param endchar
	 * @return
	 * @exception NullPointerException
	 */
	public static String findString(String data, String startchar, String endchar) {
		if (isBlank(data) == true || isBlank(startchar) == true || isBlank(endchar)) {
			return "";
		} else {
			return data.substring(data.indexOf(startchar) + 2, data.indexOf(endchar));
		}
	}
	/**
	 * This method return the given input empty or not and hangle the excceptions
	 * @author HARINATH
	 * @param <E>
	 * @since 02/12/2022
	 * @param input
	 * @return true or false
	 */
	@SuppressWarnings("unused")
	public static boolean isBlank(Object input) {
		try {
			if (input instanceof String) {
				String stringvar = (String) input;
				if (stringvar.trim().equals("")) 
					return true;
			} else if(input instanceof Integer){
				if ((int)input <= 0) 
					return true;
			} else if (input instanceof Boolean) {
				if ((boolean)input == false) 
					return true;
			} else if(input instanceof Character) {
				if ((char)input == ' ') 
					return true;
			} else if (input instanceof Byte) {
				if ((byte)input <= 0) 
					return true;
			} else if (input instanceof Long) {
				if ((long)input <= 0) 
					return true;
			} else if (input instanceof Double) {
				if ((double)input <= 0) 
					return true;
			} else if (input instanceof Float) {
				if ((float)input <= 0) 
					return true;
			} else if (input.getClass().isArray()) {
				if (Array.getLength(input) == 0) 
					return true;
			} else if (input instanceof Collection<?>) {
				if (((Collection<?>) input).size() == 0) 
					return true;
			} else
				return false;
		} catch (Exception e) {
		}
		return false;
	}
	/**
	 * This method is used to check given inputs empty or not and return boolean 
	 * @author HARINATH
	 * @since 20/12/2022
	 * @param input
	 * @return boolean
	 */
	public static boolean isBlankWithVar(Object...input) {
		for (Object object : input) {
			if (isBlank(object)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * This method checks the input having data or not...
	 * @author HARINATH
	 * @since 02/12/2022
	 * @param input
	 * @return
	 */
	public static boolean hasData(Object input) {
		if (isBlank(input)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * This method returns the replace of all speccial characters and spaces with undersocre( _ )
	 * @author HARINATH
	 * @since 07/12/2022
	 * @param data
	 * @return
	 */
	public static String getString(String data) {
		if (isBlank(data)) return "";
		data = data.trim().replaceAll("[^a-zA-Z]+", "_").toLowerCase();
		return data.endsWith("_")?data.substring(0, data.length()-1):data;
	}
	/**
	 * This method is used to convert string date to Date 
	 * @author HARINATH
	 * @since 16/12/2022
	 * @param date
	 * @return Date
	 */
	public static Date stringToDate(String date) {
		if (Utility.isBlank(date)) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date res = null;
		try {
			res = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
}
