package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daos_implementation.CustomerDAOPostgresImplementation;
import daos_implementation.MealDAOPostgresImplementation;
import daos_implementation.RiderDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.CustomerDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import entities.Address;
import entities.Customer;
import entities.Meal;
import entities.Rider;
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
		initializeAdminRiderFrameTable(admin_rider_frame);
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
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		TableModelUtility table = new TableModelUtility();
		table.initializeShopTable(admin_shop_frame, shop_list);
	}
	
	public void initializeAdminRiderFrameTable(AdminRiderFrame admin_rider_frame)
	{
		
		RiderDAO rider_dao = new RiderDAOPostgresImplementation();
		ArrayList<Rider>rider_list = new ArrayList<Rider>();
		try {
			rider_list = rider_dao.getAllRiders();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		TableModelUtility table = new TableModelUtility();
		table.initializeRiderTable(admin_rider_frame, rider_list);
	}
	
	public void initializeAdminCustomerFrameTable(AdminCustomerFrame admin_customer_frame)
	{
		
		CustomerDAO customer_dao = new CustomerDAOPostgresImplementation();
		ArrayList<Customer>customer_list = new ArrayList<Customer>();
		try {
			customer_list = customer_dao.getAllCustomers();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		TableModelUtility table = new TableModelUtility();
		table.initializeCustomerTable(admin_customer_frame, customer_list);
	}
	ArrayList<Meal> meal_list = new ArrayList<Meal>();
	public void initializeAdminMealFrameTable(AdminMealFrame admin_meal_frame) {
		MealDAO meal_dao = new MealDAOPostgresImplementation();
		
		try {
			meal_list = meal_dao.getAllMeals();
			if(meal_list.size()==0)
				JOptionPane.showMessageDialog(null, "Non ci sono shop da visualizzare","Errore",JOptionPane.ERROR_MESSAGE);
			else
			{
				TableModelUtility table = new TableModelUtility();
				table.initializeMealTable(admin_meal_frame, meal_list);
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void addShop(AdminShopFrame admin_shop_frame) 
	{

		ShopDAO shop_dao = new ShopDAOPostgresImplementation();
		Shop shop = new Shop(admin_shop_frame.getNameTF().getText(), admin_shop_frame.getPasswordTF().getText(), admin_shop_frame.getWorking_hoursTF().getText(),
							 new Address(admin_shop_frame.getAddress_nameTF().getText(), admin_shop_frame.getAddress_civic_numberTF().getText(), admin_shop_frame.getAddress_capTF().getText(), 
						     admin_shop_frame.getAddress_cityTF().getText(), admin_shop_frame.getAddress_provinceTF().getText()), admin_shop_frame.getClosing_daysTF().getText(), null, null);
		try {
			shop_dao.insertShop(shop);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void addMeal(AdminMealFrame admin_meal_frame) {
		MealDAO meal_dao = new MealDAOPostgresImplementation();
		ArrayList<String> allergens = new ArrayList<String>();
		for(JCheckBox cb : admin_meal_frame.getAllergens()) {
			if(cb.isSelected())
				allergens.add(cb.getText());
		}
		Meal meal = new Meal(admin_meal_frame.getNameTF().getText(), Float.parseFloat(admin_meal_frame.getPriceTF().getText()), 
				admin_meal_frame.getIngredientsTF().getText(), admin_meal_frame.getDishJCB().getSelectedItem().toString(), allergens);
		
		try {
			meal_dao.insertMeal(meal);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public boolean shopRemoved(AdminShopFrame admin_shop_frame)
	{
		ShopDAO shop_dao = new ShopDAOPostgresImplementation();
		int selected_row = admin_shop_frame.getTable().getSelectedRow();
		String id_of_shop_to_delete = admin_shop_frame.getTable().getModel().getValueAt(selected_row, 0).toString();
		try {
			shop_dao.deleteShop(id_of_shop_to_delete);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean mealRemoved(AdminMealFrame admin_meal_frame) {
		MealDAO meal_dao = new MealDAOPostgresImplementation();
		String mealName = admin_meal_frame.getTable().getModel().getValueAt(admin_meal_frame.getTable().getSelectedRow(), 0).toString();
		try {
			meal_dao.deleteMeal(mealName);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public boolean mealUpdated(AdminMealFrame admin_meal_frame) {
		MealDAO meal_dao = new MealDAOPostgresImplementation();
		String mealName = admin_meal_frame.getTable().getModel().getValueAt(admin_meal_frame.getTable().getSelectedRow(), 0).toString();
		Meal meal=null;
		for(Meal m: meal_list) {
			if(m.getName()==mealName)
				meal=m;
		}
		ArrayList<String> allergens = new ArrayList<String>();
		for(JCheckBox cb : admin_meal_frame.getAllergens()) {
			if(cb.isSelected())
				allergens.add(cb.getText());
		}
		meal.setName(admin_meal_frame.getNameTF().getText());
		meal.setCategory(admin_meal_frame.getDishJCB().getSelectedItem().toString());
		meal.setPrice(Float.parseFloat(admin_meal_frame.getPriceTF().getText()));
		meal.setIngredients(admin_meal_frame.getIngredientsTF().getText());
		meal.setAllergen_list(allergens);
		try {
			meal_dao.updateMeal(meal, mealName);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
}
