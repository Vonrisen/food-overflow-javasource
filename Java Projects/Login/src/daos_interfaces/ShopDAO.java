package daos_interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Shop;

public interface ShopDAO {

	public ResultSet getAllShops() throws SQLException;
	public void insertShop(Shop shop) throws SQLException;
	public void deleteShop(String shop_id) throws SQLException;
	public void updateShop(Shop shop) throws SQLException;
	
}
