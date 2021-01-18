package controllers;

import java.sql.SQLException;
import java.util.ArrayList;


import daos_implementation.CustomerDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.CustomerDAO;
import daos_interfaces.ShopDAO;
import entities.Customer;
import entities.Shop;
import gui.AdminCustomerFrame;
import gui.AdminCustomerPanelFrame;
import gui.AdminFrame;
import gui.AdminMealFrame;
import gui.AdminRiderFrame;
import gui.AdminShopFrame;
import utilities.TableModelUtility;

public class AdminController {
	
	public void openAdminFrame()
	{
		AdminFrame admin_frame = new AdminFrame();
		admin_frame.setVisible(true);
	}
	
	public void openAdminShopFrame()
	{
		AdminShopFrame admin_shop_frame = new AdminShopFrame();
		initializeAdminShopFrameTable(admin_shop_frame);
		admin_shop_frame.setVisible(true);
	}
	
	public void openAdminRiderFrame()
	{
		AdminRiderFrame admin_rider_frame = new AdminRiderFrame();
		admin_rider_frame.setVisible(true);
	}
	
	public void openAdminMealFrame()
	{
		AdminMealFrame admin_meal_frame = new AdminMealFrame();
		admin_meal_frame.setVisible(true);
	}
	
	public void openAdminCustomerPanelFrame()
	{
		AdminCustomerPanelFrame admin_customer_panel_frame = new AdminCustomerPanelFrame();
		admin_customer_panel_frame.setVisible(true);
	}
	
	public void openAdminCustomerFrame()
	{
		AdminCustomerFrame admin_customer_frame = new AdminCustomerFrame();
		initializeAdminCustomerFrameTable(admin_customer_frame);
		admin_customer_frame.setVisible(true);
	}
	
	public void initializeAdminShopFrameTable(AdminShopFrame admin_shop_frame)
	{
		
		ShopDAO shop_dao = new ShopDAOPostgresImplementation();
		ArrayList<Shop>shop_list = new ArrayList<Shop>();
		try {
			shop_list = shop_dao.getAllShops();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TableModelUtility table = new TableModelUtility();
		table.initializeShopTable(admin_shop_frame, shop_list);
		return;
	}
	
	public void initializeAdminCustomerFrameTable(AdminCustomerFrame admin_customer_frame)
	{
		
		CustomerDAO customer_dao = new CustomerDAOPostgresImplementation();
		ArrayList<Customer>customer_list = new ArrayList<Customer>();
		try {
			customer_list = customer_dao.getAllCustomers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TableModelUtility table = new TableModelUtility();
		table.initializeCustomerTable(admin_customer_frame, customer_list);
		return;
	}
	
	
}
