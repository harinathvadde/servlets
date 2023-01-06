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
import de.zeroco.db.DbUtility;

public class RegistrationService {

	public static int getInsertedId(String name, String email, String phone, String dob,String password) {
		return RegistrationDao.getInsertedId(name, email, phone, DateUtility.stringToDate(dob), DateUtility.findAge(dob, 
				LocalDate.now()),DbUtility.encryptPassword(password));
	}
	
	public static String updateData(String name, String newEmail, String phone, String dob,String password, String email) {
		int age = DateUtility.findAge(dob, LocalDate.now());
		Date date = DateUtility.stringToDate(dob);
		List<String> col = Arrays.asList("name", "email","phno","dob","age","password");
		List<Object> data = Arrays.asList(name,newEmail,phone,date,age,DbUtility.encryptPassword(password));
		List<String> colmn = new ArrayList<>();
		List<Object> inputData = new ArrayList<>();
		int count = 0;
		System.out.println(data);
		for (Object object : data) {
			if (!Utility.isBlank(object)) {
				colmn.add(col.get(count));
				inputData.add(object);
			}
			count ++;
		}
		inputData.add(email);
		return RegistrationDao.updateData(colmn, inputData);
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
	public static boolean  login(String email, String password) {
		return RegistrationDao.logIn(email, password);
	}
}	
