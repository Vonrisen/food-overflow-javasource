package app;

import controllers.LoginController;


public class Main {
	
	public static void main(String[] args) {
		
		LoginController login_controller = new LoginController();
		login_controller.openLoginFrame();
		
	}
	
}