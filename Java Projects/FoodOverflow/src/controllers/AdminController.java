package controllers;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import daos_interfaces.CustomerDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.OrderDAO;
import daos_interfaces.ShopDAO;
import entities.Address;
import entities.Customer;
import entities.Meal;
import entities.Order;
import entities.Rider;
import entities.Shop;
import exceptions.DAOException;
import gui.AdminCustomerFrame;
import gui.AdminFrame;
import gui.AdminMealFrame;
import gui.AdminOrderFrame;
import gui.AdminRiderFrame;
import gui.AdminShopFrame;
import utilities.DBUtility;
import utilities.InputUtility;
import utilities.IstatUtility;
import utilities.TableModelUtility;

public class AdminController {

	private Connection connection;
	private LoginController login_controller;
	private CustomerDAO customer_dao;
	private ShopDAO shop_dao;
	private MealDAO meal_dao;
	private OrderDAO order_dao;

	public AdminController(Connection connection, LoginController login_controller, CustomerDAO customer_dao,
			ShopDAO shop_dao, MealDAO meal_dao, OrderDAO order_dao) {
		this.login_controller = login_controller;
		this.connection = connection;
		this.customer_dao = customer_dao;
		this.shop_dao = shop_dao;
		this.meal_dao = meal_dao;
		this.order_dao = order_dao;
	}

	public void openAdminFrame(JFrame frame) {
		frame.dispose();
		AdminFrame admin_frame = new AdminFrame(this, login_controller);
		admin_frame.setVisible(true);
	}

	public void openAdminShopFrame(JFrame frame) {
		frame.dispose();
		TableModelUtility table_utility = new TableModelUtility();
		AdminShopFrame admin_shop_frame = new AdminShopFrame(this);
		IstatUtility istat_utils = new IstatUtility();
		List<String> provinces = istat_utils.getProvinces();
		admin_shop_frame.getAddress_provinceCB().addItem("Seleziona provincia");
		admin_shop_frame.getAddress_provinceCB().addItem("-------------------");
		for (String s : provinces)
			admin_shop_frame.getAddress_provinceCB().addItem(s);
		try {
			List<Shop> shop_list = shop_dao.getAllShops();
			table_utility.initializeShopTable(admin_shop_frame.getModel(), shop_list);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Errore critico, riprovare", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		admin_shop_frame.setVisible(true);
		return;
	}

	public void openAdminRiderFrame(AdminShopFrame admin_shop_frame) {
		AdminRiderFrame admin_rider_frame = new AdminRiderFrame();
		if (initializeAdminRiderFrameTable(admin_rider_frame, admin_shop_frame))
			admin_rider_frame.setVisible(true);
		return;
	}

	public void openAdminMealFrame(JFrame frame) {
		frame.dispose();
		TableModelUtility table_utility = new TableModelUtility();
		AdminMealFrame admin_meal_frame = new AdminMealFrame(this);
		try {
			List<Meal> meal_list = meal_dao.getAllMeals();
			table_utility.initializeMealTable(admin_meal_frame.getModel(), meal_list);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Errore critico, riprovare", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		admin_meal_frame.setVisible(true);
		return;
	}

	public void openAdminCustomerFrame(JFrame frame) {
		frame.dispose();
		TableModelUtility table_utility = new TableModelUtility();
		AdminCustomerFrame admin_customer_frame = new AdminCustomerFrame(this);
		try {
			List<Customer> customer_list = customer_dao.getAllCustomers();
			table_utility.initializeCustomerTable(admin_customer_frame.getModel(), customer_list);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Errore critico, riprovare", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		admin_customer_frame.setVisible(true);
		return;
	}

	public void openAdminOrderFrame(JFrame frame) {
		frame.dispose();
		AdminOrderFrame admin_order_frame = new AdminOrderFrame(this);
		admin_order_frame.setVisible(true);
		IstatUtility istat_utils = new IstatUtility();
		TableModelUtility table_util = new TableModelUtility();
		try {
			List<Order> order_list = order_dao.getAllOrders();
			table_util.initializeOrderTable(admin_order_frame.getModel(), order_list);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Errore critico, riprovare", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		List<String> provinces = istat_utils.getProvinces();
		admin_order_frame.getAddressCB().addItem("Seleziona provincia di consegna");
		admin_order_frame.getAddressCB().addItem("-------------------");
		for (String s : provinces) {
			admin_order_frame.getAddressCB().addItem(s);
		}
		return;
	}

	public boolean initializeAdminRiderFrameTable(AdminRiderFrame admin_rider_frame, AdminShopFrame admin_shop_frame) {
		int row = admin_shop_frame.getTable().getSelectedRow();
		TableModelUtility table_utility = new TableModelUtility();
		if (row != -1) {
			List<Rider> rider_list = new ArrayList<Rider>();
			String shop_email = admin_shop_frame.getTable().getValueAt(row, 0).toString();
			try {
				rider_list = shop_dao.getRidersOfAShopByShopEmail(shop_email);
			} catch (DAOException e) {
				JOptionPane.showMessageDialog(null, "Errore critico, riprovare", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			if (!rider_list.isEmpty()) {
				table_utility.initializeRiderTable(admin_rider_frame.getModel(), rider_list);
			} else {
				JOptionPane.showMessageDialog(null, "Il negozio selezionato non ha rider al momento", "Errore",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selezionare uno shop di cui visualizare i rider impiegati", "Errore",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public void addShop(AdminShopFrame admin_shop_frame) {
		if (!admin_shop_frame.getAddress_townCB().getSelectedItem().toString().equals("Seleziona comune")
				&& !admin_shop_frame.getAddress_provinceCB().getSelectedItem().toString().equals("Seleziona provincia")
				&& !admin_shop_frame.getAddress_townCB().getSelectedItem().toString().equals("-------------------")) {
			try {
				Shop shop = new Shop(admin_shop_frame.getEmailTF().getText(), admin_shop_frame.getNameTF().getText(),
						admin_shop_frame.getPasswordTF().getText(), admin_shop_frame.getWorking_hoursTF().getText(),
						new Address(admin_shop_frame.getAddress_nameTF().getText(),
								admin_shop_frame.getAddress_civic_numberTF().getText(),
								admin_shop_frame.getAddress_capTF().getText(),
								admin_shop_frame.getAddress_townCB().getSelectedItem().toString(),
								admin_shop_frame.getAddress_provinceCB().getSelectedItem().toString()),
						admin_shop_frame.getClosing_daysTF().getText(), null, null,
						admin_shop_frame.getHome_phoneTF().getText());
				shop_dao.insertShop(shop);
				admin_shop_frame.getModel().insertRow(admin_shop_frame.getModel().getRowCount(),
						new Object[] { shop.getEmail(), shop.getPassword(), shop.getName(),
								shop.getAddress().toString(), shop.getWorking_hours(), shop.getClosing_days(),
								shop.getHome_phone() });
			} catch (DAOException e) {
				JOptionPane.showMessageDialog(null,
						"Inserire correttamente i campi.\nHint: Controlla la validita' dell' indirizzo, email, orario lavorativo e giorni di chiusura",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Seleziona correttamente la provincia o il comune", "Errore",
					JOptionPane.ERROR_MESSAGE);

		return;
	}

	public void addMeal(AdminMealFrame admin_meal_frame) {

		if ((!admin_meal_frame.getDishJCB().getSelectedItem().toString().equals("Seleziona categoria"))
				&& (!admin_meal_frame.getDishJCB().getSelectedItem().toString().equals("-------------------"))) {

			InputUtility input_util = new InputUtility();
			List<String> allergens = new ArrayList<String>();
			for (JCheckBox cb : admin_meal_frame.getAllergens()) {
				if (cb.isSelected())
					allergens.add(cb.getText());
			}

			try {
				Meal meal = new Meal(admin_meal_frame.getNameTF().getText(),
						Math.abs(Float.parseFloat(admin_meal_frame.getPriceTF().getText())),
						admin_meal_frame.getIngredientsTF().getText(),
						admin_meal_frame.getDishJCB().getSelectedItem().toString(), allergens);
				meal_dao.insertMeal(meal);
				admin_meal_frame.getModel().insertRow(admin_meal_frame.getModel().getRowCount(),
						new Object[] { meal.getName(), meal.getCategory(), meal.getPrice(), meal.getIngredients(),
								input_util.arrayListToTokenizedString(allergens, ", ") });
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Inserisci un prezzo valido ", "Errore", JOptionPane.ERROR_MESSAGE);
			} catch (DAOException e) {
				JOptionPane.showMessageDialog(null, "Inserire correttamente i campi", "Errore",
						JOptionPane.ERROR_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Seleziona una categoria valida", "Attenzione",
					JOptionPane.WARNING_MESSAGE);

	}

	public void removeShop(AdminShopFrame admin_shop_frame) {
		int row = admin_shop_frame.getTable().getSelectedRow();
		if (row != -1) {
			String shop_email = admin_shop_frame.getTable().getValueAt(row, 0).toString();
			try {
				Shop shop_to_remove = shop_dao.getShopByEmail(shop_email);
				shop_dao.deleteShop(shop_to_remove);
				admin_shop_frame.getModel().removeRow(row);
				JOptionPane.showMessageDialog(null, "Negozio cancellato dal sistema");
			} catch (DAOException e) {
				JOptionPane.showMessageDialog(null, "Errore critico, riprovare", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Selezionare il negozio che desideri cancellare", "Attenzione",
					JOptionPane.WARNING_MESSAGE);
		return;
	}

	public void removeMeal(AdminMealFrame admin_meal_frame) {
		int row = admin_meal_frame.getTable().getSelectedRow();
		if (row != -1) {
			String name_of_meal_to_delete = admin_meal_frame.getTable().getModel().getValueAt(row, 0).toString();
			try {
				Meal meal = meal_dao.getMealByName(name_of_meal_to_delete);
				meal_dao.deleteMeal(meal);
				admin_meal_frame.getModel().removeRow(row);
				JOptionPane.showMessageDialog(null, "Pasto rimosso con successo");
			} catch (DAOException e) {
				JOptionPane.showMessageDialog(null, "Errore critico, riprovare", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		} else
			JOptionPane.showMessageDialog(null, "Select il pasto da rimuovere", "Errore", JOptionPane.ERROR_MESSAGE);
		return;
	}

	public void updateShop(AdminShopFrame admin_shop_frame) {
		int row = admin_shop_frame.getTable().getSelectedRow();
		TableModelUtility table_utility = new TableModelUtility();
		if (row != -1) {
			if (!admin_shop_frame.getAddress_townCB().getSelectedItem().toString().equals("Seleziona comune")
					&& !admin_shop_frame.getAddress_provinceCB().getSelectedItem().toString()
							.equals("Seleziona provincia")) {
				String email_of_shop_to_update = admin_shop_frame.getTable().getModel().getValueAt(row, 0).toString();
				try {
					Shop shop_to_update = shop_dao.getShopByEmail(email_of_shop_to_update);
					shop_to_update.setName(admin_shop_frame.getNameTF().getText());
					shop_to_update.setAddress(new Address(admin_shop_frame.getAddress_nameTF().getText(),
							admin_shop_frame.getAddress_civic_numberTF().getText(),
							admin_shop_frame.getAddress_capTF().getText(),
							admin_shop_frame.getAddress_townCB().getSelectedItem().toString(),
							admin_shop_frame.getAddress_provinceCB().getSelectedItem().toString()));
					shop_to_update.setClosing_days(admin_shop_frame.getClosing_daysTF().getText());
					shop_to_update.setWorking_hours(admin_shop_frame.getWorking_hoursTF().getText());
					shop_to_update.setPassword(admin_shop_frame.getPasswordTF().getText());
					shop_to_update.setEmail(admin_shop_frame.getEmailTF().getText());
					shop_to_update.setHome_phone(admin_shop_frame.getHome_phoneTF().getText());
					shop_dao.updateShop(shop_to_update, email_of_shop_to_update);
					table_utility.updateShopTableColumns(admin_shop_frame, row, shop_to_update);
					JOptionPane.showMessageDialog(null, "Shop aggiornato con successo");
				} catch (DAOException e) {
					JOptionPane.showMessageDialog(null,
							"Inserire correttamente i campi.\nHint: Controlla la validita' dell' indirizzo, email, orario lavorativo e giorni di chiusura",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} else
				JOptionPane.showMessageDialog(null, "Seleziona correttamente la provincia o il comune", "Errore",
						JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Seleziona lo shop da aggiornare", "Attenzione",
					JOptionPane.WARNING_MESSAGE);
		return;
	}

	public void updateAddressTownsCB(String selected_province, AdminShopFrame frame) {
		IstatUtility istat_utils = new IstatUtility();
		List<String> towns = istat_utils.getTownsByProvince(selected_province);
		frame.getAddress_townCB().removeAllItems();
		for (String s : towns)
			frame.getAddress_townCB().addItem(s);
		return;
	}

	public void releaseAllDaoResourcesAndDisposeFrame(JFrame frame) {
		DBUtility db_utility = new DBUtility();
		db_utility.closeAllResources(shop_dao, order_dao, meal_dao, null, customer_dao, connection, frame);
		return;
	}

	public void doAdminComplexSearch(AdminOrderFrame admin_order_frame) {
		String category = admin_order_frame.getCategoryCB().getSelectedItem().toString();
		String vehicle = admin_order_frame.getVehicleCB().getSelectedItem().toString();
		String province = admin_order_frame.getAddressCB().getSelectedItem().toString();
		List<Order> order_list = new ArrayList<Order>();
		admin_order_frame.getModel().setRowCount(0);
		TableModelUtility table_util = new TableModelUtility();
		try {
			float min_price = Float.parseFloat(admin_order_frame.getPrice_minTF().getText());
			float max_price = Float.parseFloat(admin_order_frame.getPrice_maxTF().getText());
			order_list = order_dao.doAdminComplexSearch(category, min_price, max_price, vehicle, province);
			if (!order_list.isEmpty())
				table_util.initializeOrderTable(admin_order_frame.getModel(), order_list);
			else
				JOptionPane.showMessageDialog(null, "La tua ricerca non ha portato a nessun risultato", "Warning",
						JOptionPane.WARNING_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Inserisci un prezzo valido ", "Errore", JOptionPane.ERROR_MESSAGE);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Errore critico, riprovare", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void visualizeAllOrders(AdminOrderFrame admin_order_frame) {

		admin_order_frame.getModel().setRowCount(0);
		List<Order> order_list = new ArrayList<Order>();
		TableModelUtility table_util = new TableModelUtility();
		try {
			order_list = order_dao.getAllOrders();
			table_util.initializeOrderTable(admin_order_frame.getModel(), order_list);
		} catch (DAOException e) {
			JOptionPane.showMessageDialog(null, "Errore critico, riprovare", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return;
	}
}
