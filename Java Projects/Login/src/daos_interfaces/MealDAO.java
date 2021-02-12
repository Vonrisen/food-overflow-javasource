package daos_interfaces;

import java.util.List;

import entities.Meal;
import exceptions.DaoException;

public interface MealDAO {
	
	public List<Meal> getAllMeals() throws DaoException;
	public void insertMeal(Meal meal) throws DaoException;
	public void deleteMeal(Meal meal) throws DaoException;
	public List<Meal> getAllMealsExceptShopMeals(String shop_email) throws DaoException;
	public void insertSupply(String shop_email, Meal meal) throws DaoException;
	public void deleteFromSupply(String shop_email, Meal meal)throws DaoException;
	public Meal getMealByName(String name)throws DaoException;
	public void closeStatements() throws DaoException;

}
