package daos_interfaces;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Rider;
import entities.Shop;

public interface ShopDAO {
	
	public List<Shop> getAllShops() throws SQLException;
	public int insertShop(Shop shop) throws SQLException;
	public int delete(Shop shop) throws SQLException;
	public int updateShop(Shop shop) throws SQLException;
	public Shop getShopById(String shop_id) throws SQLException;
	public List<Shop> getShopsOfARider(String cf) throws SQLException;
	public Shop getShopOfTheOrder(String order_id) throws SQLException;
}
