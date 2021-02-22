package entities;

import java.util.List;

public class Shop {

	private String email;
	private String name;
	private String password;
	private String working_hours;
	private Address address;
	private String closing_days;
	private List<Rider> employed_riders_list;
	private List<Meal> meal_list;
	private String home_phone;

	public Shop(String email, String name, String password, String working_hours, Address address, String closing_days,
			List<Rider> employed_riders_list, List<Meal> meal_list, String home_phone) {

		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.working_hours = working_hours;
		this.address = address;
		this.closing_days = closing_days;
		this.employed_riders_list = employed_riders_list;
		this.meal_list = meal_list;
		this.home_phone = home_phone;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWorking_hours() {
		return working_hours;
	}

	public void setWorking_hours(String working_hours) {
		this.working_hours = working_hours;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getClosing_days() {
		return closing_days;
	}

	public void setClosing_days(String closing_days) {
		this.closing_days = closing_days;
	}

	public List<Rider> getEmployed_riders_list() {
		return employed_riders_list;
	}

	public void setEmployed_riders_list(List<Rider> employed_riders_list) {
		this.employed_riders_list = employed_riders_list;
	}

	public List<Meal> getMeal_list() {
		return meal_list;
	}

	public void setMeal_list(List<Meal> meal_list) {
		this.meal_list = meal_list;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHome_phone() {
		return home_phone;
	}

	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}

}
