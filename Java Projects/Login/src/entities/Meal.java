package entities;

import java.util.ArrayList;

public class Meal {

	private String meal_id;
	private String name;
	private float price;
	private String description;
	private ArrayList<String>allergen_list;
	public Meal(String meal_id, String name, float price, String description, ArrayList<String> allergen_list) {
		super();
		this.meal_id = meal_id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.allergen_list = allergen_list;
	}
	public String getMeal_id() {
		return meal_id;
	}
	public void setMeal_id(String meal_id) {
		this.meal_id = meal_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<String> getAllergen_list() {
		return allergen_list;
	}
	public void setAllergen_list(ArrayList<String> allergen_list) {
		this.allergen_list = allergen_list;
	}
	
	
	
}
