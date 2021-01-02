package entities;


import java.sql.Date;

public class Customer {
	
	//ATTRIBUTI
	private String customer_id;
	private String customer_name;
	private String surname;
	private String address;
	private Date birth_date;
	private String birth_place;
	private String gender;
	private String cellphone;
	private String email;
	private String password;
	private String cf;
	
	//COSTRUTTORE
	public Customer( String customer_name, String surname, String address, Date birth_date,
			String birth_place, String gender, String cellular, String email, String password, String cf) {
		super();
		this.customer_name = customer_name;
		this.surname = surname;
		this.address = address;
		this.birth_date = birth_date;
		this.birth_place = birth_place;
		this.gender = gender;
		this.cellphone = cellular;
		this.email = email;
		this.password = password;
		this.cf = cf;
	}

	
	
	//GETTER AND SETTER
	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
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

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
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
