package entities;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Order {

	//ATTRIBUTI
	private String id;
	private Date date;
	private String payment;
	private String status;
	private Address address;
	private Time deliveried_time;
	private String note;
	private Customer customer;
	private Rider rider;
	private Shop shop;
	private List<OrderComposition> order_composition;
	
	//COSTRUTTORE ORDINE
	public Order(String id, Date date, String payment, String status, Address address, Time deliveried_time,
			String note, Customer customer, Rider rider, Shop shop, List<OrderComposition> order_composition) {
		
		this.id = id;
		this.date = date;
		this.payment = payment;
		this.status = status;
		this.address = address;
		this.deliveried_time = deliveried_time;
		this.note = note;
		this.customer = customer;
		this.rider = rider;
		this.shop = shop;
		this.order_composition = order_composition;
	}
	//COSTRUTTORE CARRELLO
	public Order(Customer customer, Shop shop) {
		initialize();
		this.customer = customer;
		this.shop = shop;
	}
	//GETTER AND SETTER
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Time getDeliveried_time() {
		return deliveried_time;
	}
	public void setDeliveried_time(Time deliveried_time) {
		this.deliveried_time = deliveried_time;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	public List<OrderComposition> getOrder_composition() {
		return order_composition;
	}
	public void setOrder_composition(List<OrderComposition> order_composition) {
		this.order_composition = order_composition;
	}
	
	//METODI
	//aggiunge un order comp alla lista
	public void addIntoOrderComposition(OrderComposition meal) {
		
		this.order_composition.add(meal);
	}
	//inizializza le variabili dell'oggetto a null
	public void initialize() {
		
		this.id = null;
		this.date = null;
		this.payment = null;
		this.status = null;
		this.address = null;
		this.deliveried_time = null;
		this.note = null;
		this.rider = null;
		this.order_composition = null;
	}
	
}
