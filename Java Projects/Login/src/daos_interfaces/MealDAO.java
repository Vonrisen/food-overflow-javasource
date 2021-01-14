package daos_interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Meal;

public interface MealDAO {
	

	public Meal getMealById(String meal_id) throws SQLException;
	
}
