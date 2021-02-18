package daos_interfaces;

import java.util.List;

import entities.Customer;
import exceptions.DaoException;

public interface CustomerDAO {

	public List<Customer> getAllCustomers() throws DaoException;

	public void insertCustomer(Customer customer) throws DaoException;

	public boolean isCustomerLoginValidated(String email, String password) throws DaoException;

	public void closeStatements() throws DaoException;

	public Customer getCustomerByEmail(String email) throws DaoException;
}
