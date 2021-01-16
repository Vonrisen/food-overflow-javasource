package entities;

import java.util.Date;

public class Rider extends Person{

	//ATTRIBUTI
	private String vehicle;
	private String working_time;
	private Short deliveries_number;
	
	//COSTRUTTORE
	public Rider(String cf, String name, String surname, Date birth_date, String birth_place, String gender,
				String cellphone, Address address, String vehicle, String working_time, Short deliveries_number) {
		
		super(cf, name, surname, birth_date, birth_place, gender, cellphone, address);
		this.vehicle = vehicle;
		this.working_time = working_time;
		this.deliveries_number = deliveries_number;
	}

	//GETTER AN D SETTER
	public String getVehicle() {
		return vehicle;
	}


	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}


	public String getWorking_time() {
		return working_time;
	}


	public void setWorking_time(String working_time) {
		this.working_time = working_time;
	}


	public Short getDeliveries_number() {
		return deliveries_number;
	}


	public void setDeliveries_number(Short deliveries_number) {
		this.deliveries_number = deliveries_number;
	}

	
}
