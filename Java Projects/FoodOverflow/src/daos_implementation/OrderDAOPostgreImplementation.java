package daos_implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import daos_interfaces.OrderDAO;
import entities.Address;
import entities.Cart;
import entities.Customer;
import entities.Order;
import entities.OrderComposition;
import entities.Rider;
import entities.Shop;
import exceptions.DAOException;
import utilities.DBUtility;
import utilities.InputUtility;

public class OrderDAOPostgreImplementation implements OrderDAO {

	private PreparedStatement update_delivering_order_PS, link_rider_to_order_PS, get_orders_of_a_shop_by_shop_email_PS,
			get_customer_of_the_order_PS, get_rider_of_the_order_PS, get_shop_of_the_order_PS,
			get_delivering_orders_of_a_shop_PS, get_pending_orders_of_a_shop_PS, get_customer_email_PS,
			get_order_by_id_PS, do_complex_admin_search_PS, get_all_orders_PS;
	private CallableStatement create_order_CS;
	private DBUtility db_util = new DBUtility();
	public OrderDAOPostgreImplementation(Connection connection) {

		try {

			update_delivering_order_PS = connection
					.prepareStatement("UPDATE Customerorder SET delivery_time = current_time, status=? WHERE id=?");
			link_rider_to_order_PS = connection
					.prepareStatement("UPDATE Customerorder SET status = 'In consegna', rider_cf=? WHERE id=?");
			get_pending_orders_of_a_shop_PS = connection.prepareStatement(
					"SELECT * FROM CustomerOrder WHERE status LIKE 'In attesa' and shop_id IN (SELECT id FROM Shop WHERE email=?)");
			get_delivering_orders_of_a_shop_PS = connection.prepareStatement(
					"SELECT * FROM CustomerOrder WHERE status LIKE 'In consegna' and shop_id IN (SELECT id FROM Shop WHERE email=?)");
			get_shop_of_the_order_PS = connection.prepareStatement("SELECT * FROM Shop Where email =?");
			get_customer_of_the_order_PS = connection.prepareStatement("SELECT * FROM Customer WHERE email =?");
			get_rider_of_the_order_PS = connection.prepareStatement("SELECT * FROM Rider WHERE cf =?");
			get_orders_of_a_shop_by_shop_email_PS = connection.prepareStatement(
					"SELECT * FROM CustomerOrder WHERE delivery_time is not null and shop_id IN (SELECT id FROM Shop WHERE email=?)");
			get_order_by_id_PS = connection.prepareStatement("SELECT * FROM CustomerOrder WHERE id=?");
			get_customer_email_PS = connection.prepareStatement("SELECT email FROM Customer WHERE id=?");
			create_order_CS = connection.prepareCall("CALL CreateOrder(?,'Contrassegno',?,?,?,?,?)");
			do_complex_admin_search_PS = connection.prepareCall(
					"SELECT * FROM effettuaRicercaComplessaAdmin(?,?,?,?,?) AS t(id character(12),order_date timestamp, delivery_time time, address varchar(255), status varchar (20), "
							+ "payment varchar(12), note varchar(255), rider_cf character(16), shop_email varchar(320), customer_email varchar(320))");
			get_all_orders_PS = connection.prepareStatement("SELECT * FROM CustomerOrder");

		} catch (SQLException s) {
			JOptionPane.showMessageDialog(null, "Errore durante il prepare degli statements");
		}
	}
	public Rider getRiderOfTheOrderByCF(String cf) throws DAOException {

		ResultSet rs = null;
		List<String> address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		Rider rider = null;
		try {
			get_rider_of_the_order_PS.setString(1, cf);
			rs = get_rider_of_the_order_PS.executeQuery();
			while (rs.next()) {
				address_fields = string_util.tokenizedStringToList(rs.getString("address"), "(, )");
				rider = new Rider(rs.getString("cf"), rs.getString("name"), rs.getString("surname"),
						new Date(rs.getDate("birth_date").getTime()), rs.getString("birth_place"),
						rs.getString("gender"), rs.getString("cellphone"),
						new Address(address_fields.get(0), address_fields.get(1), address_fields.get(2),
								address_fields.get(3), address_fields.get(4)),
						rs.getString("vehicle"), rs.getString("working_hours"), rs.getShort("deliveries_number"));
			}
		} catch (SQLException s) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
		}
		return rider;
	}

	public Shop getShopOfTheOrderByEmail(String email) throws DAOException {

		InputUtility string_util = new InputUtility();
		List<String> address_fields = new ArrayList<String>();
		Shop shop = null;
		ResultSet rs = null;
		try {
			get_shop_of_the_order_PS.setString(1, email);
			rs = get_shop_of_the_order_PS.executeQuery();
			while (rs.next()) {
				address_fields = string_util.tokenizedStringToList(rs.getString("address"), "(, )");
				shop = new Shop(rs.getString("email"), rs.getString("name"), rs.getString("password"),
						rs.getString("working_hours"),
						new Address(address_fields.get(0), address_fields.get(1), address_fields.get(2),
								address_fields.get(3), address_fields.get(4)),
						rs.getString("closing_days"), null, null, rs.getString("home_phone"));
			}
		} catch (SQLException s) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
		}
		return shop;
	}

	public List<Order> getOrdersOfAShopByShopEmail(String shop_email) throws DAOException {

		ResultSet rs1 = null;
		ResultSet rs = null;
		List<Order> order_list = new ArrayList<Order>();
		String customer_email = null;
		InputUtility string_util = new InputUtility();
		try {
			get_orders_of_a_shop_by_shop_email_PS.setString(1, shop_email);
			rs1 = get_orders_of_a_shop_by_shop_email_PS.executeQuery();
			while (rs1.next()) {
				get_customer_email_PS.setString(1, rs1.getString("customer_id"));
				rs = get_customer_email_PS.executeQuery();
				while (rs.next())
					customer_email = rs.getString("email");
				Customer customer = getCustomerOfTheOrderByEmail(customer_email);
				Rider rider = getRiderOfTheOrderByCF(rs1.getString("rider_cf"));
				Shop shop = getShopOfTheOrderByEmail(shop_email);
				List<String> address_fields = string_util.tokenizedStringToList(rs1.getString("address"), "(, )");
				order_list.add(new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()),
						rs1.getString("payment"), rs1.getString("status"),
						new Address(address_fields.get(0), address_fields.get(1), address_fields.get(2),
								address_fields.get(3), address_fields.get(4)),
						rs1.getTime("delivery_time"), rs1.getString("note"), rider));
			}
		} catch (SQLException s) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
			db_util.closeResultSet(rs1);
		}
		return order_list;
	}

	public List<Order> getInDeliveryOrdersOfAShop(String shop_email) throws DAOException {

		ResultSet rs = null;
		ResultSet rs1 = null;
		List<Order> order_list = new ArrayList<Order>();
		InputUtility string_util = new InputUtility();
		String customer_email = null;
		try {
			get_delivering_orders_of_a_shop_PS.setString(1, shop_email);
			rs1 = get_delivering_orders_of_a_shop_PS.executeQuery();
			while (rs1.next()) {
				get_customer_email_PS.setString(1, rs1.getString("customer_id"));
				rs = get_customer_email_PS.executeQuery();
				while (rs.next())
					customer_email = rs.getString("email");
				Customer customer = getCustomerOfTheOrderByEmail(customer_email);
				Rider rider = getRiderOfTheOrderByCF(rs1.getString("rider_cf"));
				Shop shop = getShopOfTheOrderByEmail(shop_email);
				List<String> address_fields = string_util.tokenizedStringToList(rs1.getString("address"), "(, )");
				order_list
						.add(new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()),
								rs1.getString("payment"), rs1.getString("status"),
								new Address(address_fields.get(0), address_fields.get(1), address_fields.get(2),
										address_fields.get(3), address_fields.get(4)),
								null, rs1.getString("note"), rider));
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
			db_util.closeResultSet(rs1);
		}
		return order_list;
	}

	public Customer getCustomerOfTheOrderByEmail(String email) throws DAOException {

		InputUtility string_util = new InputUtility();
		List<String> address_fields = new ArrayList<String>();
		Customer customer = null;
		ResultSet rs = null;
		try {
			get_customer_of_the_order_PS.setString(1, email);
			rs = get_customer_of_the_order_PS.executeQuery();
			while (rs.next()) {
				address_fields = string_util.tokenizedStringToList(rs.getString("address"), "(, )");
				customer = new Customer(rs.getString("cf"), rs.getString("name"), rs.getString("surname"),
						new Date(rs.getDate("birth_date").getTime()), rs.getString("birth_place"),
						rs.getString("gender"), rs.getString("cellphone"),
						new Address(address_fields.get(0), address_fields.get(1), address_fields.get(2),
								address_fields.get(3), address_fields.get(4)),
						rs.getString("email"), rs.getString("password"));
			}
		} catch (SQLException s) {
			throw new DAOException();

		}
		return customer;
	}

	public List<Order> getPendingOrdersOfAShop(String shop_email) throws DAOException {

		ResultSet rs = null;
		ResultSet rs1 = null;
		List<Order> order_list = new ArrayList<Order>();
		InputUtility string_util = new InputUtility();
		String customer_email = null;
		try {
			get_pending_orders_of_a_shop_PS.setString(1, shop_email);
			rs1 = get_pending_orders_of_a_shop_PS.executeQuery();
			while (rs1.next()) {
				get_customer_email_PS.setString(1, rs1.getString("customer_id"));
				rs = get_customer_email_PS.executeQuery();
				while (rs.next())
					customer_email = rs.getString("email");
				Customer customer = getCustomerOfTheOrderByEmail(customer_email);
				Rider rider = getRiderOfTheOrderByCF(rs1.getString("rider_cf"));
				Shop shop = getShopOfTheOrderByEmail(shop_email);
				List<String> address_fields = string_util.tokenizedStringToList(rs1.getString("address"), "(, )");
				order_list
						.add(new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()),
								rs1.getString("payment"), rs1.getString("status"),
								new Address(address_fields.get(0), address_fields.get(1), address_fields.get(2),
										address_fields.get(3), address_fields.get(4)),
								null, rs1.getString("note"), rider));
			}
		} catch (SQLException e) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
			db_util.closeResultSet(rs1);
		}
		return order_list;
	}

	public void linkRiderToOrder(Order order, Rider rider) throws DAOException {
		try {
			link_rider_to_order_PS.setString(1, rider.getCf());
			link_rider_to_order_PS.setString(2, order.getId());
			link_rider_to_order_PS.executeUpdate();
		} catch (SQLException s) {
			throw new DAOException();
		}
		return;
	}

	public void updateDeliveringOrder(Order order, String stato) throws DAOException {
		try {
			update_delivering_order_PS.setString(1, stato);
			update_delivering_order_PS.setString(2, order.getId());
			update_delivering_order_PS.executeUpdate();
		} catch (SQLException s) {
			throw new DAOException();
		}
		return;
	}

	public Order getOrderById(String id) throws DAOException {

		ResultSet rs1 = null;
		ResultSet rs = null;
		InputUtility string_util = new InputUtility();
		Order order = null;
		String customer_email = null;
		try {
			get_order_by_id_PS.setString(1, id);
			rs1 = get_order_by_id_PS.executeQuery();
			while (rs1.next()) {
				get_customer_email_PS.setString(1, rs1.getString("customer_id"));
				rs = get_customer_email_PS.executeQuery();
				while (rs.next())
					customer_email = rs.getString("email");
				Customer customer = getCustomerOfTheOrderByEmail(customer_email);
				Rider rider = getRiderOfTheOrderByCF(rs1.getString("rider_cf"));
				Shop shop = getShopOfTheOrderByEmail(id);
				List<String> address_fields = string_util.tokenizedStringToList(rs1.getString("address"), "(, )");
				order = new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()),
						rs1.getString("payment"), rs1.getString("status"),
						new Address(address_fields.get(0), address_fields.get(1), address_fields.get(2),
								address_fields.get(3), address_fields.get(4)),
						rs1.getTime("delivery_time"), rs1.getString("note"), rider);
			}
		} catch (SQLException s) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
			db_util.closeResultSet(rs1);
		}
		return order;

	}

	@Override
	public void createOrder(Address address, String payment, String note, Shop shop, Customer customer, Cart cart)
			throws DAOException {

		InputUtility input_util = new InputUtility();
		List<String> meal_names = new ArrayList<String>();
		List<String> quantities = new ArrayList<String>();
		for (OrderComposition order_comp : cart.getOrder_composition_list()) {
			meal_names.add(order_comp.getMeal().getName());
			quantities.add(order_comp.getQuantity().toString());
		}
		try {
			create_order_CS.setString(1, customer.getAddress().toString());
			create_order_CS.setString(2, note);
			create_order_CS.setString(3, shop.getEmail());
			create_order_CS.setString(4, customer.getEmail());
			create_order_CS.setString(5, input_util.arrayListToTokenizedString(meal_names, ", "));
			create_order_CS.setString(6, input_util.arrayListToTokenizedString(quantities, ", "));
			create_order_CS.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		}
		return;
	}

	public List<Order> getAllOrders() throws DAOException {

		ResultSet rs1 = null;
		ResultSet rs = null;
		InputUtility string_util = new InputUtility();
		String customer_email = null;
		List<Order> order_list = new ArrayList<Order>();
		try {

			rs1 = get_all_orders_PS.executeQuery();
			while (rs1.next()) {
				Customer customer;
				if (rs1.getString("customer_id") == null)
					customer = null;
				else {
					get_customer_email_PS.setString(1, rs1.getString("customer_id"));
					rs = get_customer_email_PS.executeQuery();
					while (rs.next())
						customer_email = rs.getString("email");
					customer = getCustomerOfTheOrderByEmail(customer_email);
				}
				Rider rider;
				if (rs1.getString("rider_cf") == null)
					rider = null;
				else
					rider = getRiderOfTheOrderByCF(rs1.getString("rider_cf"));
				Shop shop = getShopOfTheOrderByEmail(rs1.getString("shop_id"));
				List<String> address_fields = string_util.tokenizedStringToList(rs1.getString("address"), "(, )");
				order_list.add(new Order(shop, customer, rs1.getString("id"), new Date(rs1.getDate("date").getTime()),
						rs1.getString("payment"), rs1.getString("status"),
						new Address(address_fields.get(0), address_fields.get(1), address_fields.get(2),
								address_fields.get(3), address_fields.get(4)),
						rs1.getTime("delivery_time"), rs1.getString("note"), rider));
			}
		} catch (SQLException s) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
			db_util.closeResultSet(rs1);
		}
		return order_list;

	}

	public List<Order> doAdminComplexSearch(String category, float min_price, float max_price, String vehicle,
			String province) throws DAOException {

		ResultSet rs = null;
		List<Order> order_list = new ArrayList<Order>();
		InputUtility input_util = new InputUtility();
		try {
			do_complex_admin_search_PS.setString(1, category);
			do_complex_admin_search_PS.setFloat(2, min_price);
			do_complex_admin_search_PS.setFloat(3, max_price);
			do_complex_admin_search_PS.setString(4, vehicle);
			do_complex_admin_search_PS.setString(5, province);
			rs = do_complex_admin_search_PS.executeQuery();
			while (rs.next()) {
				Customer customer = getCustomerOfTheOrderByEmail(rs.getString("customer_email"));
				Shop shop = getShopOfTheOrderByEmail(rs.getString("shop_email"));
				Rider rider = getRiderOfTheOrderByCF(rs.getString("rider_cf"));
				order_list.add(new Order(shop, customer, rs.getString("id"),
						new Date(rs.getDate("order_date").getTime()), rs.getString("payment"), rs.getString("status"),
						input_util.tokenizedStringToAddress(rs.getString("address"), "(, )"),
						rs.getTime("delivery_time"), rs.getString("note"), rider));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DAOException();
		}
		return order_list;
	}
	
	public void closeStatements() throws DAOException {

		db_util.closeStatement(update_delivering_order_PS);
		db_util.closeStatement(link_rider_to_order_PS);
		db_util.closeStatement(get_orders_of_a_shop_by_shop_email_PS);
		db_util.closeStatement(get_customer_of_the_order_PS);
		db_util.closeStatement(get_rider_of_the_order_PS);
		db_util.closeStatement(get_shop_of_the_order_PS);
		db_util.closeStatement(get_delivering_orders_of_a_shop_PS);
		db_util.closeStatement(get_pending_orders_of_a_shop_PS);
		db_util.closeStatement(get_customer_email_PS);
		db_util.closeStatement(get_order_by_id_PS);
		db_util.closeStatement(create_order_CS);
		return;

	}
}
