package daos_interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Shop;

public interface ShopDAO {

	public ResultSet getAllShops() throws SQLException;
	public void insertShop(String name, String address, String working_time, String closing_days) throws SQLException;
	public void deleteShop(String shop_id) throws SQLException;
	public void updateShop(String shop_id,String name, String address, String working_time, String closing_days) throws SQLException;
	
}
