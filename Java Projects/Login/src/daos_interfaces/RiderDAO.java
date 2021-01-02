package daos_interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Rider;

public interface RiderDAO {

	public ResultSet getAllRiders() throws SQLException;
	public void insertRider(Rider rider) throws SQLException;
	public void deleteRider(String rider_id) throws SQLException;
}
