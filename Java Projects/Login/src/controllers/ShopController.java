package controllers;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import daos_implementation.OrderDAOPostgresImplementation;
import daos_implementation.RiderDAOPostgresImplementation;
import daos_interfaces.CustomerDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.OrderDAO;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import entities.Address;
import entities.Meal;
import entities.Order;
import entities.Rider;
import exceptions.CfException;
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
import utilities.IstatUtils;
import utilities.TableModelUtility;

public class ShopController {
	
	private String current_shop_email;
	Connection connection;
	private ShopDAO shop_dao;
	private OrderDAO order_dao;
	private MealDAO meal_dao;
	private RiderDAO rider_dao;
	private CustomerDAO customer_dao;
	
	public ShopController(String email, Connection connection, ShopDAO shop_dao, CustomerDAO customer_dao, MealDAO meal_dao) 
	{
		this.connection = connection;
		this.current_shop_email=email;
		this.shop_dao = shop_dao;
		this.customer_dao = customer_dao;
		this.meal_dao = meal_dao;
		this.rider_dao = new RiderDAOPostgresImplementation(connection);
		this.order_dao = new OrderDAOPostgresImplementation(connection);
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
		TableModelUtility table = new TableModelUtility();
		ShopMealFrame shop_meal_frame = new ShopMealFrame(this);
		List<Meal>meal_list = new ArrayList<Meal>();
		try {
			meal_list = shop_dao.getMealsOfAShopByShopEmail(current_shop_email);
			table.initializeMealTable(shop_meal_frame.getModel(), meal_list);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null,
					"An error has occurred, please try again or contact the administrator", "Error",JOptionPane.ERROR_MESSAGE);
		} 
		shop_meal_frame.setVisible(true);
		return;
	}
	
	public void openShopRiderFrame(JFrame frame)
	{
		
		frame.dispose();
		TableModelUtility table = new TableModelUtility();
		ShopRiderFrame shop_rider_frame = new ShopRiderFrame(this);
		IstatUtils istat_utils = new IstatUtils();
		List<String> nations = istat_utils.getNations();
		List<String> provinces = istat_utils.getProvinces();
		shop_rider_frame.getBirth_nationCB().addItem("ITALIA");
		shop_rider_frame.getAddress_provinceCB().addItem("Seleziona provincia di residenza");
		shop_rider_frame.getAddress_provinceCB().addItem("-------------------");
		for(String s : nations)
			shop_rider_frame.getBirth_nationCB().addItem(s);
		for(String s : provinces)
		{
			shop_rider_frame.getBirth_provinceCB().addItem(s);
			shop_rider_frame.getAddress_provinceCB().addItem(s);
		}
		List<Rider>rider_list = new ArrayList<Rider>();
			try {
				rider_list = shop_dao.getRidersOfAShopByShopEmail(current_shop_email);
				table.initializeRiderTable(shop_rider_frame.getModel(), rider_list);
				shop_rider_frame.setVisible(true);
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null,"An error has occurred, please try again or contact the administrator", "Error",JOptionPane.ERROR_MESSAGE);
			} 
			shop_rider_frame.setVisible(true);
		return;
	}
	
	public void openShopAllMealsFrame()
	{
		ShopAllMealsFrame shop_all_meals_frame = new ShopAllMealsFrame();
		TableModelUtility table = new TableModelUtility();
		List<Meal> meal_list = new ArrayList<Meal>();
		try {
			meal_list = meal_dao.getAllMealsExceptShopMeals(current_shop_email);
			if(!meal_list.isEmpty())
				table.initializeMealTable(shop_all_meals_frame.getModel(), meal_list);
			else
				JOptionPane.showMessageDialog(null, "Lo shop selezionato non vende alimenti","Warning",JOptionPane.WARNING_MESSAGE);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred, please try again or contact the administrator","Error",JOptionPane.ERROR_MESSAGE);
		}
		shop_all_meals_frame.setVisible(true);
		return;
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
		frame.setVisible(false);
		TableModelUtility table = new TableModelUtility();
		ShopViewOrdersFrame shop_view_orders_frame = new ShopViewOrdersFrame(this);
		List<Order> all_orders = new ArrayList<Order>();
		try {
			all_orders = order_dao.getOrdersOfAShopByShopEmail(current_shop_email);
			if(!all_orders.isEmpty())
			{
			    table.initializeCompletedOrderTable(shop_view_orders_frame.getModel(), all_orders);
			    shop_view_orders_frame.setVisible(true);
			}
			else
			{
				frame.setVisible(true);
				JOptionPane.showMessageDialog(null, "Lo shop selezionato non ha ordini completati","Warning",JOptionPane.WARNING_MESSAGE);
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} 

		return;
	}
	public void openAdminRiderFrame()
	{
		AdminRiderFrame admin_rider_frame = new AdminRiderFrame();
		TableModelUtility table = new TableModelUtility();
		List<Rider>employed_rider_list = new ArrayList<Rider>();
		try {
			employed_rider_list = shop_dao.getRidersOfAShopByShopEmail(current_shop_email);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred, please try again or contact the administrator","Error",JOptionPane.ERROR_MESSAGE);
		}
		if(!employed_rider_list.isEmpty())
		{
			table.initializeRiderTable(admin_rider_frame.getModel(), employed_rider_list);
			admin_rider_frame.setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "Lo shop selezionato non ha rider in servizio","Warning",JOptionPane.WARNING_MESSAGE);
		return;
	}
	public void openShopDeliveringOrdersFrame(JFrame frame) {
		frame.setVisible(false);
		ShopDeliveringOrdersFrame shop_delivering_orders_frame = new ShopDeliveringOrdersFrame(this);
		TableModelUtility table = new TableModelUtility();
		List<Order> in_delivery_order_list = new ArrayList<Order>();
			try {
				in_delivery_order_list = order_dao.getInDeliveryOrdersOfAShop(current_shop_email);
				if(!in_delivery_order_list.isEmpty())
				{
				    table.initializeDeliveringOrderTable(shop_delivering_orders_frame.getModel(), in_delivery_order_list);
					shop_delivering_orders_frame.setVisible(true);
				}
				else
				{
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "Lo shop selezionato non ha ordini in consegna","Warning",JOptionPane.WARNING_MESSAGE);
				}
			} catch (DaoException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
	}
			return;
	}
	
	public void openShopPendingOrdersFrame(JFrame frame) {
		
		frame.setVisible(false);
		TableModelUtility table = new TableModelUtility();
		ShopPendingOrdersFrame shop_pending_orders_frame = new ShopPendingOrdersFrame(this);
		List<Order> pending_order_list = new ArrayList<Order>();
		try {
			pending_order_list = order_dao.getPendingOrdersOfAShop(current_shop_email);
			if(!pending_order_list.isEmpty())
			{
			    table.initializePendingOrderTable(shop_pending_orders_frame.getModel(), pending_order_list);
				shop_pending_orders_frame.setVisible(true);
			}
			else
			{
				frame.setVisible(true);
				JOptionPane.showMessageDialog(null, "Lo shop selezionato non ha ordini in attesa di essere ritirati dai rider","Warning",JOptionPane.WARNING_MESSAGE);
			}
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} 
		return;
	}	
	public void addMeal(ShopMealFrame shop_meal_frame) {
		
		List<Meal> meal_list = new ArrayList<Meal>();
		int i=0;
		try {
			meal_list = meal_dao.getAllMealsExceptShopMeals(current_shop_email);
			while(!meal_list.get(i).getName().equals(shop_meal_frame.getMealTF().getText()))
				i++;
			meal_dao.insertSupply(current_shop_email,meal_list.get(i));
			shop_meal_frame.getModel().insertRow(shop_meal_frame.getModel().getRowCount(), new Object[]{meal_list.get(i).getName(),
					meal_list.get(i).getCategory(), new DecimalFormat("00.00").format(meal_list.get(i).getPrice()),
					meal_list.get(i).getIngredients(),meal_list.get(i).getAllergen_list().toString().substring(1,meal_list.get(i).getAllergen_list().toString().length()-1)});
			JOptionPane.showMessageDialog(null, "Pasto aggiunto correttamente");
		}
		catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred, please try again or contact the administrator","Errore",JOptionPane.ERROR_MESSAGE);
		}catch (IndexOutOfBoundsException in) {
			JOptionPane.showMessageDialog(null, "Pasto errato oppure e' gia' disponibile nel negozio","Errore",JOptionPane.ERROR_MESSAGE);
		}
			return;
	}
	
	public void addRider(ShopRiderFrame shop_rider_frame) {
		
		InputUtility input_util = new InputUtility();
		CodiceFiscaleUtility codice_fiscale = new CodiceFiscaleUtility();
		String birth_place_town;
		if(!shop_rider_frame.getBirth_nationCB().getSelectedItem().toString().equals("ITALIA"))
			birth_place_town = shop_rider_frame.getBirth_nationCB().getSelectedItem().toString();
		else
			birth_place_town = shop_rider_frame.getBirth_townCB().getSelectedItem().toString();
		try {
			String name = shop_rider_frame.getNameTF().getText();
			String surname = shop_rider_frame.getSurnameTF().getText();
			Date birth_date = new SimpleDateFormat("dd/MM/yyyy").parse(shop_rider_frame.getBirth_dateTF().getText());
			
			String gender = shop_rider_frame.getGenderCB().getSelectedItem().toString().substring(0,1);
			String CF = codice_fiscale.getCF(name,surname ,	birth_date ,birth_place_town ,gender.charAt(0));
			String address = shop_rider_frame.getAddress_nameTF().getText();
			String address_civic_number = shop_rider_frame.getAddress_civic_numberTF().getText();
			String cap = shop_rider_frame.getAddress_capTF().getText();
			String address_town = shop_rider_frame.getAddress_townCB().getSelectedItem().toString();
			String province_town = shop_rider_frame.getAddress_provinceCB().getSelectedItem().toString();
			String vehicle = shop_rider_frame.getVehicleCB().getSelectedItem().toString();
			String working_hours = shop_rider_frame.getWorking_hoursTF().getText();
			String cellphone = shop_rider_frame.getCellphoneTF().getText();
			if(!CF.equals("Errore!"))
			{
			Rider rider = new Rider(CF,name ,surname , birth_date, birth_place_town,gender ,cellphone , new Address(address, address_civic_number, 
					cap, address_town, province_town),vehicle, working_hours,(short)0);
			rider_dao.insertRider(rider,current_shop_email);
			shop_rider_frame.getModel().insertRow(shop_rider_frame.getModel().getRowCount(), new Object[] {CF, name, surname, 
					input_util.formatDate(birth_date), birth_place_town, input_util.addressToTokenizedString(rider.getAddress(), ", "), gender, cellphone, 
					vehicle, working_hours, (short)0});
			JOptionPane.showMessageDialog(null, "Rider assunto con successo");
		} }
		catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Please, fill correctly the text fields.\nHint: Check the validity of the address, birth_date, working hours and cellphone","Error",JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "Insert date in a valid format","Errore",JOptionPane.ERROR_MESSAGE);
		}catch(CfException c)
		{
			JOptionPane.showMessageDialog(null, c.getMessage());
		}
				
		return;
	}
	
	public void removeMeal(ShopMealFrame shop_meal_frame){
		
		int row = shop_meal_frame.getTable().getSelectedRow() ;
		if(row != -1) {

			String meal_name_to_remove = shop_meal_frame.getTable().getValueAt(row, 0).toString();
			int i = 0;
			try {
				Meal meal_to_remove = meal_dao.getMealByName(meal_name_to_remove);
				meal_dao.deleteFromSupply(current_shop_email, meal_to_remove);
				shop_meal_frame.getModel().removeRow(row);
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
		
		int row = shop_rider_frame.getTable().getSelectedRow();
		if(row != -1) {
			String cf_of_the_rider_to_dismiss = shop_rider_frame.getTable().getValueAt(row, 0).toString();
			int i = 0;
			try {
				Rider rider_to_dismiss = rider_dao.getRiderByCf(cf_of_the_rider_to_dismiss);
				rider_dao.dismissRider(rider_to_dismiss);
				shop_rider_frame.getModel().removeRow(row);
				JOptionPane.showMessageDialog(null, "Rider licenziato con successo");
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
			
			Rider rider_to_update = rider_dao.getRiderByCf(cf_of_rider_to_update);
			String CF = codice_fiscale.getCF(shop_rider_frame.getNameTF().getText(), shop_rider_frame.getSurnameTF().getText(),new SimpleDateFormat("dd/MM/yyyy").parse(shop_rider_frame.getBirth_dateTF().getText()),shop_rider_frame.getBirth_townCB().getSelectedItem().toString(),shop_rider_frame.getGenderCB().getSelectedItem().toString().charAt(0) );
			if(!CF.equals("Errore!"))
			{
			rider_to_update.setCf(CF);
			rider_to_update.setName(shop_rider_frame.getNameTF().getText());
			rider_to_update.setSurname(shop_rider_frame.getSurnameTF().getText());
			rider_to_update.setAddress(new Address(shop_rider_frame.getAddress_nameTF().getText(),
					shop_rider_frame.getAddress_civic_numberTF().getText(), shop_rider_frame.getAddress_capTF().getText(),
					shop_rider_frame.getAddress_cityTF().getText(), shop_rider_frame.getAddress_provinceTF().getText()));
			rider_to_update.setCellphone(shop_rider_frame.getCellphoneTF().getText());
			rider_to_update.setGender(shop_rider_frame.getGenderCB().getSelectedItem().toString().substring(0,1));
			rider_to_update.setVehicle(shop_rider_frame.getVehicleCB().getSelectedItem().toString());
			rider_to_update.setWorking_hours(shop_rider_frame.getWorking_hoursTF().getText());
			rider_to_update.setBirth_date(new SimpleDateFormat("dd/MM/yyyy").parse(shop_rider_frame.getBirth_dateTF().getText()));
			rider_to_update.setBirth_place(shop_rider_frame.getBirth_townCB().getSelectedItem().toString());
			rider_dao.updateRider(rider_to_update);
			updateRiderTableColumns(shop_rider_frame, selected_row, rider_to_update);
				JOptionPane.showMessageDialog(null, "Shop aggiornato con successo");
			} }catch (DaoException e) {
				JOptionPane.showMessageDialog(null, "Please, fill correctly the text fields.\nHint: Check the validity of the address, birth_date, working hours and cellphone","Error",JOptionPane.ERROR_MESSAGE);
			}
		      catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Insert date in a valid format","Errore",JOptionPane.ERROR_MESSAGE);
			}catch(CfException c)
			{
				JOptionPane.showMessageDialog(null, c.getMessage());
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
		
		try {
			Order pending_order = order_dao.getOrderById(shop_pending_orders_frame.getOrderTF().getText());
			order_dao.updatePendingOrder(pending_order, shop_pending_orders_frame.getRiderTF().getText());
			shop_pending_orders_frame.getModel().removeRow(shop_pending_orders_frame.getTable().getSelectedRow());
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "ID non trovato. Riprovare! ","Errore",JOptionPane.ERROR_MESSAGE);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore. Riprovare! ","Errore",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateDeliveringOrder(ShopDeliveringOrdersFrame shop_delivering_orders_frame) {
		int i=0;
		try {
			
			Order in_delivery_order = order_dao.getOrderById(shop_delivering_orders_frame.getOrderTF().getText());
			order_dao.updateDeliveringOrder(in_delivery_order, shop_delivering_orders_frame.getStatusCB().getSelectedItem().toString());
			shop_delivering_orders_frame.getModel().removeRow(shop_delivering_orders_frame.getTable().getSelectedRow());
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "ID non trovato. Riprovare! ","Errore",JOptionPane.ERROR_MESSAGE);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore. Riprovare! ","Errore",JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getcurrent_shop_email() {
		return current_shop_email;
	}
	
	public void releaseAllDaoResourcesAndDisposeFrame(JFrame frame)
	{
		DButility db_utility = new DButility();
		try {
			shop_dao.closeStatements();
			order_dao.closeStatements();
			meal_dao.closeStatements();
			rider_dao.closeStatements();
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
	//Metodo che chiude solo gli statement usati dallo shop, quando ci si disconnette dallo shop frame e si ritorna al login frame
	public void releaseDaoResourcesWhenLoggingOut() {
		try {
			order_dao.closeStatements();
			rider_dao.closeStatements();
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore. Contattare l' amministratore","Errore",JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		return;
	}

	public void updateBirth_townCB(String selected_province, ShopRiderFrame shopRiderFrame) {
		
		IstatUtils istat_utils = new IstatUtils();
		List<String> towns = istat_utils.getTownsByProvince(selected_province);
		shopRiderFrame.getBirth_townCB().removeAllItems();
		for(String s : towns)
			shopRiderFrame.getBirth_townCB().addItem(s);
		return;
		
	}

	public void updateAddress_townCB(String selected_province, ShopRiderFrame shopRiderFrame) {
		
		IstatUtils istat_utils = new IstatUtils();
		List<String> towns = istat_utils.getTownsByProvince(selected_province);
		shopRiderFrame.getAddress_townCB().removeAllItems();
		for(String s : towns)
			shopRiderFrame.getAddress_townCB().addItem(s);
		return;
		
	}
}
