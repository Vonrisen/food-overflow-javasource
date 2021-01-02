package entities;


import java.util.ArrayList;

public class Cart {
	
	//ATTRIBUTI
	private String cart_id;
	private char complete = 'n';
	private Customer customer;
	private ArrayList <CartComposition> cartComp_list;
	
	
	//COSTRUTTORE
	public Cart(String cart_id, char complete, Customer customer, ArrayList<CartComposition> cartComp_list) {
		super();
		this.cart_id = cart_id;
		this.complete = complete;
		this.customer = customer;
		this.cartComp_list = cartComp_list;
	}


	//GETTER AND SETTER
	public String getCart_id() {
		return cart_id;
	}


	public void setCart_id(String cart_id) {
		this.cart_id = cart_id;
	}


	public char getComplete() {
		return complete;
	}


	public void setComplete(char complete) {
		this.complete = complete;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public ArrayList<CartComposition> getCartComp_list() {
		return cartComp_list;
	}


	public void setCartComp_list(ArrayList<CartComposition> cartComp_list) {
		this.cartComp_list = cartComp_list;
	}
	
	

}
