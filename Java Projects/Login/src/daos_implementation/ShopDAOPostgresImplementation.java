package daos_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daos_interfaces.ShopDAO;
import db_connection.DBconnection;
import entities.Customer;
import entities.CustomerOrder;
import entities.Rider;
import entities.Shop;

public class ShopDAOPostgresImplementation implements  ShopDAO{
	
	
	private Connection connection;
	private DBconnection instance;
	private PreparedStatement get_all_shops_PS, get_shop_by_id_PS, insert_shop_PS, delete_shop_PS;
	private CallableStatement update_shop_CS;
	
	public ShopDAOPostgresImplementation()
	{
		try {
			instance = DBconnection.getInstance();
			connection = instance.getConnection();
		} catch (SQLException e1) {
			System.out.println("Errore di connessione col database "+e1.getMessage());
		}
		try {
			insert_shop_PS = connection.prepareStatement("INSERT INTO Shop VALUES(?,?,?,?,?,?)");
			delete_shop_PS = connection.prepareCall("DELETE FROM Shop WHERE shop_id=?");
			update_shop_CS = connection.prepareCall("CALL updateShop(?,?,?,?,?,?)");
			get_all_shops_PS = connection.prepareStatement("SELECT * FROM Shop ORDER BY shop_id");
			get_shop_by_id_PS = connection.prepareStatement("SELECT * FROM Shop WHERE shop_id =?");
		} catch (SQLException e) {
			System.out.println("Errore durante la preparazione degli statement "+e.getMessage());
		}
	}

	public ArrayList<Shop> getAllShops() throws SQLException {
		ResultSet rs = get_all_shops_PS.executeQuery();
		ArrayList<Shop>shops = new ArrayList<Shop>();
		ArrayList<CustomerOrder>customer_orders = new ArrayList<CustomerOrder>();
		ArrayList<Rider>riders = new ArrayList<Rider>();
		while(rs.next())
		{
			customer_orders = customer_orders_dao.getCustomerOrdersOfAShop(rs.getString("shop_id"));
			riders = riders_dao.getRidersOfAShop(rs.getString("shop_id"));
			Shop shop = new Shop(rs.getString("shop_id"),rs.getString("shop_name"),rs.getString("address"),rs.getString("working_hours"),rs.getString("closing_days"), rs.getString("shop_password"),
								 customer_orders,riders);
			shops.add(shop);
		}
		return shops;
	}


	public void insertShop(Shop s) throws SQLException {
		
		//Prendere il nextval della sequenza dello shop_id
		insert_shop_PS.setString(1, s.getShop_id());
		insert_shop_PS.setString(2, s.getShop_name());
		insert_shop_PS.setString(3, s.getAddress());
		insert_shop_PS.setString(4, s.getWorking_hours());
		insert_shop_PS.setString(5, s.getClosing_days());
		insert_shop_PS.setString(6, s.getPassword());
		insert_shop_PS.executeUpdate();
		return;
		
	}


	public void delete(Shop shop) throws SQLException {
		
		delete_shop_PS.setString(1, shop.getShop_id());
		delete_shop_PS.executeUpdate();
		return;
	}


	public Shop getShopById(String shop_id) throws SQLException {
		
		ArrayList<CustomerOrder>customer_orders = new ArrayList<CustomerOrder>();
		ArrayList<Rider>riders = new ArrayList<Rider>();
		get_shop_by_id_PS.setString(1, shop_id);
		ResultSet rs = get_shop_by_id_PS.executeQuery();
		customer_orders = getCustomerOrdersOfAShop(rs.getString("shop_id"));
		riders = getRidersOfAShop(rs.getString("shop_id"));
		Shop shop = new Shop(rs.getString("shop_id"),rs.getString("shop_name"),rs.getString("address"),rs.getString("working_hours"),rs.getString("closing_days"),rs.getString("shop_password"),customer_orders,riders);
		return shop;
		
	}
	
	public void updateShop(Shop s) throws SQLException {
		
		update_shop_CS.setString(1, s.getShop_id());
		update_shop_CS.setString(2, s.getShop_name());
		update_shop_CS.setString(3, s.getAddress());
		update_shop_CS.setString(4, s.getWorking_hours());
		update_shop_CS.setString(5, s.getClosing_days());
		update_shop_CS.setString(6, s.getPassword());
		update_shop_CS.executeUpdate();
		return;
		
	}
	
}
