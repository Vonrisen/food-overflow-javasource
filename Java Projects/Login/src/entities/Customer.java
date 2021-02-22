package entities;

import java.util.Date;

public class Customer extends Person {

	private String email;
	private String password;

	public Customer(String cf, String name, String surname, Date birth_date, String birth_place, String gender,
			String cellphone, Address address, String email, String password) {

		super(cf, name, surname, birth_date, birth_place, gender, cellphone, address);
		this.email = email;
		this.password = password;
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

}
