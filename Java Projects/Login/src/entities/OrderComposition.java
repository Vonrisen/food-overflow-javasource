package entities;



public class OrderComposition {
	
	//ATTRIBUTI
	private CustomerOrder order;
	private Meal meal;
	private int quantity;
	
	
	//COSTRUTTORE
	public OrderComposition(CustomerOrder order, Meal meal, int quantity) {
		super();
		this.order = order;
		this.meal = meal;
		this.quantity = quantity;
	}


	//GETTER AND SETTER
	public CustomerOrder getOrder() {
		return order;
	}


	public void setOrder(CustomerOrder order) {
		this.order = order;
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
