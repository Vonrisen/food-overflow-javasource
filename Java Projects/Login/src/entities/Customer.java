package entities;



public class Customer {
	
	//ATTRIBUTI
	private String cf;
	private String name;
	private String surname;
	private String address;
	private String birth_date;
	private String birth_place;
	private String gender;
	private String cellphone;
	private String email;
	private String password;
	
	//COSTRUTTORE
	public Customer(String cf,String customer_name, String surname, String address, String birth_date,
			String birth_place, String gender, String cellular, String email, String password) {
		super();
		this.cf = cf;
		this.name = customer_name;
		this.surname = surname;
		this.address = address;
		this.birth_date = birth_date;
		this.birth_place = birth_place;
		this.gender = gender;
		this.cellphone = cellular;
		this.email = email;
		this.password = password;
	}

	//GETTER AND SETTER
	public String getCustomer_name() {
		return name;
	}

	public void setCustomer_name(String customer_name) {
		this.name = customer_name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getBirth_place() {
		return birth_place;
	}

	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellular) {
		this.cellphone = cellular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}
	

}
