package daos_interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.Customer;


public interface CustomerDAO {
	
	public List<Customer>getAllCustomers() throws SQLException ;
	public void insertCustomer(Customer customer)throws SQLException;
	public void deleteCustomer(Customer customer)throws SQLException;
	public void updateCustomer (Customer customer)throws SQLException;
	public Customer getCustomerOfTheOrder(String order_id)throws SQLException;
}
