package daos_implementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import daos_interfaces.CustomerDAO;
import db_connection.DBconnection;
import entities.Address;
import entities.Customer;
import exceptions.DaoException;
import utilities.DButility;
import utilities.InputUtility;

public class CustomerDAOPostgresImplementation implements CustomerDAO{

	private Connection connection;
	private PreparedStatement get_all_customers_PS, insert_customer_PS, delete_customer_PS, update_customer_PS;
	DButility db_util = new DButility();
	public CustomerDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Network error, please try again","Error",JOptionPane.ERROR_MESSAGE);
		}
		try {
			get_all_customers_PS = connection.prepareStatement("SELECT cf, name, surname, address, birth_date, birth_place, gender, cellphone, email, password FROM Customer");
			insert_customer_PS = connection.prepareStatement("INSERT INTO Customer VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?)");
			delete_customer_PS = connection.prepareStatement("DELETE FROM Customer WHERE email=?");
			update_customer_PS = connection.prepareStatement("UPDATE Customer SET email=?, password=? WHERE cellphone=?");
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Generic error, please contact your administrator","Error",JOptionPane.ERROR_MESSAGE);
		}
		
}
	public List<Customer> getAllCustomers() throws DaoException {
		
		ResultSet rs = null;
		List<Customer> customer_list = new ArrayList<Customer>();
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		try
		{
		rs = get_all_customers_PS.executeQuery();
		while(rs.next())
		{
			address_fields = string_util.tokenizedStringToList(rs.getString("address"),"(, )");
			customer_list.add(new Customer(rs.getString("cf"),rs.getString("name"),rs.getString("surname"),new Date(rs.getDate("birth_date").getTime()),rs.getString("birth_place"),
					          rs.getString("gender"),rs.getString("cellphone"),  new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
							  rs.getString("email"),
					          rs.getString("password")));
		}}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs, get_all_customers_PS);
		}
		return customer_list;
	}
	
	public void insertCustomer(Customer customer) throws DaoException
	{
		InputUtility input_util = new InputUtility();
		try
		{
		insert_customer_PS.setString(1, customer.getCf());
		insert_customer_PS.setString(2, customer.getName());
		insert_customer_PS.setString(3, customer.getSurname());
		insert_customer_PS.setString(4, input_util.addressToTokenizedString(customer.getAddress(), ", "));
		insert_customer_PS.setDate(5, new java.sql.Date(customer.getBirth_date().getTime()));
		insert_customer_PS.setString(6, customer.getBirth_place());
		insert_customer_PS.setString(7, customer.getGender().toUpperCase());
		insert_customer_PS.setString(8, customer.getCellphone());
		insert_customer_PS.setString(9, customer.getEmail());
		insert_customer_PS.setString(10, customer.getPassword());
		insert_customer_PS.executeUpdate();
		}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(insert_customer_PS);
		}
		return;
	}
	
	public void deleteCustomer(Customer customer) throws DaoException {
		
		try
		{
		delete_customer_PS.setString(1, customer.getEmail());
		delete_customer_PS.executeUpdate();
		}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(delete_customer_PS);
		}
		return;
	}
	
	public void updateCustomerFromAdmin(Customer customer) throws DaoException{
		
		try
		{
		update_customer_PS.setString(1, customer.getEmail());
		update_customer_PS.setString(2, customer.getPassword());
		update_customer_PS.setString(3, customer.getCellphone());
		update_customer_PS.executeUpdate();
		}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(update_customer_PS);
		}
		return;
	}

	
}
