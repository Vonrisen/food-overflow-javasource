package daos_interfaces;

import java.util.List;

import entities.Customer;
import exceptions.DaoException;


public interface CustomerDAO {
	
	public List<Customer>getAllCustomers() throws DaoException;
	public void insertCustomer(Customer customer) throws DaoException;
	public void deleteCustomer(Customer customer) throws DaoException;
	public void updateCustomerFromAdmin(Customer customer) throws DaoException;
}
