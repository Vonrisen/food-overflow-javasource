package app;
import java.util.ArrayList;
import java.util.List;

import controllers.LoginController;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.ShopDAO;
import db_connection.DBconnection;
import entities.Shop;
import exceptions.DaoException;
import gui.AdminOrderFrame;

public class Main {
	
	public static void main(String[] args) {
		
		LoginController login_controller = new LoginController();
		login_controller.openLoginFrame();	
		
		
}
}
