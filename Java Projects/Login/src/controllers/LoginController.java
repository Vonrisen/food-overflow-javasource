package controllers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daos_implementation.CustomerDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.CustomerDAO;
import daos_interfaces.ShopDAO;
import entities.Address;
import entities.Customer;
import exceptions.CfException;
import exceptions.DaoException;
import gui.LoginFrame;
import gui.RegisterFrame;
import utilities.CodiceFiscaleUtility;
import utilities.DButility;
import utilities.InputUtility;
import utilities.IstatUtils;
public class LoginController{
	


	public LoginController()
	{
		
	}
	//Metodo per aprire login frame dopo essersi disconnessi da altri frame
	public void openLoginFrame(JFrame frame)
	{
		frame.dispose();
		DButility db_util = new DButility();
		db_util.closeCurrentConnection();
		LoginFrame login_frame = new LoginFrame();
		login_frame.setVisible(true);
		return;
	}
	//Metodo per aprire login frame per la prima volta
	public void openLoginFrame()
	{
		LoginFrame login_frame = new LoginFrame();
		login_frame.setVisible(true);
		return;
	}
	
	@SuppressWarnings("deprecation")
	public void accessAuthentication(LoginFrame login_frame)
	{
		//SE ACCEDE L' ADMIN
		AdminController admin_controller = new AdminController();
		boolean access_succeded = false;
		DButility db_utility = new DButility();
		if(login_frame.getLogoLabel().getIcon() == login_frame.getAdminLogoImage())
		{
			
		if (!login_frame.getUsernameTF().getText().equals("admin") || !login_frame.getPasswordTF().getText().equals("admin"))
		{
			
			JOptionPane.showMessageDialog(null, "Incorrect credentials, please try again","Errore",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			//Chiudo il login frame e passo all' admin frame
			admin_controller.openAdminFrame(login_frame);
		}
		}
		//SE ACCEDE LO SHOP
		else if(login_frame.getLogoLabel().getIcon() == login_frame.getShopLogoImage())
		{
			
			ShopDAO shop_dao = new ShopDAOPostgresImplementation();
			try {
				access_succeded = shop_dao.isShopLoginValidated(login_frame.getUsernameTF().getText(), login_frame.getPasswordTF().getText());
				if(access_succeded)
				{
					login_frame.setVisible(false);
					String shop_email= login_frame.getUsernameTF().getText();
					ShopController shop_controller = new ShopController(shop_email);
					shop_controller.openShopFrame(login_frame);
				}
				else
				{
					db_utility.closeCurrentConnection();
					JOptionPane.showMessageDialog(null, "Incorrect credentials, please try again","Errore",JOptionPane.ERROR_MESSAGE);
				}
			} catch (DaoException e) {
				
				JOptionPane.showMessageDialog(null, "Critical error, please try again or contact the administrator");
			}
			
	} //SE ACCEDE IL CUSTOMER
		else
		{
			CustomerDAO customer_dao = new CustomerDAOPostgresImplementation();
			try {
				access_succeded = customer_dao.isCustomerLoginValidated(login_frame.getUsernameTF().getText(), login_frame.getPasswordTF().getText());
				if(access_succeded)
				{
					login_frame.setVisible(false);
					String customer_email= login_frame.getUsernameTF().getText();
					CustomerController customer_controller = new CustomerController(customer_email);
					customer_controller.openCustomerFrame(login_frame);
				}
				else
				{
					db_utility.closeCurrentConnection();
					JOptionPane.showMessageDialog(null, "Incorrect credentials, please try again","Errore",JOptionPane.ERROR_MESSAGE);
				}
			} catch (DaoException e) {
				
				JOptionPane.showMessageDialog(null, "Critical error, please try again or contact the administrator");	
			}	
			}
		return;
	
}
	public void openRegisterFrame(JFrame frame) 
	{
		frame.dispose();
		RegisterFrame register_frame = new RegisterFrame(this);
		register_frame.setVisible(true);
		IstatUtils istat_utils = new IstatUtils();
		List<String> nations = istat_utils.getNations();
		List<String> provinces = istat_utils.getProvinces();
		register_frame.getStatiCB().addItem("ITALIA");
		for(String s : nations)
		    register_frame.getStatiCB().addItem(s);
		for(String s : provinces)
		{
			register_frame.getProvincesCB().addItem(s);
			register_frame.getAddress_provinceCB().addItem(s);
		}
		return;
	}

	public void registerCustomer(RegisterFrame frame) {

		String name = frame.getNameTF().getText();
		String surname = frame.getSurnameTF().getText();
		Date birth_date = null;
		try {
			birth_date = new SimpleDateFormat("dd/MM/yyyy").parse(frame.getBirth_dateTF().getText());
		String birth_place = frame.getTownsCB().getSelectedItem().toString();
		String gender = frame.getGenderCB().getSelectedItem().toString().substring(0,1);
		String cellphone = frame.getCellphoneTF().getText();
//		String province_abbrv = db_util.getProvinceAbbreviationByName(frame.getAddress_provinceCB().getSelectedItem().toString());
		Address address = new Address(frame.getAddress_nameTF().getText(), frame.getAddress_civic_numberTF().getText(), frame.getAddress_capTF().getText(),
									  frame.getAddress_cityCB().getSelectedItem().toString(),"MI");
		String email = frame.getEmailTF().getText();
		String password = frame.getPasswordTF().getText();
		CodiceFiscaleUtility cf_util = new CodiceFiscaleUtility();
		String cf = cf_util.getCF(name, surname, birth_date, birth_place, gender.charAt(0));
		Customer customer = new Customer(cf, name, surname, birth_date, birth_place, gender, cellphone, address, email, password);
		CustomerDAO customer_dao = new CustomerDAOPostgresImplementation();
			customer_dao.insertCustomer(customer);
		}catch(DaoException e)
		{
			JOptionPane.showMessageDialog(null, "Uno o piu campi non sono stati inseriti correttamente","Errore",JOptionPane.ERROR_MESSAGE);
		}catch(CfException c)
		{
			JOptionPane.showMessageDialog(null, c.getMessage());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Inserire data nel formato dd/mm/yyyy","Errore",JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
	
	public List<String> updateAddressProvinceCB(String selected_province, RegisterFrame frame)
	{
		IstatUtils istat_utils = new IstatUtils();
		List<String> towns = istat_utils.getTownsByProvince(selected_province);
		frame.getAddress_cityCB().removeAllItems();
		for(String s : towns)
			frame.getAddress_cityCB().addItem(s);
		return towns;
	}
	
	public List<String> updateProvincesCB(String selected_province, RegisterFrame frame)
	{	
		IstatUtils istat_utils = new IstatUtils();
	    List<String> towns = istat_utils.getTownsByProvince(selected_province);
		frame.getTownsCB().removeAllItems();
		for(String s : towns)
			frame.getTownsCB().addItem(s);
		return towns;
	}
	
	
}