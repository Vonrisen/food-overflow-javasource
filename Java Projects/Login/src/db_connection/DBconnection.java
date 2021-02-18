package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBconnection {

	private static DBconnection instance = null;

	private final String USERNAME = "postgres";
	private final String PWD = "password123";
	private final String IP = "localhost";
	private final String PORT = "5432";
	private final String DBNAME = "FOOD-OVERFLOW_DB";
	private Connection connection;

	private DBconnection() {
		try {
			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager.getConnection("jdbc:postgresql://" + IP + ":" + PORT + "/" + DBNAME,
					USERNAME, PWD);
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Errore critico, contattare l' amministratore", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DBconnection getInstance() {
		if (instance == null) {
			instance = new DBconnection();
		} else
			try {
				if (instance.getConnection().isClosed()) {
					instance = new DBconnection();
				}
			} catch (SQLException e) {

			}

		return instance;
	}
}
