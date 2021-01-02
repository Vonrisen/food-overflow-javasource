package entities;

import java.util.ArrayList;

public class Shop {
	
	//ATTRIBUTI
	private String shop_id;
	private String shop_name;
	private String address;
	private String working_hours;
	private String closing_days;
	private ArrayList<CustomerOrder> customer_order_list;
	public Shop(String shop_name, String address, String working_hours, String closing_days) {
		super();
		this.shop_name = shop_name;
		this.address = address;
		this.working_hours = working_hours;
		this.closing_days = closing_days;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWorking_hours() {
		return working_hours;
	}
	public void setWorking_hours(String working_hours) {
		this.working_hours = working_hours;
	}
	public String getClosing_days() {
		return closing_days;
	}
	public void setClosing_days(String closing_days) {
		this.closing_days = closing_days;
	}
	public ArrayList<CustomerOrder> getCustomer_order_list() {
		return customer_order_list;
	}
	public void setCustomer_order_list(ArrayList<CustomerOrder> customer_order_list) {
		this.customer_order_list = customer_order_list;
	}
	
	
}