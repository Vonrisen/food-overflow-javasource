package entities;


import java.sql.Date;
import java.util.ArrayList;

public class Rider {
	
	//ATTRIBUTI
	private String rider_id;
	private String cf;
	private String rider_name;
	private String surname;
	private String address;
	private Date birth_date;
	private String birth_place;
	private String gender;
	private String cellphone;
	private String vehicle;
	private String working_time;
	private String deliveries_number;
	private ArrayList <Shop> shop_list;
	private ArrayList <CustomerOrder> order_list;
	
	
	//COSTRUTTORE
	public Rider(String rider_id, String cf, String rider_name, String surname, String address, Date birth_date,
			String birth_place, String gender, String cellular, String vehicle,
			String working_time, String deliveries_number, ArrayList<Shop> shop_list,
			ArrayList<CustomerOrder> order_list) {
		super();
		this.rider_id = rider_id;
		this.cf = cf;
		this.rider_name = rider_name;
		this.surname = surname;
		this.address = address;
		this.birth_date = birth_date;
		this.birth_place = birth_place;
		this.gender = gender;
		this.cellphone = cellular;
		this.vehicle = vehicle;
		this.working_time = working_time;
		this.deliveries_number = deliveries_number;
		this.shop_list = shop_list;
		this.order_list = order_list;
	}


	//GETTER AND SETTER
	public String getRider_id() {
		return rider_id;
	}


	public void setRider_id(String rider_id) {
		this.rider_id = rider_id;
	}


	public String getCf() {
		return cf;
	}


	public void setCf(String cf) {
		this.cf = cf;
	}


	public String getRider_name() {
		return rider_name;
	}


	public void setRider_name(String rider_name) {
		this.rider_name = rider_name;
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


	public String getDeliveries_number() {
		return deliveries_number;
	}


	public void setDeliveries_number(String deliveries_number) {
		this.deliveries_number = deliveries_number;
	}


	public ArrayList<Shop> getShop_list() {
		return shop_list;
	}


	public void setShop_list(ArrayList<Shop> shop_list) {
		this.shop_list = shop_list;
	}


	public ArrayList<CustomerOrder> getOrder_list() {
		return order_list;
	}


	public void setOrder_list(ArrayList<CustomerOrder> order_list) {
		this.order_list = order_list;
	}
	

	
}
