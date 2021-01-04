package app;

import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.SQLException;

import controllers.AdminController;
import db_connection.DBconnection;
import gui.AdminFrame;
import gui.LoginFrame;

public class Main {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				DBconnection dbconn = null;
			    Connection connection = null;
			    //Connessione al database
				try
				{
					  dbconn = DBconnection.getInstance();
			          connection = dbconn.getConnection();
				}
				 catch (SQLException e)
				 {
					  System.out.println("Errore durante la connessione con il database"+e.getMessage());
				 }
				//Inizio applicativo
				try {
					new LoginFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
