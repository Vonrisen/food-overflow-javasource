package entities;


public class CartComposition {
	
	//ATTRIBUTI
	private Cart cart;
	private Meal meal;
	private int quantity;
	
	
	//COSTRUTTORE
	public CartComposition(Cart cart, Meal meal, int quantity) {
		super();
		this.cart = cart;
		this.meal = meal;
		this.quantity = quantity;
	}


	//GETTER AND SETTER
	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public Meal getMeal() {
		return meal;
	}


	public void setMeal(Meal meal) {
		this.meal = meal;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
