package entities;

import java.util.ArrayList;

public class Meal {

	//ATTRIBUTI
	
	private String name;
	private float price;
	private String ingredients;
	private String category;
	private ArrayList<String>allergen_list;
	
	//COTRUTTORE
	public Meal(String name, float price, String ingredients, String category, ArrayList<String>allergen_list) {
		
		this.name = name;
		this.price = price;
		this.ingredients = ingredients;
		this.allergen_list = allergen_list;
	}

	//GETTERE AND SETTER
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

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<String> getAllergen_list() {
		return allergen_list;
	}

	public void setAllergen_list(ArrayList<String>allergen_list) {
		this.allergen_list = allergen_list;
	}

}
