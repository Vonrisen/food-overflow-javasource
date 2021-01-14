package daos_interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Meal;

public interface MealDAO {
	
	public ResultSet getAllMeals() throws SQLException;
	public void insertMeal(Meal meal) throws SQLException;
	public void deleteMeal(Meal meal) throws SQLException;
	public void updateMeal(Meal meal) throws SQLException;
}
