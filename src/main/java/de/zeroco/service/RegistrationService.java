package de.zeroco.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import de.zeroco.assignment.Utility;
import de.zeroco.dao.RegistrationDao;
import de.zeroco.date.DateUtility;

public class RegistrationService {

	public static int getInsertedId(String name, String email, String phno, String dob,String password) {
		int age = DateUtility.findAge(dob, LocalDate.now());
		Date date = DateUtility.stringToDate(dob);
		return RegistrationDao.getInsertedId(name, email, phno, date, age, password);
	}
	public static String updateData(String name, String email, String phno, String dob,String pass, String id) {
		int age = DateUtility.findAge(dob, LocalDate.now());
		Date date = DateUtility.stringToDate(dob);
		List<String> col = Arrays.asList("name", "email","phno","dob","age","password");
		List<Object> data = Arrays.asList(name,email,phno,date,age,pass);
		List<String> cl = new ArrayList<>();
		List<Object> dt = new ArrayList<>();
		int count = 0;
		System.out.println(data);
		for (Object object : data) {
			if (!Utility.isBlank(object)) {
				cl.add(col.get(count));
				dt.add(object);
			}
			count ++;
		}
		dt.add(Integer.parseInt(id));
		return RegistrationDao.updateData(cl, dt);
	}
	public static List<Map<String, Object>> get(String email){
		return RegistrationDao.get(email);
	}
	public static List<Map<String, Object>> getAll(){
		return RegistrationDao.getAll();
	}
	public static String deleteData(String email) {
		return RegistrationDao.deleteData(email);
	}
	public static boolean  login(String email, String pass) {
		return RegistrationDao.logIn(email, pass);
	}
}	
