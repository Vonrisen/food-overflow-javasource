package db_connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBconnection_ProvincesAndCities {
	
	private static DBconnection_ProvincesAndCities instance = null;
	
	private final String USERNAME="postgres";
	private final String PWD="password123";
	private final String IP="localhost";
	private final String PORT="5432";
	private final String DBNAME="Istat";
	private Connection connection;
	
	private DBconnection_ProvincesAndCities() {
		
	 try {
		 
         Class.forName("org.postgresql.Driver");
         
     }
     catch (ClassNotFoundException e)
	 
     {
         System.out.println("Class not found: "+e);
     }
	 
	 try
	 {
		 connection = DriverManager.getConnection("jdbc:postgresql://"+IP+":"+PORT+"/"+DBNAME,USERNAME,PWD);
	 }
	 catch (SQLException e)
	 {
		 System.out.println("Connection failed "+e);
	 }
	 
	}
	
	public Connection getConnection()
	{
		return connection;
	}
	
	public static DBconnection_ProvincesAndCities getInstance() throws SQLException {
        if (instance == null)
        {
            instance = new DBconnection_ProvincesAndCities();
        }
        else
            if (instance.getConnection().isClosed())
            {
                instance = new DBconnection_ProvincesAndCities();
            }

        return instance;
    }
}
