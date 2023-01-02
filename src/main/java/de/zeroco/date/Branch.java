package de.zeroco.date;

import java.util.List;

public class Branch {

	String name;
	List<Student> details;
	
	public Branch(String branchName, List<Student> details) {
		this.name = branchName;
		this.details = details;
	}
	public String getBranchName() {
		return name;
	}
	public void setBranchName(String branchName) {
		this.name = branchName;
	}
	public List<Student> getDetails() {
		return details;
	}
	public void setDetails(List<Student> details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "Branch [branchName=" + name + ", details=" + details + "]";
	}
}
