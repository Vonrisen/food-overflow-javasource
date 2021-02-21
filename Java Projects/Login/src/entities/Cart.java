package entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private Shop shop;
	private Customer customer;
	private List<OrderComposition> order_composition_list;

	public Cart(Shop shop, Customer customer) {
		super();
		this.shop = shop;
		this.customer = customer;
		order_composition_list = new ArrayList<OrderComposition>();
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderComposition> getOrder_composition_list() {
		return order_composition_list;
	}

	public void setOrder_composition_list(List<OrderComposition> order_composition_list) {
		this.order_composition_list = order_composition_list;
	}

	public void addMealIntoCart(OrderComposition meal) {

		this.order_composition_list.add(meal);

	}

	public float getTotalPrice() {
		float total_price = 0;
		for (OrderComposition o : order_composition_list) {
			total_price += o.getMeal().getPrice() * o.getQuantity();
		}
		return total_price;
	}
}
