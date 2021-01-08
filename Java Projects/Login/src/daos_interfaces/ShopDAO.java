package daos_interfaces;


import java.sql.SQLException;
import java.util.ArrayList;

import entities.Rider;
import entities.Shop;

public interface ShopDAO {
	
	public ArrayList<Shop> getAllShops() throws SQLException;
	public void insertShop(Shop shop) throws SQLException;
	public void delete(Shop shop) throws SQLException;
	public void updateShop(Shop shop) throws SQLException;
	public Shop getShopById(String shop_id) throws SQLException;
	public ArrayList<Shop> getShopsOfARider(Rider rider) throws SQLException;
}
