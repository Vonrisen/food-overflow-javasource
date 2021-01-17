package entities;
import java.util.ArrayList;
import java.util.List;

public class Cart {

	Shop shop;
	Customer customer;
	List<OrderComposition> order_composition_list;
	
	
	
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



	public void addIntoOrderComposition(OrderComposition meal) {
		
		this.order_composition_list.add(meal);
		
	}
}
