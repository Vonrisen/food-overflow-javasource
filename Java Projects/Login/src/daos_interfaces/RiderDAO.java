package daos_interfaces;

import java.sql.SQLException;
import java.util.List;

import entities.Rider;

public interface RiderDAO {

	public List<Rider> getAllRiders() throws SQLException;
	public List<Rider> getRidersOfAShopByShopEmail(String shop_email) throws SQLException;
	public void insertRider(Rider rider, String shop_id) throws SQLException;
	public void dismissRider(Rider rider) throws SQLException;
	public void updateRider(Rider rider) throws SQLException;
}
