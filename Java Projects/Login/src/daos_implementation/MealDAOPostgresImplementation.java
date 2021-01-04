package daos_implementation;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import daos_interfaces.MealDAO;
import db_connection.DBconnection_CodiceCatastale;
import entities.Meal;
import entities.Meal;

public class MealDAOPostgresImplementation implements MealDAO {
	
	private Connection connection;
	private PreparedStatement print_all_meals_PS;
	private CallableStatement insert_meal_CS, delete_meal_CS;
	public MealDAOPostgresImplementation(Connection connection)
	{
		this.connection = connection;
		try {
			print_all_meals_PS = connection.prepareStatement("SELECT * FROM Meal");
			insert_meal_CS = connection.prepareCall("CALL insertMeal(?,?,?,?)");
			delete_meal_CS = connection.prepareCall("CALL deleteMeal(?)");
		} catch (SQLException e) {
			System.out.println("Errore durante la preparazione degli statement "+e.getMessage());
		}
	}
	public ResultSet getAllMeals() throws SQLException {
		
		ResultSet rs = print_all_meals_PS.executeQuery();
		return rs;
	}
	
	public void insertMeal(String name, String prefix, Float price, String description) throws SQLException {
		
		insert_meal_CS.setString(1, name);
		insert_meal_CS.setString(2, prefix);
		insert_meal_CS.setFloat(3, price);
		insert_meal_CS.setString(4, description);
		insert_meal_CS.executeUpdate();
		return;
	}
	
	public void deleteMeal(String meal_id) throws SQLException {

		delete_meal_CS.setString(1, meal_id);
		delete_meal_CS.executeUpdate();
		return;
		
	}
	
	

}
