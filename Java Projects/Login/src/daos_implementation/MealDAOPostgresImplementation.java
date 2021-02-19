package daos_implementation;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import daos_interfaces.MealDAO;
import entities.Meal;
import exceptions.DaoException;
import utilities.DButility;
import utilities.InputUtility;

public class MealDAOPostgresImplementation implements MealDAO {

	PreparedStatement get_allergens_of_a_meal_PS, get_all_meals_PS, insert_meal_PS, delete_meal_PS,
			get_all_meals_except_shop_meals_PS, insert_supply_PS, delete_from_supply_PS, get_meal_by_name_PS,
			customer_complex_search_PS;
	CallableStatement add_allergens_CS;
	DButility db_util = new DButility();

	public MealDAOPostgresImplementation(Connection connection) {

		try {

			get_all_meals_except_shop_meals_PS = connection.prepareStatement(
					"SELECT * FROM meal WHERE id NOT IN (select meal_id from supply where shop_id=(SELECT id FROM Shop WHERE email=?)) ORDER BY category, name");
			get_all_meals_PS = connection.prepareStatement("SELECT * FROM MEAL ORDER BY category, name");
			get_meal_by_name_PS = connection.prepareStatement("SELECT * FROM Meal WHERE name=?");
			get_allergens_of_a_meal_PS = connection
					.prepareStatement("SELECT allergen_name FROM MEALCOMPOSITION WHERE meal_id=?");
			insert_meal_PS = connection.prepareStatement("INSERT INTO MEAL VALUES (DEFAULT,?,?,?,?)");
			add_allergens_CS = connection.prepareCall("CALL addAllergens(?,?)");
			delete_meal_PS = connection.prepareStatement("DELETE FROM Meal WHERE name=?");
			insert_supply_PS = connection.prepareStatement(
					"INSERT INTO Supply SELECT (SELECT id FROM Shop WHERE email=?), id FROM MEAL WHERE name=?");
			delete_from_supply_PS = connection.prepareStatement(
					"DELETE FROM Supply WHERE shop_id=(SELECT id FROM Shop WHERE email=?) AND meal_id IN (SELECT id FROM Meal WHERE name=?)");
			customer_complex_search_PS = connection.prepareStatement(
					"SELECT * FROM  effettuaRicercaComplessaCustomer(?,?,?,?,?) AS t(name varchar, category varchar, price real, ingredients varchar, id character(4))");

		} catch (SQLException s) {
			JOptionPane.showMessageDialog(null, "Generic error, please contact your administrator", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public List<Meal> getAllMeals() throws DaoException {

		ArrayList<String> allergens;
		ArrayList<Meal> meal_list = new ArrayList<Meal>();
		ResultSet rs2 = null;
		ResultSet rs1 = null;
		try {
			rs1 = get_all_meals_PS.executeQuery();
			while (rs1.next()) {
				get_allergens_of_a_meal_PS.setString(1, rs1.getString("id"));
				rs2 = get_allergens_of_a_meal_PS.executeQuery();
				allergens = new ArrayList<String>();
				while (rs2.next())
					allergens.add(rs2.getString("allergen_name"));
				meal_list.add(new Meal(rs1.getString("name"), rs1.getFloat("price"), rs1.getString("ingredients"),
						rs1.getString("category"), allergens));
			}
		} catch (SQLException s) {
			throw new DaoException();
		} finally {
			db_util.closeResultSet(rs2);
			db_util.closeResultSet(rs1);
		}
		return meal_list;
	}

	public List<Meal> getAllMealsExceptShopMeals(String shop_email) throws DaoException {

		ArrayList<String> allergens;
		ArrayList<Meal> meal_list = new ArrayList<Meal>();
		ResultSet rs2 = null;
		ResultSet rs1 = null;
		try {
			get_all_meals_except_shop_meals_PS.setString(1, shop_email);
			rs1 = get_all_meals_except_shop_meals_PS.executeQuery();
			while (rs1.next()) {
				get_allergens_of_a_meal_PS.setString(1, rs1.getString("id"));
				rs2 = get_allergens_of_a_meal_PS.executeQuery();
				allergens = new ArrayList<String>();
				while (rs2.next())
					allergens.add(rs2.getString("allergen_name"));
				meal_list.add(new Meal(rs1.getString("name"), rs1.getFloat("price"), rs1.getString("ingredients"),
						rs1.getString("category"), allergens));
			}
		} catch (SQLException s) {

			throw new DaoException();
		} finally {
			db_util.closeResultSet(rs2);
			db_util.closeResultSet(rs1);
		}
		return meal_list;
	}

	public void insertMeal(Meal meal) throws DaoException {

		try {
			insert_meal_PS.setString(1, meal.getCategory());
			insert_meal_PS.setString(2, meal.getName());
			insert_meal_PS.setFloat(3, meal.getPrice());
			insert_meal_PS.setString(4, meal.getIngredients());
			insert_meal_PS.executeUpdate();
			if (!meal.getAllergen_list().isEmpty())
				associateAllergensToMeal(meal);
		} catch (SQLException s) {
			throw new DaoException();
		}
		return;
	}

	public void associateAllergensToMeal(Meal meal) throws DaoException {
		InputUtility input_utility = new InputUtility();
		try {
			add_allergens_CS.setString(1, meal.getName());
			add_allergens_CS.setString(2, input_utility.arrayListToTokenizedString(meal.getAllergen_list(), ", "));
			add_allergens_CS.executeUpdate();
		} catch (SQLException s) {
			throw new DaoException();
		}
		return;
	}

	public void deleteMeal(Meal meal) throws DaoException {
		try {
			delete_meal_PS.setString(1, meal.getName());
			delete_meal_PS.executeUpdate();
		} catch (SQLException s) {
			throw new DaoException();
		}
		return;
	}

	public void insertSupply(String shop_email, Meal meal) throws DaoException {

		try {
			insert_supply_PS.setString(1, shop_email);
			insert_supply_PS.setString(2, meal.getName());
			insert_supply_PS.executeUpdate();
		} catch (SQLException s) {
			throw new DaoException();
		}
		return;
	}

	public void deleteFromSupply(String shop_email, Meal meal) throws DaoException {

		try {
			delete_from_supply_PS.setString(1, shop_email);
			delete_from_supply_PS.setString(2, meal.getName());
			delete_from_supply_PS.executeUpdate();
		} catch (SQLException s) {
			throw new DaoException();
		}
		return;
	}

	public void closeStatements() throws DaoException {

		db_util.closeStatement(get_allergens_of_a_meal_PS);
		db_util.closeStatement(get_all_meals_PS);
		db_util.closeStatement(insert_meal_PS);
		db_util.closeStatement(delete_meal_PS);
		db_util.closeStatement(get_all_meals_except_shop_meals_PS);
		db_util.closeStatement(insert_supply_PS);
		db_util.closeStatement(delete_from_supply_PS);
		db_util.closeStatement(add_allergens_CS);
		db_util.closeStatement(customer_complex_search_PS);
		return;

	}

	@Override
	public Meal getMealByName(String name) throws DaoException {

		ArrayList<String> allergens;
		Meal meal = null;
		ResultSet rs2 = null;
		ResultSet rs1 = null;
		try {
			get_meal_by_name_PS.setString(1, name);
			rs1 = get_meal_by_name_PS.executeQuery();
			while (rs1.next()) {
				get_allergens_of_a_meal_PS.setString(1, rs1.getString("id"));
				rs2 = get_allergens_of_a_meal_PS.executeQuery();
				allergens = new ArrayList<String>();
				while (rs2.next())
					allergens.add(rs2.getString("allergen_name"));
				meal = new Meal(rs1.getString("name"), rs1.getFloat("price"), rs1.getString("ingredients"),
						rs1.getString("category"), allergens);
			}
		} catch (SQLException s) {
			throw new DaoException();
		} finally {
			db_util.closeResultSet(rs2);
			db_util.closeResultSet(rs1);
		}
		return meal;
	}

	public List<Meal> doCustomerComplexSearch(String category, float min_price, float max_price,
			List<String> allergens, String shop_email) throws DaoException {
		List<String> allergen_list;
		List<Meal> meal_list = new ArrayList<>();
		ResultSet rs = null;
		ResultSet rs1 = null;
		InputUtility input_util = new InputUtility();
		try {
			customer_complex_search_PS.setString(1, category);
			customer_complex_search_PS.setFloat(2, min_price);
			customer_complex_search_PS.setFloat(3, max_price);
			customer_complex_search_PS.setString(4, input_util.arrayListToTokenizedString(allergens, ","));
			customer_complex_search_PS.setString(5, shop_email);
			rs = customer_complex_search_PS.executeQuery();
			while (rs.next()) {
				get_allergens_of_a_meal_PS.setString(1, rs.getString("id"));
				rs1 = get_allergens_of_a_meal_PS.executeQuery();
				allergen_list = new ArrayList<String>();
				while (rs1.next())
					allergen_list.add(rs1.getString("allergen_name"));
				meal_list.add(new Meal(rs.getString("name"), rs.getFloat("price"), rs.getString("ingredients"),
						rs.getString("category"), allergen_list));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException();
		} finally {
			db_util.closeResultSet(rs);
			db_util.closeResultSet(rs1);
		}

		return meal_list;
	}
}
