package hrs.training.springmvcex1.model;

import java.util.List;

public class Customer {
	
	// form:hidden - hidden value
	int id;
	
	// form:input - textbox
	String name;
	
	// form:textarea - textarea
	String address;
	
	// form:radiobutton - radio button
	String sex;
	
	// form:input - textbox
	String school;
	
	// form:select - form:option - dropdown - single select
	int year;
	
	// form:checkboxes - multiple checkboxes
	List<String> subjects;

	public Customer() {

	}

	public Customer(String name, String address, String sex, String school, int year, List<String> subjects) {
		this.name = name;
		this.address = address;
		this.sex = sex;
		this.school = school;
		this.year = year;
		this.subjects = subjects;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}
}
