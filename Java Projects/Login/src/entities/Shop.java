package entities;

import java.util.List;

import enumerations.DaysOfTheWeek;

public class Shop {

	//ATTRIBUTI
	private String id;
	private String name;
	private String password;
	private String working_hours;
	private DaysOfTheWeek[] closing_days;
	private List<Rider> employed_riders_list;
	
	
	//COSTRUTTORE
	public Shop(String id, String name, String password, String working_hours, DaysOfTheWeek[] closing_days,
			List<Rider> employed_riders_list) {
		
		this.id = id;
		this.name = name;
		this.password = password;
		this.working_hours = working_hours;
		this.closing_days = closing_days;
		this.employed_riders_list = employed_riders_list;
	}

	//GETER AND SETTER
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public DaysOfTheWeek[] getClosing_days() {
		return closing_days;
	}

	public void setClosing_days(DaysOfTheWeek[] closing_days) {
		this.closing_days = closing_days;
	}

	public List<Rider> getEmployed_riders_list() {
		return employed_riders_list;
	}

	public void setEmployed_riders_list(List<Rider> employed_riders_list) {
		this.employed_riders_list = employed_riders_list;
	}
	
	
}
