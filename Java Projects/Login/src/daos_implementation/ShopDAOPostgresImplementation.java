package daos_implementation;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import daos_interfaces.ShopDAO;
import db_connection.DBconnection;
import entities.Shop;

public class ShopDAOPostgresImplementation implements  ShopDAO{
	
	private Connection connection;
	private DBconnection instance;
	private PreparedStatement print_all_shops_PS;
	private CallableStatement insert_shop_CS,delete_shop_CS, update_shop_CS;
	
	public ShopDAOPostgresImplementation()
	{
		try {
			instance = DBconnection.getInstance();
		} catch (SQLException e1) {
			System.out.println("Errore di connessione col database "+e1.getMessage());
		}
		connection = instance.getConnection();
		try {
			insert_shop_CS = connection.prepareCall("CALL insertShop(?,?,?,?)");
			delete_shop_CS = connection.prepareCall("CALL deleteShop(?)");
			update_shop_CS = connection.prepareCall("CALL updateShop(?,?,?,?,?)");
			print_all_shops_PS = connection.prepareStatement("SELECT * FROM Shop ORDER BY shop_id");
		} catch (SQLException e) {
			System.out.println("Errore durante la preparazione degli statement "+e.getMessage());
		}
	}
	public void insertShop(String name, String address, String working_time, String closing_days) throws SQLException {
		
		insert_shop_CS.setString(1, name);
		insert_shop_CS.setString(2, address);
		insert_shop_CS.setString(3, working_time);
		insert_shop_CS.setString(4, closing_days);
		insert_shop_CS.executeUpdate();
		return;
		
	}
	
	public void deleteShop(String shop_id) throws SQLException {
		
		delete_shop_CS.setString(1, shop_id);
		delete_shop_CS.executeUpdate();
		return;
	}
	public void updateShop(String shop_id, String name, String address, String working_time, String closing_days) throws SQLException {
		
		update_shop_CS.setString(1, shop_id);
		update_shop_CS.setString(2, name);
		update_shop_CS.setString(3, address);
		update_shop_CS.setString(4, working_time);
		update_shop_CS.setString(5, closing_days);
		update_shop_CS.executeUpdate();
		return;
	}

	public ResultSet getAllShops() throws SQLException {
		
		ResultSet rs = print_all_shops_PS.executeQuery();
		return rs;
	}

	
}
