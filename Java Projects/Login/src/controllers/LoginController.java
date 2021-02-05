package controllers;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daos_implementation.CustomerDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.CustomerDAO;
import daos_interfaces.ShopDAO;
import exceptions.DaoException;
import gui.LoginFrame;
import utilities.DButility;
public class LoginController{
	


	public LoginController()
	{
		
	}
	//Metodo per aprire login frame dopo essersi disconnessi da altri frame
	public void openLoginFrame(JFrame frame)
	{
		frame.dispose();
		DButility db_util = new DButility();
		db_util.closeCurrentConnection();
		LoginFrame login_frame = new LoginFrame();
		login_frame.setVisible(true);
		return;
	}
	//Metodo per aprire login frame per la prima volta
	public void openLoginFrame()
	{
		LoginFrame login_frame = new LoginFrame();
		login_frame.setVisible(true);
		return;
	}
	
	@SuppressWarnings("deprecation")
	public void accessAuthentication(LoginFrame login_frame)
	{
		//SE ACCEDE L' ADMIN
		AdminController admin_controller = new AdminController();
		boolean access_succeded = false;
		DButility db_utility = new DButility();
		if(login_frame.getLogoLabel().getIcon() == login_frame.getAdminLogoImage())
		{
			
		if (!login_frame.getUsernameTF().getText().equals("admin") || !login_frame.getPasswordTF().getText().equals("admin"))
		{
			
			JOptionPane.showMessageDialog(null, "Incorrect credentials, please try again","Errore",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			//Chiudo il login frame e passo all' admin frame
			admin_controller.openAdminFrame(login_frame);
		}
		}
		//SE ACCEDE LO SHOP
		else if(login_frame.getLogoLabel().getIcon() == login_frame.getShopLogoImage())
		{
			
			ShopDAO shop_dao = new ShopDAOPostgresImplementation();
			try {
				access_succeded = shop_dao.isShopLoginValidated(login_frame.getUsernameTF().getText(), login_frame.getPasswordTF().getText());
				if(access_succeded)
				{
					login_frame.setVisible(false);
					String shop_email= login_frame.getUsernameTF().getText();
					ShopController shop_controller = new ShopController(shop_email);
					shop_controller.openShopFrame(login_frame);
				}
				else
				{
					db_utility.closeCurrentConnection();
					JOptionPane.showMessageDialog(null, "Incorrect credentials, please try again","Errore",JOptionPane.ERROR_MESSAGE);
				}
			} catch (DaoException e) {
				
				JOptionPane.showMessageDialog(null, "Critical error, please try again or contact the administrator");
			}
			
	} //SE ACCEDE IL CUSTOMER
		else
		{
			CustomerDAO customer_dao = new CustomerDAOPostgresImplementation();
			try {
				access_succeded = customer_dao.isCustomerLoginValidated(login_frame.getUsernameTF().getText(), login_frame.getPasswordTF().getText());
				if(access_succeded)
				{
					login_frame.setVisible(false);
					String customer_email= login_frame.getUsernameTF().getText();
					CustomerController customer_controller = new CustomerController(customer_email);
					customer_controller.openCustomerFrame(login_frame);
				}
				else
				{
					db_utility.closeCurrentConnection();
					JOptionPane.showMessageDialog(null, "Incorrect credentials, please try again","Errore",JOptionPane.ERROR_MESSAGE);
				}
			} catch (DaoException e) {
				
				JOptionPane.showMessageDialog(null, "Critical error, please try again or contact the administrator");	
			}	
			}
		return;
	
}
	}
