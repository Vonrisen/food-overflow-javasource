package utilities;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entities.Address;
import entities.Cart;
import entities.Customer;
import entities.Meal;
import entities.Order;
import entities.OrderComposition;
import entities.Rider;
import entities.Shop;
import gui.AdminShopFrame;
import gui.ShopRiderFrame;

public class TableModelUtility {

	public void initializeShopTable(DefaultTableModel model, List<Shop> shop_list) {

		Object[] row = new Object[7];
		for (Shop shop : shop_list) {
			row[0] = shop.getEmail();
			row[1] = shop.getPassword();
			row[2] = shop.getName();
			row[3] = shop.getAddress();
			row[4] = shop.getWorking_hours();
			row[5] = shop.getClosing_days();
			row[6] = shop.getHome_phone();
			model.addRow(row);
		}
		return;
	}

	public void initializeCustomerShopTable(DefaultTableModel model, List<Shop> shop_list) {

		Object[] row = new Object[6];
		for (Shop shop : shop_list) {
			row[0] = shop.getName();
			row[1] = shop.getAddress();
			row[2] = shop.getWorking_hours();
			row[3] = shop.getClosing_days();
			row[4] = shop.getHome_phone();
			row[5] = shop.getEmail();
			model.addRow(row);
		}
		return;
	}

	public void initializeCustomerTable(DefaultTableModel model, List<Customer> customer_list) {
		Object[] row = new Object[10];
		InputUtility date_util = new InputUtility();
		for (Customer customer : customer_list) {
			
			row[0] = customer.getCf();
			row[1] = customer.getName();
			row[2] = customer.getSurname();
			row[3] = date_util.formatDate(customer.getBirth_date());
			row[4] = customer.getBirth_place();
			row[5] = customer.getAddress().toString();
			row[6] = customer.getGender();
			row[7] = customer.getCellphone();
			row[8] = customer.getEmail();
			row[9] = customer.getPassword();
			model.addRow(row);
			
		}
		return;
	}

	public void initializeMealTable(DefaultTableModel model, List<Meal> meal_list) {
		Object[] row = new Object[5];
		InputUtility input_util = new InputUtility();
		for (Meal meal : meal_list) {
			row[0] = meal.getName();
			row[1] = meal.getCategory();
			row[2] = meal.getPrice();
			row[3] = meal.getIngredients();
			row[4] = input_util.arrayListToTokenizedString(meal.getAllergen_list(), ", ");
			model.addRow(row);
		}
	}

	public void initializeRiderOrderTable(DefaultTableModel model, List<Rider> rider_list) {
		Object[] row = new Object[5];
		for (Rider rider : rider_list) {
			row[0] = rider.getCf();
			row[1] = rider.getCellphone();
			row[2] = rider.getVehicle();
			row[3] = rider.getWorking_hours();
			row[4] = rider.getDeliveries_number();
			model.addRow(row);
		}
	}

	public void initializeRiderTable(DefaultTableModel model, List<Rider> rider_list) {
		InputUtility date_util = new InputUtility();
		Object[] row = new Object[11];
		for (Rider rider : rider_list) {
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
			model.addRow(row);
		}
	}

	public void updateShopTableColumns(AdminShopFrame admin_shop_frame, int selected_row, Shop shop) {
		Object[] row = new Object[7];
		row[0] = shop.getEmail();
		row[1] = shop.getPassword();
		row[2] = shop.getName();
		row[3] = shop.getAddress().toString();
		row[4] = shop.getWorking_hours();
		row[5] = shop.getClosing_days();
		row[6] = shop.getHome_phone();
		for (int i = 0; i < 7; i++)
			admin_shop_frame.getModel().setValueAt(row[i], selected_row, i);
		return;
	}

	public void updateRiderTableColumns(ShopRiderFrame shop_rider_frame, int selected_row, Rider rider) {
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
		for (int i = 0; i < 10; i++)
			shop_rider_frame.getModel().setValueAt(row[i], selected_row, i);
		return;
	}

	public void initializeCompletedOrderTable(DefaultTableModel model, List<Order> order_list) {
		InputUtility date_util = new InputUtility();
		Object[] row = new Object[9];
		for (Order order : order_list) {
			row[0] = order.getId();
			row[1] = date_util.formatDate(order.getDate());
			row[2] = order.getDeliveried_time();
			row[3] = order.getAddress().toString();
			row[4] = order.getStatus();
			row[5] = order.getPayment();
			row[6] = order.getNote();
			row[7] = order.getCustomer().getCf();
			row[8] = order.getRider().getCf();
			model.addRow(row);
		}
	}

	public void initializePendingOrderTable(DefaultTableModel model, List<Order> order_list) {
		InputUtility date_util = new InputUtility();
		Object[] row = new Object[6];
		for (Order order : order_list) {
			row[0] = order.getId();
			row[1] = date_util.formatDate(order.getDate());
			row[2] = order.getAddress().toString();
			row[3] = order.getPayment();
			row[4] = order.getNote();
			row[5] = order.getCustomer().getCf();
			model.addRow(row);
		}
	}

	public void initializeDeliveringOrderTable(DefaultTableModel model, List<Order> order_list) {
		InputUtility date_util = new InputUtility();
		Object[] row = new Object[7];
		for (Order order : order_list) {
			row[0] = order.getId();
			row[1] = date_util.formatDate(order.getDate());
			row[2] = order.getAddress().toString();
			row[3] = order.getPayment();
			row[4] = order.getNote();
			row[5] = order.getCustomer().getCf();
			row[6] = order.getRider().getCf();
			model.addRow(row);
		}
	}

	public void initializeOrderTable(DefaultTableModel model, List<Order> order_list) {
		InputUtility date_util = new InputUtility();
		Object[] row = new Object[9];
		for (Order order : order_list) {
			row[0] = order.getId();
			row[1] = date_util.formatDate(order.getDate());
			if (order.getDeliveried_time() == null)
				row[2] = "In consegna..";
			row[2] = order.getDeliveried_time();
			row[3] = order.getAddress().toString();
			row[4] = order.getStatus();
			row[5] = order.getPayment();
			row[6] = order.getNote();
			row[7] = order.getCustomer().getEmail();
			row[8] = order.getRider().getCf();
			model.addRow(row);
		}
		return;
	}

	public void initializeCustomerCartTable(DefaultTableModel model, Cart cart) {

		InputUtility input_util = new InputUtility();
		Object[] row = new Object[6];
		for (OrderComposition order_comp : cart.getOrder_composition_list()) {
			row[0] = order_comp.getMeal().getName();
			row[1] = order_comp.getMeal().getCategory();
			row[2] = order_comp.getMeal().getPrice();
			row[3] = order_comp.getMeal().getIngredients();
			row[4] = input_util.arrayListToTokenizedString(order_comp.getMeal().getAllergen_list(), ", ");
			row[5] = order_comp.getQuantity();
			model.addRow(row);
		}

	}

	public void fillFieldsFromJTable(AdminShopFrame admin_shop_frame) {

		JTable table = admin_shop_frame.getTable();
		int row = table.getSelectedRow();
		if (!table.getSelectionModel().isSelectionEmpty()) {
			InputUtility input_util = new InputUtility();
			Address address = input_util.tokenizedStringToAddress(table.getModel().getValueAt(row, 3).toString(), "(, )");
			String closing_days;	
			try {
				closing_days = table.getModel().getValueAt(row, 5).toString();
			} catch (Exception e) {
				closing_days = "";
			}
			admin_shop_frame.getEmailTF().setText(table.getModel().getValueAt(row, 0).toString());
			admin_shop_frame.getNameTF().setText(table.getModel().getValueAt(row, 2).toString());
			admin_shop_frame.getAddress_nameTF().setText(address.getAddress());
			admin_shop_frame.getAddress_civic_numberTF().setText(address.getCivic_number());
			admin_shop_frame.getAddress_capTF().setText(address.getCap());
			admin_shop_frame.getAddress_provinceCB().setSelectedItem(address.getProvince().toUpperCase());
			admin_shop_frame.getAddress_townCB().setSelectedItem(address.getCity().toUpperCase());
			admin_shop_frame.getWorking_hoursTF()
					.setText(table.getModel().getValueAt(row, 4).toString());
			admin_shop_frame.getClosing_daysTF().setText(closing_days);
			admin_shop_frame.getPasswordTF().setText(table.getModel().getValueAt(row, 1).toString());
			admin_shop_frame.getHome_phoneTF()
					.setText(table.getModel().getValueAt(row, 6).toString());
		}

	}

	public void fillFieldsFromJTable(ShopRiderFrame shop_rider_frame) {

		JTable table = shop_rider_frame.getTable();
		int row = table.getSelectedRow();
		if (!table.getSelectionModel().isSelectionEmpty()) {
			String birth_town = table.getModel().getValueAt(row, 4).toString().toUpperCase();
			IstatUtility istat_util = new IstatUtility();
			if(!istat_util.isBirthPlaceANation(birth_town))
			{
				shop_rider_frame.getBirth_nationCB().setSelectedItem("ITALIA");
			shop_rider_frame.getBirth_provinceCB().setSelectedItem(istat_util.getProvinceOfATown(birth_town));
			shop_rider_frame.getBirth_townCB().setSelectedItem(birth_town);
			InputUtility input_util = new InputUtility();
			Address address = input_util.tokenizedStringToAddress(table.getModel().getValueAt(row, 5).toString(), "(, )");
			shop_rider_frame.getNameTF().setText(table.getModel().getValueAt(row, 1).toString());
			shop_rider_frame.getSurnameTF().setText(table.getModel().getValueAt(row, 2).toString());
			shop_rider_frame.getBirth_dateTF()
					.setText(table.getModel().getValueAt(row, 3).toString());
			shop_rider_frame.getAddress_nameTF().setText(address.getAddress());
			shop_rider_frame.getAddress_civic_numberTF().setText(address.getCivic_number());
			shop_rider_frame.getAddress_capTF().setText(address.getCap());
			shop_rider_frame.getAddress_provinceCB().setSelectedItem(address.getProvince().toUpperCase());
			shop_rider_frame.getAddress_townCB().setSelectedItem(address.getCity().toUpperCase());
			shop_rider_frame.getCellphoneTF()
					.setText(table.getModel().getValueAt(row, 7).toString());
			shop_rider_frame.getWorking_hoursTF()
					.setText(table.getModel().getValueAt(row, 9).toString());
			}else
				shop_rider_frame.getBirth_nationCB().setSelectedItem(birth_town);
		}
	}
}
