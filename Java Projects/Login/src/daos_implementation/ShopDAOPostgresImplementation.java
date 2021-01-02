package daos_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import daos_interfaces.ShopDAO;
import entities.Shop;

public class ShopDAOPostgresImplementation implements ShopDAO{
	
	private Connection connection;
	private PreparedStatement print_all_shops_PS;
	private CallableStatement insert_shop_CS,delete_shop_CS, update_shop_CS;
	public ShopDAOPostgresImplementation(Connection connection)
	{
		this.connection = connection;
		try {
			insert_shop_CS = connection.prepareCall("CALL insertShop(?,?,?,?)");
			delete_shop_CS = connection.prepareCall("CALL deleteShop(?)");
			update_shop_CS = connection.prepareCall("CALL updateShop(?,?,?,?)");
			print_all_shops_PS = connection.prepareStatement("SELECT * FROM Shop");
		} catch (SQLException e) {
			System.out.println("Errore durante la preparazione degli statement "+e.getMessage());
		}
	}
	public void insertShop(Shop shop) throws SQLException {
		
		insert_shop_CS.setString(1, shop.getShop_name());
		insert_shop_CS.setString(2, shop.getAddress());
		insert_shop_CS.setString(3, shop.getWorking_hours());
		insert_shop_CS.setString(4, shop.getClosing_days());
		insert_shop_CS.executeUpdate();
		return;
		
	}
	
	public void deleteShop(String shop_id) throws SQLException {
		
		delete_shop_CS.setString(1, shop_id);
		delete_shop_CS.executeUpdate();
		return;
	}
	
	public void updateShop(Shop shop) throws SQLException {
		
		update_shop_CS.setString(1, shop.getShop_id());
		update_shop_CS.setString(2, shop.getShop_name());
		update_shop_CS.setString(3, shop.getAddress());
		update_shop_CS.setString(4, shop.getWorking_hours());
		update_shop_CS.setString(5, shop.getClosing_days());
		update_shop_CS.executeUpdate();
		return;
	}
	@Override
	public ResultSet getAllShops() throws SQLException {
		
		ResultSet rs = print_all_shops_PS.executeQuery();
		return rs;
	}
	
}
