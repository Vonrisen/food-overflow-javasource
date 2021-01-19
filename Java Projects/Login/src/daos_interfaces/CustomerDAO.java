package daos_interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;


public interface CustomerDAO {
	
	public ArrayList<Customer>getAllCustomers() throws SQLException;
	public void insertCustomer(Customer customer) throws SQLException;
}
