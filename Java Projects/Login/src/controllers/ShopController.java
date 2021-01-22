package controllers;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daos_implementation.MealDAOPostgresImplementation;
import daos_implementation.RiderDAOPostgresImplementation;
import daos_interfaces.MealDAO;
import daos_interfaces.RiderDAO;
import entities.Address;
import entities.Meal;
import entities.Rider;
import gui.ShopAllMealsFrame;
import gui.ShopFrame;
import gui.ShopMealFrame;
import gui.ShopRiderFrame;
import utilities.InputUtility;
import utilities.TableModelUtility;

public class ShopController {
	
	String id;
	private TableModelUtility table = new TableModelUtility();
	private RiderDAO rider_dao = new RiderDAOPostgresImplementation();
	private List<Rider> rider_list = new ArrayList<Rider>();
	private MealDAO meal_dao = new MealDAOPostgresImplementation();
	private List<Meal> meal_list = new ArrayList<Meal>();
	
	
	public ShopController(String id) 
	{
		this.id=id;
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
	
	public void initializeShopMealFrameTable(ShopMealFrame shop_meal_frame)
	{
		try {
			meal_list = meal_dao.getMealsOfAShopByShopId(id);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeMealTable(shop_meal_frame.getModel(), meal_list);
		return;
	}
	
	public void initializeShopRiderFrameTable(ShopRiderFrame shop_rider_frame) {
		
		try {
			rider_list = rider_dao.getRidersOfAShopByShopId(id);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeRiderTable(shop_rider_frame.getModel(), rider_list);
	}
	
	public void initializeShopAllMealsFrameTable(ShopAllMealsFrame shop_all_meals_frame) {
		List<Meal> meal_list = new ArrayList<Meal>();
		try {
			meal_list = meal_dao.getAllMealsExceptShopMeals(id);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeMealTable(shop_all_meals_frame.getModel(), meal_list);
	}
	
	public void addMeal(ShopMealFrame shop_meal_frame) {
		
		List<Meal> meal_list = new ArrayList<Meal>();
		int i=0;
			
		try {
			meal_list = meal_dao.getAllMealsExceptShopMeals(id);
			while(!meal_list.get(i).getName().equals(shop_meal_frame.getMealTF().getText()))
				i++;
			meal_dao.insertSupply(id,meal_list.get(i));
			shop_meal_frame.getModel().insertRow(meal_list.size(), new Object[]{meal_list.get(i).getName(),
					meal_list.get(i).getCategory(), meal_list.get(i).getPrice(),
					meal_list.get(i).getIngredients(),meal_list.get(i).getAllergen_list()});
			meal_list.add(meal_list.get(i));
			
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}catch (IndexOutOfBoundsException in) {
			JOptionPane.showMessageDialog(null, "Strunz! Hai sbagliato a scrivere","Errore",JOptionPane.ERROR_MESSAGE);
		}
			return;
	}
	
	public void addRider(ShopRiderFrame shop_rider_frame) {
		
		InputUtility input_util = new InputUtility();
		try {
			Rider rider = new Rider("x2xx1x00x00x00lx",shop_rider_frame.getNameTF().getText(), shop_rider_frame.getSurnameTF().getText(), 
					new SimpleDateFormat("dd/MM/YYYY").parse(shop_rider_frame.getBirth_dateTF().getText()), shop_rider_frame.getBirth_placeTF().getText(), 
					shop_rider_frame.getGenderCB().getSelectedItem().toString().substring(0,1), shop_rider_frame.getCellphoneTF().getText(),
					new Address(shop_rider_frame.getAddress_nameTF().getText(), shop_rider_frame.getAddress_civic_numberTF().getText(), 
					shop_rider_frame.getAddress_capTF().getText(), shop_rider_frame.getAddress_cityTF().getText(), 
					shop_rider_frame.getAddress_provinceTF().getText()), shop_rider_frame.getVehicleCB().getSelectedItem().toString(), 
					shop_rider_frame.getWorking_hoursTF().getText(),(short)0);
			rider_dao.insertRider(rider,id);
			shop_rider_frame.getModel().insertRow(rider_list.size(), new Object[] {rider.getCf(), rider.getName(), rider.getSurname(), 
					input_util.formatDate(rider.getBirth_date()), rider.getBirth_place(), rider.getAddress().toString(), rider.getGender(), rider.getCellphone(), 
					rider.getVehicle(), rider.getWorking_hours(), rider.getDeliveries_number()});
			rider_list.add(rider);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
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
				while(!meal_list.get(i).getName().equals(meal_name_to_remove))
					i++;
				meal_dao.deleteFromSupply(id, meal_list.get(i));
				shop_meal_frame.getModel().removeRow(selected_row);
				meal_list.remove(i);
				JOptionPane.showMessageDialog(null, "Selected shop deleted successfully");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errors while deleting selected shop","Errore",JOptionPane.ERROR_MESSAGE);
			}
	}
		else
			JOptionPane.showMessageDialog(null, "Select the shop you want to delete","Errore",JOptionPane.ERROR_MESSAGE);
		return;
	
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
