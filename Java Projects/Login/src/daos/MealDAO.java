package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Meal;

public interface MealDAO {
	
	public ResultSet getAllShops() throws SQLException;
	public void insertShop(Meal meal) throws SQLException;
	public void deleteShop(Meal meal) throws SQLException;
	public void updateShop(Meal meal) throws SQLException;
}
