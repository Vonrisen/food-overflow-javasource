package controllers;

import javax.swing.JFrame;

import gui.CustomerFrame;

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
		return;
	}

}
