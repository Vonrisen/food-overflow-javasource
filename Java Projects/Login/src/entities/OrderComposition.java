package entities;

public class OrderComposition {

	private Meal meal;
	private Short quantity;

	public OrderComposition(Meal meal, short quantity) {

		this.meal = meal;
		this.quantity = quantity;
	}

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
