package db_connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;
public class DBconnection {
	
	private static DBconnection instance = null;
	
	private final String USERNAME="postgres";
	private final String PWD="password123";
	private final String IP="localhost";
	private final String PORT="5432";
	private final String DBNAME="FOOD-OVERFLOW_DB";
	private Connection connection;
	
	private DBconnection() {
	 try
	 {
		 Class.forName("org.postgresql.Driver");
		 this.connection = DriverManager.getConnection("jdbc:postgresql://"+IP+":"+PORT+"/"+DBNAME,USERNAME,PWD);
		 System.out.println("Connessione con il database avvenuta");
	 }
	 catch (SQLException e)
	 {
			JOptionPane.showMessageDialog(null, "Errore di connessione con il database","Errore",JOptionPane.ERROR_MESSAGE);
	 }
     catch (ClassNotFoundException e)
	 
     {
    		JOptionPane.showMessageDialog(null, "Driver non trovato","Errore",JOptionPane.ERROR_MESSAGE);
     }
	 
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public static DBconnection getInstance() throws SQLException {
        if (instance == null)
        {
            instance = new DBconnection();
        }
        else
            if (instance.getConnection().isClosed())
            {
                instance = new DBconnection();
            }

        return instance;
    }
}
