package entities;


public class Supply {
	
	//ATTRIBUTI
	private Shop shop;
	private Meal meal;
	private int quantity;
	
	
	//COSTRUTTORE
	public Supply(Shop shop, Meal meal, int quantity) {
		super();
		this.shop = shop;
		this.meal = meal;
		this.quantity = quantity;
	}


	//GETTER AND SETTER
	public Shop getShop() {
		return shop;
	}


	public void setShop(Shop shop) {
		this.shop = shop;
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
