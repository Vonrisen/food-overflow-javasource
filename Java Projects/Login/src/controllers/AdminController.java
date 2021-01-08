package controllers;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import app.CF;
import daos_implementation.RiderDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import db_connection.DBconnection;
import db_connection.DBconnection_CodiceCatastale;
import entities.Shop;
import gui.AdminFrame;
import gui.LoginFrame;
import gui.RiderFrame;
import gui.ShopFrame;
import net.proteanit.sql.DbUtils;


public class AdminController{
	
	ShopDAO shop = new ShopDAOPostgresImplementation();
	RiderDAO rider = new RiderDAOPostgresImplementation();
	public AdminController()
	
	{
	}
	
	//FRAME OPENERS
	public void openAdminFrame()
	{
		new AdminFrame();
		return;
	}
	public void openRiderFrame()
	{
		RiderFrame rider_frame = new RiderFrame();
		try {
			rider_frame.getTable().setModel(DbUtils.resultSetToTableModel(rider.getAllRiders()));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Errore durante l' interrogazione"+e.getMessage());
		}
		return;
	}
	public void openShopFrame() {
		ShopFrame shop_frame = new ShopFrame();
		ArrayList<Shop> shop_list = null;
		try {
			shop_list = shop.getAllShops();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Errore durante l' interrogazione"+e.getMessage());
		}
	    String[]columns = {"shop_id","name","address","working hours","closing days","password"};
	    Object[] row = new Object[6];
	    DefaultTableModel model = new DefaultTableModel(columns,0);
	    shop_frame.getTable().setModel(model);
	    for(int i=0;i<shop_list.size();i++)
	    {
	    	row[0] = shop_list.get(i).getShop_id();
	    	row[1] = shop_list.get(i).getShop_name();
	    	row[2] = shop_list.get(i).getAddress();
	    	row[3] = shop_list.get(i).getWorking_hours();
	    	row[4] = shop_list.get(i).getClosing_days();
	    	row[5] = shop_list.get(i).getPassword();
	    	model.addRow(row);
	    }
		return;
	}

	
}
