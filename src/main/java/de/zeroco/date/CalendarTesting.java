package de.zeroco.date;

import java.util.Calendar;

public class CalendarTesting {

	public static void main(String[] args) {
		
		Calendar cldr = Calendar.getInstance();
		System.out.println(cldr.getTime());
		cldr.add(Calendar.DATE, 0);
		
	}
}
