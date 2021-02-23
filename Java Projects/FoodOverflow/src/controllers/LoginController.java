package controllers;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import daos_implementation.CustomerDAOPostgreImplementation;
import daos_implementation.MealDAOPostgreImplementation;
import daos_implementation.OrderDAOPostgreImplementation;
import daos_implementation.ShopDAOPostgreImplementation;
import daos_interfaces.CustomerDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.OrderDAO;
import daos_interfaces.ShopDAO;
import db_connection.DBconnection;
import entities.Address;
import entities.Customer;
import exceptions.FiscalCodeException;
import exceptions.DAOException;
import gui.LoginFrame;
import gui.RegisterFrame;
import utilities.FiscalCodeUtility;
import utilities.DBUtility;
import utilities.IstatUtility;

public class LoginController {

	private Connection connection;
	private ShopDAO shop_dao;
	private CustomerDAO customer_dao;
	private MealDAO meal_dao;
	private OrderDAO order_dao;

	public LoginController() {
		DBconnection instance = DBconnection.getInstance();
		this.connection = instance.getConnection();
		shop_dao = new ShopDAOPostgreImplementation(connection);
		customer_dao = new CustomerDAOPostgreImplementation(connection);
		meal_dao = new MealDAOPostgreImplementation(connection);
		order_dao = new OrderDAOPostgreImplementation(connection);
	}

	public void openLoginFrame(JFrame frame) {
		frame.dispose();
		LoginFrame login_frame = new LoginFrame(this);
		login_frame.setVisible(true);
		return;
	}

	public void openLoginFrame() {
		LoginFrame login_frame = new LoginFrame(this);
		login_frame.setVisible(true);
		return;
	}

	@SuppressWarnings("deprecation")
	public void accessAuthentication(LoginFrame login_frame) {
		// SE ACCEDE L' ADMIN
		boolean access_succeded = false;
		if (login_frame.getLogoLabel().getIcon() == login_frame.getAdminLogoImage()) {

			if (!login_frame.getUsernameTF().getText().equals("admin")
					|| !login_frame.getPasswordTF().getText().equals("admin")) {

				JOptionPane.showMessageDialog(null, "Credenziali errate, riprovare", "Errore",
						JOptionPane.ERROR_MESSAGE);
			} else {
				AdminController admin_controller = new AdminController(connection, this, customer_dao, shop_dao,
						meal_dao, order_dao);
				admin_controller.openAdminFrame(login_frame);
			}
		}
		// SE ACCEDE LO SHOP
		else if (login_frame.getLogoLabel().getIcon() == login_frame.getShopLogoImage()) {

			try {
				access_succeded = shop_dao.isShopLoginValidated(login_frame.getUsernameTF().getText(),
						login_frame.getPasswordTF().getText());
				if (access_succeded) {
					login_frame.setVisible(false);
					String shop_email = login_frame.getUsernameTF().getText();
					ShopController shop_controller = new ShopController(shop_email, connection, shop_dao, customer_dao,
							meal_dao, order_dao, this);
					shop_controller.openShopFrame(login_frame);
				} else {
					JOptionPane.showMessageDialog(null, "Credenziali errate, riprovare", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (DAOException e) {

				JOptionPane.showMessageDialog(null, "Errore critico, contattare l' amministratore");
			}

		} // SE ACCEDE IL CUSTOMER
		else {
			try {
				access_succeded = customer_dao.isCustomerLoginValidated(login_frame.getUsernameTF().getText(),
						login_frame.getPasswordTF().getText());
				if (access_succeded) {
					login_frame.setVisible(false);
					Customer customer = customer_dao.getCustomerByEmail(login_frame.getUsernameTF().getText());
					CustomerController customer_controller = new CustomerController(customer, connection, customer_dao,
							shop_dao, meal_dao, order_dao, this);
					customer_controller.openCustomerFrame(login_frame);
				} else {
					JOptionPane.showMessageDialog(null, "Credenziali errate, riprovare", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (DAOException e) {

				JOptionPane.showMessageDialog(null, "Errore critico, contattare l' amministratore");
			}
		}
		return;

	}

	public void openRegisterFrame(LoginFrame frame) {

		frame.dispose();
		RegisterFrame register_frame = new RegisterFrame(this);
		register_frame.setVisible(true);
		IstatUtility istat_utils = new IstatUtility();
		List<String> nations = istat_utils.getNations();
		List<String> provinces = istat_utils.getProvinces();
		register_frame.getBirth_nationCB().addItem("ITALIA");
		register_frame.getAddress_provinceCB().addItem("Seleziona provincia di residenza");
		register_frame.getAddress_provinceCB().addItem("-------------------");
		for (String s : nations)
			register_frame.getBirth_nationCB().addItem(s);
		for (String s : provinces) {
			register_frame.getBirth_provinceCB().addItem(s);
			register_frame.getAddress_provinceCB().addItem(s);
		}
		return;
	}

	@SuppressWarnings("deprecation")
	public void registerCustomer(RegisterFrame frame) {

		String name = frame.getNameTF().getText();
		String surname = frame.getSurnameTF().getText();
		Date birth_date = null;
		try {
			birth_date = new SimpleDateFormat("dd/MM/yyyy").parse(frame.getBirth_dateTF().getText());
			String birth_place;
			String gender = frame.getGenderCB().getSelectedItem().toString().substring(0, 1);
			String cellphone = frame.getCellphoneTF().getText();
			Address address = new Address(frame.getAddress_nameTF().getText(),
					frame.getAddress_civic_numberTF().getText(), frame.getAddress_capTF().getText(),
					frame.getAddress_townCB().getSelectedItem().toString(),
					frame.getAddress_provinceCB().getSelectedItem().toString());
			String email = frame.getEmailTF().getText();
			String password = frame.getPasswordTF().getText();
			if (!password.equals(frame.getPasswordTF1().getText()))
				throw new Exception("Le password non coincidono");
			FiscalCodeUtility cf_util = new FiscalCodeUtility();
			if (!frame.getBirth_nationCB().getSelectedItem().toString().equals("ITALIA"))
				birth_place = frame.getBirth_nationCB().getSelectedItem().toString();
			else
				birth_place = frame.getBirth_townCB().getSelectedItem().toString();
			String cf = cf_util.getCF(name, surname, birth_date, birth_place, gender.charAt(0));
			Customer customer = new Customer(cf, name, surname, birth_date, birth_place, gender, cellphone, address,
					email, password);
			customer_dao.insertCustomer(customer);
			JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo");
			frame.dispose();
			LoginFrame login_frame = new LoginFrame(this);
			login_frame.setVisible(true);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Uno o piu campi non sono stati inseriti correttamente", "Errore",
					JOptionPane.ERROR_MESSAGE);
		} catch (FiscalCodeException c) {
			JOptionPane.showMessageDialog(null, c.getMessage());
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Inserire data nel formato dd/mm/yyyy", "Errore",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

	public void updateAddressTownsCB(String selected_province, RegisterFrame frame) {
		IstatUtility istat_utils = new IstatUtility();
		List<String> towns = istat_utils.getTownsByProvince(selected_province);
		frame.getAddress_townCB().removeAllItems();
		for (String s : towns)
			frame.getAddress_townCB().addItem(s);
		return;
	}

	public void updateTownsCB(String selected_province, RegisterFrame frame) {
		IstatUtility istat_utils = new IstatUtility();
		List<String> towns = istat_utils.getTownsByProvince(selected_province);
		frame.getBirth_townCB().removeAllItems();
		for (String s : towns)
			frame.getBirth_townCB().addItem(s);
		return;
	}

	public void releaseAllDaoResourcesAndDisposeFrame(JFrame frame) {
		DBUtility db_utility = new DBUtility();
		db_utility.closeAllResources(shop_dao, order_dao, meal_dao, null, customer_dao, connection, frame);
		return;
	}

}