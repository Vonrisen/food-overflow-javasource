package controllers;
import java.sql.Connection;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import exceptions.FiscalCodeException;
import exceptions.DAOException;
import gui.AdminRiderFrame;
import gui.ShopDeliveringOrdersFrame;
import gui.ShopFrame;
import gui.ShopMealFrame;
import gui.ShopOrderManagementFrame;
import gui.ShopPendingOrdersFrame;
import gui.ShopRiderFrame;
import gui.ShopViewOrdersFrame;
import utilities.FiscalCodeUtility;
import utilities.DBUtility;
import utilities.InputUtility;
import utilities.IstatUtility;
import utilities.TableModelUtility;

public class ShopController {
	
	private String current_shop_email;
	Connection connection;
	private ShopDAO shop_dao;
	private OrderDAO order_dao;
	private MealDAO meal_dao;
	private RiderDAO rider_dao;
	private CustomerDAO customer_dao;
	
	public ShopController(String email, Connection connection, ShopDAO shop_dao, CustomerDAO customer_dao, MealDAO meal_dao, OrderDAO order_dao) 
	{
		
		this.connection = connection;
		this.current_shop_email=email;
		this.shop_dao = shop_dao;
		this.customer_dao = customer_dao;
		this.meal_dao = meal_dao;
		this.order_dao = order_dao;
		this.rider_dao = new RiderDAOPostgresImplementation(connection);
		
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
		try {
			List<Meal> meal_list = shop_dao.getMealsOfAShopByShopEmail(current_shop_email);
			List<Meal> meals_not_in_menu = meal_dao.getAllMealsExceptShopMeals(current_shop_email);
			table.initializeMealTable(shop_meal_frame.getMenu_model(), meal_list);
			table.initializeMealTable(shop_meal_frame.getAll_meals_model(), meals_not_in_menu);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null,
					"Errore critico, contattare l' amministratore", "Error",JOptionPane.ERROR_MESSAGE);
		} 
		shop_meal_frame.setVisible(true);
		return;
	}
	
	public void openShopRiderFrame(JFrame frame)
	{
		
		frame.dispose();
		TableModelUtility table = new TableModelUtility();
		ShopRiderFrame shop_rider_frame = new ShopRiderFrame(this);
		IstatUtility istat_utils = new IstatUtility();
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

			try {
				List<Rider> rider_list = shop_dao.getRidersOfAShopByShopEmail(current_shop_email);
				table.initializeRiderTable(shop_rider_frame.getModel(), rider_list);
				shop_rider_frame.setVisible(true);
			} catch (DAOException e) {
				JOptionPane.showMessageDialog(null,"Errore critico, contattare l' amministratore", "Error",JOptionPane.ERROR_MESSAGE);
			} 
			shop_rider_frame.setVisible(true);
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
		try {
			List<Order> all_orders = order_dao.getOrdersOfAShopByShopEmail(current_shop_email);
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
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Errore critico, contattare l' amministratore", "Errore", JOptionPane.ERROR_MESSAGE);
		} 

		return;
	}
	public void openAdminRiderFrame()
	{
		AdminRiderFrame admin_rider_frame = new AdminRiderFrame();
		TableModelUtility table = new TableModelUtility();
		List<Rider> employed_rider_list = new ArrayList<Rider>();
		try {
			employed_rider_list = shop_dao.getRidersOfAShopByShopEmail(current_shop_email);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Errore critico, contattare l' amministratore", "Errore", JOptionPane.ERROR_MESSAGE);
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
			try {
				List<Order> in_delivery_order_list = order_dao.getInDeliveryOrdersOfAShop(current_shop_email);
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
			} catch (DAOException e) {
				JOptionPane.showMessageDialog(null, "Errore critico, contattare l' amministratore", "Errore", JOptionPane.ERROR_MESSAGE);
	}
			return;
	}
	
	public void openShopPendingOrdersFrame(JFrame frame) {
		
		frame.setVisible(false);
		TableModelUtility table = new TableModelUtility();
		ShopPendingOrdersFrame shop_pending_orders_frame = new ShopPendingOrdersFrame(this);
		try {
			List<Order> pending_order_list = order_dao.getPendingOrdersOfAShop(current_shop_email);
			List<Rider> rider_list = shop_dao.getRidersOfAShopMax2DeliveriesByShopEmail(current_shop_email);
			if(!pending_order_list.isEmpty())
			{
			    table.initializePendingOrderTable(shop_pending_orders_frame.getPending_orders_model(), pending_order_list);
			    table.initializeRiderOrderTable(shop_pending_orders_frame.getRiders_model(), rider_list);
				shop_pending_orders_frame.setVisible(true);
			}
			else
			{
				frame.setVisible(true);
				JOptionPane.showMessageDialog(null, "Lo shop selezionato non ha ordini in attesa di essere ritirati dai rider","Warning",JOptionPane.WARNING_MESSAGE);
			}
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} 
		return;
	}	
	public void addMealToMenu(ShopMealFrame shop_meal_frame) {
		
		int row_selected = shop_meal_frame.getAll_meals_table().getSelectedRow();
		if(!shop_meal_frame.getAll_meals_table().getSelectionModel().isSelectionEmpty())
		{
		try {
			Meal meal = meal_dao.getMealByName(shop_meal_frame.getAll_meals_model().getValueAt(row_selected, 0).toString());
			meal_dao.insertIntoMenu(current_shop_email,meal);
			shop_meal_frame.getMenu_model().insertRow(shop_meal_frame.getMenu_model().getRowCount(), new Object[]{meal.getName(),
					meal.getCategory(), new DecimalFormat("00.00").format(meal.getPrice()),
					meal.getIngredients(),meal.getAllergen_list().toString().substring(1,meal.getAllergen_list().toString().length()-1)});
			shop_meal_frame.getAll_meals_model().removeRow(row_selected);
			JOptionPane.showMessageDialog(null, "Pasto aggiunto correttamente");
		}
		catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Non e' stato possibile aggiungere il pasto al menu', contattare l' amministratore","Errore",JOptionPane.ERROR_MESSAGE);
		}
		}else
			JOptionPane.showMessageDialog(null, "Selezionare un pasto","Errore",JOptionPane.ERROR_MESSAGE);
			return;
	}
	
	public void addRider(ShopRiderFrame shop_rider_frame) {
		
		InputUtility input_util = new InputUtility();
		FiscalCodeUtility codice_fiscale = new FiscalCodeUtility();
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
					input_util.formatDate(birth_date), birth_place_town, rider.getAddress().toString(), gender, cellphone, 
					vehicle, working_hours, (short)0});
			JOptionPane.showMessageDialog(null, "Rider assunto con successo");
		} }
		catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Riempi correttamente i campi.\nHint: Controlla la validita dell' indirizzo, data di nascita, orario lavorativo e cellulare","Errore",JOptionPane.ERROR_MESSAGE);
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, "Inserire data nel formato dd/mm/yyyy","Errore",JOptionPane.ERROR_MESSAGE);
		}catch(FiscalCodeException c)
		{
			JOptionPane.showMessageDialog(null, c.getMessage());
		}
				
		return;
	}
	
	public void removeMealFromMenu(ShopMealFrame shop_meal_frame){
		
		int row = shop_meal_frame.getMenu_table().getSelectedRow();
		InputUtility input_util = new InputUtility();
		if(row != -1) {
			String meal_name_to_remove = shop_meal_frame.getMenu_table().getValueAt(row, 0).toString();
			try {
				Meal meal_to_remove = meal_dao.getMealByName(meal_name_to_remove);
				meal_dao.deleteFromSupply(current_shop_email, meal_to_remove);
				shop_meal_frame.getMenu_model().removeRow(row);
				shop_meal_frame.getAll_meals_model().addRow(new Object[] {meal_to_remove.getName(), meal_to_remove.getCategory(), meal_to_remove.getPrice(), 
					meal_to_remove.getIngredients(), input_util.arrayListToTokenizedString(meal_to_remove.getAllergen_list(), ", ")});
				JOptionPane.showMessageDialog(null, "Pasto rimosso con successo");
			} catch (DAOException e) {
				JOptionPane.showMessageDialog(null, "Errore critico, contattare l' amministratore","Errore",JOptionPane.ERROR_MESSAGE);
			}
	}
		else
			JOptionPane.showMessageDialog(null, "Seleziona il pasto da rimuovere","Warning",JOptionPane.WARNING_MESSAGE);
		return;
	}
	
	public void removeRider(ShopRiderFrame shop_rider_frame) {
		
		int row = shop_rider_frame.getTable().getSelectedRow();
		if(row != -1) {
			String cf_of_the_rider_to_dismiss = shop_rider_frame.getTable().getValueAt(row, 0).toString();
			try {
				Rider rider_to_dismiss = rider_dao.getRiderByCf(cf_of_the_rider_to_dismiss);
				rider_dao.dismissRider(rider_to_dismiss);
				shop_rider_frame.getModel().removeRow(row);
				JOptionPane.showMessageDialog(null, "Rider licenziato con successo");
			} catch (DAOException e) {
				JOptionPane.showMessageDialog(null, "Errore critico, contattare l' amministratore","Error",JOptionPane.ERROR_MESSAGE);
			}
	}
		else
			JOptionPane.showMessageDialog(null, "Seleziona il rider da rimuovere","Error",JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	public void updateRider(ShopRiderFrame shop_rider_frame)
	{
		int row = shop_rider_frame.getTable().getSelectedRow();
		if(row != -1) {
			String cf_of_rider_to_update = shop_rider_frame.getTable().getModel().getValueAt(row, 0).toString();
			FiscalCodeUtility codice_fiscale = new FiscalCodeUtility();
			TableModelUtility table_model = new TableModelUtility();
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
			table_model.updateRiderTableColumns(shop_rider_frame, row, rider_to_update);
				JOptionPane.showMessageDialog(null, "Rider aggiornato con successo");
			} }catch (DAOException e) {
				JOptionPane.showMessageDialog(null, "Riempi correttamente i campi.\\nHint: Controlla la validita dell' indirizzo, data di nascita, orario lavorativo e cellulare","Error",JOptionPane.ERROR_MESSAGE);
			}
		      catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Inserire data nel formato dd/mm/yyyy","Errore",JOptionPane.ERROR_MESSAGE);
			}catch(FiscalCodeException c)
			{
				JOptionPane.showMessageDialog(null, c.getMessage());
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Seleziona il rider da aggiornare","Errore",JOptionPane.ERROR_MESSAGE);
		return;
	}
	

	
	public void linkRiderToOrder(ShopPendingOrdersFrame shop_pending_orders_frame) {
		
		if(shop_pending_orders_frame.getPending_orders_table().getSelectionModel().isSelectionEmpty()||shop_pending_orders_frame.getRiders_table().getSelectionModel().isSelectionEmpty()) 
			JOptionPane.showMessageDialog(null, "Seleziona un ordine da associare ad un rider","Errore",JOptionPane.ERROR_MESSAGE);
		else
		{

			int selected_order_row = shop_pending_orders_frame.getPending_orders_table().getSelectedRow();
			int selected_rider_row = shop_pending_orders_frame.getRiders_table().getSelectedRow();
			try {
				Order order = order_dao.getOrderById(shop_pending_orders_frame.getPending_orders_model().getValueAt(selected_order_row, 0).toString());
				Rider rider = rider_dao.getRiderByCf(shop_pending_orders_frame.getRiders_model().getValueAt(selected_rider_row, 0).toString());
				order_dao.linkRiderToOrder(order, rider);
				shop_pending_orders_frame.getPending_orders_model().removeRow(selected_order_row);
				shop_pending_orders_frame.getRiders_model().setValueAt(Integer.parseInt(shop_pending_orders_frame.getRiders_model().getValueAt(selected_rider_row, 4).toString())+1, selected_rider_row, 4);
				if(shop_pending_orders_frame.getRiders_model().getValueAt(selected_rider_row, 4).toString().equals("3"))
					shop_pending_orders_frame.getRiders_model().removeRow(selected_rider_row);
			} catch (DAOException e) {
				JOptionPane.showMessageDialog(null, "Non e' stato possibile associare l' ordine al rider","Errore",JOptionPane.ERROR_MESSAGE);
			}
		}
		return;
	}
	
	public void updateDeliveringOrder(ShopDeliveringOrdersFrame shop_delivering_orders_frame) {

		int row = shop_delivering_orders_frame.getTable().getSelectedRow();
		if(row!=-1)
		{
		try {
			Order in_delivery_order = order_dao.getOrderById(shop_delivering_orders_frame.getOrderTF().getText());
			order_dao.updateDeliveringOrder(in_delivery_order, shop_delivering_orders_frame.getStatusCB().getSelectedItem().toString());
			shop_delivering_orders_frame.getModel().removeRow(shop_delivering_orders_frame.getTable().getSelectedRow());
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "ID non trovato. Riprovare! ","Errore",JOptionPane.ERROR_MESSAGE);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Errore. Riprovare! ","Errore",JOptionPane.ERROR_MESSAGE);
		}
		}else
			JOptionPane.showMessageDialog(null, "Seleziona un ordine","Errore",JOptionPane.ERROR_MESSAGE);
	}

	public String getcurrent_shop_email() {
		return current_shop_email;
	}
	
	public void releaseAllDaoResourcesAndDisposeFrame(JFrame frame)
	{
		DBUtility db_utility = new DBUtility();
		db_utility.closeAllResources(shop_dao, order_dao, meal_dao, rider_dao, customer_dao, connection, frame);
		return;
	}
	//Metodo che chiude solo gli statement usati dallo shop, quando ci si disconnette dallo shop frame e si ritorna al login frame
	public void releaseDaoResourcesWhenLoggingOut() {
		try {
			rider_dao.closeStatements();
		} catch (DAOException e) {
			System.exit(-1);;
		}
		return;
	}

	  public void updateBirth_townCB(String selected_province, ShopRiderFrame shopRiderFrame) {
			
			IstatUtility istat_utils = new IstatUtility();
			List<String> towns = istat_utils.getTownsByProvince(selected_province);
			shopRiderFrame.getBirth_townCB().removeAllItems();
			for(String s : towns)
				shopRiderFrame.getBirth_townCB().addItem(s);
			return;
			
		}

		public void updateAddress_townCB(String selected_province, ShopRiderFrame shopRiderFrame) {
			
			IstatUtility istat_utils = new IstatUtility();
			List<String> towns = istat_utils.getTownsByProvince(selected_province);
			shopRiderFrame.getAddress_townCB().removeAllItems();
			for(String s : towns)
				shopRiderFrame.getAddress_townCB().addItem(s);
			return;
			
		}

}
