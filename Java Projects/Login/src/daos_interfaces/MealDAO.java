package daos_interfaces;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Meal;

public interface MealDAO {
	
	public List<Meal>getMealsOfAShopByShopId(String shop_id) throws SQLException;
	public List<Meal> getAllMeals() throws SQLException;
	public void insertMeal(Meal meal) throws SQLException;
	public void deleteMeal(Meal meal) throws SQLException;
	public List<Meal> getMealToAdd(String shop_id) throws SQLException;

}
