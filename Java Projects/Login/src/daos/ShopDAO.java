package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Shop;

public interface ShopDAO {
	
	public ResultSet getAllShops() throws SQLException;
	public void insertShop(Shop shop) throws SQLException;
	public void deleteShop(Shop shop) throws SQLException;
}
