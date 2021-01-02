package daos_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daos_interfaces.RiderDAO;
import entities.Rider;

public class RiderDAOPostgresImplementation implements RiderDAO{
	
	private Connection connection;
	private PreparedStatement print_all_riders_PS;
	private CallableStatement insert_rider_PS, delete_rider_PS;
	
	public RiderDAOPostgresImplementation (Connection connection)
	{
		this.connection = connection;
		try {
			print_all_riders_PS = connection.prepareStatement("SELECT * FROM Rider");
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


	public void insertRider(Rider rider) throws SQLException {
		
		insert_rider_PS.setString(1,rider.getCf());
		insert_rider_PS.setString(2, rider.getRider_name());
		insert_rider_PS.setString(3,rider.getSurname());
		insert_rider_PS.setString(4, rider.getAddress());
		insert_rider_PS.setDate(5,rider.getBirth_date());
		insert_rider_PS.setString(6, rider.getBirth_place());
		insert_rider_PS.setString(7, rider.getGender());
		insert_rider_PS.setString(8,rider.getCellphone());
		insert_rider_PS.setString(9, rider.getVehicle());
		insert_rider_PS.setString(10, rider.getWorking_time());
		insert_rider_PS.setString(11,rider.getDeliveries_number());
		insert_rider_PS.executeUpdate();
		return;
	}

	public void deleteRider(String rider_id) throws SQLException {
		
		delete_rider_PS.setString(1, rider_id);
		delete_rider_PS.executeUpdate();
		return;
	}
	
}
