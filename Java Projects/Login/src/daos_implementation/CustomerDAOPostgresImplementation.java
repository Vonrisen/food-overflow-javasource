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
import utilities.StringUtility;

public class CustomerDAOPostgresImplementation implements CustomerDAO{

	private Connection connection;
	private PreparedStatement get_all_customers_PS;
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
			
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statements");
		}
		

}
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		
		ResultSet rs = get_all_customers_PS.executeQuery();
		ArrayList<Customer> customer_list = new ArrayList<Customer>();
		ArrayList<String>address_fields = new ArrayList<String>();
		StringUtility string_util = new StringUtility();
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

	
}
