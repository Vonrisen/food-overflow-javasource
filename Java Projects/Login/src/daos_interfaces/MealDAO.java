package daos_interfaces;
import java.sql.SQLException;
import java.util.List;

import entities.Meal;

public interface MealDAO {
	
	public List<Meal>getMealsOfAShopByShopEmail(String shop_email) throws SQLException;
	public List<Meal> getAllMeals() throws SQLException;
	public void insertMeal(Meal meal) throws SQLException;
	public void deleteMeal(Meal meal) throws SQLException;
	public List<Meal> getAllMealsExceptShopMeals(String shop_email) throws SQLException;
	public void insertSupply(String shop_email, Meal meal) throws SQLException;
	public void deleteFromSupply(String shop_email, Meal meal)throws SQLException;

}
