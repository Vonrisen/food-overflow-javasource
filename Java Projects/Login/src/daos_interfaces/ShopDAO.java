package daos_interfaces;


import java.sql.SQLException;
import java.util.List;

import entities.Shop;
import exceptions.DaoException;

public interface ShopDAO {
	
	public List<Shop> getAllShops() throws DaoException;
	public boolean lookForShopByEmailAndPassword(String email, String password) throws DaoException;
	public void insertShop(Shop shop) throws DaoException;
	public void deleteShop(Shop shop) throws DaoException;
	public void updateShop(Shop shop, String old_email) throws DaoException;
	
}
