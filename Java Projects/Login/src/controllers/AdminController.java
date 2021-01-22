package controllers;

import java.sql.SQLException;
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
	private List<Rider>rider_list = new ArrayList<Rider>();
	private List<Meal> meal_list = new ArrayList<Meal>();
	private List<Customer>customer_list = new ArrayList<Customer>();
	public List<Shop>shop_list= new ArrayList<Shop>();
	private List<String> allergens = new ArrayList<String>();
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
		initializeAdminRiderFrameTable(admin_rider_frame, admin_shop_frame);
		admin_rider_frame.setVisible(true);
	}
	
	public void openAdminMealFrame(AdminFrame admin_frame)
	{
		admin_frame.dispose();
		AdminMealFrame admin_meal_frame = new AdminMealFrame(this);
		initializeAdminMealFrameTable(admin_meal_frame);
		admin_meal_frame.setVisible(true);
	}
	
	public void openAdminCustomerFrame(AdminFrame admin_frame)
	{
		admin_frame.dispose();
		AdminCustomerFrame admin_customer_frame = new AdminCustomerFrame(this);
		initializeAdminCustomerFrameTable(admin_customer_frame);
		admin_customer_frame.setVisible(true);
	}
	
	public void initializeAdminShopFrameTable(AdminShopFrame admin_shop_frame)
	{
		try {
			shop_list = shop_dao.getAllShops();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeShopTable(admin_shop_frame, shop_list);
		return;
	}

	public void initializeAdminCustomerFrameTable(AdminCustomerFrame admin_customer_frame)
	{
		try {
			customer_list = customer_dao.getAllCustomers();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		table.initializeCustomerTable(admin_customer_frame, customer_list);
		return;
	}
	
	public void initializeAdminRiderFrameTable(AdminRiderFrame admin_rider_frame, AdminShopFrame admin_shop_frame)
	{
		int selected_row = admin_shop_frame.getTable().getSelectedRow();
	    String shop_id = admin_shop_frame.getTable().getValueAt(selected_row, 0).toString();
			try {
				rider_list = rider_dao.getRidersOfAShopByShopId(shop_id);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
			}
		table.initializeRiderTable(admin_rider_frame, rider_list);
		return;
	}
	
	public void addShop(AdminShopFrame admin_shop_frame) 
	{

		Shop shop = new Shop(admin_shop_frame.getNameTF().getText(), admin_shop_frame.getPasswordTF().getText(), admin_shop_frame.getWorking_hoursTF().getText(),
							 new Address(admin_shop_frame.getAddress_nameTF().getText(), admin_shop_frame.getAddress_civic_numberTF().getText(), admin_shop_frame.getAddress_capTF().getText(), 
						     admin_shop_frame.getAddress_cityTF().getText(), admin_shop_frame.getAddress_provinceTF().getText()), admin_shop_frame.getClosing_daysTF().getText(), null, null);
		try {
			shop_dao.insertShop(shop);
			admin_shop_frame.getModel().insertRow(shop_list.size()-1, new Object[] {"da definire", admin_shop_frame.getPasswordTF().getText(),
			admin_shop_frame.getNameTF().getText(), new Address(admin_shop_frame.getAddress_nameTF().getText(),
			admin_shop_frame.getAddress_civic_numberTF().getText(), admin_shop_frame.getAddress_capTF().getText(),
			admin_shop_frame.getAddress_cityTF().getText(), admin_shop_frame.getAddress_provinceTF().getText()),
			admin_shop_frame.getWorking_hoursTF().getText(), admin_shop_frame.getClosing_daysTF().getText()});
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	public void initializeAdminMealFrameTable(AdminMealFrame admin_meal_frame) {
		
		try {
			meal_list = meal_dao.getAllMeals();
			if(meal_list.size()==0)
				JOptionPane.showMessageDialog(null, "Non ci sono shop da visualizzare","Errore",JOptionPane.ERROR_MESSAGE);
			else
			{
				table.initializeMealTable(admin_meal_frame, meal_list);
			}
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	public void addMeal(AdminMealFrame admin_meal_frame) {
		
		for(JCheckBox cb : admin_meal_frame.getAllergens()) {
			if(cb.isSelected())
				allergens.add(cb.getText());
		}
		Meal meal = new Meal(admin_meal_frame.getNameTF().getText(), Float.parseFloat(admin_meal_frame.getPriceTF().getText()), 
				admin_meal_frame.getIngredientsTF().getText(), admin_meal_frame.getDishJCB().getSelectedItem().toString(), allergens);
		
		try {
			meal_dao.insertMeal(meal);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public boolean shopRemoved(AdminShopFrame admin_shop_frame)
	{

		int selected_row = admin_shop_frame.getTable().getSelectedRow();
		String shop_id_to_remove = admin_shop_frame.getTable().getValueAt(selected_row, 0).toString();
		int i = 0;
	
			while(!shop_list.get(i).getId().equals(shop_id_to_remove))
				i++;
		try {
			shop_dao.deleteShop(shop_list.get(i));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	

	public boolean mealRemoved(AdminMealFrame admin_meal_frame)
	{
		int selected_row = admin_meal_frame.getTable().getSelectedRow();
		String name_of_meal_to_delete = admin_meal_frame.getTable().getModel().getValueAt(selected_row, 0).toString();
		int i = 0;
		while(!meal_list.get(i).getName().equals(name_of_meal_to_delete))
			i++;
		try {
			meal_dao.deleteMeal(meal_list.get(i));
			admin_meal_frame.getModel().insertRow(meal_list.size()-1, new Object[]{admin_meal_frame.getNameTF().getText(),
		    admin_meal_frame.getDishJCB().getSelectedItem().toString(), Float.parseFloat(admin_meal_frame.getPriceTF().getText()),
		    admin_meal_frame.getIngredientsTF().getText(),allergens});
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
	
	public void removeCustomer(AdminCustomerFrame admin_customer_frame) {
		
		String email = JOptionPane.showInputDialog("Inserisci l'email dell'utente da eliminare");
		int i=0;
		if(email!=null) {
		try {
			while(!customer_list.get(i).getEmail().equals(email))
				i++;
		}catch (IndexOutOfBoundsException in) {
			JOptionPane.showMessageDialog(null, "Nessun Customer trovato con questa email: "+email,"Errore",JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			admin_customer_frame.getTable().getSelectionModel().clearSelection();
			customer_dao.deleteCustomer(customer_list.get(i));
			admin_customer_frame.getModel().removeRow(i);
			JOptionPane.showMessageDialog(null, "Customer deleted succesfully","Errore",JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Errore",JOptionPane.ERROR_MESSAGE);
		}
		}
		return;
	}

	
}
