package controllers;


import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
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
import entities.Rider;
import entities.Shop;
import exceptions.DaoException;
import gui.AdminCustomerFrame;
import gui.AdminFrame;
import gui.AdminMealFrame;
import gui.AdminRiderFrame;
import gui.AdminShopFrame;
import gui.RegisterFrame;
import utilities.DButility;
import utilities.InputUtility;
import utilities.IstatUtils;
import utilities.TableModelUtility;

public class AdminController {
	
	private Connection connection;
	private LoginController login_controller;
	CustomerDAO customer_dao;
	ShopDAO shop_dao;
	MealDAO meal_dao;
	public  AdminController(Connection connection, LoginController login_controller, CustomerDAO customer_dao, ShopDAO shop_dao, MealDAO meal_dao){	
		this.login_controller = login_controller;
		this.connection = connection;
		this.customer_dao = customer_dao;
		this.shop_dao = shop_dao;
		this.meal_dao = meal_dao;
	}
	
	public void openAdminFrame(JFrame frame)
	{
		frame.dispose();
		AdminFrame admin_frame = new AdminFrame(this, login_controller);
		admin_frame.setVisible(true);
	}
	public void openAdminShopFrame(JFrame frame)
	{
		frame.dispose();
		TableModelUtility table_utility = new TableModelUtility();
		AdminShopFrame admin_shop_frame = new AdminShopFrame(this);
		IstatUtils istat_utils = new IstatUtils();
		List<String> provinces = istat_utils.getProvinces();
		admin_shop_frame.getAddress_provinceCB().addItem("Seleziona provincia");
		admin_shop_frame.getAddress_provinceCB().addItem("-------------------");
		for(String s : provinces)
				admin_shop_frame.getAddress_provinceCB().addItem(s);
		try {
			List<Shop> shop_list = shop_dao.getAllShops();
		    table_utility.initializeShopTable(admin_shop_frame.getModel(), shop_list);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore imprevisto. Contattare l' admin.","Error",JOptionPane.ERROR_MESSAGE);
		}
	    admin_shop_frame.setVisible(true);
	    return;
	}
	
	public void openAdminRiderFrame(AdminShopFrame admin_shop_frame)
	{
		AdminRiderFrame admin_rider_frame = new AdminRiderFrame();
		if(initializeAdminRiderFrameTable(admin_rider_frame, admin_shop_frame))
			admin_rider_frame.setVisible(true);
		return;
	}
	
	public void openAdminMealFrame(JFrame frame)
	{
		frame.dispose();
		TableModelUtility table_utility = new TableModelUtility();
		AdminMealFrame admin_meal_frame = new AdminMealFrame(this);
		try {
			List<Meal> meal_list = meal_dao.getAllMeals();
			table_utility.initializeMealTable(admin_meal_frame.getModel(), meal_list);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore imprevisto. Contattare l' admin.","Error",JOptionPane.ERROR_MESSAGE);
		}
		admin_meal_frame.setVisible(true);
		return;
	}
	
	public void openAdminCustomerFrame(JFrame frame)
	{
		frame.dispose();
		TableModelUtility table_utility = new TableModelUtility();
		AdminCustomerFrame admin_customer_frame = new AdminCustomerFrame(this);
		List<Customer> customer_list;
		try {
			customer_list = customer_dao.getAllCustomers();
			table_utility.initializeCustomerTable(admin_customer_frame.getModel(), customer_list);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore imprevisto. Contattare l' admin.","Error",JOptionPane.ERROR_MESSAGE);
		}
		admin_customer_frame.setVisible(true);
		return;
	}
		
	public boolean initializeAdminRiderFrameTable(AdminRiderFrame admin_rider_frame, AdminShopFrame admin_shop_frame)
	{
		int row = admin_shop_frame.getTable().getSelectedRow();
	    TableModelUtility table_utility = new TableModelUtility();
		if(row != -1) {
			List<Rider>rider_list = new ArrayList<Rider>();
			String shop_email = admin_shop_frame.getTable().getValueAt(row, 0).toString();
			try {
				rider_list = shop_dao.getRidersOfAShopByShopEmail(shop_email);
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null, "Errore imprevisto. Contattare l' admin.","Error",JOptionPane.ERROR_MESSAGE);
			}
			if(!rider_list.isEmpty())
			{
				table_utility.initializeRiderTable(admin_rider_frame.getModel(), rider_list);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Il negozio selezionato non ha rider al momento","Error",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Selezionare uno shop di cui visualizare i rider impiegati","Error",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void addShop(AdminShopFrame admin_shop_frame) 
	{
		try {
			Shop shop = new Shop(admin_shop_frame.getEmailTF().getText(),admin_shop_frame.getNameTF().getText(), admin_shop_frame.getPasswordTF().getText(), admin_shop_frame.getWorking_hoursTF().getText(),
					 new Address(admin_shop_frame.getAddress_nameTF().getText(), admin_shop_frame.getAddress_civic_numberTF().getText(), admin_shop_frame.getAddress_capTF().getText(), 
				     admin_shop_frame.getAddress_townCB().getSelectedItem().toString(), admin_shop_frame.getAddress_provinceCB().getSelectedItem().toString()), admin_shop_frame.getClosing_daysTF().getText(), null, null, admin_shop_frame.getHome_phoneTF().getText());
			shop_dao.insertShop(shop);
			admin_shop_frame.getModel().insertRow(admin_shop_frame.getModel().getRowCount(), new Object[] {shop.getEmail(), shop.getPassword(),
			shop.getName(), shop.getAddress().toString(),shop.getWorking_hours(), shop.getClosing_days(), shop.getHome_phone()});
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Inserire correttamente i campi.\nHint: Controlla la validita' dell' indirizzo, email, orario lavorativo e giorni di chiusura","Error",JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	public void addMeal(AdminMealFrame admin_meal_frame) {
		
		InputUtility input_util = new InputUtility();
		List<String> allergens = new ArrayList<String>();
		for(JCheckBox cb : admin_meal_frame.getAllergens()) {
			if(cb.isSelected())
				allergens.add(cb.getText());
		}
		
		try {
			Meal meal = new Meal(admin_meal_frame.getNameTF().getText(), Float.parseFloat(admin_meal_frame.getPriceTF().getText()), 
			                     admin_meal_frame.getIngredientsTF().getText(), admin_meal_frame.getDishJCB().getSelectedItem().toString(), allergens);
			meal_dao.insertMeal(meal);
			admin_meal_frame.getModel().insertRow(admin_meal_frame.getModel().getRowCount(), new Object[]{meal.getName(), meal.getCategory(), new DecimalFormat("00.00").format(meal.getPrice()),
														meal.getIngredients(),input_util.arrayListToTokenizedString(allergens, ", ")});
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Inserire correttamente i campi","Error",JOptionPane.ERROR_MESSAGE);
		}
		 catch (NumberFormatException e)
		{
			 JOptionPane.showMessageDialog(null, "Inserci un prezzo valido ","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	public void removeShop(AdminShopFrame admin_shop_frame)
	{
		int row = admin_shop_frame.getTable().getSelectedRow();
		if(row != -1) {
			String shop_email = admin_shop_frame.getTable().getValueAt(row, 0).toString();
			try {
				Shop shop_to_remove = shop_dao.getShopByEmail(shop_email);
				shop_dao.deleteShop(shop_to_remove);
				admin_shop_frame.getModel().removeRow(row);
				JOptionPane.showMessageDialog(null, "Negozio cancellato dal sistema");
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null, "Non e' stato possibile cancellare il negozio selezionato, contattare l' amministratore","Error",JOptionPane.ERROR_MESSAGE);
			}
	}
		else
			JOptionPane.showMessageDialog(null, "Selezionare il negozio che desideri cancellare","Errore",JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	public void removeMeal(AdminMealFrame admin_meal_frame)
	{
		int row = admin_meal_frame.getTable().getSelectedRow();
		if(row!=-1)
		{
			String name_of_meal_to_delete = admin_meal_frame.getTable().getModel().getValueAt(row, 0).toString();
			try {
				Meal meal = meal_dao.getMealByName(name_of_meal_to_delete);
				meal_dao.deleteMeal(meal);
				admin_meal_frame.getModel().removeRow(row);
				JOptionPane.showMessageDialog(null, "Selected meal deleted successfully");
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null, "Could not delete selected meal, try again or contact the administrator","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Select the meal you want to delete","Errore",JOptionPane.ERROR_MESSAGE);
		return;
	}

	
	public void updateShop(AdminShopFrame admin_shop_frame) {

		int row = admin_shop_frame.getTable().getSelectedRow();
		TableModelUtility table_utility = new TableModelUtility();
		if(row != -1) {
			String email_of_shop_to_update = admin_shop_frame.getTable().getModel().getValueAt(row, 0).toString();
			try {
			Shop shop_to_update = shop_dao.getShopByEmail(email_of_shop_to_update);
			shop_to_update.setName(admin_shop_frame.getNameTF().getText());
			shop_to_update.setAddress(new Address(admin_shop_frame.getAddress_nameTF().getText(),
					admin_shop_frame.getAddress_civic_numberTF().getText(), admin_shop_frame.getAddress_capTF().getText(),
					admin_shop_frame.getAddress_townCB().getSelectedItem().toString(), admin_shop_frame.getAddress_provinceCB().getSelectedItem().toString()));
			shop_to_update.setClosing_days(admin_shop_frame.getClosing_daysTF().getText());
			shop_to_update.setWorking_hours(admin_shop_frame.getWorking_hoursTF().getText());
			shop_to_update.setPassword(admin_shop_frame.getPasswordTF().getText());
			shop_to_update.setEmail(admin_shop_frame.getEmailTF().getText());
			shop_to_update.setHome_phone(admin_shop_frame.getHome_phoneTF().getText());
			shop_dao.updateShop(shop_to_update, email_of_shop_to_update);
			table_utility.updateShopTableColumns(admin_shop_frame, row, shop_to_update);
				JOptionPane.showMessageDialog(null, "Shop aggiornato con successo");
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null, "Inserire correttamente i campi.\nHint: Controlla la validita' dell' indirizzo, email, orario lavorativo e giorni di chiusura","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Select the shop you want to update","Warning",JOptionPane.WARNING_MESSAGE);
		return;
	}
	
	public void updateAddressTownsCB(String selected_province, AdminShopFrame frame)
	{	
		IstatUtils istat_utils = new IstatUtils();
	    List<String> towns = istat_utils.getTownsByProvince(selected_province);
		frame.getAddress_townCB().removeAllItems();
		for(String s : towns)
			frame.getAddress_townCB().addItem(s);
		return;
	}
	
	public void releaseAllDaoResourcesAndDisposeFrame(JFrame frame)
	{
		DButility db_utility = new DButility();
		try {
			shop_dao.closeStatements();
			meal_dao.closeStatements();
			customer_dao.closeStatements();
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
