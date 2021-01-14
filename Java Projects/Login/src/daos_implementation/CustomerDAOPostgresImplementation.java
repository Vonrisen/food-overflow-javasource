package daos_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import daos_interfaces.CustomerDAO;
import db_connection.DBconnection;
import entities.Customer;

public class CustomerDAOPostgresImplementation implements CustomerDAO {
	
	private Connection connection;
	PreparedStatement get_customer_of_the_order_PS;
	public CustomerDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione");
		}
		try {
			
			get_customer_of_the_order_PS = connection.prepareStatement("SELECT * FROM Customer WHERE customer_id IN (SELECT customer_id FROM CustomerOrder WHERE order_id=?");
			
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statements");
		}
		

}

	@Override
	public List<Customer> getAllCustomers() throws SQLException {
		
		return null;
	}

	@Override
	public void insertCustomer(Customer customer) throws SQLException {
		
	}

	@Override
	public void deleteCustomer(Customer customer) throws SQLException {
		
		
	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException {
		
		
	}

	@Override
	public Customer getCustomerOfTheOrder(String order_id) throws SQLException {
		
		Customer customer = null;
		get_customer_of_the_order_PS.setString(1, order_id);
		ResultSet rs = 	get_customer_of_the_order_PS.executeQuery();
		while (rs.next())
		{
			customer = new Customer(rs.getString("cf"),rs.getString("name"),rs.getString("surname"), rs.getString("address"),rs.getString("birth_date"),rs.getString("birth_place"),
			rs.getString("gender"),rs.getString("cellphone"),rs.getString("email"),rs.getString("password"));
		}
		rs.close();
		connection.close();
		return customer;
	}

}
