package entities;

import java.util.List;

public class Meal {

	//ATTRIBUTI
	private String id;
	private String name;
	private float price;
	private String description;
	private String[] allergen_list;
	private List<Shop> shops_list;
	
	//COTRUTTORE
	public Meal(String id, String name, float price, String description, String[] allergen_list, List<Shop> shops_list) {
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.allergen_list = allergen_list;
		this.shops_list = shops_list;
	}

	//GETTERE AND SETTER
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String[] getAllergen_list() {
		return allergen_list;
	}

	public void setAllergen_list(String[] allergen_list) {
		this.allergen_list = allergen_list;
	}

	public List<Shop> getShops_list() {
		return shops_list;
	}

	public void setShops_list(List<Shop> shops_list) {
		this.shops_list = shops_list;
	}
	
}
