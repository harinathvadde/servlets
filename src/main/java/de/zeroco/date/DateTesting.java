package de.zeroco.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTesting {

	public static void main(String[] args) {
		
		Date dt = new Date();
		System.out.println(dt);
		@SuppressWarnings("deprecation")
		Date dtone = new Date(2020, 11, 24);
		System.out.println(dtone);
		
		System.out.println(dtone.after(dt)); // Tests if current date is after the given date.
		System.out.println(dt.before(dtone)); // Tests if current date is before the given date.
		System.out.println(dtone.compareTo(dt));
		System.out.println(dt.clone());
		System.out.println(dtone.equals(dtone));
		System.out.println(dtone.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		System.out.println(sdf.format(date));
		System.out.println(java.time.LocalDate.now());
		System.out.println(java.time.LocalDateTime.now());
		System.out.println(java.time.LocalTime.now());
	}
}
