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
import net.proteanit.sql.DbUtils;


public class LoginController{
	
	private Connection connection;
	private AdminFrame ad;
	public LoginController(Connection connection) {
		super();
		this.connection = connection;
	}
	public void displayShops(Connection connection) throws SQLException
	{
//		ResultSet all_shops_result_set = shop.getAllShops();
//		admin_frame.getTable().setModel(DbUtils.resultSetToTableModel(all_shops_result_set));
		return;
	}
	public void closeLoginOpenAdmin()
	{
		ad = new AdminFrame();
		ad.frame.setVisible(true);
	}
	
	
}
