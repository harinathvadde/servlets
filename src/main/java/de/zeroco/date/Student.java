package de.zeroco.date;

import java.util.Date;
import java.util.List;

public class Student {

	String name;
	String branch;
	String email;
	Date dob;
	List<String> hobbies;

	public Student(String name, String branch, String email, Date dob, List<String> hobbies) {
		super();
		this.name = name;
		this.branch = branch;
		this.email = email;
		this.dob = dob;
		this.hobbies = hobbies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", branch=" + branch + ", email=" + email + ", dob=" + dob + ", hobbies="
				+ hobbies + "]";
	}

	

}
