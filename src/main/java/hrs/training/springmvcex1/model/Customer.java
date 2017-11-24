package hrs.training.springmvcex1.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Customer {
	
	// form:hidden - hidden value
	private int id;
	
	// form:input - textbox
	private String name;
	
	// form:input - textbox
	@DateTimeFormat(pattern="yyyy-mm-dd") 
	private Date birthday;

	// form:textarea - textarea
	private String address;
	
	// form:radiobutton - radio button
	private String sex;
	
	// form:input - textbox
	private String school;
	
	// form:select - form:option - dropdown - single select
	private int year;
	
	// form:checkboxes - multiple checkboxes
	List<String> subjects;

	public Customer() {

	}

	public Customer(String name, Date birthday, String address, String sex, String school, int year, List<String> subjects) {
		this.name = name;
		this.birthday = birthday;
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
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
