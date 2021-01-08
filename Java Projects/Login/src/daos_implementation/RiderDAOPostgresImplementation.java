package daos_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import daos_interfaces.RiderDAO;
import db_connection.DBconnection;
import db_connection.DBconnection_CodiceCatastale;
import entities.CustomerOrder;
import entities.Rider;
import entities.Shop;

public class RiderDAOPostgresImplementation implements RiderDAO{
	
	private Connection connection;
	private DBconnection instance;
	private PreparedStatement get_all_riders_PS, insert_rider_PS, delete_rider_PS, insert_rider_into_Shop_PS;
	public RiderDAOPostgresImplementation ()
	{
		try {
			instance = DBconnection.getInstance();
			connection = instance.getConnection();
		} catch (SQLException e1) {
			System.out.println("Errore di connessione col database "+e1.getMessage());
		}
		try {
			get_all_riders_PS = connection.prepareStatement("SELECT cf, rider_name, surname, address, to_char(birth_date, 'DD/MM/YYYY') AS birth_date, birth_place,"
					+ " gender, cellphone, vehicle, working_time,deliveries_number FROM Rider");
			insert_rider_PS = connection.prepareStatement("CALL insertRider(?,?,?,?,?,?,?,?,?,?,?)");
			delete_rider_PS = connection.prepareCall("DELETE deleteRider(?)");
			insert_rider_into_Shop_PS = connection.prepareStatement("INSERT INTO Contract VALUES (?,?)");
		} catch (SQLException e) {
			System.out.println("Errore durante la preparazione degli statement "+e.getMessage());
		}
		
	}
	
	
	@Override
	public ArrayList<Rider> getAllRiders() throws SQLException {
		
		ResultSet rs = get_all_riders_PS.executeQuery();
		ArrayList<Rider>riders = new ArrayList<Rider>();
		ArrayList<Shop>shops = new ArrayList<Shop>();
		ArrayList<CustomerOrder>customer_orders = new ArrayList<CustomerOrder>();
		while(rs.next())
		{
			customer_orders = customer_orders_DAO.getCustomerOrdersInDeliveryByRider(rs.getString("shop_id"));
			shops = shops_DAO.getShopsOfARider(rs.getString("cf"));
			Rider rider = new Rider(rs.getString("cf"),rs.getString("rider_name"),rs.getString("surname"),rs.getString("address"), rs.getString("birth_date"), rs.getString("birth_place"),
									rs.getString("gender"),rs.getString("cellphone"),rs.getString("vehicle"),rs.getString("working_time"),rs.getString("deliveries_number"),shops,customer_orders);
			riders.add(rider);
			
		}
		return riders;
		
	}

	@Override
	public int insertRider(Rider rider) throws SQLException {
		
		String shop_id_list ="";
		insert_rider_PS.setString(1,rider.getCf());
		insert_rider_PS.setString(2,rider.getName());
		insert_rider_PS.setString(3,rider.getSurname());
		insert_rider_PS.setString(4,rider.getCf());
		insert_rider_PS.setString(5, rider.getAddress());
		insert_rider_PS.setString(6,rider.getBirth_date());
		insert_rider_PS.setString(7,rider.getBirth_place());
		insert_rider_PS.setString(8,rider.getGender());
		insert_rider_PS.setString(9,rider.getCellphone());
		insert_rider_PS.setString(10,rider.getVehicle());
		insert_rider_PS.setString(11,rider.getWorking_time());
		insert_rider_PS.setString(12,rider.getDeliveries_number());
		insertRiderIntoShop(rider);
		return 1;
	}
	
	public int insertRiderIntoShop(Rider rider) throws SQLException{
		
		int insert_succeded=0;
		for(Shop shop : rider.getShop_list())
		{
			insert_rider_into_Shop_PS.setString(1, rider.getCf());
			insert_rider_into_Shop_PS.setString(2, shop.getShop_id());
			insert_rider_into_Shop_PS.executeUpdate();
			if(insert_rider_into_Shop_PS.executeUpdate()<=0)
				insert_succeded=0;
		}
		if(insert_succeded<=0)
		 return 0;
		else
		 return 1;
	}

	@Override
	public void deleteRider(Rider rider) throws SQLException {
		
		delete_rider_PS;

		
	}

	@Override
	public void updateRider(Rider rider) throws SQLException {
	
		
	}

	@Override
	public Rider getRiderById(String rider_id) throws SQLException {
	
		return null;
	}

	@Override
	public ArrayList<Rider> getRidersOfAShop(Shop shop) throws SQLException {

		return null;
	}
	
	
}
