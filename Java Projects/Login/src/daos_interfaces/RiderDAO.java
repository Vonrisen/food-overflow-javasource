package daos_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Rider;
import entities.Shop;

public interface RiderDAO {

	public List<Rider> getAllRiders() throws SQLException;
	public List<Rider> getRidersOfAShopByShopId(String shop_id) throws SQLException;

}
