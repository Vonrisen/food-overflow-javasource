package daos_interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Meal;

public interface MealDAO {
	
	public ResultSet getAllMeals() throws SQLException;
	public void insertMeal(String name, String prefix, Float price, String description) throws SQLException;
	public void deleteMeal(String meal_id) throws SQLException;
}
