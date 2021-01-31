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
	private PreparedStatement get_orders_of_a_shop_by_shop_email_PS, get_customer_of_the_order_PS, get_rider_of_the_order_PS, get_shop_of_the_order_PS;
	DButility db_util = new DButility();
	public OrderDAOPostgresImplementation() {
		
		try {
			DBconnection instance = DBconnection.getInstance();
			connection = instance.getConnection();
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Network error, please try again","Error",JOptionPane.ERROR_MESSAGE);
		}
		try {
			get_shop_of_the_order_PS = connection.prepareStatement("SELECT * FROM Shop Where email = ?");
			get_customer_of_the_order_PS = connection.prepareStatement("SELECT * FROM Customer WHERE id = ?");
			get_rider_of_the_order_PS = connection.prepareStatement("SELECT * FROM Rider WHERE cf = ?");
			get_orders_of_a_shop_by_shop_email_PS = connection.prepareStatement("SELECT * FROM CustomerOrder WHERE shop_id = (SELECT id FROM Shop WHERE email=?)");
		}catch(SQLException s)
		{
			JOptionPane.showMessageDialog(null, "Generic error, please contact your administrator","Error",JOptionPane.ERROR_MESSAGE);
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
			get_customer_of_the_order_PS.setString(1, rs1.getString("customer_id"));
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
			order_list.add(new Order(shop, customer, new Date(rs1.getDate("date").getTime()), rs1.getString("payment"), 
							rs1.getString("status"), new Address(address_fields.get(0),address_fields.get(1), address_fields.get(2), address_fields.get(3), 
							address_fields.get(4)), rs1.getTime("delivery_time"), rs1.getString("note"), rider));
		}}catch(SQLException s)
		{
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

}
