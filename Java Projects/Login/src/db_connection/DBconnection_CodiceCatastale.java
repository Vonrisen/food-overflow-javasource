package db_connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBconnection_CodiceCatastale {
	
	private static DBconnection_CodiceCatastale instance = null;
	
	private final String USERNAME="postgres";
	private final String PWD="password123";
	private final String IP="localhost";
	private final String PORT="5432";
	private final String DBNAME="Istat1";
	private Connection connection = null; 
	
	private DBconnection_CodiceCatastale() {
		
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
	
	public static DBconnection_CodiceCatastale getInstance() throws SQLException {
        if (instance == null)
        {
            instance = new DBconnection_CodiceCatastale();
        }
        else
            if (instance.getConnection().isClosed())
            {
                instance = new DBconnection_CodiceCatastale();
            }

        return instance;
    }
}
