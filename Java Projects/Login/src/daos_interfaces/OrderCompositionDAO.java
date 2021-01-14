package daos_interfaces;

import java.sql.SQLException;
import java.util.List;

import entities.OrderComposition;

public interface OrderCompositionDAO {
	
	public List<OrderComposition> getOrderCompositionOfTheOrder(String order_id) throws SQLException;

}
