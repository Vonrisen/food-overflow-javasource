package daos_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import daos_interfaces.ShopDAO;
import db_connection.DBconnection;
import entities.Address;
import entities.Meal;
import entities.Rider;
import entities.Shop;
import exceptions.DaoException;
import utilities.DButility;
import utilities.InputUtility;

public class ShopDAOPostgresImplementation implements ShopDAO {

	private Connection connection;
	PreparedStatement look_for_shop_by_email_and_password_PS, get_all_shops_PS, insert_shop_PS, delete_shop_PS, get_riders_of_a_shop_by_shop_email_PS,get_meals_of_a_shop_by_shop_email_PS, get_allergens_of_a_meal_PS ;
	CallableStatement update_shop_CS;
	DButility db_util = new DButility();
	public ShopDAOPostgresImplementation() {
		try {
			DBconnection instance = DBconnection.getInstance();
			this.connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Network error, please try again","Error",JOptionPane.ERROR_MESSAGE);
		}
		try {
			
			look_for_shop_by_email_and_password_PS = connection.prepareStatement("SELECT * FROM Shop WHERE email=? AND password=?");
			get_all_shops_PS = connection.prepareStatement("SELECT * FROM Shop ORDER BY id");
			insert_shop_PS = connection.prepareStatement("INSERT INTO Shop VALUES (DEFAULT,?,?,?,?,?,?)");
			delete_shop_PS = connection.prepareStatement("DELETE FROM Shop WHERE email=?");
			update_shop_CS = connection.prepareCall("CALL updateShop(?,?,?,?,?,?,?)");
			get_riders_of_a_shop_by_shop_email_PS = connection.prepareStatement("SELECT cf, name, surname, address, birth_date, birth_place, gender, cellphone, vehicle, working_hours, deliveries_number\r\n"
					+ "FROM Rider WHERE shop_id=(SELECT id FROM Shop WHERE email=?)");
			get_meals_of_a_shop_by_shop_email_PS = connection.prepareStatement("SELECT * FROM MEAL WHERE id IN(SELECT meal_id FROM Supply WHERE shop_id=(SELECT id FROM Shop WHERE email=?)) ORDER BY category, name");
			get_allergens_of_a_meal_PS = connection.prepareStatement("SELECT allergen_name FROM MEALCOMPOSITION WHERE meal_id=?");
			
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Generic error, please contact your administrator","Error",JOptionPane.ERROR_MESSAGE);
		}
		}
		
	
	public List<Shop> getAllShops() throws DaoException {
		
		InputUtility string_util = new InputUtility();
		List<Shop> shop_list = new ArrayList<Shop>();
		List<String>address_fields = new ArrayList<String>();
		ResultSet rs = null;
		try
		{
		rs = get_all_shops_PS.executeQuery();
	
		while(rs.next())
		{

			address_fields = string_util.tokenizedStringToList(rs.getString("address"),"(, )");
			List<Rider> employed_rider_list = new ArrayList<Rider>();
			if(rs.getString("closing_days")!=null)
			employed_rider_list = getRidersOfAShopByShopEmail(rs.getString("email"));
			List<Meal> meal_list = getMealsOfAShopByShopEmail(rs.getString("email"));
			shop_list.add(new Shop(rs.getString("email"),rs.getString("name"), rs.getString("password"), rs.getString("working_hours"),
				          new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
				          rs.getString("closing_days"), employed_rider_list, meal_list));
		}}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			 db_util.releaseResources(get_riders_of_a_shop_by_shop_email_PS);
			 db_util.releaseResources(get_meals_of_a_shop_by_shop_email_PS);
			 db_util.releaseResources(get_allergens_of_a_meal_PS);
			 db_util.releaseResources(rs, get_all_shops_PS);
		}
		return shop_list;
	}
	
	public List<Rider> getRidersOfAShopByShopEmailAndCloseResources(String shop_email) throws DaoException {
		
		List<Rider>rider_list = new ArrayList<Rider>();
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		ResultSet rs = null;
		try
		{
		get_riders_of_a_shop_by_shop_email_PS.setString(1, shop_email);
		rs = get_riders_of_a_shop_by_shop_email_PS.executeQuery();
		while(rs.next())
		{
			address_fields = string_util.tokenizedStringToList(rs.getString("address"),"(, )");
			rider_list.add(new Rider(rs.getString("cf"),rs.getString("name"),rs.getString("surname"), new Date(rs.getDate("birth_date").getTime()),rs.getString("birth_place"),rs.getString("gender"),
					       rs.getString("cellphone"),new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
						   rs.getString("vehicle"),rs.getString("working_hours"),rs.getShort("deliveries_number")));
		}}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs, get_riders_of_a_shop_by_shop_email_PS);
		}
		return rider_list;
	}
	
	public List<Meal> getMealsOfAShopByShopEmail(String shop_email) throws DaoException {
		ArrayList<String> allergens;
		ArrayList<Meal> meal_list = new ArrayList<Meal>();
		ResultSet rs2 = null;
		ResultSet rs1 = null;
		try
		{
		get_meals_of_a_shop_by_shop_email_PS.setString(1, shop_email);
		rs1 = get_meals_of_a_shop_by_shop_email_PS.executeQuery();
		while(rs1.next())
		{
			get_allergens_of_a_meal_PS.setString(1, rs1.getString("id"));
			rs2 = get_allergens_of_a_meal_PS.executeQuery();
			allergens = new ArrayList<String>();
			while(rs2.next())
			{
				String allergen = rs2.getString("allergen_name");
				allergens.add(allergen);
			}
			meal_list.add(new Meal(rs1.getString("name"),rs1.getFloat("price"),rs1.getString("ingredients"),rs1.getString("category"),allergens));
		}}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs1);
			db_util.releaseResources(rs2);
			
		}
		return meal_list;
	}
	   public List<Meal> getMealsOfAShopByShopEmailAndCloseResources(String shop_email) throws DaoException {
		ArrayList<String> allergens;
		ArrayList<Meal> meal_list = new ArrayList<Meal>();
		ResultSet rs2 = null;
		ResultSet rs1 = null;
		try
		{
		get_meals_of_a_shop_by_shop_email_PS.setString(1, shop_email);
		rs1 = get_meals_of_a_shop_by_shop_email_PS.executeQuery();
		while(rs1.next())
		{
			get_allergens_of_a_meal_PS.setString(1, rs1.getString("id"));
			rs2 = get_allergens_of_a_meal_PS.executeQuery();
			allergens = new ArrayList<String>();
			while(rs2.next())
			{
				String allergen = rs2.getString("allergen_name");
				allergens.add(allergen);
			}
			meal_list.add(new Meal(rs1.getString("name"),rs1.getFloat("price"),rs1.getString("ingredients"),rs1.getString("category"),allergens));
		}}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs2, get_allergens_of_a_meal_PS);
			db_util.releaseResources(rs1, get_meals_of_a_shop_by_shop_email_PS);
		}
		return meal_list;
	}
	
       public List<Rider> getRidersOfAShopByShopEmail(String shop_email) throws DaoException {
		
		List<Rider>rider_list = new ArrayList<Rider>();
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		ResultSet rs = null;
		try
		{
		get_riders_of_a_shop_by_shop_email_PS.setString(1, shop_email);
		rs = get_riders_of_a_shop_by_shop_email_PS.executeQuery();
		while(rs.next())
		{
			address_fields = string_util.tokenizedStringToList(rs.getString("address"),"(, )");
			rider_list.add(new Rider(rs.getString("cf"),rs.getString("name"),rs.getString("surname"), new Date(rs.getDate("birth_date").getTime()),rs.getString("birth_place"),rs.getString("gender"),
					       rs.getString("cellphone"),new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
						   rs.getString("vehicle"),rs.getString("working_hours"),rs.getShort("deliveries_number")));
		}}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs);
		}
		return rider_list;
	}
	public boolean lookForShopByEmailAndPassword(String email, String password) throws DaoException {
		
		Boolean row_founded;
		ResultSet rs = null;
		try
		{
		look_for_shop_by_email_and_password_PS.setString(1, email);
		look_for_shop_by_email_and_password_PS.setString(2, password);
		rs = look_for_shop_by_email_and_password_PS.executeQuery();
		row_founded = rs.next();
		}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			 db_util.releaseResources(rs, look_for_shop_by_email_and_password_PS);
		}
		return row_founded;	
	}

	@Override
	public void insertShop(Shop shop) throws DaoException {
		
		InputUtility input_util = new InputUtility();
		try
		{
		insert_shop_PS.setString(1, shop.getName());
		insert_shop_PS.setString(2, input_util.addressToTokenizedString(shop.getAddress(), ", "));
		insert_shop_PS.setString(3, shop.getWorking_hours());
		insert_shop_PS.setString(4, shop.getClosing_days());
		insert_shop_PS.setString(5, shop.getPassword());
		insert_shop_PS.setString(6, shop.getEmail());
		insert_shop_PS.executeUpdate();
		}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			 db_util.releaseResources(insert_shop_PS);
		}
		return;
	}

	@Override
	public void deleteShop(Shop shop) throws DaoException {
		
		try
		{
		delete_shop_PS.setString(1, shop.getEmail());
		delete_shop_PS.executeUpdate();
		}
		catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			 db_util.releaseResources(delete_shop_PS);
		}
		return;
	}
	
	public void updateShop(Shop shop, String old_email) throws DaoException {
		
		InputUtility input_util = new InputUtility();
		try
		{
		update_shop_CS.setString(1, shop.getName());
		update_shop_CS.setString(2, input_util.addressToTokenizedString(shop.getAddress(), ", "));
		update_shop_CS.setString(3, shop.getWorking_hours());
		update_shop_CS.setString(4, shop.getClosing_days());
		update_shop_CS.setString(5, shop.getPassword());
		update_shop_CS.setString(6, shop.getEmail());
		update_shop_CS.setString(7, old_email);
	    update_shop_CS.executeUpdate();
		}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			 db_util.releaseResources(update_shop_CS);
		}
	    return;
	}
	
}
