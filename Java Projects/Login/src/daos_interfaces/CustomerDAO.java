package daos_interfaces;

import java.sql.SQLException;
import java.util.List;

import entities.Customer;


public interface CustomerDAO {
	
	public List<Customer>getAllCustomers() throws SQLException;
	public void insertCustomer(Customer customer) throws SQLException;
	public void deleteCustomer(Customer customer) throws SQLException;
	public void updateCustomerFromAdmin(Customer customer) throws SQLException;
}
