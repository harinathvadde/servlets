package de.zeroco.date;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import de.zeroco.assignment.Utility;

public class StudentUtility {

	/**
	 * This method will return student details by passing dob as input
	 * @author HARINATH
	 * @since 15/12/2022
	 * @param branch
	 * @param hobbie
	 * @return String list
	 */
	public static List<String> getStudentDetials(List<Branch> branch, Date date){
		if (Utility.isBlank(branch) || Utility.isBlank(date) ) return null;
		List<String> list = new ArrayList<String>();
		for (Branch brch : branch) {
			for (Student stdnt : brch.getDetails()) {
				if (stdnt.getDob().getTime() == date.getTime()) list.add(stdnt.getName());
			}
		}
		return list;
	}
	/**
	 * This method will used to sort the given list
	 * @author HARINATH
	 * @since 14/12/2022
	 * @param list
	 * @return list
	 */
	public static  List<String> sortList(List<String> list){
		if (Utility.isBlank(list)) return null;
		Collections.sort(list);
		return list;
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		List<Student> mechList = new ArrayList<Student>();
		mechList.add(new Student("harinath", "mech", "harinath123@gmail.com", new Date(1997, 01, 14), new ArrayList<String>(Arrays.asList("readingbooks","cricket"))));
		mechList.add(new Student("rajesh", "mech", "rajesh123@gmail.com", new Date(1996, 06, 05), new ArrayList<String>(Arrays.asList("cricket","movies"))));
		mechList.add(new Student("siva", "mech", "siva123@gmail.com", new Date(1990, 05, 28), new ArrayList<String>(Arrays.asList("writing","movies"))));
		mechList.add(new Student("anjith", "mech", "anjith123@gmail.com", new Date(1997, 01, 14), new ArrayList<String>(Arrays.asList("music","movies"))));
		mechList.add(new Student("ravi", "mech", "ravi123@gmail.com", new Date(1993, 10, 02), new ArrayList<String>(Arrays.asList("cricket","music"))));
		Branch mech = new Branch("mech", mechList);
		List<Student> cseList = new ArrayList<Student>();
		cseList.add(new Student("akash", "cse", "akash123@gmail.com", new Date(1996, 06, 05), new ArrayList<String>(Arrays.asList("cricket","browsing"))));
		cseList.add(new Student("balaji", "cse", "balaji123@gmail.com", new Date(2000, 03, 19), new ArrayList<String>(Arrays.asList("cricket","movies"))));
		cseList.add(new Student("swathi", "cse", "swathi123@gmail.com", new Date(1993, 10, 02), new ArrayList<String>(Arrays.asList("writing","music"))));
		cseList.add(new Student("hema", "cse", "hema123@gmail.com", new Date(1997, 01, 14), new ArrayList<String>(Arrays.asList("music","movies"))));
		cseList.add(new Student("sreekanth", "cse", "sreekanth123@gmail.com", new Date(1999, 04, 30), new ArrayList<String>(Arrays.asList("cricket","music"))));
		Branch cse = new Branch("cse", cseList);
		List<Branch> branch = new ArrayList<Branch>();
		branch.add(mech);
		branch.add(cse);
		
		System.out.println(getStudentDetials(branch, new Date(1997, 01, 14)));
	}
}
