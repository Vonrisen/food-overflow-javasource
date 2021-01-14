package daos_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import daos_interfaces.CustomerOrderDAO;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import db_connection.DBconnection;
import entities.CustomerOrder;
import entities.Rider;
import entities.Shop;

public class ShopDAOPostgresImplementation implements ShopDAO{

	private Connection connection;
	PreparedStatement get_all_shops_PS, get_orders_of_a_shop_PS, get_shop_by_id_PS, get_shops_of_a_rider_PS, get_shop_of_the_order_PS;
	public ShopDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione");
		}
		try {
			
			get_all_shops_PS = connection.prepareStatement("SELECT * FROM Shop ORDER BY shop_id ");
		    get_orders_of_a_shop_PS = connection.prepareStatement("SELECT * FROM CustomerOrder WHERE shop_id=? ");
		    get_shop_by_id_PS = connection.prepareStatement("SELECT * FROM Shop WHERE shop_id=? ");
		    get_shops_of_a_rider_PS = connection.prepareStatement("SELECT * FROM Shop WHERE shop_id IN " + 
		    													"(SELECT shop_id FROM CONTRACT WHERE rider_cf=?) ");
		    get_shop_of_the_order_PS = connection.prepareStatement("SELECT * FROM Shop WHERE shop_id IN (SELECT shop_id FROM CustomerOrder WHERE order_id=?) ");
			
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione");
		}
		
	}

	@Override
	public List<Shop> getAllShops() throws SQLException {
		List<Shop>shop_list = new ArrayList<Shop>();
		List<Rider>riders_of_a_shop = new ArrayList<Rider>();
		List<CustomerOrder>orders_of_a_shop = new ArrayList<CustomerOrder>();
		CustomerOrderDAO customer_order_DAO = new CustomerOrderDAOPostgresImplementation();
		RiderDAO rider_DAO = new RiderDAOPostgresImplementation();
		ResultSet rs = get_all_shops_PS.executeQuery();
		while(rs.next())
		{
			
			orders_of_a_shop = customer_order_DAO.getCustomerOrdersOfAShop(rs.getString("shop_id"));
			riders_of_a_shop = rider_DAO.getRidersOfAShop(rs.getString("shop_id"));
			Shop shop = new Shop(rs.getString("shop_id"),rs.getString("name"),rs.getString("address"),rs.getString("working_hours"),
								 rs.getString("closing_days"),rs.getString("password"),orders_of_a_shop, riders_of_a_shop);
			shop_list.add(shop);
			
		}
		connection.close();
		return shop_list;
	}

	@Override
	public int insertShop(Shop shop) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Shop shop) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateShop(Shop shop) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Shop getShopById(String shop_id) throws SQLException {
		
		List<Rider>riders_of_a_shop = new ArrayList<Rider>();
		List<CustomerOrder>orders_of_a_shop = new ArrayList<CustomerOrder>();
		CustomerOrderDAO customer_order_DAO = new CustomerOrderDAOPostgresImplementation();
		RiderDAO rider_DAO = new RiderDAOPostgresImplementation();
		get_shop_by_id_PS.setString(1,shop_id);
		ResultSet rs = get_shop_by_id_PS.executeQuery();
		Shop shop = null;
		while(rs.next()) {
			
			orders_of_a_shop = customer_order_DAO.getCustomerOrdersOfAShop(rs.getString("shop_id"));
			riders_of_a_shop = rider_DAO.getRidersOfAShop(rs.getString("shop_id"));
			shop = new Shop(rs.getString("shop_id"),rs.getString("name"),rs.getString("address"),rs.getString("working_hours"),
					 		rs.getString("closing_days"),rs.getString("password"),orders_of_a_shop, riders_of_a_shop);
			
		}
		rs.close();
		connection.close();
		return shop;
	}

	@Override
	public List<Shop> getShopsOfARider(String cf) throws SQLException {
		
		List<Rider>riders_of_a_shop = new ArrayList<Rider>();
		List<CustomerOrder>orders_of_a_shop = new ArrayList<CustomerOrder>();
		CustomerOrderDAO customer_order_DAO = new CustomerOrderDAOPostgresImplementation();
		RiderDAO rider_DAO = new RiderDAOPostgresImplementation();
		get_shops_of_a_rider_PS.setString(1, cf);
		ResultSet rs = get_shops_of_a_rider_PS.executeQuery();
		List<Shop> shops = new ArrayList<Shop>();
		
		while(rs.next()) {
			
			orders_of_a_shop = customer_order_DAO.getCustomerOrdersOfAShop(rs.getString("shop_id"));
			riders_of_a_shop = rider_DAO.getRidersOfAShop(rs.getString("shop_id"));
			Shop shop = new Shop(rs.getString("shop_id"),rs.getString("name"),rs.getString("address"),rs.getString("working_hours"),
			 		rs.getString("closing_days"),rs.getString("password"),orders_of_a_shop, riders_of_a_shop);
			
			shops.add(shop);
			
		}
		rs.close();
		connection.close();
		return shops;
	}

	@Override
	public Shop getShopOfTheOrder(String order_id) throws SQLException {
		
		List<Rider>riders_of_a_shop = new ArrayList<Rider>();
		List<CustomerOrder>orders_of_a_shop = new ArrayList<CustomerOrder>();
		CustomerOrderDAO customer_order_DAO = new CustomerOrderDAOPostgresImplementation();
		RiderDAO rider_DAO = new RiderDAOPostgresImplementation();
		get_shop_of_the_order_PS.setString(1,order_id);
		ResultSet rs =  get_shop_of_the_order_PS.executeQuery();
		Shop shop = null;
		 
		while(rs.next())
		{
			orders_of_a_shop = customer_order_DAO.getCustomerOrdersOfAShop(rs.getString("shop_id"));
			riders_of_a_shop = rider_DAO.getRidersOfAShop(rs.getString("shop_id"));
			shop = new Shop(rs.getString("shop_id"),rs.getString("name"),rs.getString("address"),rs.getString("working_hours"),
				 				  rs.getString("closing_days"),rs.getString("password"),orders_of_a_shop, riders_of_a_shop);
		}
		rs.close();
		connection.close();
		return shop;
	}

}
