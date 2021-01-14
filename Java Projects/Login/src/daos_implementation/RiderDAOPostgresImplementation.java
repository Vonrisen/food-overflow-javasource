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

public class RiderDAOPostgresImplementation implements RiderDAO {
	
	private Connection connection;
	PreparedStatement get_riders_of_a_shop_PS, get_rider_by_cf_PS, get_rider_of_the_order_PS;
	public RiderDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione");
		}
		try {
			
			   get_riders_of_a_shop_PS = connection.prepareStatement("SELECT R.cf, R.name, R.surname, R.address, R.birth_date, R.birth_place, R.gender, R.cellphone, R.vehicle, R.working_time, R.deliveries_number "
					  + "FROM Contract AS C JOIN Rider AS R "
					  + "ON C.rider_cf=R.cf "
					  + "WHERE shop_id=?");
			   get_rider_by_cf_PS = connection.prepareStatement("SELECT * FROM Rider WHERE cf=?");
			   get_rider_of_the_order_PS = connection.prepareStatement("SELECT * FROM Rider WHERE cf IN (SELECT rider_cf from CustomerOrder WHERE order_id='12345678')");
		
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statements");
		}
		

}

	@Override
	public List<Rider> getAllRiders() throws SQLException {
	
		return null;
	}

	@Override
	public int insertRider(Rider rider) throws SQLException {
	
		return 0;
	}

	@Override
	public int deleteRider(Rider rider) throws SQLException {
	
		return 0;
	}

	@Override
	public int updateRider(Rider rider) throws SQLException {
	
		return 0;
	}

	@Override
	public List<Rider> getRidersOfAShop(String shop_id) throws SQLException {

		List<Rider> riders = new ArrayList<Rider>();
		ShopDAO shop_DAO = new ShopDAOPostgresImplementation();
		CustomerOrderDAO customer_order_DAO = new CustomerOrderDAOPostgresImplementation();
		get_riders_of_a_shop_PS.setString(1, shop_id);
		ResultSet rs = get_riders_of_a_shop_PS.executeQuery();
		while(rs.next())
		{
			List<Shop>shop_list = shop_DAO.getShopsOfARider(rs.getString("cf"));
			List<CustomerOrder> customer_order_list = customer_order_DAO.getDeliveriesOfARider(rs.getString("cf"));
			Rider rider = new Rider(rs.getString("cf"),rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("birth_date"),rs.getString("birth_place"),
									rs.getString("gender"),rs.getString("cellphone"),rs.getString("vehicle"),rs.getString("working_time"),rs.getString("deliveries_number"),
									shop_list, customer_order_list);
			riders.add(rider);
		}
		rs.close();
		connection.close();
		return riders;
	}

	@Override
	public Rider getRiderByCf(String cf) throws SQLException {
		
		get_rider_by_cf_PS.setString(1, cf);
		ShopDAO shop_DAO = new ShopDAOPostgresImplementation();
		CustomerOrderDAO customer_order_DAO = new CustomerOrderDAOPostgresImplementation();
		ResultSet rs = get_rider_by_cf_PS.executeQuery();
		Rider rider = null;
		//Provare a togliere il while
		while(rs.next())
		{
			List<Shop>shop_list = shop_DAO.getShopsOfARider(rs.getString("cf"));
//			List<CustomerOrder> customer_order_list = customer_order_DAO.getDeliveriesOfARider(rs.getString("cf"));
			rider = new Rider(rs.getString("cf"),rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("birth_date"),rs.getString("birth_place"),
					rs.getString("gender"),rs.getString("cellphone"),rs.getString("vehicle"),rs.getString("working_time"),rs.getString("deliveries_number"),
					shop_list, null);
		}
		rs.close();
		connection.close();
		return rider;
	}

	@Override
	public Rider getRiderOfTheOrder(String order_id) throws SQLException {
	
		get_rider_of_the_order_PS.setString(1, order_id);
		ShopDAO shop_DAO = new ShopDAOPostgresImplementation();
		CustomerOrderDAO customer_order_DAO = new CustomerOrderDAOPostgresImplementation();
		ResultSet rs =  get_rider_of_the_order_PS.executeQuery();
		Rider rider = null;
		while(rs.next())
		{
			
			List<Shop>shop_list = shop_DAO.getShopsOfARider(rs.getString("cf"));
			List<CustomerOrder> customer_order_list = customer_order_DAO.getDeliveriesOfARider(rs.getString("cf"));
			rider = new Rider(rs.getString("cf"),rs.getString("name"),rs.getString("surname"),rs.getString("address"),rs.getString("birth_date"),rs.getString("birth_place"),
					rs.getString("gender"),rs.getString("cellphone"),rs.getString("vehicle"),rs.getString("working_time"),rs.getString("deliveries_number"),
					shop_list, customer_order_list);
		}
		rs.close();
		connection.close();
		return rider;
	}
	
	

}
