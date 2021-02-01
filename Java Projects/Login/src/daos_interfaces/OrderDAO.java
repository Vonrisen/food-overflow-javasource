package daos_interfaces;

import java.util.List;

import entities.Order;
import exceptions.DaoException;

public interface OrderDAO {
	public List<Order> getOrdersOfAShopByShopEmail(String shop_email) throws DaoException;

	public List<Order> getInDeliveryOrdersOfAShop(String current_shop_email) throws DaoException;

	public List<Order> getPendingOrdersOfAShop(String current_shop_email) throws DaoException;
	
	public void updatePendingOrder(Order order, String rider_cf) throws DaoException;
	
	public void updateDeliveringOrder(Order order, String stato) throws DaoException;

}
