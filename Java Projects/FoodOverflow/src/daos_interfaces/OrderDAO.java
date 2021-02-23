package daos_interfaces;

import java.util.List;

import entities.Address;
import entities.Cart;
import entities.Customer;
import entities.Order;
import entities.Rider;
import entities.Shop;
import exceptions.DAOException;

public interface OrderDAO {
	public List<Order> getOrdersOfAShopByShopEmail(String shop_email) throws DAOException;

	public List<Order> getInDeliveryOrdersOfAShop(String current_shop_email) throws DAOException;

	public List<Order> getPendingOrdersOfAShop(String current_shop_email) throws DAOException;

	public void linkRiderToOrder(Order order, Rider rider) throws DAOException;

	public void updateDeliveringOrder(Order order, String stato) throws DAOException;

	public void closeStatements() throws DAOException;

	public Order getOrderById(String id) throws DAOException;

	public void createOrder(Address address, String payment, String note, Shop shop, Customer customer, Cart cart)
			throws DAOException;

	public List<Order> doAdminComplexSearch(String category, float min_price, float max_price, String vehicle,
			String province) throws DAOException;
	
	public List<Order> getAllOrders() throws DAOException;
	
	public Rider getRiderOfTheOrderByCF(String cf) throws DAOException;
	
	public Shop getShopOfTheOrderByEmail(String email) throws DAOException;
	
	public Customer getCustomerOfTheOrderByEmail(String email) throws DAOException;

}
