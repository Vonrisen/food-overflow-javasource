package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daos_implementation.CustomerDAOPostgresImplementation;
import daos_implementation.MealDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.CustomerDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.ShopDAO;
import entities.Address;
import entities.Customer;
import entities.Meal;
import entities.Shop;
import gui.AdminCustomerFrame;
import gui.AdminCustomerPanelFrame;
import gui.AdminFrame;
import gui.AdminMealFrame;
import gui.AdminRiderFrame;
import gui.AdminShopFrame;
import utilities.InputUtility;
import utilities.TableModelUtility;

public class AdminController {
	
	public void openAdminFrame()
	{
		AdminFrame admin_frame = new AdminFrame();
		admin_frame.setVisible(true);
	}
	
	public void openAdminShopFrame()
	{
		AdminShopFrame admin_shop_frame = new AdminShopFrame();
		initializeAdminShopFrameTable(admin_shop_frame);
		admin_shop_frame.setVisible(true);
	}
	
	public void openAdminRiderFrame()
	{
		AdminRiderFrame admin_rider_frame = new AdminRiderFrame();
		admin_rider_frame.setVisible(true);
	}
	
	public void openAdminMealFrame()
	{
		AdminMealFrame admin_meal_frame = new AdminMealFrame();
		initializeAdminMealFrameTable(admin_meal_frame);
		admin_meal_frame.setVisible(true);
	}
	
	public void openAdminCustomerPanelFrame()
	{
		AdminCustomerPanelFrame admin_customer_panel_frame = new AdminCustomerPanelFrame();
		admin_customer_panel_frame.setVisible(true);
	}
	
	public void openAdminCustomerFrame()
	{
		AdminCustomerFrame admin_customer_frame = new AdminCustomerFrame();
		initializeAdminCustomerFrameTable(admin_customer_frame);
		admin_customer_frame.setVisible(true);
	}
	
	public void initializeAdminShopFrameTable(AdminShopFrame admin_shop_frame)
	{
		
		ShopDAO shop_dao = new ShopDAOPostgresImplementation();
		ArrayList<Shop>shop_list = new ArrayList<Shop>();
		try {
			shop_list = shop_dao.getAllShops();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TableModelUtility table = new TableModelUtility();
		table.initializeShopTable(admin_shop_frame, shop_list);
		return;
	}
	
	public void initializeAdminCustomerFrameTable(AdminCustomerFrame admin_customer_frame)
	{
		
		CustomerDAO customer_dao = new CustomerDAOPostgresImplementation();
		ArrayList<Customer>customer_list = new ArrayList<Customer>();
		try {
			customer_list = customer_dao.getAllCustomers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TableModelUtility table = new TableModelUtility();
		table.initializeCustomerTable(admin_customer_frame, customer_list);
		return;
	}
	
	public void addShop(AdminShopFrame admin_shop_frame) 
	{

		InputUtility input_util = new InputUtility();
		ShopDAO shop_dao = new ShopDAOPostgresImplementation();
		Shop shop = new Shop(admin_shop_frame.getNameTF().getText(), admin_shop_frame.getPasswordTF().getText(), admin_shop_frame.getWorking_hoursTF().getText(),
							 new Address(admin_shop_frame.getAddress_nameTF().getText(), admin_shop_frame.getAddress_civic_numberTF().getText(), admin_shop_frame.getAddress_capTF().getText(), 
						     admin_shop_frame.getAddress_cityTF().getText(), admin_shop_frame.getAddress_provinceTF().getText()), admin_shop_frame.getClosing_daysTF().getText(), null, null);
		try {
			shop_dao.insertShop(shop);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void initializeAdminMealFrameTable(AdminMealFrame admin_meal_frame) {
		MealDAO meal_dao = new MealDAOPostgresImplementation();
		ArrayList<Meal> meal_list = new ArrayList<Meal>();
		try {
			meal_list = meal_dao.getAllMeals();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		TableModelUtility table = new TableModelUtility();
		table.initializeMealTable(admin_meal_frame, meal_list);
	}
	
	public void addMeal(AdminMealFrame admin_meal_frame) {
		MealDAO meal_dao = new MealDAOPostgresImplementation();
		ArrayList<String> allergens = new ArrayList<String>();
		for()
		Meal meal = new Meal(admin_meal_frame.getNameTF().getText(), admin_meal_frame.getPriceTF().getText(), 
				admin_meal_frame.getIngredientsTF().getText(), admin_meal_frame.getDishJCB().getSelectedItem(), ArrayList<String>allergen_list)
	}
	
	
}
