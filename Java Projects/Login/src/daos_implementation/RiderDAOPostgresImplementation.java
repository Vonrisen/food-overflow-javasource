package daos_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import daos_interfaces.RiderDAO;
import db_connection.DBconnection;
import db_connection.DBconnection_CodiceCatastale;
import entities.Rider;

public class RiderDAOPostgresImplementation implements RiderDAO{
	
	private Connection connection;
	private DBconnection instance;
	private PreparedStatement print_all_riders_PS;
	private CallableStatement insert_rider_PS, delete_rider_PS;
	
	public RiderDAOPostgresImplementation ()
	{
		try {
			instance = DBconnection.getInstance();
			connection = instance.getConnection();
		} catch (SQLException e1) {
			System.out.println("Errore di connessione col database "+e1.getMessage());
		}
		try {
			print_all_riders_PS = connection.prepareStatement("SELECT rider_id, cf, rider_name, surname, address, to_char(birth_date, 'DD/MM/YYYY') AS birth_date, birth_place,"
					+ " gender, cellphone, vehicle, working_time,deliveries_number FROM Rider");
			insert_rider_PS = connection.prepareCall("CALL insertRider(?,?,?,?,?,?,?,?,?,?,?)");
			delete_rider_PS = connection.prepareCall("DELETE deleteRider(?)");
		} catch (SQLException e) {
			System.out.println("Errore durante la preparazione degli statement "+e.getMessage());
		}
		
	}
	
	public ResultSet getAllRiders() throws SQLException {
		
		ResultSet rs = print_all_riders_PS.executeQuery();
		return rs;
	}
	
	public void insertRider(String cf, String name, String surname, String address,String birth_date, String birth_place, String gender, String cellphone, String vehicle, String working_time, String shop_id) throws SQLException {
		
		insert_rider_PS.setString(1,cf);
		insert_rider_PS.setString(2, name);
		insert_rider_PS.setString(3,surname);
		insert_rider_PS.setString(4, address);
		insert_rider_PS.setString(5,birth_date);
		insert_rider_PS.setString(6, birth_place);
		insert_rider_PS.setString(7, gender);
		insert_rider_PS.setString(8,cellphone);
		insert_rider_PS.setString(9, vehicle);
		insert_rider_PS.setString(10, working_time);
		insert_rider_PS.setString(11, shop_id);
		insert_rider_PS.executeUpdate();
		return;
	}
	public void deleteRider(String rider_id) throws SQLException {
		delete_rider_PS.setString(1, rider_id);
		delete_rider_PS.executeUpdate();
		return;
	}
}
