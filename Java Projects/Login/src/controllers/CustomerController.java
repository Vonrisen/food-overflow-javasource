package controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import daos_implementation.AddressDAOPostgresImplementation;
import daos_interfaces.AddressDAO;
import exceptions.DaoException;
import gui.CustomerFrame;
import gui.CustomerShopListFrame;

public class CustomerController {
	
	
	private String customer_email;
	public CustomerController(String email)
	{
		this.customer_email = email;
	}
	
	public void openCustomerFrame(JFrame frame)
	{
		frame.dispose();
		CustomerFrame customer_frame = new CustomerFrame(this);
		customer_frame.setVisible(true);
	}

	public void checkProvinceAndOpenShopListFrame(CustomerFrame customer_frame) {
	
		AddressDAO address_dao = new AddressDAOPostgresImplementation();
		try
		{
			if(address_dao.isProvinceValid(customer_frame.getProvinceTF().getText()))
			{
				customer_frame.dispose();
				openCustomerShopListFrame();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Non sono riuscito a trovare la tua provincia, riprovare", "Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(DaoException d)
		{
			JOptionPane.showMessageDialog(null,"Errore critico, contattare l' amministratore", "Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void openCustomerShopListFrame() {
		
		CustomerShopListFrame customer_shop_list_frame = new CustomerShopListFrame(this);
		customer_shop_list_frame.setVisible(true);
		return;
	}
}
