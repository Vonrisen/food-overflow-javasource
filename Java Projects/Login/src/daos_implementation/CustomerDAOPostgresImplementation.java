package daos_implementation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import daos_interfaces.CustomerDAO;
import db_connection.DBconnection;
import entities.Address;
import entities.Customer;
import utilities.InputUtility;

public class CustomerDAOPostgresImplementation implements CustomerDAO{

	private Connection connection;
	private PreparedStatement get_all_customers_PS, insert_customer_PS;
	public CustomerDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione");
		}
		try {
			get_all_customers_PS = connection.prepareStatement("SELECT * FROM Customer");
			insert_customer_PS = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?,?)");
			
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statements");
		}
		

}
	
	public ArrayList<Customer> getAllCustomers () throws SQLException {
		
		ResultSet rs = get_all_customers_PS.executeQuery();
		ArrayList<Customer> customer_list = new ArrayList<Customer>();
		ArrayList<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		while(rs.next())
		{
			address_fields = string_util.tokenizedStringToArrayList(rs.getString("address"),"(, )");
			customer_list.add(new Customer(rs.getString("cf"),rs.getString("name"),rs.getString("surname"),new Date(rs.getDate("birth_date").getTime()),rs.getString("birth_place"),
					          rs.getString("gender"),rs.getString("cellphone"),  new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
							  rs.getString("email"),
					          rs.getString("password")));
		}
		return customer_list;
	}
	
	public int insertCustomer(Customer customer) throws SQLException
	{
		InputUtility input_util = new InputUtility();
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
		return insert_customer_PS.executeUpdate();
	}

	
}
