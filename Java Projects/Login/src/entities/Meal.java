package entities;

import java.util.ArrayList;
import java.util.List;

public class Meal {

	private String prefix;
	private String name;
	private float price;
	private String description;
	private List<String>allergen_list;
	public Meal(String prefix, String name, float price, String description, List<String> allergen_list) {
		super();
		this.prefix = prefix;
		this.name = name;
		this.price = price;
		this.description = description;
		this.allergen_list = allergen_list;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
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
	public List<String> getAllergen_list() {
		return allergen_list;
	}
	public void setAllergen_list(List<String> allergen_list) {
		this.allergen_list = allergen_list;
	}

	
}
