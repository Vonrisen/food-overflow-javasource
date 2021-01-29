package daos_implementation;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import daos_interfaces.RiderDAO;
import db_connection.DBconnection;
import entities.Address;
import entities.Rider;
import utilities.InputUtility;

public class RiderDAOPostgresImplementation implements RiderDAO {
	
	private Connection connection;
	PreparedStatement get_all_riders_PS, get_riders_of_a_shop_by_shop_email_PS, insert_rider_PS, dismiss_rider_PS, update_rider_PS;
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
			get_riders_of_a_shop_by_shop_email_PS = connection.prepareStatement("SELECT cf, name, surname, address, birth_date, birth_place, gender, cellphone, vehicle, working_hours, deliveries_number\r\n"
					+ "FROM Rider WHERE shop_id=(SELECT id FROM Shop WHERE email=?)");
			insert_rider_PS = connection.prepareStatement("INSERT INTO Rider (SELECT ?,?,?,?,?,?,?,?,?,?,?, id FROM Shop WHERE email=?)");
			dismiss_rider_PS = connection.prepareStatement("DELETE FROM Rider WHERE cf=?");
			update_rider_PS = connection.prepareStatement("UPDATE Rider SET name=?, surname=?, birth_date=?, birth_place=?, address=?, gender=?, cellphone=?, vehicle=?, working_hours=? WHERE cf=?");
			
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statement","Errore",JOptionPane.ERROR_MESSAGE);
		}
		
      }

	public List<Rider>getAllRiders() throws SQLException
	{
		ResultSet rs = get_all_riders_PS.executeQuery();
		List<Rider> rider_list = new ArrayList<Rider>();
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		while(rs.next())
		{
			address_fields = string_util.tokenizedStringToList(rs.getString("address"),"(, )");
			rider_list.add(new Rider(rs.getString("cf"),rs.getString("name"),rs.getString("surname"), new Date(rs.getDate("birth_date").getTime()),rs.getString("birth_place"),rs.getString("gender"),
				       rs.getString("cellphone"), new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
					   rs.getString("vehicle"),rs.getString("working_hours"),rs.getShort("deliveries_number")));
			
		}
		return rider_list;
	}
	
	public List<Rider> getRidersOfAShopByShopEmail(String shop_email) throws SQLException {
		
		get_riders_of_a_shop_by_shop_email_PS.setString(1, shop_email);
		ResultSet rs = get_riders_of_a_shop_by_shop_email_PS.executeQuery();
		List<Rider>rider_list = new ArrayList<Rider>();
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		while(rs.next())
		{
			address_fields = string_util.tokenizedStringToList(rs.getString("address"),"(, )");
			rider_list.add(new Rider(rs.getString("cf"),rs.getString("name"),rs.getString("surname"), new Date(rs.getDate("birth_date").getTime()),rs.getString("birth_place"),rs.getString("gender"),
					       rs.getString("cellphone"),new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
						   rs.getString("vehicle"),rs.getString("working_hours"),rs.getShort("deliveries_number")));
		}
		return rider_list;
	}
	
	public void insertRider(Rider rider, String shop_email) throws SQLException {
		
		InputUtility input_util = new InputUtility();
		insert_rider_PS.setString(1, rider.getCf());
		insert_rider_PS.setString(2, rider.getName());
		insert_rider_PS.setString(3, rider.getSurname());
		insert_rider_PS.setString(4, input_util.addressToTokenizedString(rider.getAddress(), ", "));
		insert_rider_PS.setDate(5,  new java.sql.Date(rider.getBirth_date().getTime()));
		insert_rider_PS.setString(6, rider.getBirth_place());
		insert_rider_PS.setString(7, rider.getGender());
		insert_rider_PS.setString(8, rider.getCellphone());
		insert_rider_PS.setString(9, rider.getVehicle());
		insert_rider_PS.setString(10, rider.getWorking_hours());
		insert_rider_PS.setShort(11, rider.getDeliveries_number());
		insert_rider_PS.setString(12, shop_email);
		insert_rider_PS.executeUpdate();
		return;
	}
	
	public void dismissRider(Rider rider) throws SQLException{
		
		dismiss_rider_PS.setString(1, rider.getCf());
		dismiss_rider_PS.executeUpdate();
	}

	@Override
	public void updateRider(Rider rider) throws SQLException {
		
		InputUtility input_util = new InputUtility();
		update_rider_PS.setString(1, rider.getName());
		update_rider_PS.setString(2, rider.getSurname());
		update_rider_PS.setDate(3, new java.sql.Date(rider.getBirth_date().getTime()));
		update_rider_PS.setString(4, rider.getBirth_place());
		update_rider_PS.setString(5, input_util.addressToTokenizedString(rider.getAddress(),", "));
		update_rider_PS.setString(6, rider.getGender());
		update_rider_PS.setString(7, rider.getCellphone());
		update_rider_PS.setString(8, rider.getVehicle());
		update_rider_PS.setString(9, rider.getWorking_hours());
		update_rider_PS.setString(10, rider.getCf());
		update_rider_PS.executeUpdate();
		return;
	}
}
