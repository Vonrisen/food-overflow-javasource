package daos_interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Customer;


public interface CustomerDAO {
	
	public ResultSet getAllCustomers() throws SQLException ;
	public void insertCustomer(Customer customer)throws SQLException;
	public void deleteCustomer(String customer_id)throws SQLException;
	public void updateCustomer (String email, String password, String cellphone, String customer_id)throws SQLException;
	
}
