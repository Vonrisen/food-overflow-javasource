package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import daos_implementation.MealDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.MealDAO;
import daos_interfaces.ShopDAO;
import entities.Shop;
import gui.AdminFrame;
import gui.LoginFrame;
import net.proteanit.sql.DbUtils;


public class AdminController{
	
	private Connection connection;
	ShopDAO shop;
	private static AdminFrame admin_frame;
	public AdminController(Connection connection,AdminFrame ad) {
		super();
		this.connection = connection;
		this.admin_frame=ad;
	}
	public void setAdminFrameVisible()
	{
		admin_frame.initialize(connection);
		admin_frame.frame.setVisible(true);
	}
	public void displayShops()
	{
		shop = new ShopDAOPostgresImplementation(connection);
		ResultSet all_shops_result_set;
		try {
			all_shops_result_set = shop.getAllShops();
			admin_frame.getTable().setModel(DbUtils.resultSetToTableModel(all_shops_result_set));
		} catch (SQLException e) {
			System.out.println("Errore nella trasposizione delle righe sulla tabella"+e.getMessage());
		}
		return;
	}
	public void insertShops()
	{
		
		try {
			shop = new ShopDAOPostgresImplementation(connection);
			shop.insertShop(admin_frame.getNameTF().getText(),admin_frame.getAddressTF().getText() , admin_frame.getWorking_timeTF().getText(), admin_frame.getClosing_daysTF().getText());
			ResultSet rs = shop.getAllShops();
			admin_frame.getTable().setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			System.out.println("Errore durante l' inserimento: "+e.getMessage());
		}
	}
}