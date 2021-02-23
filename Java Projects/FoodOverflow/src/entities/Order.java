package entities;

import java.sql.Time;
import java.util.Date;

public class Order extends Cart {


	private String id;
	private Date date;
	private String payment;
	private String status;
	private Address address;
	private Time deliveried_time;
	private String note;
	private Rider rider;

	public Order(Shop shop, Customer customer, String id, Date date, String payment, String status, Address address,
			Time deliveried_time, String note, Rider rider) {
		super(shop, customer);
		this.id = id;
		this.date = date;
		this.payment = payment;
		this.status = status;
		this.address = address;
		this.deliveried_time = deliveried_time;
		this.note = note;
		this.rider = rider;
	}

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

	public Rider getRider() {
		return rider;
	}

	public void setRider(Rider rider) {
		this.rider = rider;
	}

}
