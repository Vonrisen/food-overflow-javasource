package daos_interfaces;

import java.util.List;

import entities.Address;
import entities.Cart;
import entities.Customer;
import entities.Order;
import entities.Rider;
import entities.Shop;
import exceptions.DaoException;

public interface OrderDAO {
	public List<Order> getOrdersOfAShopByShopEmail(String shop_email) throws DaoException;

	public List<Order> getInDeliveryOrdersOfAShop(String current_shop_email) throws DaoException;

	public List<Order> getPendingOrdersOfAShop(String current_shop_email) throws DaoException;
	
	public void linkRiderToOrder(Order order, Rider rider) throws DaoException;
	
	public void updateDeliveringOrder(Order order, String stato) throws DaoException;
	
	public void closeStatements() throws DaoException;
	
	public Order getOrderById(String id) throws DaoException;
	
	public void createOrder(Address address, String payment ,String note, Shop shop, Customer customer, Cart cart) throws DaoException;

}
