package daos_interfaces;

import java.sql.SQLException;
import java.util.List;

import entities.Order;

public interface OrderDAO {
	public List<Order> getOrdersOfAShopByShopEmail(String shop_email) throws SQLException;

}
