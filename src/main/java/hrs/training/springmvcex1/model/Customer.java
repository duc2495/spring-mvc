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
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date birthday;

	// form:textarea - textarea
	private String address;
	
	// form:radiobutton - radio button
	private String gender;
	
	// form:input - textbox
	private String school;
	
	// form:select - form:option - dropdown - single select
	private int schoolYear;
	
	// form:checkboxes - multiple checkboxes
	List<Language> languages;

	public Customer() {

	}

	public Customer(String name, Date birthday, String address, String gender, String school, int schoolYear, List<Language> languages) {
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.gender = gender;
		this.school = school;
		this.schoolYear = schoolYear;
		this.languages = languages;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
}
