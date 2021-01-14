package daos_interfaces;

import java.sql.SQLException;
import java.util.List;

import entities.CustomerOrder;

public interface CustomerOrderDAO {


	public List<CustomerOrder> getAllCustomerOrders() throws SQLException;
	public int insertCustomerOrder(CustomerOrder customer_order) throws SQLException;
	public void updateCustomerOrder (CustomerOrder customer_order) throws SQLException;
	public List<CustomerOrder> getCustomerOrdersOfAShop(String shop_id) throws SQLException;
	public List<CustomerOrder> getDeliveriesOfARider(String cf) throws SQLException;
	public CustomerOrder getCustomerOrderByOrderId(String order_id) throws SQLException;
	
}

