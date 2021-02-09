package controllers;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daos_implementation.MealDAOPostgresImplementation;
import daos_implementation.OrderDAOPostgresImplementation;
import daos_implementation.RiderDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.MealDAO;
import daos_interfaces.OrderDAO;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import entities.Address;
import entities.Meal;
import entities.Order;
import entities.Rider;
import exceptions.DaoException;
import gui.AdminRiderFrame;
import gui.ShopAllMealsFrame;
import gui.ShopDeliveringOrdersFrame;
import gui.ShopFrame;
import gui.ShopMealFrame;
import gui.ShopOrderManagementFrame;
import gui.ShopPendingOrdersFrame;
import gui.ShopRiderFrame;
import gui.ShopViewOrdersFrame;
import utilities.CodiceFiscaleUtility;
import utilities.DButility;
import utilities.InputUtility;
import utilities.TableModelUtility;

public class ShopController {
	
	private String current_shop_email;
	private TableModelUtility table = new TableModelUtility();
	private List<Rider> rider_list = new ArrayList<Rider>();
	private List<Meal> meal_list = new ArrayList<Meal>();
	private List<Order> all_order_list = new ArrayList<Order>();
	private List<Order> pending_order_list = new ArrayList<Order>();
	private List<Order> delivering_order_list = new ArrayList<Order>();
	OrderDAO order_dao= new OrderDAOPostgresImplementation();
	
	public ShopController(String email) 
	{
		this.current_shop_email=email;
	}
	
	public void openShopFrame(JFrame frame)
	{
		frame.dispose();
		ShopFrame shop_frame = new 	ShopFrame(this);
		shop_frame.setVisible(true);
		return;
	}
	
	public void openShopMealFrame(JFrame frame)
	{
		frame.dispose();
		ShopMealFrame shop_meal_frame = new ShopMealFrame(this);
		initializeMealList();
		table.initializeMealTable(shop_meal_frame.getModel(), meal_list);
		shop_meal_frame.setVisible(true);
		return;
	}
	
	public void openShopRiderFrame(JFrame frame)
	{
		frame.dispose();
		ShopRiderFrame shop_rider_frame = new ShopRiderFrame(this);
		initializeRiderList();
		table.initializeRiderTable(shop_rider_frame.getModel(), rider_list);
		shop_rider_frame.setVisible(true);
		return;
	}
	
	public void openShopAllMealsFrame()
	{
		ShopAllMealsFrame shop_all_meals_frame = new ShopAllMealsFrame(this);
		List<Meal> meal_list = getAllMealsExceptShopMeals();
		table.initializeMealTable(shop_all_meals_frame.getModel(), meal_list);
		shop_all_meals_frame.setVisible(true);
		return;
	}

	public void openAdminRiderFrame() {
		AdminRiderFrame admin_rider_frame = new AdminRiderFrame();
		if(rider_list.isEmpty()) {
			try {
				ShopDAO shop_dao = new ShopDAOPostgresImplementation();
				rider_list = shop_dao.getRidersOfAShopByShopEmail(current_shop_email);
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null,"An error has occurred, please try again or contact the administrator", "Error",JOptionPane.ERROR_MESSAGE);
			} 
		}
		table.initializeRiderTable(admin_rider_frame.getModel(), rider_list);
		admin_rider_frame.setVisible(true);
	}
	
	public void openShopOrderManagementFrame(JFrame frame)
	{
		frame.dispose();
		ShopOrderManagementFrame shop_order_management_frame = new ShopOrderManagementFrame(this);
		shop_order_management_frame.setVisible(true);
		return;
	}
	
	public void openShopViewOrdersFrame(JFrame frame)
	{
		frame.dispose();
		ShopViewOrdersFrame shop_view_orders_frame = new ShopViewOrdersFrame(this);
		initializeAllOrdersList();
		table.initializeCompletedOrderTable(shop_view_orders_frame.getModel(), all_order_list);
		shop_view_orders_frame.setVisible(true);
	}
	
	public void openShopDeliveringOrdersFrame(JFrame frame) {
		frame.dispose();
		ShopDeliveringOrdersFrame shop_delivering_orders_frame = new ShopDeliveringOrdersFrame(this);
		initializeDeliveringOrdersList();
		table.initializeDeliveringOrderTable(shop_delivering_orders_frame.getModel(), delivering_order_list);
		shop_delivering_orders_frame.setVisible(true);
	}
	
	public void openShopPendingOrdersFrame(JFrame frame) {
		frame.dispose();
		ShopPendingOrdersFrame shop_pending_orders_frame = new ShopPendingOrdersFrame(this);
		initializePendingOrdersList();
		table.initializePendingOrderTable(shop_pending_orders_frame.getModel(), pending_order_list);
		shop_pending_orders_frame.setVisible(true);
	}
	
	public void initializePendingOrdersList() {
		OrderDAO order_dao= new OrderDAOPostgresImplementation();
		if (pending_order_list.isEmpty()) {
			try {
				pending_order_list = order_dao.getPendingOrdersOfAShop(current_shop_email);
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			} 
		}
		return;
	}
	
	public void initializeDeliveringOrdersList() {
		if (delivering_order_list.isEmpty()) {
			try {
				delivering_order_list = order_dao.getInDeliveryOrdersOfAShop(current_shop_email);
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			} 
		}
		return;
	}
	
	public void initializeAllOrdersList()
	{
		OrderDAO order_dao= new OrderDAOPostgresImplementation();
		if (all_order_list.isEmpty()) {
			try {
				all_order_list = order_dao.getOrdersOfAShopByShopEmail(current_shop_email);
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			} 
		}
		return;
	}
	
	public void initializeMealList()
	{
		if (meal_list.isEmpty()) {
			try {
				ShopDAO shop_dao = new ShopDAOPostgresImplementation();
				meal_list = shop_dao.getMealsOfAShopByShopEmail(current_shop_email);
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null,
						"An error has occurred, please try again or contact the administrator", "Error",
						JOptionPane.ERROR_MESSAGE);
			} 
		}
		return;
	}
	
	public void initializeRiderList() {
		
		if (rider_list.isEmpty()) {
			try {
				ShopDAO shop_dao = new ShopDAOPostgresImplementation();
				rider_list = shop_dao.getRidersOfAShopByShopEmail(current_shop_email);
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null,"An error has occurred, please try again or contact the administrator", "Error",JOptionPane.ERROR_MESSAGE);
			} 
		}
	}
	
	public List<Meal> getAllMealsExceptShopMeals() {
		List<Meal> meal_list = new ArrayList<Meal>();
		try {
			MealDAO meal_dao = new MealDAOPostgresImplementation();
			meal_list = meal_dao.getAllMealsExceptShopMeals(current_shop_email);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred, please try again or contact the administrator","Error",JOptionPane.ERROR_MESSAGE);
		}
		return meal_list;
	}
	
	public void addMeal(ShopMealFrame shop_meal_frame) {
		
		List<Meal> meal_list = new ArrayList<Meal>();
		int i=0;
			
		try {
			MealDAO meal_dao = new MealDAOPostgresImplementation();
			meal_list = meal_dao.getAllMealsExceptShopMeals(current_shop_email);
			while(!meal_list.get(i).getName().equals(shop_meal_frame.getMealTF().getText()))
				i++;
			meal_dao.insertSupply(current_shop_email,meal_list.get(i));
			shop_meal_frame.getModel().insertRow(shop_meal_frame.getModel().getRowCount(), new Object[]{meal_list.get(i).getName(),
					meal_list.get(i).getCategory(), new DecimalFormat("00.00").format(meal_list.get(i).getPrice()),
					meal_list.get(i).getIngredients(),meal_list.get(i).getAllergen_list().toString().substring(1,meal_list.get(i).getAllergen_list().toString().length()-1)});
			meal_list.remove(meal_list.get(i));
			JOptionPane.showMessageDialog(null, "Meal succesfully added");
		}
		catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred, please try again or contact the administrator","Errore",JOptionPane.ERROR_MESSAGE);
		}catch (IndexOutOfBoundsException in) {
			JOptionPane.showMessageDialog(null, "Wrong meal name or meal already inserted","Errore",JOptionPane.ERROR_MESSAGE);
		}
			return;
	}
	
	public void addRider(ShopRiderFrame shop_rider_frame) {
		
		InputUtility input_util = new InputUtility();
		CodiceFiscaleUtility codice_fiscale = new CodiceFiscaleUtility();
		try {
			RiderDAO rider_dao = new RiderDAOPostgresImplementation();
			String name = shop_rider_frame.getNameTF().getText();
			String surname = shop_rider_frame.getSurnameTF().getText();
			String birth_date = shop_rider_frame.getBirth_dateTF().getText();
			String birth_place_town = shop_rider_frame.getBirth_placeTF().getText();
			String gender = shop_rider_frame.getGenderCB().getSelectedItem().toString().substring(0,1);
			String CF = codice_fiscale.getCF(name,surname ,	new SimpleDateFormat("dd/MM/yyyy").parse(birth_date) ,birth_place_town ,gender.charAt(0));
			if(!CF.equals("Errore!"))
			{
			Rider rider = new Rider(CF,shop_rider_frame.getNameTF().getText(), shop_rider_frame.getSurnameTF().getText(), 
					new SimpleDateFormat("dd/MM/yyyy").parse(shop_rider_frame.getBirth_dateTF().getText()), shop_rider_frame.getBirth_placeTF().getText(), 
					shop_rider_frame.getGenderCB().getSelectedItem().toString().substring(0,1), shop_rider_frame.getCellphoneTF().getText(),
					new Address(shop_rider_frame.getAddress_nameTF().getText(), shop_rider_frame.getAddress_civic_numberTF().getText(), 
					shop_rider_frame.getAddress_capTF().getText(), shop_rider_frame.getAddress_cityTF().getText(), 
					shop_rider_frame.getAddress_provinceTF().getText()), shop_rider_frame.getVehicleCB().getSelectedItem().toString(), 
					shop_rider_frame.getWorking_hoursTF().getText(),(short)0);
			rider_dao.insertRider(rider,current_shop_email);
			shop_rider_frame.getModel().insertRow(rider_list.size(), new Object[] {rider.getCf(), rider.getName(), rider.getSurname(), 
					input_util.formatDate(rider.getBirth_date()), rider.getBirth_place(), rider.getAddress().toString(), rider.getGender(), rider.getCellphone(), 
					rider.getVehicle(), rider.getWorking_hours(), rider.getDeliveries_number()});
			rider_list.add(rider);
			JOptionPane.showMessageDialog(null, "This rider can now work for this shop");
			
		} }
		catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Please, fill correctly the text fields.\nHint: Check the validity of the address, birth_date, working hours and cellphone","Error",JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "Insert date in a valid format","Errore",JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	public void removeMeal(ShopMealFrame shop_meal_frame){
		
		if(shop_meal_frame.getTable().getSelectedRow() != -1) {

			int selected_row = shop_meal_frame.getTable().getSelectedRow();
			String meal_name_to_remove = shop_meal_frame.getTable().getValueAt(selected_row, 0).toString();
			int i = 0;
			try {
				MealDAO meal_dao = new MealDAOPostgresImplementation();
				while(!meal_list.get(i).getName().equals(meal_name_to_remove))
					i++;
				meal_dao.deleteFromSupply(current_shop_email, meal_list.get(i));
				shop_meal_frame.getModel().removeRow(selected_row);
				meal_list.remove(i);
				JOptionPane.showMessageDialog(null, "Selected shop deleted successfully");
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null, "Errors while deleting selected shop","Errore",JOptionPane.ERROR_MESSAGE);
			}
	}
		else
			JOptionPane.showMessageDialog(null, "Select the shop you want to delete","Warning",JOptionPane.WARNING_MESSAGE);
		return;
	
	}
	
	public void removeRider(ShopRiderFrame shop_rider_frame) {
		
		if(shop_rider_frame.getTable().getSelectedRow() != -1) {

			int selected_row = shop_rider_frame.getTable().getSelectedRow();
			String cf_of_the_rider_to_dismiss = shop_rider_frame.getTable().getValueAt(selected_row, 0).toString();
			int i = 0;
			try {
				RiderDAO rider_dao = new RiderDAOPostgresImplementation();
				while(!rider_list.get(i).getCf().equals(cf_of_the_rider_to_dismiss))
					i++;
				rider_dao.dismissRider(rider_list.get(i));
				shop_rider_frame.getModel().removeRow(selected_row);
				rider_list.remove(i);
				JOptionPane.showMessageDialog(null, "Selected rider dismissed successfully");
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null, "Errors while trying to dismiss selected rider","Error",JOptionPane.ERROR_MESSAGE);
			}
	}
		else
			JOptionPane.showMessageDialog(null, "Select the rider you want to dismiss","Error",JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	public void updateRider(ShopRiderFrame shop_rider_frame)
	{
	
		if(shop_rider_frame.getTable().getSelectedRow() != -1) {
			int selected_row = shop_rider_frame.getTable().getSelectedRow();
			String cf_of_rider_to_update = shop_rider_frame.getTable().getModel().getValueAt(selected_row, 0).toString();
			CodiceFiscaleUtility codice_fiscale = new CodiceFiscaleUtility();
			int i = 0;
			try {
			RiderDAO rider_dao = new RiderDAOPostgresImplementation();
			while(!rider_list.get(i).getCf().equals(cf_of_rider_to_update))
				i++;
			String CF = codice_fiscale.getCF(shop_rider_frame.getNameTF().getText(), shop_rider_frame.getSurnameTF().getText(),new SimpleDateFormat("dd/MM/yyyy").parse(shop_rider_frame.getBirth_dateTF().getText()),shop_rider_frame.getBirth_placeTF().getText(),shop_rider_frame.getGenderCB().getSelectedItem().toString().charAt(0) );
			if(!CF.equals("Errore!"))
			{
			rider_list.get(i).setCf(CF);
			rider_list.get(i).setName(shop_rider_frame.getNameTF().getText());
			rider_list.get(i).setSurname(shop_rider_frame.getSurnameTF().getText());
			rider_list.get(i).setAddress(new Address(shop_rider_frame.getAddress_nameTF().getText(),
					shop_rider_frame.getAddress_civic_numberTF().getText(), shop_rider_frame.getAddress_capTF().getText(),
					shop_rider_frame.getAddress_cityTF().getText(), shop_rider_frame.getAddress_provinceTF().getText()));
			rider_list.get(i).setCellphone(shop_rider_frame.getCellphoneTF().getText());
			rider_list.get(i).setGender(shop_rider_frame.getGenderCB().getSelectedItem().toString().substring(0,1));
			rider_list.get(i).setVehicle(shop_rider_frame.getVehicleCB().getSelectedItem().toString());
			rider_list.get(i).setWorking_hours(shop_rider_frame.getWorking_hoursTF().getText());
			rider_list.get(i).setBirth_date(new SimpleDateFormat("dd/MM/yyyy").parse(shop_rider_frame.getBirth_dateTF().getText()));
			rider_list.get(i).setBirth_place(shop_rider_frame.getBirth_placeTF().getText());
			rider_dao.updateRider(rider_list.get(i));
			updateRiderTableColumns(shop_rider_frame, selected_row, rider_list.get(i));
				JOptionPane.showMessageDialog(null, "Selected shop updated succesfully");
			} }catch (DaoException e) {
				JOptionPane.showMessageDialog(null, "Please, fill correctly the text fields.\nHint: Check the validity of the address, birth_date, working hours and cellphone","Error",JOptionPane.ERROR_MESSAGE);
			}
		      catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Insert date in a valid format","Errore",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Select the shop you want to update","Errore",JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	public void updateRiderTableColumns(ShopRiderFrame shop_rider_frame, int selected_row, Rider rider)
	{
		InputUtility date_util = new InputUtility();
		Object[] row = new Object[11];
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
		for (int i=0; i<10; i++)
		shop_rider_frame.getModel().setValueAt(row[i], selected_row, i);
		return;
	}
	
	public void updatePendingOrder(ShopPendingOrdersFrame shop_pending_orders_frame) {
		OrderDAO order_dao = new OrderDAOPostgresImplementation();
		int i=0;
		try {
			while(!pending_order_list.get(i).getId().equals(shop_pending_orders_frame.getOrderTF().getText()))
				i++;
			order_dao.updatePendingOrder(pending_order_list.get(i), shop_pending_orders_frame.getRiderTF().getText());
			delivering_order_list.add(pending_order_list.get(i));
			pending_order_list.remove(i);
			shop_pending_orders_frame.getModel().removeRow(i);
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "ID non trovato. Riprovare! ","Errore",JOptionPane.ERROR_MESSAGE);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore. Riprovare! ","Errore",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateDeliveringOrder(ShopDeliveringOrdersFrame shop_delivering_order_frame) {
		OrderDAO order_dao = new OrderDAOPostgresImplementation();
		int i=0;
		try {
			while(!delivering_order_list.get(i).getId().equals(shop_delivering_order_frame.getOrderTF().getText()))
				i++;
			order_dao.updateDeliveringOrder(delivering_order_list.get(i), shop_delivering_order_frame.getStatusCB().getSelectedItem().toString());
			all_order_list.add(delivering_order_list.get(i));
			delivering_order_list.remove(i);
			shop_delivering_order_frame.getModel().removeRow(i);
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "ID non trovato. Riprovare! ","Errore",JOptionPane.ERROR_MESSAGE);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore. Riprovare! ","Errore",JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getcurrent_shop_email() {
		return current_shop_email;
	}

	public void setcurrent_shop_email(String current_shop_email) {
		this.current_shop_email = current_shop_email;
	}
	
	public void closeWindow(JFrame frame)
	{
		DButility db_utility = new DButility();
		frame.dispose();
		db_utility.closeCurrentConnection();
		return;
	}
}
