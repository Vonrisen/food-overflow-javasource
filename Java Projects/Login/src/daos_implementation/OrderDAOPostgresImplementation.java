package daos_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import daos_interfaces.OrderDAO;
import db_connection.DBconnection;
import entities.Address;
import entities.Customer;
import entities.Order;
import entities.Rider;
import entities.Shop;
import exceptions.DaoException;
import utilities.DButility;
import utilities.InputUtility;

public class OrderDAOPostgresImplementation implements OrderDAO {
	
	private Connection connection;
	private PreparedStatement update_delivering_order_PS, update_pending_order_PS, get_orders_of_a_shop_by_shop_email_PS, get_customer_of_the_order_PS, 
							  get_rider_of_the_order_PS, get_shop_of_the_order_PS, get_delivering_orders_of_a_shop_PS, get_pending_orders_of_a_shop_PS, get_customer_email_PS;
	DButility db_util = new DButility();
	public OrderDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore di connessione");
		}
		try {
			
			update_delivering_order_PS = connection.prepareStatement("UPDATE Customerorder SET delivery_time = current_time, status=? WHERE id=?");
			update_pending_order_PS = connection.prepareStatement("UPDATE Customerorder SET status = 'In consegna', rider_cf=? WHERE id=?");
			get_pending_orders_of_a_shop_PS = connection.prepareStatement("SELECT * FROM CustomerOrder WHERE status LIKE 'In attesa' and shop_id IN (SELECT id FROM Shop WHERE email=?)");
			get_delivering_orders_of_a_shop_PS = connection.prepareStatement("SELECT * FROM CustomerOrder WHERE status LIKE 'In consegna' and shop_id IN (SELECT id FROM Shop WHERE email=?)");
			get_shop_of_the_order_PS = connection.prepareStatement("SELECT * FROM Shop Where email =?");
			get_customer_of_the_order_PS = connection.prepareStatement("SELECT * FROM Customer WHERE email =?");
			get_rider_of_the_order_PS = connection.prepareStatement("SELECT * FROM Rider WHERE cf =?");
			get_orders_of_a_shop_by_shop_email_PS = connection.prepareStatement("SELECT * FROM CustomerOrder WHERE delivery_time is not null and shop_id IN (SELECT id FROM Shop WHERE email=?)");
			get_customer_email_PS = connection.prepareStatement("SELECT email FROM Customer WHERE id=?");
			
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statements");
		}
	}
	public Rider getRiderOfTheOrderByCF(String cf) throws DaoException{
		
		ResultSet rs = null;
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		Rider rider = null;
		try
		{
		get_rider_of_the_order_PS.setString(1, cf);
		rs = get_rider_of_the_order_PS.executeQuery();
		while(rs.next()) {
			address_fields = string_util.tokenizedStringToList(rs.getString("address"),"(, )");
			rider = new Rider(rs.getString("cf"),rs.getString("name"),rs.getString("surname"), new Date(rs.getDate("birth_date").getTime()), 
					rs.getString("birth_place"),rs.getString("gender"), rs.getString("cellphone"), new Address(address_fields.get(0),
						address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)), rs.getString("vehicle"),
					rs.getString("working_hours"),rs.getShort("deliveries_number"));
		}}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs);
		}
		return rider;
	}
	
	public Shop getShopOfTheOrderByEmail(String email) throws DaoException{
		
		InputUtility string_util = new InputUtility();
		List<String>address_fields = new ArrayList<String>();
		Shop shop = null;
		ResultSet rs = null;
		try
		{
		get_shop_of_the_order_PS.setString(1, email);
	    rs = get_shop_of_the_order_PS.executeQuery();
		while(rs.next()) {
			address_fields = string_util.tokenizedStringToList(rs.getString("address"),"(, )");
			shop = new Shop(rs.getString("email"),rs.getString("name"), rs.getString("password"), rs.getString("working_hours"),
				          new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
				          rs.getString("closing_days"), null, null, rs.getString("home_phone"));
		}
		}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs);
		}
		return shop;
	}
	public List<Order> getOrdersOfAShopByShopEmail(String shop_email) throws DaoException{
		
		ResultSet rs1 = null;
		ResultSet rs = null;
		Customer customer = null;
		Rider rider = null;
		Shop shop = null;
		List<Order> order_list = new ArrayList<Order>();
		String customer_email = null;
		InputUtility string_util = new InputUtility();
		List<String>address_fields = new ArrayList<String>();
		try
		{
		get_orders_of_a_shop_by_shop_email_PS.setString(1, shop_email);
		rs1 = get_orders_of_a_shop_by_shop_email_PS.executeQuery();
		while(rs1.next()) {
			get_customer_email_PS.setString(1,rs1.getString("customer_id"));
			rs = get_customer_email_PS.executeQuery();
			while(rs.next())
			customer_email = rs.getString("email");
			customer = getCustomerOfTheOrderByEmail(customer_email);
			rider = getRiderOfTheOrderByCF(rs1.getString("rider_cf"));
			shop = getShopOfTheOrderByEmail(shop_email);
			address_fields = string_util.tokenizedStringToList(rs1.getString("address"),"(, )");
			order_list.add(new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()), rs1.getString("payment"), 
					rs1.getString("status"), new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), 
					address_fields.get(4)), rs1.getTime("delivery_time"), rs1.getString("note"), rider));
		}}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs, get_customer_email_PS);
			db_util.releaseResources(rs1, get_orders_of_a_shop_by_shop_email_PS);
			db_util.releaseResources(get_rider_of_the_order_PS);
			db_util.releaseResources(get_customer_of_the_order_PS);
			db_util.releaseResources(get_shop_of_the_order_PS);
		}
		return order_list;
	}
	
	public List<Order> getInDeliveryOrdersOfAShop(String shop_email) throws DaoException{
		
		ResultSet rs = null;
		ResultSet rs1 = null;
		Customer customer = null;
		Rider rider = null;
		Shop shop = null;
		List<Order> order_list = new ArrayList<Order>();
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		String customer_email = null;
		try {
			get_delivering_orders_of_a_shop_PS.setString(1, shop_email);
			rs1 = get_delivering_orders_of_a_shop_PS.executeQuery();
			while(rs1.next()) {
				get_customer_email_PS.setString(1,rs1.getString("customer_id"));
				rs = get_customer_email_PS.executeQuery();
					while(rs.next())
						customer_email = rs.getString("email");
				customer = getCustomerOfTheOrderByEmail(customer_email);
				rider = getRiderOfTheOrderByCF(rs1.getString("rider_cf"));
				shop = getShopOfTheOrderByEmail(shop_email);
				address_fields = string_util.tokenizedStringToList(rs1.getString("address"),"(, )");
				order_list.add(new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()), rs1.getString("payment"), 
								rs1.getString("status"), new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), 
								address_fields.get(4)), null, rs1.getString("note"), rider));
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs, get_customer_email_PS);
			db_util.releaseResources(rs1, get_delivering_orders_of_a_shop_PS);
			db_util.releaseResources(get_rider_of_the_order_PS);
			db_util.releaseResources(get_customer_of_the_order_PS);
			db_util.releaseResources(get_shop_of_the_order_PS);
		}
		return order_list;
	}
	
	public Customer getCustomerOfTheOrderByEmail(String email) throws DaoException{
		
		InputUtility string_util = new InputUtility();
		List<String>address_fields = new ArrayList<String>();
		Customer customer = null;
		ResultSet rs = null;
		try
		{
		get_customer_of_the_order_PS.setString(1, email);
		rs = get_customer_of_the_order_PS.executeQuery();
		while(rs.next()) {
			address_fields = string_util.tokenizedStringToList(rs.getString("address"),"(, )");
			customer = new Customer(rs.getString("cf"),rs.getString("name"),rs.getString("surname"),new Date(rs.getDate("birth_date").getTime()),
									rs.getString("birth_place"), rs.getString("gender"),rs.getString("cellphone"),  new Address(address_fields.get(0),address_fields.get(1), 
									address_fields.get(2), address_fields.get(3), address_fields.get(4)), rs.getString("email"), rs.getString("password"));
		}}catch(SQLException s)
		{
			throw new DaoException();
		
		}
		finally
		{
		}
		return customer;
	}
	public List<Order> getPendingOrdersOfAShop(String shop_email) throws DaoException{
		
		ResultSet rs = null;
		ResultSet rs1 = null;
		Customer customer = null;
		Rider rider = null;
		Shop shop = null;
		List<Order> order_list = new ArrayList<Order>();
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		String customer_email = null;
		try {
			get_pending_orders_of_a_shop_PS.setString(1, shop_email);
			rs1 = get_pending_orders_of_a_shop_PS.executeQuery();
			while(rs1.next()) {
				get_customer_email_PS.setString(1,rs1.getString("customer_id"));
				rs = get_customer_email_PS.executeQuery();
					while(rs.next())
						customer_email = rs.getString("email");
				customer = getCustomerOfTheOrderByEmail(customer_email);
				rider = getRiderOfTheOrderByCF(rs1.getString("rider_cf"));
				shop = getShopOfTheOrderByEmail(shop_email);
				address_fields = string_util.tokenizedStringToList(rs1.getString("address"),"(, )");
				order_list.add(new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()), rs1.getString("payment"), 
								rs1.getString("status"), new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), 
								address_fields.get(4)), null, rs1.getString("note"), rider));
		} }catch (SQLException e) {
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs, get_customer_email_PS);
			db_util.releaseResources(rs1, get_pending_orders_of_a_shop_PS);
			db_util.releaseResources(get_rider_of_the_order_PS);
			db_util.releaseResources(get_customer_of_the_order_PS);
			db_util.releaseResources(get_shop_of_the_order_PS);
		}
		return order_list;
	}

	public void updatePendingOrder(Order order, String rider_cf) throws DaoException {
		try
		{
			update_pending_order_PS.setString(1, rider_cf);
			update_pending_order_PS.setString(2, order.getId());
			update_pending_order_PS.executeUpdate();
		}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(update_pending_order_PS);
		}
		return;
	}
	
	public void updateDeliveringOrder(Order order, String stato) throws DaoException{
		try {
			update_delivering_order_PS.setString(1, stato);
			update_delivering_order_PS.setString(2, order.getId());
			update_delivering_order_PS.executeUpdate();
		}catch(SQLException s)
		{
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(update_delivering_order_PS);
		}
		return;
	}
}
