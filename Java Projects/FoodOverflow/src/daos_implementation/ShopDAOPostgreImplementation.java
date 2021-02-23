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
import daos_interfaces.ShopDAO;
import entities.Address;
import entities.Meal;
import entities.Rider;
import entities.Shop;
import exceptions.DAOException;
import utilities.DBUtility;
import utilities.InputUtility;

public class ShopDAOPostgreImplementation implements ShopDAO {

	private PreparedStatement authenticate_shop_login_PS, get_all_shops_PS, insert_shop_PS, delete_shop_PS,
			get_riders_of_a_shop_by_shop_email_PS, get_meals_of_a_shop_by_shop_email_PS, get_allergens_of_a_meal_PS,
			get_shop_by_email_PS, get_shop_by_province_PS, get_riders_of_a_shop_max_2_deliveries_by_shop_email_PS;
	private CallableStatement update_shop_CS;
	private DBUtility db_util = new DBUtility();

	public ShopDAOPostgreImplementation(Connection connection) {
		try {

			authenticate_shop_login_PS = connection.prepareStatement("SELECT * FROM Shop WHERE email=? AND password=?");
			get_all_shops_PS = connection.prepareStatement("SELECT * FROM Shop ORDER BY id");
			get_shop_by_email_PS = connection.prepareStatement("SELECT * FROM Shop WHERE email=?");
			insert_shop_PS = connection.prepareStatement("INSERT INTO Shop VALUES (DEFAULT,?,?,?,?,?,?,?)");
			delete_shop_PS = connection.prepareStatement("DELETE FROM Shop WHERE email=?");
			update_shop_CS = connection.prepareCall("CALL updateShop(?,?,?,?,?,?,?,?)");
			get_riders_of_a_shop_by_shop_email_PS = connection.prepareStatement(
					"SELECT cf, name, surname, address, birth_date, birth_place, gender, cellphone, vehicle, working_hours, deliveries_number\r\n"
							+ "FROM Rider WHERE shop_id=(SELECT id FROM Shop WHERE email=?)");
			get_meals_of_a_shop_by_shop_email_PS = connection.prepareStatement(
					"SELECT * FROM MEAL WHERE id IN(SELECT meal_id FROM Supply WHERE shop_id=(SELECT id FROM Shop WHERE email=?)) ORDER BY category, name");
			get_allergens_of_a_meal_PS = connection
					.prepareStatement("SELECT allergen_name FROM MEALCOMPOSITION WHERE meal_id=?");
			get_shop_by_province_PS = connection
					.prepareStatement("SELECT * FROM Shop WHERE SPLIT_PART(address,', ',5)=UPPER(?)");
			get_riders_of_a_shop_max_2_deliveries_by_shop_email_PS = connection.prepareStatement(
					"SELECT * FROM Rider WHERE shop_id = (SELECT id FROM Shop WHERE email=?) AND deliveries_number<3");

		} catch (SQLException s) {
			JOptionPane.showMessageDialog(null, "Generic error, please contact your administrator", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public List<Shop> getAllShops() throws DAOException {

		InputUtility string_util = new InputUtility();
		List<Shop> shop_list = new ArrayList<Shop>();
		List<String> address_fields_shop = new ArrayList<String>();
		List<String> address_fields_rider;
		List<Rider> employed_rider_list;
		List<Meal> meal_list;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		try {
			rs = get_all_shops_PS.executeQuery();
			while (rs.next()) {
				address_fields_shop = string_util.tokenizedStringToList(rs.getString("address"), "(, )");
				employed_rider_list = new ArrayList<Rider>();
				meal_list = new ArrayList<Meal>();
				try {
					get_riders_of_a_shop_by_shop_email_PS.setString(1, rs.getString("email"));
					rs1 = get_riders_of_a_shop_by_shop_email_PS.executeQuery();
					while (rs1.next()) {
						address_fields_rider = new ArrayList<String>();
						address_fields_rider = string_util.tokenizedStringToList(rs1.getString("address"), "(, )");
						employed_rider_list.add(new Rider(rs1.getString("cf"), rs1.getString("name"),
								rs1.getString("surname"), new Date(rs1.getDate("birth_date").getTime()),
								rs1.getString("birth_place"), rs1.getString("gender"), rs1.getString("cellphone"),
								new Address(address_fields_rider.get(0), address_fields_rider.get(1),
										address_fields_rider.get(2), address_fields_rider.get(3),
										address_fields_rider.get(4)),
								rs1.getString("vehicle"), rs.getString("working_hours"),
								rs1.getShort("deliveries_number")));
					}
				} catch (SQLException s) {
					throw new DAOException();
				}
				try {
					get_meals_of_a_shop_by_shop_email_PS.setString(1, rs.getString("email"));
					rs2 = get_meals_of_a_shop_by_shop_email_PS.executeQuery();
					meal_list = new ArrayList<Meal>();
					while (rs2.next()) {
						get_allergens_of_a_meal_PS.setString(1, rs2.getString("id"));
						rs3 = get_allergens_of_a_meal_PS.executeQuery();
						ArrayList<String> allergens = new ArrayList<String>();
						;
						while (rs3.next()) {
							String allergen = rs3.getString("allergen_name");
							allergens.add(allergen);
						}
						meal_list.add(new Meal(rs2.getString("name"), rs2.getFloat("price"),
								rs2.getString("ingredients"), rs2.getString("category"), allergens));
					}
				} catch (SQLException s) {
					throw new DAOException();
				}
				shop_list.add(new Shop(rs.getString("email"), rs.getString("name"), rs.getString("password"),
						rs.getString("working_hours"),
						new Address(address_fields_shop.get(0), address_fields_shop.get(1), address_fields_shop.get(2),
								address_fields_shop.get(3), address_fields_shop.get(4)),
						rs.getString("closing_days"), employed_rider_list, meal_list, rs.getString("home_phone")));
			}
		} catch (SQLException s) {

			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
			db_util.closeResultSet(rs1);
			db_util.closeResultSet(rs2);
			db_util.closeResultSet(rs3);
		}
		return shop_list;
	}

	public List<Rider> getRidersOfAShopByShopEmail(String shop_email) throws DAOException {

		List<Rider> rider_list = new ArrayList<Rider>();
		List<String> address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		ResultSet rs = null;
		try {
			get_riders_of_a_shop_by_shop_email_PS.setString(1, shop_email);
			rs = get_riders_of_a_shop_by_shop_email_PS.executeQuery();
			while (rs.next()) {
				address_fields = string_util.tokenizedStringToList(rs.getString("address"), "(, )");
				rider_list.add(new Rider(rs.getString("cf"), rs.getString("name"), rs.getString("surname"),
						new Date(rs.getDate("birth_date").getTime()), rs.getString("birth_place"),
						rs.getString("gender"), rs.getString("cellphone"),
						new Address(address_fields.get(0), address_fields.get(1), address_fields.get(2),
								address_fields.get(3), address_fields.get(4)),
						rs.getString("vehicle"), rs.getString("working_hours"), rs.getShort("deliveries_number")));
			}
		} catch (SQLException s) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
		}
		return rider_list;
	}

	public List<Rider> getRidersOfAShopMax2DeliveriesByShopEmail(String shop_email) throws DAOException {

		List<Rider> rider_list = new ArrayList<Rider>();
		List<String> address_fields = new ArrayList<String>();
		InputUtility string_util = new InputUtility();
		ResultSet rs = null;
		try {
			get_riders_of_a_shop_max_2_deliveries_by_shop_email_PS.setString(1, shop_email);
			rs = get_riders_of_a_shop_max_2_deliveries_by_shop_email_PS.executeQuery();
			while (rs.next()) {
				address_fields = string_util.tokenizedStringToList(rs.getString("address"), "(, )");
				rider_list.add(new Rider(rs.getString("cf"), rs.getString("name"), rs.getString("surname"),
						new Date(rs.getDate("birth_date").getTime()), rs.getString("birth_place"),
						rs.getString("gender"), rs.getString("cellphone"),
						new Address(address_fields.get(0), address_fields.get(1), address_fields.get(2),
								address_fields.get(3), address_fields.get(4)),
						rs.getString("vehicle"), rs.getString("working_hours"), rs.getShort("deliveries_number")));
			}
		} catch (SQLException s) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
		}
		return rider_list;
	}

	public List<Meal> getMealsOfAShopByShopEmail(String shop_email) throws DAOException {
		ArrayList<String> allergens;
		ArrayList<Meal> meal_list = new ArrayList<Meal>();
		ResultSet rs2 = null;
		ResultSet rs1 = null;
		try {
			get_meals_of_a_shop_by_shop_email_PS.setString(1, shop_email);
			rs1 = get_meals_of_a_shop_by_shop_email_PS.executeQuery();
			while (rs1.next()) {
				get_allergens_of_a_meal_PS.setString(1, rs1.getString("id"));
				rs2 = get_allergens_of_a_meal_PS.executeQuery();
				allergens = new ArrayList<String>();
				while (rs2.next()) {
					String allergen = rs2.getString("allergen_name");
					allergens.add(allergen);
				}
				meal_list.add(new Meal(rs1.getString("name"), rs1.getFloat("price"), rs1.getString("ingredients"),
						rs1.getString("category"), allergens));
			}
		} catch (SQLException s) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs2);
			db_util.closeResultSet(rs1);
		}
		return meal_list;
	}

	public boolean isShopLoginValidated(String email, String password) throws DAOException {

		Boolean row_founded;
		ResultSet rs = null;
		try {
			authenticate_shop_login_PS.setString(1, email);
			authenticate_shop_login_PS.setString(2, password);
			rs = authenticate_shop_login_PS.executeQuery();
			row_founded = rs.next();
		} catch (SQLException s) {
			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
		}
		return row_founded;
	}

	@Override
	public void insertShop(Shop shop) throws DAOException {

		try {
			insert_shop_PS.setString(1, shop.getName());
			insert_shop_PS.setString(2, shop.getAddress().toString());
			insert_shop_PS.setString(3, shop.getWorking_hours());
			insert_shop_PS.setString(4, shop.getClosing_days());
			insert_shop_PS.setString(5, shop.getPassword());
			insert_shop_PS.setString(6, shop.getEmail());
			insert_shop_PS.setString(7, shop.getHome_phone());
			insert_shop_PS.executeUpdate();
		} catch (SQLException s) {
			System.out.println(s.getMessage());
			throw new DAOException();
		}
		return;
	}

	@Override
	public void deleteShop(Shop shop) throws DAOException {

		try {
			delete_shop_PS.setString(1, shop.getEmail());
			delete_shop_PS.executeUpdate();
		} catch (SQLException s) {
			throw new DAOException();
		}
		return;
	}

	public void updateShop(Shop shop, String old_email) throws DAOException {

		try {
			update_shop_CS.setString(1, shop.getName());
			update_shop_CS.setString(2, shop.getAddress().toString());
			update_shop_CS.setString(3, shop.getWorking_hours());
			update_shop_CS.setString(4, shop.getClosing_days());
			update_shop_CS.setString(5, shop.getPassword());
			update_shop_CS.setString(6, shop.getEmail());
			update_shop_CS.setString(7, shop.getHome_phone());
			update_shop_CS.setString(8, old_email);
			update_shop_CS.executeUpdate();
		} catch (SQLException s) {
			throw new DAOException();
		}
		return;
	}

	public Shop getShopByEmail(String email) throws DAOException {

		InputUtility string_util = new InputUtility();
		Shop shop = null;
		List<String> address_fields_shop = new ArrayList<String>();
		List<String> address_fields_rider;
		List<Rider> employed_rider_list;
		List<Meal> meal_list;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		try {
			get_shop_by_email_PS.setString(1, email);
			rs = get_shop_by_email_PS.executeQuery();
			while (rs.next()) {
				address_fields_shop = string_util.tokenizedStringToList(rs.getString("address"), "(, )");
				employed_rider_list = new ArrayList<Rider>();
				meal_list = new ArrayList<Meal>();
				try {
					get_riders_of_a_shop_by_shop_email_PS.setString(1, rs.getString("email"));
					rs1 = get_riders_of_a_shop_by_shop_email_PS.executeQuery();
					while (rs1.next()) {
						address_fields_rider = new ArrayList<String>();
						address_fields_rider = string_util.tokenizedStringToList(rs1.getString("address"), "(, )");
						employed_rider_list.add(new Rider(rs1.getString("cf"), rs1.getString("name"),
								rs1.getString("surname"), new Date(rs1.getDate("birth_date").getTime()),
								rs1.getString("birth_place"), rs1.getString("gender"), rs1.getString("cellphone"),
								new Address(address_fields_rider.get(0), address_fields_rider.get(1),
										address_fields_rider.get(2), address_fields_rider.get(3),
										address_fields_rider.get(4)),
								rs1.getString("vehicle"), rs.getString("working_hours"),
								rs1.getShort("deliveries_number")));
					}
				} catch (SQLException s) {
					throw new DAOException();
				}
				try {
					get_meals_of_a_shop_by_shop_email_PS.setString(1, rs.getString("email"));
					rs2 = get_meals_of_a_shop_by_shop_email_PS.executeQuery();
					meal_list = new ArrayList<Meal>();
					while (rs2.next()) {
						get_allergens_of_a_meal_PS.setString(1, rs2.getString("id"));
						rs3 = get_allergens_of_a_meal_PS.executeQuery();
						ArrayList<String> allergens = new ArrayList<String>();
						;
						while (rs3.next()) {
							String allergen = rs3.getString("allergen_name");
							allergens.add(allergen);
						}
						meal_list.add(new Meal(rs2.getString("name"), rs2.getFloat("price"),
								rs2.getString("ingredients"), rs2.getString("category"), allergens));
					}
				} catch (SQLException s) {
					throw new DAOException();
				}
				shop = new Shop(rs.getString("email"), rs.getString("name"), rs.getString("password"),
						rs.getString("working_hours"),
						new Address(address_fields_shop.get(0), address_fields_shop.get(1), address_fields_shop.get(2),
								address_fields_shop.get(3), address_fields_shop.get(4)),
						rs.getString("closing_days"), employed_rider_list, meal_list, rs.getString("home_phone"));
			}
		} catch (SQLException s) {

			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
			db_util.closeResultSet(rs1);
			db_util.closeResultSet(rs2);
			db_util.closeResultSet(rs3);
		}
		return shop;
	}

	public List<Shop> getShopByProvince(String province) throws DAOException {

		InputUtility string_util = new InputUtility();
		List<Shop> shop_list = new ArrayList<Shop>();
		List<String> address_fields_shop = new ArrayList<String>();
		List<String> address_fields_rider;
		List<Rider> employed_rider_list;
		List<Meal> meal_list;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		try {
			get_shop_by_province_PS.setString(1, province);
			rs = get_shop_by_province_PS.executeQuery();
			while (rs.next()) {
				address_fields_shop = string_util.tokenizedStringToList(rs.getString("address"), "(, )");
				employed_rider_list = new ArrayList<Rider>();
				meal_list = new ArrayList<Meal>();
				try {
					get_riders_of_a_shop_by_shop_email_PS.setString(1, rs.getString("email"));
					rs1 = get_riders_of_a_shop_by_shop_email_PS.executeQuery();
					while (rs1.next()) {
						address_fields_rider = new ArrayList<String>();
						address_fields_rider = string_util.tokenizedStringToList(rs1.getString("address"), "(, )");
						employed_rider_list.add(new Rider(rs1.getString("cf"), rs1.getString("name"),
								rs1.getString("surname"), new Date(rs1.getDate("birth_date").getTime()),
								rs1.getString("birth_place"), rs1.getString("gender"), rs1.getString("cellphone"),
								new Address(address_fields_rider.get(0), address_fields_rider.get(1),
										address_fields_rider.get(2), address_fields_rider.get(3),
										address_fields_rider.get(4)),
								rs1.getString("vehicle"), rs.getString("working_hours"),
								rs1.getShort("deliveries_number")));
					}
				} catch (SQLException s) {
					throw new DAOException();
				}
				try {
					get_meals_of_a_shop_by_shop_email_PS.setString(1, rs.getString("email"));
					rs2 = get_meals_of_a_shop_by_shop_email_PS.executeQuery();
					meal_list = new ArrayList<Meal>();
					while (rs2.next()) {
						get_allergens_of_a_meal_PS.setString(1, rs2.getString("id"));
						rs3 = get_allergens_of_a_meal_PS.executeQuery();
						ArrayList<String> allergens = new ArrayList<String>();
						;
						while (rs3.next()) {
							String allergen = rs3.getString("allergen_name");
							allergens.add(allergen);
						}
						meal_list.add(new Meal(rs2.getString("name"), rs2.getFloat("price"),
								rs2.getString("ingredients"), rs2.getString("category"), allergens));
					}
				} catch (SQLException s) {
					throw new DAOException();
				}
				shop_list.add(new Shop(rs.getString("email"), rs.getString("name"), rs.getString("password"),
						rs.getString("working_hours"),
						new Address(address_fields_shop.get(0), address_fields_shop.get(1), address_fields_shop.get(2),
								address_fields_shop.get(3), address_fields_shop.get(4)),
						rs.getString("closing_days"), employed_rider_list, meal_list, rs.getString("home_phone")));
			}
		} catch (SQLException s) {

			throw new DAOException();
		} finally {
			db_util.closeResultSet(rs);
			db_util.closeResultSet(rs1);
			db_util.closeResultSet(rs2);
			db_util.closeResultSet(rs3);
		}
		return shop_list;
	}

	public void closeStatements() throws DAOException {

		DBUtility db_util = new DBUtility();
		db_util.closeStatement(authenticate_shop_login_PS);
		db_util.closeStatement(get_all_shops_PS);
		db_util.closeStatement(insert_shop_PS);
		db_util.closeStatement(delete_shop_PS);
		db_util.closeStatement(get_riders_of_a_shop_by_shop_email_PS);
		db_util.closeStatement(get_meals_of_a_shop_by_shop_email_PS);
		db_util.closeStatement(get_allergens_of_a_meal_PS);
		db_util.closeStatement(update_shop_CS);
		db_util.closeStatement(get_riders_of_a_shop_max_2_deliveries_by_shop_email_PS);
		db_util.closeStatement(get_shop_by_province_PS);
		return;

	}

}
