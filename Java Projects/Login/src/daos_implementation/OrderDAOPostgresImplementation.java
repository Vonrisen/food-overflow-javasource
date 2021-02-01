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
	private PreparedStatement update_delivering_order_PS, update_pending_order_PS, get_orders_of_a_shop_by_shop_email_PS, get_customer_of_the_order_PS, get_rider_of_the_order_PS, get_shop_of_the_order_PS, get_delivering_orders_of_a_shop_PS, get_pending_orders_of_a_shop_PS;
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
			get_shop_of_the_order_PS = connection.prepareStatement("SELECT * FROM Shop Where email = ?");
			get_customer_of_the_order_PS = connection.prepareStatement("SELECT * FROM Customer WHERE cf = ?");
			get_rider_of_the_order_PS = connection.prepareStatement("SELECT * FROM Rider WHERE cf = ?");
			get_orders_of_a_shop_by_shop_email_PS = connection.prepareStatement("SELECT * FROM CustomerOrder WHERE delivery_time is not null and shop_id IN (SELECT id FROM Shop WHERE email=?)");
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statements");
		}
	}
	
	public List<Order> getOrdersOfAShopByShopEmail(String shop_email) throws DaoException{
		
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		Customer customer = null;
		Rider rider = null;
		Shop shop = null;
		List<Order> order_list = new ArrayList<Order>();
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		try
		{
		get_orders_of_a_shop_by_shop_email_PS.setString(1, shop_email);
		rs1 = get_orders_of_a_shop_by_shop_email_PS.executeQuery();
		while(rs1.next()) {
			get_customer_of_the_order_PS.setString(1, rs1.getString("customer_cf"));
			rs2 = get_customer_of_the_order_PS.executeQuery();
			while(rs2.next()) {
				address_fields = string_util.tokenizedStringToList(rs2.getString("address"),"(, )");
				customer = new Customer(rs2.getString("cf"),rs2.getString("name"),rs2.getString("surname"),new Date(rs2.getDate("birth_date").getTime()),
							rs2.getString("birth_place"), rs2.getString("gender"),rs2.getString("cellphone"),  new Address(address_fields.get(0),address_fields.get(1), 
							address_fields.get(2), address_fields.get(3), address_fields.get(4)), rs2.getString("email"), rs2.getString("password"));

			}
			
			get_rider_of_the_order_PS.setString(1, rs1.getString("rider_cf"));
			rs3 = get_rider_of_the_order_PS.executeQuery();
			while(rs3.next()) {
				address_fields = string_util.tokenizedStringToList(rs3.getString("address"),"(, )");
				rider = new Rider(rs3.getString("cf"),rs3.getString("name"),rs3.getString("surname"), new Date(rs3.getDate("birth_date").getTime()), 
							rs3.getString("birth_place"),rs3.getString("gender"), rs3.getString("cellphone"), new Address(address_fields.get(0),
							address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)), rs3.getString("vehicle"),
							rs3.getString("working_hours"),rs3.getShort("deliveries_number"));

			}
			
			get_shop_of_the_order_PS.setString(1, shop_email);
			rs4 = get_shop_of_the_order_PS.executeQuery();
			while(rs4.next()) {
				address_fields = string_util.tokenizedStringToList(rs4.getString("address"),"(, )");
				shop = new Shop(rs4.getString("email"),rs4.getString("name"), rs4.getString("password"), rs4.getString("working_hours"),
					          new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
					          rs4.getString("closing_days"), null, null);
			}
			address_fields = string_util.tokenizedStringToList(rs1.getString("address"),"(, )");
			order_list.add(new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()), rs1.getString("payment"), 
							rs1.getString("status"), new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), 
							address_fields.get(4)), rs1.getTime("delivery_time"), rs1.getString("note"), rider));
			
		}}catch(SQLException s)
		{
			System.out.println(s.getMessage());
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs1, get_orders_of_a_shop_by_shop_email_PS);
			db_util.releaseResources(rs2, get_customer_of_the_order_PS);
			db_util.releaseResources(rs3, get_rider_of_the_order_PS);
			db_util.releaseResources(rs4, get_rider_of_the_order_PS);
		}
		return order_list;
	}
	
	public List<Order> getDeliveringOrdersOfAShop(String shop_email) throws DaoException{
		
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		Customer customer = null;
		Rider rider = null;
		Shop shop = null;
		List<Order> order_list = new ArrayList<Order>();
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		
		try {
			get_delivering_orders_of_a_shop_PS.setString(1, shop_email);
			rs1 = get_delivering_orders_of_a_shop_PS.executeQuery();
			
			while(rs1.next()) {
				get_customer_of_the_order_PS.setString(1, rs1.getString("customer_cf"));
				rs2 = get_customer_of_the_order_PS.executeQuery();
				while(rs2.next()) {
					address_fields = string_util.tokenizedStringToList(rs2.getString("address"),"(, )");
					customer = new Customer(rs2.getString("cf"),rs2.getString("name"),rs2.getString("surname"),new Date(rs2.getDate("birth_date").getTime()),
								rs2.getString("birth_place"), rs2.getString("gender"),rs2.getString("cellphone"),  new Address(address_fields.get(0),address_fields.get(1), 
								address_fields.get(2), address_fields.get(3), address_fields.get(4)), rs2.getString("email"), rs2.getString("password"));

				}
				
				get_rider_of_the_order_PS.setString(1, rs1.getString("rider_cf"));
				rs3 = get_rider_of_the_order_PS.executeQuery();
				while(rs3.next()) {
					address_fields = string_util.tokenizedStringToList(rs3.getString("address"),"(, )");
					rider = new Rider(rs3.getString("cf"),rs3.getString("name"),rs3.getString("surname"), new Date(rs3.getDate("birth_date").getTime()), 
								rs3.getString("birth_place"),rs3.getString("gender"), rs3.getString("cellphone"), new Address(address_fields.get(0),
								address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)), rs3.getString("vehicle"),
								rs3.getString("working_hours"),rs3.getShort("deliveries_number"));

				}
				
				get_shop_of_the_order_PS.setString(1, shop_email);
				rs4 = get_shop_of_the_order_PS.executeQuery();
				while(rs4.next()) {
					address_fields = string_util.tokenizedStringToList(rs4.getString("address"),"(, )");
					shop = new Shop(rs4.getString("email"),rs4.getString("name"), rs4.getString("password"), rs4.getString("working_hours"),
						          new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
						          rs4.getString("closing_days"), null, null);
				}
				address_fields = string_util.tokenizedStringToList(rs1.getString("address"),"(, )");
				order_list.add(new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()), rs1.getString("payment"), 
								rs1.getString("status"), new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), 
								address_fields.get(4)), rs1.getTime("delivery_time"), rs1.getString("note"), rider));
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs1, get_orders_of_a_shop_by_shop_email_PS);
			db_util.releaseResources(rs2, get_customer_of_the_order_PS);
			db_util.releaseResources(rs3, get_rider_of_the_order_PS);
			db_util.releaseResources(rs4, get_rider_of_the_order_PS);
		}
		
		return order_list;
	}
	
	public List<Order> getPendingOrdersOfAShop(String shop_email) throws DaoException{
		
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		Customer customer = null;
		Shop shop = null;
		List<Order> order_list = new ArrayList<Order>();
		List<String>address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		
		
		try {
			get_pending_orders_of_a_shop_PS.setString(1, shop_email);
			rs1 = get_pending_orders_of_a_shop_PS.executeQuery();
			
			
			while(rs1.next()) {
				get_customer_of_the_order_PS.setString(1, rs1.getString("customer_cf"));
				rs2 = get_customer_of_the_order_PS.executeQuery();
				while(rs2.next()) {
					address_fields = string_util.tokenizedStringToList(rs2.getString("address"),"(, )");
					customer = new Customer(rs2.getString("cf"),rs2.getString("name"),rs2.getString("surname"),new Date(rs2.getDate("birth_date").getTime()),
								rs2.getString("birth_place"), rs2.getString("gender"),rs2.getString("cellphone"),  new Address(address_fields.get(0),address_fields.get(1), 
								address_fields.get(2), address_fields.get(3), address_fields.get(4)), rs2.getString("email"), rs2.getString("password"));

				}
				
				get_shop_of_the_order_PS.setString(1, shop_email);
				rs3 = get_shop_of_the_order_PS.executeQuery();
				while(rs3.next()) {
					address_fields = string_util.tokenizedStringToList(rs3.getString("address"),"(, )");
					shop = new Shop(rs3.getString("email"),rs3.getString("name"), rs3.getString("password"), rs3.getString("working_hours"),
						          new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), address_fields.get(4)),
						          rs3.getString("closing_days"), null, null);
				}
				address_fields = string_util.tokenizedStringToList(rs1.getString("address"),"(, )");
				order_list.add(new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()), rs1.getString("payment"), 
								rs1.getString("status"), new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), 
								address_fields.get(4)), null, rs1.getString("note"), null));
			}
		} catch (SQLException e) {
			throw new DaoException();
		}
		finally
		{
			db_util.releaseResources(rs1, get_orders_of_a_shop_by_shop_email_PS);
			db_util.releaseResources(rs2, get_customer_of_the_order_PS);
			db_util.releaseResources(rs3, get_rider_of_the_order_PS);
			db_util.releaseResources(rs4, get_rider_of_the_order_PS);
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
