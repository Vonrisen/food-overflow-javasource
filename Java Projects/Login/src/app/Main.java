package app;
import java.sql.Connection;

import controllers.LoginController;
import daos_implementation.CustomerDAOPostgresImplementation;
import daos_implementation.MealDAOPostgresImplementation;
import daos_implementation.OrderDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.CustomerDAO;
import daos_interfaces.MealDAO;
import daos_interfaces.OrderDAO;
import daos_interfaces.ShopDAO;
import db_connection.DBconnection;

public class Main {
	
	public static void main(String[] args) {

		LoginController login_controller = new LoginController();
		login_controller.openLoginFrame();	
		
}
}
