package daos_interfaces;

import java.sql.SQLException;
import java.util.List;

import entities.Order;
import exceptions.DaoException;

public interface OrderDAO {
	
	public List<Order> getOrdersOfAShopByShopEmail(String shop_email) throws DaoException;

}
