package app;
import java.awt.EventQueue;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos_implementation.CustomerOrderDAOPostgresImplementation;
import daos_implementation.OrderCompositionDAOPostgresImplementation;
import daos_implementation.RiderDAOPostgresImplementation;
import daos_implementation.ShopDAOPostgresImplementation;
import daos_interfaces.CustomerOrderDAO;
import daos_interfaces.OrderCompositionDAO;
import daos_interfaces.RiderDAO;
import daos_interfaces.ShopDAO;
import db_connection.DBconnection_CodiceCatastale;
import entities.CustomerOrder;
import entities.OrderComposition;
import entities.Rider;
import entities.Shop;


public class Main {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerOrderDAO order = new CustomerOrderDAOPostgresImplementation();
					CustomerOrder o = order.getCustomerOrderByOrderId("12345678");
					System.out.println(o.getAddress());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}
}
