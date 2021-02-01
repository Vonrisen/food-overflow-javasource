package utilities;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import entities.Customer;
import entities.Meal;
import entities.Order;
import entities.Rider;
import entities.Shop;
import gui.AdminCustomerFrame;
import gui.AdminShopFrame;

public class TableModelUtility {
	
	public void initializeShopTable(DefaultTableModel model,  List<Shop> shop_list)
	{
		
		Object[] row = new Object[6];
		for(Shop shop : shop_list) {
			row[0] = shop.getEmail();
			row[1] = shop.getPassword();
			row[2] = shop.getName();
			row[3] = shop.getAddress();
			row[4] = shop.getWorking_hours();
			row[5] = shop.getClosing_days();
			model.addRow(row);
		}
		return;
	}
	
	public void initializeCustomerTable( DefaultTableModel model,  List<Customer> customer_list)
	{
		Object[] row = new Object[10];
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
	
	public void initializeMealTable(DefaultTableModel model, List<Meal> meal_list) {
		Object[] row = new Object[5];
		InputUtility input_util = new InputUtility();
		for(Meal meal : meal_list) {
			row[0] = meal.getName();
			row[1] = meal.getCategory();
			row[2] = new DecimalFormat("00.00").format(meal.getPrice());;
			row[3] = meal.getIngredients();
			row[4] = input_util.arrayListToTokenizedString(meal.getAllergen_list(), ", ");
			model.addRow(row);
		}
	}
	
	public void initializeRiderTable(DefaultTableModel model,  List<Rider> rider_list)
	{
		InputUtility date_util = new InputUtility();
		Object[] row = new Object[11];
		for(Rider rider : rider_list) {
			row[0] = rider.getCf();
			row[1] = rider.getName();
			row[2] = rider.getSurname();
			row[3] = date_util.formatDate(rider.getBirth_date());
			row[4] = rider.getBirth_place();
			row[5] = rider.getAddress().toString();
			row[6] = rider.getGender();
			row[7] = rider.getCellphone();
			row[8] = rider.getVehicle();
			row[9] = rider.getWorking_hours();
			row[10] = rider.getDeliveries_number();
			model.addRow(row);
		}
	}
	
	public void updateShopTableColumns(AdminShopFrame admin_shop_frame, int selected_row, Shop shop)
	{
		Object[] row = new Object[6];
		row[0] = shop.getEmail();
		row[1] = shop.getPassword();
		row[2] = shop.getName();
		row[3] = shop.getAddress();
		row[4] = shop.getWorking_hours();
		row[5] = shop.getClosing_days();
		for (int i=0; i<6; i++)
		admin_shop_frame.getModel().setValueAt(row[i], selected_row, i);
		return;
	}
	
	public void updateCustomerTableColumns(AdminCustomerFrame admin_customer_frame, int selected_row)
	{
		Object email = admin_customer_frame.getEmailTF().getText();
		Object password = admin_customer_frame.getPasswordTF().getText();
		admin_customer_frame.getModel().setValueAt(email, selected_row, 8);
		admin_customer_frame.getModel().setValueAt(password, selected_row, 9);
		return;
	}
	
	public void initializeCompletedOrderTable(DefaultTableModel model,  List<Order> order_list) {
		InputUtility date_util = new InputUtility();
		Object[] row = new Object[9];
		for(Order order : order_list) {
			row[0] = order.getId();
			row[1] = date_util.formatDate(order.getDate());
			row[2] = order.getDeliveried_time();
			row[3] = order.getAddress().toString();
			row[4] = order.getStatus();
			row[5] = order.getPayment();
			row[6] = order.getNote();
			row[7] = order.getCustomer().getCf();
			row[8] = order.getRider().getCf();
			model.addRow(row);
		}
	}
	 
	public void initializePendingOrderTable(DefaultTableModel model,  List<Order> order_list){
		InputUtility date_util = new InputUtility();
		Object[] row = new Object[6];
		for(Order order : order_list) {
			row[0] = order.getId();
			row[1] = date_util.formatDate(order.getDate());
			row[2] = order.getAddress().toString();
			row[3] = order.getPayment();
			row[4] = order.getNote();
			row[5] = order.getCustomer().getCf();
			model.addRow(row);
		}
	}

	public void initializeDeliveringOrderTable(DefaultTableModel model,  List<Order> order_list){
		InputUtility date_util = new InputUtility();
		Object[] row = new Object[7];
		for(Order order : order_list) {
			row[0] = order.getId();
			row[1] = date_util.formatDate(order.getDate());
			row[2] = order.getAddress().toString();
			row[3] = order.getPayment();
			row[4] = order.getNote();
			row[5] = order.getCustomer().getCf();
			row[6] = order.getRider().getCf();
			model.addRow(row);
		}
	}

}
