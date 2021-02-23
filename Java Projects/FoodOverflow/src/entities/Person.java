package entities;

import java.util.Date;

public class Person {

	private String cf;
	private String name;
	private String surname;
	private Date birth_date;
	private String birth_place;
	private String gender;
	private String cellphone;
	private Address address;

	public Person(String cf, String name, String surname, Date birth_date, String birth_place, String gender,
			String cellphone, Address address) {

		super();
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.birth_date = birth_date;
		this.birth_place = birth_place;
		this.gender = gender;
		this.cellphone = cellphone;
		this.address = address;

	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
