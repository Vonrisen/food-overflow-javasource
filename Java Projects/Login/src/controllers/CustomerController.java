package controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import daos_implementation.OrderDAOPostgresImplementation;
import daos_interfaces.CustomerDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.OrderDAO;
import daos_interfaces.ShopDAO;
import entities.Cart;
import entities.Customer;
import entities.Meal;
import entities.OrderComposition;
import entities.Shop;
import exceptions.DaoException;
import gui.CustomerCartFrame;
import gui.CustomerCheckoutFrame;
import gui.CustomerFrame;
import gui.CustomerMealListFrame;
import gui.CustomerProfileFrame;
import gui.CustomerShopListFrame;
import utilities.DButility;
import utilities.InputUtility;
import utilities.IstatUtils;
import utilities.TableModelUtility;

public class CustomerController {

	private Customer customer;
	// Negozio sul quale l' utente al momento sta effettuando acquisti
	private Shop shop;
	private LoginController login_controller;
	private Cart cart;
	private CustomerDAO customer_dao;
	private ShopDAO shop_dao;
	private MealDAO meal_dao;
	private Connection connection;
	private OrderDAO order_dao;

	public CustomerController(Customer customer, Connection connection, CustomerDAO customer_dao, ShopDAO shop_dao, MealDAO meal_dao, LoginController login_controller) {

		this.customer = customer;
		this.connection = connection;
		this.customer_dao = customer_dao;
		this.shop_dao = shop_dao;
		this.meal_dao = meal_dao;
		this.login_controller = login_controller;
		this.cart = new Cart(shop, customer);
		this.order_dao = new OrderDAOPostgresImplementation(connection);

	}

	public CustomerController() {

	}

	public void openCustomerFrame(JFrame frame) {
		frame.dispose();
		CustomerFrame customer_frame = new CustomerFrame(this);
		customer_frame.setVisible(true);
	}

	public void checkProvinceAndOpenShopListFrame(CustomerFrame customer_frame) {

		IstatUtils istat_utils = new IstatUtils();
		if (istat_utils.isProvinceValid(customer_frame.getProvinceTF().getText())) {
			openCustomerShopListFrame(customer_frame, customer_frame.getProvinceTF().getText().toUpperCase());
		} else
			JOptionPane.showMessageDialog(null, "Non sono riuscito a trovare la tua provincia, riprovare", "Errore",
					JOptionPane.ERROR_MESSAGE);
	}

	public void openCustomerShopListFrame(JFrame frame, String shop_province) {

		frame.dispose();
		TableModelUtility table_util = new TableModelUtility();
		CustomerShopListFrame customer_shop_list_frame = new CustomerShopListFrame(this, login_controller);
		List<Shop> shop_list = new ArrayList<Shop>();
		try {

			shop_list = shop_dao.getShopByProvince(shop_province);
			table_util.initializeCustomerShopTable(customer_shop_list_frame.getModel(), shop_list);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred, please try again or contact the administrator",
					"Errore", JOptionPane.ERROR_MESSAGE);
		}
		customer_shop_list_frame.setVisible(true);
		return;
	}

	public void openCustomerMealListFrame(JFrame frame, String shop_email) {

		TableModelUtility table_util = new TableModelUtility();
		CustomerMealListFrame customer_meal_list_frame = new CustomerMealListFrame(this, login_controller);
		frame.dispose();
		List<Meal> meal_list = new ArrayList<Meal>();
		try {
			meal_list = shop_dao.getMealsOfAShopByShopEmail(shop_email);
			this.shop = shop_dao.getShopByEmail(shop_email);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "An error has occurred, please try again or contact the administrator",
					"Errore", JOptionPane.ERROR_MESSAGE);
		}
		table_util.initializeMealTable(customer_meal_list_frame.getModel(), meal_list);
		customer_meal_list_frame.setVisible(true);
		return;

	}

	public void addMealToCart(CustomerMealListFrame frame) {

		int row = frame.getTable().getSelectedRow();
		boolean meal_already_inserted = false;
		InputUtility input_util = new InputUtility();
		if (row != -1) {
			List<String> allergen_list = new ArrayList<String>();
			try {
				allergen_list = input_util.tokenizedStringToList(frame.getTable().getValueAt(row, 4).toString(),
						"(, )");
			} catch (NullPointerException n) {
			}
			Meal meal = new Meal(frame.getTable().getValueAt(row, 0).toString(),
					Float.parseFloat(frame.getTable().getValueAt(row, 2).toString()),
					frame.getTable().getValueAt(row, 3).toString(), frame.getTable().getValueAt(row, 1).toString(),
					allergen_list);
			short quantity = Short.parseShort(frame.getQuantityTF().getText());
			OrderComposition new_meal = new OrderComposition(meal, quantity);
			for (OrderComposition o : cart.getOrder_composition_list()) {
				if (o.getMeal().getName().equals(meal.getName())) {
					o.setQuantity((short) (quantity + o.getQuantity()));
					meal_already_inserted = true;
				}
			}
			if (!meal_already_inserted)
				cart.addMealIntoCart(new_meal);
		} else
			JOptionPane.showMessageDialog(null, "Selezionare uno pasto da mettere nel carrello", "Errore",
					JOptionPane.ERROR_MESSAGE);
		return;
	}

	public void releaseAllDaoResourcesAndDisposeFrame(JFrame frame) {
		DButility db_utility = new DButility();
		try {
			shop_dao.closeStatements();
			customer_dao.closeStatements();
			meal_dao.closeStatements();
			order_dao.closeStatements();
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore. Contattare l' amministratore", "Errore",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		} finally {
			db_utility.closeConnection(connection);
			frame.dispose();
		}
		return;
	}

	public void doCustomerComplexSearch(CustomerMealListFrame customer_meal_list_frame) {

		String selected_category = customer_meal_list_frame.getCategoryCB().getSelectedItem().toString();
		String min_price_string = customer_meal_list_frame.getPrice_minTF().getText();
		String max_price_string = customer_meal_list_frame.getPrice_maxTF().getText();
		float min_price = 0;
		float max_price = 0;
		List<Meal> meal_list = new ArrayList<Meal>();
		try {
			min_price = Float.parseFloat(min_price_string);
			max_price = Float.parseFloat(max_price_string);
			List<String> allergen_list = new ArrayList<String>();
			for (JCheckBox allergen_cb : customer_meal_list_frame.getAllergens()) {
				if (allergen_cb.isSelected())
					allergen_list.add(allergen_cb.getText());
			}
			String shop_email = shop.getEmail();
			meal_list = meal_dao.doCustomerComplexSearch(selected_category,  min_price, max_price,
					allergen_list, shop_email);
			customer_meal_list_frame.getModel().setRowCount(0);
		} catch (NumberFormatException n) {
			JOptionPane.showMessageDialog(null, "Inserire un prezzo valido", "Errore", JOptionPane.ERROR_MESSAGE);
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore. Contattare l' amministratore", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
		TableModelUtility table_model_util = new TableModelUtility();
		table_model_util.initializeMealTable(customer_meal_list_frame.getModel(), meal_list);
		return;
	}

	public void openCustomerCartFrame(JFrame frame) {
		frame.dispose();
		CustomerCartFrame customer_cart_frame = new CustomerCartFrame(this, login_controller);
		TableModelUtility table_util = new TableModelUtility();
		table_util.initializeCustomerCartTable(customer_cart_frame.getModel(), cart);
		customer_cart_frame.setVisible(true);
		return;
	}

	public Shop getShop() {
		return this.shop;
	}

	public void updateMealQuantity(CustomerCartFrame customerCartFrame) {

		int row = customerCartFrame.getTable().getSelectedRow();
		if (row != -1) {
			String meal_name = customerCartFrame.getModel().getValueAt(row, 0).toString();
			try {

				short new_quantity = Short.parseShort(customerCartFrame.getQuantityTF().getText());
				for (OrderComposition o : cart.getOrder_composition_list()) {
					if (o.getMeal().getName().equals(meal_name)) {
						o.setQuantity(new_quantity);
						customerCartFrame.getModel().setValueAt(new_quantity, row, 5);
					}
				}
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Inserire un prezzo valido", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Seleziona qualcosa", "Error", JOptionPane.ERROR_MESSAGE);
		return;

	}

	public void removeMealFromCart(CustomerCartFrame customer_customer_frame) {

		int row = customer_customer_frame.getTable().getSelectedRow();
		if (row != -1) {
			cart.getOrder_composition_list().remove(row);
			customer_customer_frame.getModel().removeRow(row);
		} else
			JOptionPane.showMessageDialog(null, "Seleziona qualcosa", "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void removeEverythingFromCart(CustomerCartFrame customer_customer_frame) {

		if (!cart.getOrder_composition_list().isEmpty()) {
			cart.getOrder_composition_list().clear();
			customer_customer_frame.getModel().setRowCount(0);
		} else
			JOptionPane.showMessageDialog(null, "Il carrello e' gia' vuoto", "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void openCustomerCheckoutFrame(CustomerCartFrame customer_cart_frame) {

		CustomerCheckoutFrame customer_checkout_frame = new CustomerCheckoutFrame(this, customer_cart_frame);
		customer_checkout_frame.setVisible(true);
		customer_checkout_frame.setAddressTF(customer.getAddress().toString());
		customer_checkout_frame.setShopTF(shop.getName());
		customer_checkout_frame.setCellphoneTF(shop.getHome_phone());
		customer_checkout_frame.setCellphoneTF(shop.getHome_phone());
		customer_checkout_frame.setTotal_priceTF(String.valueOf(cart.getTotalPrice()));
		return;
	}

	public void completeOrder(CustomerCheckoutFrame customer_checkout_frame, CustomerCartFrame customer_cart_frame) {

		try {
			order_dao.createOrder(customer.getAddress(), "Contrassegno", customer_checkout_frame.getNoteTF().getText(), shop, customer, cart);
			JOptionPane.showMessageDialog(null, "Ordine completato. Tra poco l' ordine partira' dal ristorante!");
			customer_cart_frame.dispose();
			cart.getOrder_composition_list().clear();
			openCustomerMealListFrame(customer_checkout_frame, shop.getEmail());
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Non e' stato possibile completare l' ordine", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
		return;
	}

	public void releaseDaoResourcesWhenLoggingOut() {
		try {
			order_dao.closeStatements();
		} catch (DaoException e) {
			JOptionPane.showMessageDialog(null, "Errore. Contattare l' amministratore", "Errore",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		return;
	}

	public void openCustomerProfileFrame() {
		
		CustomerProfileFrame customer_profile_frame = new CustomerProfileFrame(this);
		if(customer.getGender().equals("m".toUpperCase()))
		customer_profile_frame.getAvatarLB().setIcon(customer_profile_frame.getMale_avatarIMG());
		else
			customer_profile_frame.getAvatarLB().setIcon(customer_profile_frame.getFemale_avatarIMG());
		customer_profile_frame.setVisible(true);
		return;
		
	}
}
