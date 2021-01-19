package app;
import java.util.ArrayList;

import controllers.LoginController;
import utilities.InputUtility;

public class Main {
	
	public static void main(String[] args) {
		
		LoginController login_controller = new LoginController();
		login_controller.openLoginFrame();
		
	}
	
}