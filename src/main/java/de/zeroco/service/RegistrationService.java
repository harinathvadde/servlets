package de.zeroco.service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.zeroco.dao.RegistrationDao;
import de.zeroco.date.DateUtility;

public class RegistrationService {

	public static int getInsertedId(String name, String email, String phno, String dob) {
		int age = DateUtility.findAge(dob, LocalDate.now());
		Date date = DateUtility.stringToDate(dob);
		return RegistrationDao.getInsertedId(name, email, phno, date, age);
	}
	public static String updateData(String name, String email, String phno, String dob,String userid) {
		int age = DateUtility.findAge(dob, LocalDate.now());
		Date date = DateUtility.stringToDate(dob);
		return RegistrationDao.updateData(age, date, userid, name,email,phno);
	}
	public static List<Map<String, Object>> get(String userId){
		return RegistrationDao.get(userId);
	}
	public static String deleteData(String userId) {
		return RegistrationDao.deleteData(userId);
	}
}	
