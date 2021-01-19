package daos_interfaces;
import java.sql.SQLException;
import java.util.ArrayList;
import entities.Meal;

public interface MealDAO {
	
	public ArrayList<Meal>getMealsOfAShopByShopId(String shop_id) throws SQLException;
	public ArrayList<Meal> getAllMeals() throws SQLException;
	public int insertMeal(Meal meal) throws SQLException;

}
