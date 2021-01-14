package entities;



import java.util.List;

public class Rider {
	
	//ATTRIBUTI
	
	private String cf;
	private String name;
	private String surname;
	private String address;
	private String birth_date;
	private String birth_place;
	private String gender;
	private String cellphone;
	private String vehicle;
	private String working_time;
	private String deliveries_number;
	private List <Shop> shop_list;
	private List <CustomerOrder> customer_order_list;

	
	public Rider(String cf, String name, String surname, String address, String birth_date, String birth_place,
				 String gender, String cellphone, String vehicle, String working_time, String deliveries_number,
			     List<Shop> shop_list, List<CustomerOrder> customer_order_list) {
		super();
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.birth_date = birth_date;
		this.birth_place = birth_place;
		this.gender = gender;
		this.cellphone = cellphone;
		this.vehicle = vehicle;
		this.working_time = working_time;
		this.deliveries_number = deliveries_number;
		this.shop_list = shop_list;
		this.customer_order_list = customer_order_list;
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

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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

	public List<Shop> getShop_list() {
		return shop_list;
	}

	public void setShop_list(List<Shop> shop_list) {
		this.shop_list = shop_list;
	}

	public List<CustomerOrder> getCustomer_order_list() {
		return customer_order_list;
	}

	public void setCustomer_order_list(List<CustomerOrder> customer_order_list) {
		this.customer_order_list = customer_order_list;
	}

	
	

	
}
