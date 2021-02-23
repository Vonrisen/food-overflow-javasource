package daos_interfaces;

import java.util.List;

import entities.Meal;
import entities.Rider;
import entities.Shop;
import exceptions.DAOException;

public interface ShopDAO {

	public List<Shop> getAllShops() throws DAOException;

	public boolean isShopLoginValidated(String email, String password) throws DAOException;

	public void insertShop(Shop shop) throws DAOException;

	public void deleteShop(Shop shop) throws DAOException;

	public void updateShop(Shop shop, String old_email) throws DAOException;

	public List<Rider> getRidersOfAShopByShopEmail(String current_shop_email) throws DAOException;

	public List<Meal> getMealsOfAShopByShopEmail(String shop_email) throws DAOException;

	public Shop getShopByEmail(String email) throws DAOException;

	public List<Shop> getShopByProvince(String province) throws DAOException;

	public void closeStatements() throws DAOException;

	public List<Rider> getRidersOfAShopMax2DeliveriesByShopEmail(String shop_email) throws DAOException;
}
