package daos_implementation;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import daos_interfaces.MealDAO;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import db_connection.DBconnection;
import entities.Address;
import entities.Meal;
import entities.Rider;
import entities.Shop;
import utilities.InputUtility;

public class ShopDAOPostgresImplementation implements ShopDAO {

	private Connection connection;
	PreparedStatement look_for_shop_by_id_and_password_PS, get_all_shops_PS, insert_shop_PS, delete_shop_PS;
	public ShopDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione con il database","Errore",JOptionPane.ERROR_MESSAGE);
		}
		try {
			
			look_for_shop_by_id_and_password_PS = connection.prepareStatement("SELECT * FROM Shop WHERE id=? AND password=?");
			get_all_shops_PS = connection.prepareStatement("SELECT * FROM Shop");
			insert_shop_PS = connection.prepareStatement("INSERT INTO Shop VALUES (DEFAULT,?,?,?,?,?)");
			delete_shop_PS = connection.prepareStatement("DELETE FROM Shop WHERE id=?");
		
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statement","Errore",JOptionPane.ERROR_MESSAGE);
		}
		
    }
	
	public ArrayList<Shop> getAllShops() throws SQLException {
		
		ResultSet rs = get_all_shops_PS.executeQuery();
		InputUtility string_util = new InputUtility();
		ArrayList<Shop> shop_list = new ArrayList<Shop>();
		ArrayList<String>address_fields = new ArrayList<String>();
		ArrayList<String>closing_days = new ArrayList<String>();
		RiderDAO rider_dao = new RiderDAOPostgresImplementation();
		MealDAO meal_dao = new MealDAOPostgresImplementation();
		while(rs.next())
		{
			address_fields = string_util.tokenizedStringToArrayList(rs.getString("address"),"(, )");
			ArrayList<Rider> employed_rider_list = new ArrayList<Rider>();
			if(rs.getString("closing_days")!=null)
			employed_rider_list = rider_dao.getRidersOfAShopByShopId(rs.getString("id"));
			
			ArrayList<Meal> meal_list = meal_dao.getMealsOfAShopByShopId(rs.getString("id"));
			shop_list.add(new Shop(rs.getString("id"),rs.getString("name"), rs.getString("password"), rs.getString("working_hours"),
				          new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
				          rs.getString("closing_days"), employed_rider_list, meal_list));
		}
		return shop_list;
		
	}
	
	public boolean lookForShopByIdAndPassword(String id, String password) throws SQLException {
		
		Boolean row_founded;
		look_for_shop_by_id_and_password_PS.setString(1, id);
		look_for_shop_by_id_and_password_PS.setString(2, password);
		ResultSet rs = look_for_shop_by_id_and_password_PS.executeQuery();
		row_founded = rs.next();
		return row_founded;
		
	}

	@Override
	public void insertShop(Shop shop) throws SQLException {
		
		InputUtility input_util = new InputUtility();
		insert_shop_PS.setString(1, shop.getName());
		insert_shop_PS.setString(2, input_util.addressToTokenizedString(shop.getAddress(), ", "));
		insert_shop_PS.setString(3, shop.getWorking_hours());
		insert_shop_PS.setString(4, shop.getClosing_days());
		insert_shop_PS.setString(5, shop.getPassword());
		insert_shop_PS.executeUpdate();
		
	}

	@Override
	public void deleteShop(String shop_id) throws SQLException {
		delete_shop_PS.setString(1, shop_id);
		delete_shop_PS.executeQuery();
	}
}
