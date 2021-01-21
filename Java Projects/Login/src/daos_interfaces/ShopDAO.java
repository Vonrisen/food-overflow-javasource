package daos_interfaces;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import entities.Shop;

public interface ShopDAO {
	
	public List<Shop> getAllShops() throws SQLException;
	public boolean lookForShopByIdAndPassword(String id, String password) throws SQLException;
	public void insertShop(Shop shop) throws SQLException;
	public void deleteShop(String shop_id) throws SQLException;
}
