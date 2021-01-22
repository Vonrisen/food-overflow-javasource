package daos_interfaces;

import java.sql.SQLException;
import java.util.List;

import entities.Rider;

public interface RiderDAO {

	public List<Rider> getAllRiders() throws SQLException;
	public List<Rider> getRidersOfAShopByShopId(String shop_id) throws SQLException;
	public void insertRider(Rider rider, String shop_id) throws SQLException;

}
