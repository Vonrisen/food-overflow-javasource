package daos_implementation;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import db_connection.DBconnection;
import entities.Address;
import entities.Rider;
import entities.Shop;
import utilities.StringUtility;

public class RiderDAOPostgresImplementation implements RiderDAO {
	
	private Connection connection;
	PreparedStatement get_all_riders_PS, get_riders_of_a_shop_by_shop_id_PS;
	public RiderDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione con il database","Errore",JOptionPane.ERROR_MESSAGE);
		}
		try {
			
			get_all_riders_PS = connection.prepareStatement("SELECT cf, name, surname, address, birth_date, birth_place, gender, cellphone, vehicle, working_hours, deliveries_number FROM Rider");
			get_riders_of_a_shop_by_shop_id_PS = connection.prepareStatement("SELECT cf, name, surname, address, birth_date, birth_place, gender, cellphone, vehicle, working_hours, deliveries_number\r\n"
					+ "FROM Rider WHERE shop_id=?");
		
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statement","Errore",JOptionPane.ERROR_MESSAGE);
		}
		

}

	public ArrayList<Rider>getAllRiders() throws SQLException
	{
		ResultSet rs = get_all_riders_PS.executeQuery();
		ArrayList<Rider> rider_list = new ArrayList<Rider>();
		ArrayList<String>address_fields = new ArrayList<String>();
		StringUtility string_util = new StringUtility();
		while(rs.next())
		{
			address_fields = string_util.tokenizedStringToArrayList(rs.getString("address"),"(, )");
			rider_list.add(new Rider(rs.getString("cf"),rs.getString("name"),rs.getString("surname"), new Date(rs.getDate("birth_date").getTime()),rs.getString("birth_place"),rs.getString("gender"),
					       rs.getString("cellphone"), new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
						   rs.getString("vehicle"),rs.getString("working_hours"),rs.getShort("deliveries_number")));
			
		}
		return rider_list;
	}

	@Override
	public ArrayList<Rider> getRidersOfAShopByShopId(String shop_id) throws SQLException {
		
		get_riders_of_a_shop_by_shop_id_PS.setString(1, shop_id);
		ResultSet rs = get_riders_of_a_shop_by_shop_id_PS.executeQuery();
		ArrayList<Rider>rider_list = new ArrayList<Rider>();
		ArrayList<String>address_fields = new ArrayList<String>();
		StringUtility string_util = new StringUtility();
		while(rs.next())
		{
			address_fields = string_util.tokenizedStringToArrayList(rs.getString("address"),"(, )");
			rider_list.add(new Rider(rs.getString("cf"),rs.getString("name"),rs.getString("surname"), new Date(rs.getDate("birth_date").getTime()),rs.getString("birth_place"),rs.getString("gender"),
					       rs.getString("cellphone"),new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
						   rs.getString("vehicle"),rs.getString("working_hours"),rs.getShort("deliveries_number")));
		}
		return rider_list;
	}
	
}
