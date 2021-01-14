package entities;


import java.util.ArrayList;

public class Cart {
	
	//ATTRIBUTI
	
	private char complete = 'n';
	private Customer customer;
	private ArrayList <CartComposition> cart_composition;
	
	
	//COSTRUTTORE
	public Cart(char complete, Customer customer, ArrayList<CartComposition> cart_composition) {
		super();
		this.complete = complete;
		this.customer = customer;
		this.cart_composition = cart_composition;
	}


	//GETTER AND SETTER
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


	public ArrayList<CartComposition> getCart_composition() {
		return cart_composition;
	}


	public void setCart_composition(ArrayList<CartComposition> cart_composition) {
		this.cart_composition = cart_composition;
	}
	
	

}
