package controllers;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import daos_implementation.CustomerDAOPostgresImplementation;
import daos_implementation.MealDAOPostgresImplementation;
import daos_implementation.RiderDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.CustomerDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import entities.Address;
import entities.Customer;
import entities.Meal;
import entities.Rider;
import entities.Shop;
import gui.AdminCustomerFrame;
import gui.AdminFrame;
import gui.AdminMealFrame;
import gui.AdminRiderFrame;
import gui.AdminShopFrame;
import utilities.InputUtility;
import utilities.TableModelUtility;

public class AdminController {
	

	private CustomerDAO customer_dao = new CustomerDAOPostgresImplementation();
	private MealDAO meal_dao = new MealDAOPostgresImplementation();
	private ShopDAO shop_dao = new ShopDAOPostgresImplementation();
	private RiderDAO rider_dao = new RiderDAOPostgresImplementation();
	private List<Meal> meal_list = new ArrayList<Meal>();
	private List<Customer>customer_list = new ArrayList<Customer>();
	public List<Shop>shop_list= new ArrayList<Shop>();
	private TableModelUtility table = new TableModelUtility();
	
	public  AdminController()
	{
		
	}
	public void openAdminFrame(JFrame frame)
	{
		frame.dispose();
		AdminFrame admin_frame = new AdminFrame();
		admin_frame.setVisible(true);
	}
	
	public void openAdminShopFrame(AdminFrame admin_frame)
	{	
		admin_frame.dispose();
		AdminShopFrame admin_shop_frame = new AdminShopFrame(this);
	    initializeAdminShopFrameTable(admin_shop_frame);
	    admin_shop_frame.setVisible(true);
	    return;
	}
	
	public void openAdminRiderFrame(AdminShopFrame admin_shop_frame)
	{
		
		AdminRiderFrame admin_rider_frame = new AdminRiderFrame(this);
		if(initializeAdminRiderFrameTable(admin_rider_frame, admin_shop_frame))
		admin_rider_frame.setVisible(true);
		return;
	}
	
	public void openAdminMealFrame(AdminFrame admin_frame)
	{
		admin_frame.dispose();
		AdminMealFrame admin_meal_frame = new AdminMealFrame(this);
		initializeAdminMealFrameTable(admin_meal_frame);
		admin_meal_frame.setVisible(true);
		return;
	}
	
	public void openAdminCustomerFrame(AdminFrame admin_frame)
	{
		admin_frame.dispose();
		AdminCustomerFrame admin_customer_frame = new AdminCustomerFrame(this);
		initializeAdminCustomerFrameTable(admin_customer_frame);
		admin_customer_frame.setVisible(true);
		return;
	}
	
	public void initializeAdminShopFrameTable(AdminShopFrame admin_shop_frame)
	{
		try {
			shop_list = shop_dao.getAllShops();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeShopTable(admin_shop_frame.getModel(), shop_list);
		return;
	}

	public void initializeAdminCustomerFrameTable(AdminCustomerFrame admin_customer_frame)
	{
		try {
			customer_list = customer_dao.getAllCustomers();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeCustomerTable(admin_customer_frame.getModel(), customer_list);
		return;
	}
	
	public boolean initializeAdminRiderFrameTable(AdminRiderFrame admin_rider_frame, AdminShopFrame admin_shop_frame)
	{
		if(admin_shop_frame.getTable().getSelectedRow() != -1) {
		int selected_row = admin_shop_frame.getTable().getSelectedRow();
		List<Rider>rider_list = new ArrayList<Rider>();
		String shop_email = admin_shop_frame.getTable().getValueAt(selected_row, 0).toString();
		try {
			rider_list = rider_dao.getRidersOfAShopByShopEmail(shop_email);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		if(!rider_list.isEmpty())
		{
			table.initializeRiderTable(admin_rider_frame.getModel(), rider_list);
		}
		 else
		 {
			 JOptionPane.showMessageDialog(null, "Selected shop doesn't have riders","Errore",JOptionPane.ERROR_MESSAGE);
			 return false;
		 }
	}
		else
		{
			JOptionPane.showMessageDialog(null, "Select a shop first","Errore",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void addShop(AdminShopFrame admin_shop_frame) 
	{

		try {
			Shop shop = new Shop(admin_shop_frame.getNameTF().getText(), admin_shop_frame.getPasswordTF().getText(), admin_shop_frame.getWorking_hoursTF().getText(),
					 new Address(admin_shop_frame.getAddress_nameTF().getText(), admin_shop_frame.getAddress_civic_numberTF().getText(), admin_shop_frame.getAddress_capTF().getText(), 
				     admin_shop_frame.getAddress_cityTF().getText(), admin_shop_frame.getAddress_provinceTF().getText()), admin_shop_frame.getClosing_daysTF().getText(), null, null);
			shop_dao.insertShop(shop);
			admin_shop_frame.getModel().insertRow(shop_list.size(), new Object[] {"da definire", shop.getPassword(),
			shop.getName(), shop.getAddress().toString(),shop.getWorking_hours(), shop.getClosing_days()});
			shop_list.add(shop);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	public void initializeAdminMealFrameTable(AdminMealFrame admin_meal_frame) {
		
		try {
			meal_list = meal_dao.getAllMeals();
			if(meal_list.isEmpty())
				JOptionPane.showMessageDialog(null, "Non ci sono shop da visualizzare","Errore",JOptionPane.ERROR_MESSAGE);
			else
			{
				table.initializeMealTable(admin_meal_frame.getModel(), meal_list);
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
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
			admin_meal_frame.getModel().insertRow(meal_list.size(), new Object[]{meal.getName(), meal.getCategory(), new DecimalFormat("00.00").format(meal.getPrice()),
														meal.getIngredients(),input_util.arrayListToTokenizedString(allergens, ", ")});
			meal_list.add(meal);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		 catch (NumberFormatException e)
		{
			 JOptionPane.showMessageDialog(null, "Insert a valid price","Errore",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	public void removeShop(AdminShopFrame admin_shop_frame)
	{
		if(admin_shop_frame.getTable().getSelectedRow() != -1) {

			int selected_row = admin_shop_frame.getTable().getSelectedRow();
			String shop_email_to_remove = admin_shop_frame.getTable().getValueAt(selected_row, 0).toString();
			int i = 0;
			if(shop_email_to_remove.equals("da definire")) {
				JOptionPane.showMessageDialog(null, "Ricaricare la pagina e riprovare","Errore",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				while(!shop_list.get(i).getEmail().equals(shop_email_to_remove))
					i++;
				shop_dao.deleteShop(shop_list.get(i));
				shop_list.remove(i);
				admin_shop_frame.getModel().removeRow(admin_shop_frame.getTable().getSelectedRow());
				JOptionPane.showMessageDialog(null, "Selected shop deleted successfully");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errors while deleting selected shop","Errore",JOptionPane.ERROR_MESSAGE);
			}
		
	}
		else
			JOptionPane.showMessageDialog(null, "Select the shop you want to delete","Errore",JOptionPane.ERROR_MESSAGE);
		return;
	}
	

	public void removeMeal(AdminMealFrame admin_meal_frame)
	{
		
		if(admin_meal_frame.getTable().getSelectedRow()!=-1)
		{
			int selected_row = admin_meal_frame.getTable().getSelectedRow();
			String name_of_meal_to_delete = admin_meal_frame.getTable().getModel().getValueAt(selected_row, 0).toString();
			int i = 0;
			try {
				while(!meal_list.get(i).getName().equals(name_of_meal_to_delete))
					i++;
				System.out.println(meal_list.get(i));
				System.out.println(meal_list.get(selected_row));
				meal_dao.deleteMeal(meal_list.get(i));
				meal_list.remove(i);
				admin_meal_frame.getModel().removeRow(selected_row);
				JOptionPane.showMessageDialog(null, "Selected meal deleted successfully");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Errors while deleting selected meal","Errore",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Select the meal you want to delete","Errore",JOptionPane.ERROR_MESSAGE);
		return;
	}
	
	
	public void removeCustomer(AdminCustomerFrame admin_customer_frame)
	 {
		
		String email = JOptionPane.showInputDialog("Inserisci l'email dell'utente da eliminare");
		int i=0;
		if(email!=null) {
			try {
				while(!customer_list.get(i).getEmail().equals(email))
					i++;
				admin_customer_frame.getTable().getSelectionModel().clearSelection();
				customer_dao.deleteCustomer(customer_list.get(i));
				admin_customer_frame.getModel().removeRow(i);
				customer_list.remove(i);
				JOptionPane.showMessageDialog(null, "Customer deleted successfully");
			}catch (IndexOutOfBoundsException in) {
				JOptionPane.showMessageDialog(null, "Nessun Customer trovato con questa email","Errore",JOptionPane.ERROR_MESSAGE);
				return;
			}
			catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Email must not be empty","Warning",JOptionPane.WARNING_MESSAGE);
		return;
	}
	
	public void updateCustomer(AdminCustomerFrame admin_customer_frame) {
		String cellphone_of_customer_to_update = JOptionPane.showInputDialog("Inserisci il numero di cellullare dell'utente che vuoi modificare");
		String new_email = admin_customer_frame.getEmailTF().getText();
		String new_password = admin_customer_frame.getPasswordTF().getText();
		int i=0;
		
		try {
			while(!customer_list.get(i).getCellphone().equals(cellphone_of_customer_to_update))
				i++;
			if(new_email.equals("E-Mail"))
				new_email = customer_list.get(i).getEmail();
			if(new_password.equals("Password"))
				new_password = customer_list.get(i).getPassword();
			customer_list.get(i).setEmail(new_email);
			customer_list.get(i).setPassword(new_password);
			customer_dao.updateCustomerFromAdmin(customer_list.get(i));
		}catch (IndexOutOfBoundsException in) {
			JOptionPane.showMessageDialog(null, "Nessun Customer trovato con questo numero di cellulare","Errore",JOptionPane.ERROR_MESSAGE);
			return;
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
			return;
		}
		return;
	}
	
	public void shopUpdated(AdminShopFrame admin_shop_frame) {

		if(admin_shop_frame.getTable().getSelectedRow() != -1) {
			int selected_row = admin_shop_frame.getTable().getSelectedRow();
			String email_of_shop_to_update = admin_shop_frame.getTable().getModel().getValueAt(selected_row, 0).toString();
			int i = 0;
			while(!shop_list.get(i).getEmail().equals(email_of_shop_to_update))
				i++;

			shop_list.get(i).setName(admin_shop_frame.getNameTF().getText());
			shop_list.get(i).setAddress(new Address(admin_shop_frame.getAddress_nameTF().getText(),
					admin_shop_frame.getAddress_civic_numberTF().getText(), admin_shop_frame.getAddress_capTF().getText(),
					admin_shop_frame.getAddress_cityTF().getText(), admin_shop_frame.getAddress_provinceTF().getText()));
			shop_list.get(i).setClosing_days(admin_shop_frame.getClosing_daysTF().getText());
			shop_list.get(i).setWorking_hours(admin_shop_frame.getWorking_hoursTF().getText());
			shop_list.get(i).setPassword(admin_shop_frame.getPasswordTF().getText());

			try {
				shop_dao.updateShop(shop_list.get(i));
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Select the shop you want to update","Errore",JOptionPane.ERROR_MESSAGE);
	}

}
