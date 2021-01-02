package daos_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daos_interfaces.CustomerDAO;
import entities.Customer;

public class CustomerDAOPostgresImplementation implements CustomerDAO {

	private Connection connection;
	private PreparedStatement print_all_customers_PS, update_email_password_cellphone_customer_PS;
	private CallableStatement insert_customer_CS, delete_customer_CS, update ;
	
	public CustomerDAOPostgresImplementation(Connection connection)
	{
		this.connection = connection;
		try {
			print_all_customers_PS = connection.prepareStatement("SELECT * FROM Customer");
			insert_customer_CS = connection.prepareCall("CALL insertCustomer(?,?,?,?,?,?,?,?,?,?)");
			delete_customer_CS = connection.prepareCall("CALL deleteCustomer(?)");
			update_email_password_cellphone_customer_PS = connection.prepareStatement("UPDATE CUSTOMER SET email=?,password=?,cellphone=? WHERE customer_id=?");
		} catch (SQLException e) {
			System.out.println("Errore durante la preparazione degli statement"+e.getMessage());
		}
		
	}
	public void insertCustomer(Customer customer)throws SQLException {
		
			insert_customer_CS.setString(1, customer.getCustomer_name());
			insert_customer_CS.setString(2, customer.getSurname());
			insert_customer_CS.setString(3, customer.getAddress());
			insert_customer_CS.setDate(4, customer.getBirth_date());
			insert_customer_CS.setString(5, customer.getBirth_place());
			insert_customer_CS.setString(6, customer.getGender());
			insert_customer_CS.setString(7, customer.getCellphone());
			insert_customer_CS.setString(8, customer.getEmail());
			insert_customer_CS.setString(9, customer.getPassword());
			insert_customer_CS.setString(10, customer.getCf());
			insert_customer_CS.executeUpdate();
		return;
	}

	
	public void deleteCustomer(String customer_id)throws SQLException {
		
			delete_customer_CS.setString(1, customer_id);
			delete_customer_CS.executeUpdate();
		return;
	}

	
	public void updateCustomer(String email, String password,String cellphone, String customer_id)throws SQLException  {
		
			update_email_password_cellphone_customer_PS.setString(1, email);
			update_email_password_cellphone_customer_PS.setString(2,password);
			update_email_password_cellphone_customer_PS.setString(3,cellphone);
			update_email_password_cellphone_customer_PS.setString(4, customer_id);
			update_email_password_cellphone_customer_PS.executeUpdate();
			
		return;
	
	}
	
	public ResultSet getAllCustomers() throws SQLException{
		
		ResultSet rs = print_all_customers_PS.executeQuery();
		return rs;
	}

}
