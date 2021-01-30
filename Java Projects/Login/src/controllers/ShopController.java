package controllers;


import java.sql.SQLException;
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
import daos_interfaces.MealDAO;
import daos_interfaces.OrderDAO;
import daos_interfaces.RiderDAO;
import entities.Address;
import entities.Meal;
import entities.Order;
import entities.Rider;
import exceptions.DaoException;
import gui.ShopAllMealsFrame;
import gui.ShopFrame;
import gui.ShopMealFrame;
import gui.ShopOrderManagementFrame;
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
	
	public void openShopMealFrame(ShopFrame shop_frame)
	{
		shop_frame.dispose();
		ShopMealFrame shop_meal_frame = new ShopMealFrame(this);
		initializeShopMealFrameTable(shop_meal_frame);
		shop_meal_frame.setVisible(true);
		return;
	}
	
	public void openShopRiderFrame(ShopFrame shop_frame)
	{
		shop_frame.dispose();
		ShopRiderFrame shop_rider_frame = new ShopRiderFrame(this);
		initializeShopRiderFrameTable(shop_rider_frame);
		shop_rider_frame.setVisible(true);
		return;
	}
	
	public void openShopAllMealsFrame()
	{
		
		ShopAllMealsFrame shop_all_meals_frame = new ShopAllMealsFrame(this);
		initializeShopAllMealsFrameTable(shop_all_meals_frame);
		shop_all_meals_frame.setVisible(true);
		return;
	}
	
	public void openShopOrderManagementFrame(ShopFrame shop_frame)
	{
		shop_frame.dispose();
		ShopOrderManagementFrame shop_order_management_frame = new ShopOrderManagementFrame(this);
		shop_order_management_frame.setVisible(true);
		return;
	}
	
	public void openShopViewOrdersFrame(JFrame frame)
	{
		frame.dispose();
		ShopViewOrdersFrame shop_view_orders_frame = new ShopViewOrdersFrame(this);
		initializeShopOrderManagementFrame(shop_view_orders_frame);
		shop_view_orders_frame.setVisible(true);
	}
	
	public void initializeShopOrderManagementFrame(ShopViewOrdersFrame shop_view_orders_frame)
	{
		List<Order> order_list=null;
		OrderDAO order_dao= new OrderDAOPostgresImplementation();
		try {
			order_list = order_dao.getOrdersOfAShopByShopEmail(current_shop_email);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeOrderTable(shop_view_orders_frame.getModel(), order_list);
		return;
	}
	
	public void initializeShopMealFrameTable(ShopMealFrame shop_meal_frame)
	{
		try {
			MealDAO meal_dao = new MealDAOPostgresImplementation();
			meal_list = meal_dao.getMealsOfAShopByShopEmail(current_shop_email);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred, please try again or contact the administrator","Error",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeMealTable(shop_meal_frame.getModel(), meal_list);
		return;
	}
	
	public void initializeShopRiderFrameTable(ShopRiderFrame shop_rider_frame) {
		
		try {
			RiderDAO rider_dao = new RiderDAOPostgresImplementation();
			rider_list = rider_dao.getRidersOfAShopByShopEmail(current_shop_email);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred, please try again or contact the administrator","Error",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeRiderTable(shop_rider_frame.getModel(), rider_list);
	}
	
	public void initializeShopAllMealsFrameTable(ShopAllMealsFrame shop_all_meals_frame) {
		List<Meal> meal_list = new ArrayList<Meal>();
		try {
			MealDAO meal_dao = new MealDAOPostgresImplementation();
			meal_list = meal_dao.getAllMealsExceptShopMeals(current_shop_email);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred, please try again or contact the administrator","Error",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeMealTable(shop_all_meals_frame.getModel(), meal_list);
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
			Rider rider = new Rider(codice_fiscale.getCF(name,surname ,	new SimpleDateFormat("dd/MM/yyyy").parse(birth_date) ,birth_place_town ,gender.charAt(0)),shop_rider_frame.getNameTF().getText(), shop_rider_frame.getSurnameTF().getText(), 
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
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Please, fill correctly the text fields.\nHint: Check the validity of the address, birth_date, working hours and cellphone","Error",JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "Insert date in a valid format","Errore",JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	public void removeMeal(ShopMealFrame shop_meal_frame){
		
		if(shop_meal_frame.getShop_meals_table().getSelectedRow() != -1) {

			int selected_row = shop_meal_frame.getShop_meals_table().getSelectedRow();
			String meal_name_to_remove = shop_meal_frame.getShop_meals_table().getValueAt(selected_row, 0).toString();
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
			int i = 0;
			try {
			RiderDAO rider_dao = new RiderDAOPostgresImplementation();
			while(!rider_list.get(i).getCf().equals(cf_of_rider_to_update))
				i++;
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
			} catch (DaoException e) {
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
