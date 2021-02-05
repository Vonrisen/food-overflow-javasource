package daos_interfaces;

import java.util.List;

import entities.Meal;
import entities.Rider;
import entities.Shop;
import exceptions.DaoException;

public interface ShopDAO {
	
	public List<Shop> getAllShops() throws DaoException;
	public boolean isShopLoginValidated(String email, String password) throws DaoException;
	public void insertShop(Shop shop) throws DaoException;
	public void deleteShop(Shop shop) throws DaoException;
	public void updateShop(Shop shop, String old_email) throws DaoException;
	public List<Rider> getRidersOfAShopByShopEmail(String current_shop_email) throws DaoException;
	public List<Rider> getRidersOfAShopByShopEmailAndCloseResources(String current_shop_email) throws DaoException;
	public List<Meal>getMealsOfAShopByShopEmail(String shop_email) throws DaoException;
	public List<Meal>getMealsOfAShopByShopEmailAndCloseResources(String shop_email) throws DaoException;
}
