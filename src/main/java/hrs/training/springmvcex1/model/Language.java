package hrs.training.springmvcex1.model;

public class Language {
	// form:hidden - hidden value
	private int id;

	private String name;

	public Language() {

	}


	public Language(String name) {
		this.name = name;
	}
	
	public Language(int id, String name) {
		this.id = id;
		this.name = name;
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

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;

		Language obj2 = (Language) obj;
		if ((this.id == obj2.getId()) && (this.name.equals(obj2.getName()))) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int tmp = 0;
		tmp = (id + name).hashCode();
		return tmp;
	}

}
