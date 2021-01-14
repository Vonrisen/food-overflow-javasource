package daos_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Rider;
import entities.Shop;

public interface RiderDAO {

	public List<Rider> getAllRiders() throws SQLException;
	public int insertRider(Rider rider) throws SQLException;
	public int deleteRider(Rider rider) throws SQLException;
	public int updateRider(Rider rider) throws SQLException;
	public List<Rider> getRidersOfAShop(String shop_id) throws SQLException;
	public Rider getRiderByCf(String cf) throws SQLException;
	public Rider getRiderOfTheOrder(String order_id) throws SQLException;

}
