package db_connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBconnection {
	
	private static DBconnection instance = null;
	
	private final String USERNAME="postgres";
	private final String PWD="password123";
	private final String IP="localhost";
	private final String PORT="5432";
	private final String DBNAME="FOODOVERFLOW_DB";
	private Connection connection = null; 
	
	private DBconnection() {
	 try
	 {
		 Class.forName("org.postgresql.Driver");
		 connection = DriverManager.getConnection("jdbc:postgresql://"+IP+":"+PORT+"/"+DBNAME,USERNAME,PWD);
		 System.out.println("Connessione con il database avvenuta");
	 }
	 catch (SQLException e)
	 {
		 System.out.println("Connessione con il database fallita "+e);
	 }
     catch (ClassNotFoundException e)
	 
     {
         System.out.println("Non e' stato possibile trovare il driver desiderato");
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
