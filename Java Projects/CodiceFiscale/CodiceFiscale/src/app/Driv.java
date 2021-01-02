package app;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import db_connection.DBconnection;


public class Driv {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		

	    DBconnection dbconn = null;
        Connection connection = null;
        CF cf = new CF();
        try
        {
            dbconn = DBconnection.getInstance();
            connection = dbconn.getConnection();
            String codice_fiscale=cf.getCF("C", "AA", new Date("1996/01/28"), "NOLA", 'M', connection);
            System.out.println(codice_fiscale);
       
        } catch (SQLException e)
        {
        	System.out.println("SQL error: "+e.getMessage());
        }
	}

}
