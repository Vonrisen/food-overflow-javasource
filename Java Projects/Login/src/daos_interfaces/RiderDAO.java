package daos_interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import entities.Rider;
import entities.Shop;

public interface RiderDAO {

	public ArrayList<Rider> getAllRiders() throws SQLException;
	public void insertRider(Rider rider) throws SQLException;
	public void deleteRider(Rider rider) throws SQLException;
	public void updateRider(Rider rider) throws SQLException;
	public Rider getRiderById(String rider_id) throws SQLException;
	public ArrayList<Rider> getRidersOfAShop(Shop shop) throws SQLException;

}
