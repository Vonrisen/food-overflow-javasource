package entities;

import java.util.Date;

public class Rider extends Person {

	private String vehicle;
	private String working_hours;
	private Short deliveries_number = 0;

	public Rider(String cf, String name, String surname, Date birth_date, String birth_place, String gender,
			String cellphone, Address address, String vehicle, String working_hours, Short deliveries_number) {

		super(cf, name, surname, birth_date, birth_place, gender, cellphone, address);
		this.vehicle = vehicle;
		this.working_hours = working_hours;
		this.deliveries_number = deliveries_number;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getWorking_hours() {
		return working_hours;
	}

	public void setWorking_hours(String working_hours) {
		this.working_hours = working_hours;
	}

	public Short getDeliveries_number() {
		return deliveries_number;
	}

	public void setDeliveries_number(Short deliveries_number) {
		this.deliveries_number = deliveries_number;
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + "[vehicle=" + vehicle + ", working_time=" + working_hours
				+ ", deliveries_number=" + deliveries_number + ", toString()=" + "]";
	}

}
