package entities;

public class OrderComposition {

	//ATTRIBUTI
	
	private Meal meal;
	private Short quantity;
	
	//COSTRUTTORE
	
	public OrderComposition(Meal meal, Short quantity) {

		this.meal = meal;
		this.quantity = quantity;
	}

	//GETTER AND SETTER
	
	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Short getQuantity() {
		return quantity;
	}

	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}
	
}
