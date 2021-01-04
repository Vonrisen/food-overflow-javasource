package controllers;



import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import app.CF;
import daos_implementation.RiderDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import db_connection.DBconnection;
import db_connection.DBconnection_CodiceCatastale;
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
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Errore durante la trasposizione del result set sulla tabella"+e1.getMessage());
		}
		return;
	}
	public void openShopFrame() {
		ShopFrame shop_frame = new ShopFrame();
		try {
			shop_frame.getTable().setModel(DbUtils.resultSetToTableModel(shop.getAllShops()));
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Errore durante la trasposizione del result set sulla tabella"+e1.getMessage());
		}
		return;
	}
	//SHOP CONTROL
	public void insert_refreshShopTable(ShopFrame shop_frame)
	{
		try {
			shop.insertShop(shop_frame.getNameTF().getText(), shop_frame.getAddressTF().getText(), shop_frame.getWorking_hoursTF().getText(), shop_frame.getClosing_daysTF().getText(),shop_frame.getPasswordTF().getText());
			shop_frame.getTable().setModel(DbUtils.resultSetToTableModel(shop.getAllShops()));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Errore durante l'inserimento di una riga: "+e.getMessage());
		}
		return;
	}
	public void delete_refreshShopTable(ShopFrame shop_frame)
	{
		String value;
		try {
			value = shop_frame.getTable().getModel().getValueAt(shop_frame.getTable().getSelectedRow(), 0).toString();
			shop.deleteShop(value);
			shop_frame.getTable().setModel(DbUtils.resultSetToTableModel(shop.getAllShops()));
		} catch (SQLException e1) {
			System.out.println("Errore durante la cancellazione: "+e1.getMessage());
		}
		catch(ArrayIndexOutOfBoundsException e2)
		{
			JOptionPane.showMessageDialog(null,"Selezionare la riga da cancellare!");
		}
		return;
	}
	public void update_refreshShopTable(ShopFrame shop_frame)
	{
		
		String shop_id;
		String name=shop_frame.getNameTF().getText();
		String address=shop_frame.getAddressTF().getText();
		String working_hours=shop_frame.getWorking_hoursTF().getText();
		String closing_days=shop_frame.getClosing_daysTF().getText();
		String password=shop_frame.getPasswordTF().getText();
		try {
			shop_id=shop_frame.getTable().getModel().getValueAt(shop_frame.getTable().getSelectedRow(), 0).toString();
			shop.updateShop(shop_id, name, address, working_hours, closing_days, password);
			shop_frame.getTable().setModel(DbUtils.resultSetToTableModel(shop.getAllShops()));
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Errore durante l' update della riga: "+e1.getMessage());
		}
		catch(ArrayIndexOutOfBoundsException e2)
		{
			JOptionPane.showMessageDialog(null,"Selezionare la riga da aggiornare!");
		}
		return;
	}
	//RIDER CONTROL
	public void insert_refreshRiderTable(RiderFrame rider_frame)
	{
		Connection connection=null;
		DBconnection_CodiceCatastale instance;
		try {
			instance = DBconnection_CodiceCatastale.getInstance();
			connection = instance.getConnection();
		} catch (SQLException e1) {
			System.out.println("Errore di connessione col database Istat1"+e1.getMessage());
		}
		String name=rider_frame.getNameTF().getText();
		String surname=rider_frame.getSurnameTF().getText();
		String birth_date=rider_frame.getBirth_dateTF().getText();
		String birth_place=rider_frame.getBirth_placeTF().getText();
		String address=rider_frame.getAddressTF().getText();
		String gender=rider_frame.getGender().getSelection().getActionCommand();
		String cellphone=rider_frame.getCellphoneTF().getText();
		String shop_id=rider_frame.getShop_idTF().getText();
		String working_time=rider_frame.getWorking_timeTF().getText();
		String vehicle = rider_frame.getVehicleCB().getActionCommand();
		CF codice_fiscale=new CF();
		String cf=null;
		try {
			cf = codice_fiscale.getCF(name, surname, new SimpleDateFormat("dd/MM/yyyy").parse(birth_date), birth_place, gender, connection);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		System.out.println(cf);
		System.out.println(vehicle);
		try {
			rider.insertRider(cf, name, surname, address,birth_date ,birth_place , gender, cellphone, "Bicicletta", working_time, shop_id);
			rider_frame.getTable().setModel(DbUtils.resultSetToTableModel(rider.getAllRiders()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
	
}
