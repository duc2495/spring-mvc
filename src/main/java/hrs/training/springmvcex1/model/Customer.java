package hrs.training.springmvcex1.model;

public class Customer {
	int custId;
	String name;
	String address;
	String sex;
	String school;
	int	year;
	
	public Customer() {
		
	}
	
    public Customer(String name, String address, String sex, String school, int	year) {
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.school = school;
        this.year = year;
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

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
