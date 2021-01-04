package daos_interfaces;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Rider;
public interface RiderDAO {

	public ResultSet getAllRiders() throws SQLException;
	public void insertRider(String cf, String name, String surname, String address,String birth_date,String birth_place, String gender, String cellphone, String vehicle, String working_time, String shop_id) throws SQLException;
	public void deleteRider(String rider_id) throws SQLException;
}
