package daos_interfaces;

import java.util.List;

import entities.Meal;
import exceptions.DAOException;

public interface MealDAO {

	public List<Meal> getAllMeals() throws DAOException;

	public void insertMeal(Meal meal) throws DAOException;

	public void deleteMeal(Meal meal) throws DAOException;

	public List<Meal> getAllMealsExceptShopMeals(String shop_email) throws DAOException;

	public void insertIntoMenu(String shop_email, Meal meal) throws DAOException;

	public void deleteFromMenu(String shop_email, Meal meal) throws DAOException;

	public Meal getMealByName(String name) throws DAOException;

	public void closeStatements() throws DAOException;

	public List<Meal> doCustomerComplexSearch(String category, float min_price, float max_price, List<String> allergens,
			String shop_email) throws DAOException;
	
	public void associateAllergensToMeal(Meal meal) throws DAOException;

}
