package controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import daos_interfaces.CustomerDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.ShopDAO;
import entities.Meal;
import entities.Shop;
import exceptions.DaoException;
import gui.CustomerFrame;
import gui.CustomerMealListFrame;
import gui.CustomerShopListFrame;
import utilities.DButility;
import utilities.IstatUtils;
import utilities.TableModelUtility;

public class CustomerController {
	
	
	private String customer_email;
	CustomerDAO customer_dao;
	ShopDAO shop_dao;
	MealDAO meal_dao;
	Connection connection;
	
	public CustomerController(String email, Connection connection, CustomerDAO customer_dao, ShopDAO shop_dao, MealDAO meal_dao)
	{
		
		this.customer_email = email;
		this.connection = connection;
		this.customer_dao = customer_dao;
		this.shop_dao = shop_dao;
		this.meal_dao = meal_dao;
		
	}
	public CustomerController()
	{
		
	}
	public void openCustomerFrame(JFrame frame)
	{
		frame.dispose();
		CustomerFrame customer_frame = new CustomerFrame(this);
		customer_frame.setVisible(true);
	}

	public void checkProvinceAndOpenShopListFrame(CustomerFrame customer_frame) {
	
		IstatUtils istat_utils = new IstatUtils();
			if(istat_utils.isProvinceValid(customer_frame.getProvinceTF().getText()))
			{
				customer_frame.dispose();
				openCustomerShopListFrame();
			}
			else
				JOptionPane.showMessageDialog(null,"Non sono riuscito a trovare la tua provincia, riprovare", "Error",JOptionPane.ERROR_MESSAGE);
	}
	public void openCustomerShopListFrame() {
		
		TableModelUtility table_util = new TableModelUtility();
		CustomerShopListFrame customer_shop_list_frame = new CustomerShopListFrame(this);
		List<Shop>shop_list = new ArrayList<Shop>();
		try {
			shop_list = shop_dao.getAllShops();
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,
					"An error has occurred, please try again or contact the administrator", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	    table_util.initializeCustomerShopTable(customer_shop_list_frame.getModel(), shop_list);
		customer_shop_list_frame.setVisible(true);
		return;
	}
	
	public void openCustomerMealListFrame(JFrame frame, String shop_email) {
		
		TableModelUtility table_util = new TableModelUtility();
		CustomerMealListFrame customer_meal_list_frame = new CustomerMealListFrame(this);
		frame.dispose();
		List<Meal>meal_list = new ArrayList<Meal>();
		try {
			meal_list = shop_dao.getMealsOfAShopByShopEmail(shop_email);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,
					"An error has occurred, please try again or contact the administrator", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	    table_util.initializeMealTable(customer_meal_list_frame.getModel(), meal_list);
	    customer_meal_list_frame.setVisible(true);
		return;
	}
	
	public void releaseAllDaoResourcesAndDisposeFrame(JFrame frame)
	{
		DButility db_utility = new DButility();
		try {
			shop_dao.closeStatements();
			customer_dao.closeStatements();
			meal_dao.closeStatements();
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore. Contattare l' amministratore","Errore",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		finally {
			db_utility.closeConnection(connection);
			frame.dispose();
		}
		return;
	}

	
}
