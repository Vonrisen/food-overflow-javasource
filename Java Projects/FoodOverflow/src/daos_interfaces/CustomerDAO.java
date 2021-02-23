package daos_interfaces;

import java.util.List;

import entities.Address;
import entities.Customer;
import exceptions.DAOException;

public interface CustomerDAO {

	public List<Customer> getAllCustomers() throws DAOException;

	public void insertCustomer(Customer customer) throws DAOException;

	public boolean isCustomerLoginValidated(String email, String password) throws DAOException;

	public void closeStatements() throws DAOException;

	public Customer getCustomerByEmail(String email) throws DAOException;

	public void updateCustomerAddress(Customer customer, Address address) throws DAOException;

	public void updateCustomerPassword(Customer customer, String new_password) throws DAOException;
	
	public void updateCustomerCF(Customer customer, String old_cf) throws DAOException;
}
