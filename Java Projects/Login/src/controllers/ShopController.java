package controllers;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daos_implementation.MealDAOPostgresImplementation;
import daos_interfaces.MealDAO;
import entities.Meal;
import gui.AdminShopFrame;
import gui.ShopAllMealsFrame;
import gui.ShopFrame;
import gui.ShopMealFrame;
import gui.ShopRiderFrame;
import utilities.TableModelUtility;

public class ShopController {
	String id;
	private TableModelUtility table = new TableModelUtility();
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
		shop_rider_frame.setVisible(true);
		return;
	}
	
	public void openShopAllMealsFrame(ShopMealFrame shop_meal_frame)
	{
		
		ShopAllMealsFrame shop_all_meals_frame = new ShopAllMealsFrame(this);
		initializeShopAllMealFrameTable(shop_all_meals_frame);
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
		table.initializeMealTable(shop_meal_frame,shop_meal_frame.getModel(), meal_list);
		return;
	}
	
	public void initializeShopAllMealFrameTable(ShopAllMealsFrame shop_all_meals_frame) {
		List<Meal> meal_list = new ArrayList<Meal>();
		try {
			meal_list = meal_dao.getMealToAdd(id);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeMealTable(shop_all_meals_frame,shop_all_meals_frame.getModel(), meal_list);
	}
	
	
	
	
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
