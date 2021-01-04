package controllers;


import java.sql.SQLException;
import javax.swing.JOptionPane;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.ShopDAO;
import gui.AdminFrame;
import gui.LoginFrame;
import gui.RiderFrame;
import gui.ShopFrame;
import net.proteanit.sql.DbUtils;


public class AdminController{
	
	ShopDAO shop = new ShopDAOPostgresImplementation();
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
		new RiderFrame();
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
	//SHOP CONTROLLING
	public void insert_refreshShopTable(ShopFrame shop_frame)
	{
		try {
			shop.insertShop(shop_frame.getNameTF().getText(), shop_frame.getAddressTF().getText(), shop_frame.getWorking_hoursTF().getText(), shop_frame.getClosing_daysTF().getText());
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
		try {
			shop_id=shop_frame.getTable().getModel().getValueAt(shop_frame.getTable().getSelectedRow(), 0).toString();
			shop.updateShop(shop_id, name, address, working_hours, closing_days);
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
}
