package entities;



import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomerOrder {
	
	//ATTRIBUTI
	private String order_id;
	private String order_date; //= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	private Time delivery_time;
	private String status;
	private String payment;
	private String note;
	private Rider rider;
	private Shop shop;
	private Customer customer;
	private ArrayList <OrderComposition> orderComp_list;
	
	
	//COSTRUTTORE
	public CustomerOrder(String order_id, String order_date, Time delivery_time, String status, String payment,
			String note, Rider rider, Shop shop, Customer customer, ArrayList<OrderComposition> orderComp_list) {
		super();
		this.order_id = order_id;
		this.order_date = order_date;
		this.delivery_time = delivery_time;
		this.status = status;
		this.payment = payment;
		this.note = note;
		this.rider = rider;
		this.shop = shop;
		this.customer = customer;
		this.orderComp_list = orderComp_list;
	}


	//GETTER AND SETTER
	public String getOrder_id() {
		return order_id;
	}


	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}


	public String getOrder_date() {
		return order_date;
	}


	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}


	public Time getDelivery_time() {
		return delivery_time;
	}


	public void setDelivery_time(Time delivery_time) {
		this.delivery_time = delivery_time;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public Rider getRider() {
		return rider;
	}


	public void setRider(Rider rider) {
		this.rider = rider;
	}


	public Shop getShop() {
		return shop;
	}


	public void setShop(Shop shop) {
		this.shop = shop;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public ArrayList<OrderComposition> getOrderComp_list() {
		return orderComp_list;
	}


	public void setOrderComp_list(ArrayList<OrderComposition> orderComp_list) {
		this.orderComp_list = orderComp_list;
	}
	
	
	
	

}
