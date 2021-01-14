package daos_implementation;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daos_interfaces.MealDAO;
import db_connection.DBconnection;
import entities.Meal;
import entities.Meal;

public class MealDAOPostgresImplementation implements MealDAO {
	
	private Connection connection;
	private PreparedStatement print_all_meals_PS;
	private CallableStatement insert_meal_CS, delete_meal_CS, update_meal_CS;
	public MealDAOPostgresImplementation(Connection connection)
	{
		this.connection = connection;
		try {
			print_all_meals_PS = connection.prepareStatement("SELECT * FROM Meal Order by meal_name");
			insert_meal_CS = connection.prepareCall("CALL insertMeal(?,?,?,?)");
			delete_meal_CS = connection.prepareCall("CALL deleteMeal(?)");
//			update_meal_CS = connection.prepareCall("CALL updateMeal()");
		} catch (SQLException e) {
			System.out.println("Errore durante la preparazione degli statement "+e.getMessage());
		}
	}
	
	public ResultSet getAllMeals() throws SQLException {
		
		ResultSet rs = print_all_meals_PS.executeQuery();
		return rs;
	}
	
	public void insertMeal(Meal meal) throws SQLException {
		
		insert_meal_CS.setString(1, meal.getName());
		insert_meal_CS.setString(2, meal.getPrefix());
		insert_meal_CS.setFloat(3, meal.getPrice());
		insert_meal_CS.setString(4, meal.getDescription());
		insert_meal_CS.executeUpdate();
	}
	
	public void deleteMeal(Meal meal) throws SQLException {
		
		delete_meal_CS.setString(1, meal.getName());
		delete_meal_CS.executeUpdate();
	}
	
	public void updateMeal(Meal meal) throws SQLException {
		
//		update_meal_CS.setString(1, meal.getName());
//		update_meal_CS.setString(2, meal.getPrefix());
		update_meal_CS.setFloat(3, meal.getPrice());
		update_meal_CS.setString(4, meal.getDescription());
		update_meal_CS.executeUpdate();
	}

}
