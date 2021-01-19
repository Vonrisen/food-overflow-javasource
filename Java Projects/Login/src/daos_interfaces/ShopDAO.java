package daos_interfaces;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import entities.Shop;

public interface ShopDAO {
	
	public ArrayList<Shop> getAllShops() throws SQLException;
	public boolean lookForShopByIdAndPassword(String id, String password) throws SQLException;
	public int insertShop(Shop shop) throws SQLException;
}
