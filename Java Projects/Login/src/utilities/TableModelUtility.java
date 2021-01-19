package utilities;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import entities.Customer;
import entities.Meal;
import entities.Shop;
import gui.AdminCustomerFrame;
import gui.AdminMealFrame;
import gui.AdminShopFrame;

public class TableModelUtility {
	
	public void initializeShopTable(AdminShopFrame admin_shop_frame,  ArrayList<Shop> shop_list)
	{
		String[]columns = {"ID", "Password", "Name", "Address", "Working Hours", "Closing Days"};
		Object[] row = new Object[6];
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		admin_shop_frame.getTable().setModel(model);
		String formatted_closing_days;
		for(Shop shop : shop_list) {
			   
			formatted_closing_days="";
			row[0] = shop.getId();
			row[1] = shop.getPassword();
			row[2] = shop.getName();
			row[3] = shop.getAddress();
			row[4] = shop.getWorking_hours();
			row[5] = shop.getClosing_days();
			model.addRow(row);
		}
		return;
	}
	
	public void initializeCustomerTable(AdminCustomerFrame admin_customer_frame,  ArrayList<Customer> customer_list)
	{
		String[]columns = {"CF", "Name", "Surname","Birth Date","Birth Place", "Address", "Gender", "Cellphone","Email","Password"};
		Object[] row = new Object[10];
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		admin_customer_frame.getTable().setModel(model);
		InputUtility date_util = new InputUtility();
		for(Customer customer : customer_list) {
			   
			row[0] = customer.getCf();
			row[1] = customer.getName();
			row[2] = customer.getSurname();
			row[3] = date_util.formatDate(customer.getBirth_date());
			row[4] = customer.getBirth_place();
			row[5] = customer.getAddress().toString();
			row[6] = customer.getGender();
			row[7] = customer.getCellphone();
			row[8] = customer.getEmail();
			row[9] = customer.getPassword();
			model.addRow(row);
		}
		return;
	}
	
	public void initializeMealTable(AdminMealFrame admin_meal_frame, ArrayList<Meal> meal_list) {
		String[] columns = {"Name", "Category", "Price", "ingredients", "Allergens"};
		Object[] row = new Object[5];
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		admin_meal_frame.getTable().setModel(model);
		for(Meal meal : meal_list) {
			row[0] = meal.getName();
			row[1] = meal.getCategory();
			row[2] = meal.getPrice();
			row[3] = meal.getIngredients();
			row[4] = meal.getAllergen_list();
			model.addRow(row);
		}
	}

}
