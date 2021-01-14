package entities;




import java.util.List;


public class CustomerOrder {
	
	//ATTRIBUTI
	private String order_id;
	private String order_date; 
	private String delivery_time;
	private String address;
	private String status;
	private String payment;
	private String note;
	private Rider rider;
	private Shop shop;
	private Customer customer;
	private List <OrderComposition> order_composition;
	
	//COSTRUTTORE
	
	public CustomerOrder(String order_id, String order_date, String delivery_time, String address, String status,
			String payment, String note, Rider rider, Shop shop, Customer customer,
			List<OrderComposition> order_composition) {
		super();
		this.order_id = order_id;
		this.order_date = order_date;
		this.delivery_time = delivery_time;
		this.address = address;
		this.status = status;
		this.payment = payment;
		this.note = note;
		this.rider = rider;
		this.shop = shop;
		this.customer = customer;
		this.order_composition = order_composition;
	}
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
	public String getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public List<OrderComposition> getOrder_composition() {
		return order_composition;
	}
	public void setOrder_composition(List<OrderComposition> order_composition) {
		this.order_composition = order_composition;
	}
	
	
	
}
